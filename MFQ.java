import java.io.*;
import java.util.Scanner;
import java.text.DecimalFormat;
/**
 * <h2>MFQ.java - Create a MFQ class that will output a header, arrivalmessage and departmessage and run the simulation using queues.</h2> 
 * <p>Algorithm:</p>
 * <ol>
 *   <li>Create a PrintWriter to write to file and passs it through in a single arg constructor.</li>
 *   <li>insatiate an inputQueue and instantiate 4 more queues to represent each queue level.</li>
 *   <li>instatiate the CPU class.</li> 
 *   <li>Create a method called getJobs that will read in the ""mfq5.txt" file also use a try and catch exception in case the file was not read properly.</li>
 *   <li>create an out header method that will display a header for the arriving and departing jobs.</li>
 *   <li>create a printarrival method that will pass the job and the system clock as it parameters.</li>
 *   <li>create a depart message method that will pass the job and the system clock and reponse time as it parameters.</li>
 *   <li>create a runSimulation method that will take in the jobs, send them to the cpu, preempt them.</li>
 *   <li>create an outStats method that will out put the stats of the system after it has finished.</li>
 *   <li>.</li>
 *   <li>.</li>
 *   <li>.</li>
 *   <li>.</li>
 *   <li>.</li>
 *   <li>.</li>
 * </ol>
 * @author Brandon Figueroa
 * Palomar ID 010881556
 */
public class MFQ
{
    private PrintWriter pw;
    private ObjectQueue inputQueue;
    private ObjectQueue[] queues;
    private CPU cpu;
    private int systemClock;
    private int numOfJobs;
    private int totalJobTime;
    private int responseTime;
    private int departRT;
    private int waitTime;
    /**
     * Single arg constructor passing PrintWriter object as it parameter
     */
    public MFQ(PrintWriter newPw){
        pw = newPw;
        inputQueue = new ObjectQueue();
        queues = new ObjectQueue[4];
        queues[0] = new ObjectQueue();
        queues[1] = new ObjectQueue();
        queues[2] = new ObjectQueue();
        queues[3] = new ObjectQueue();
        cpu = new CPU(2);
        totalJobTime = 0;
        numOfJobs = 0;
        responseTime = 0;
        departRT = 0;
        waitTime = 0;
        systemClock = 0;
    }
    /**
     * Method getJobs() reads the text file containing the input
     */
    public void getJobs(){
        try{
            Scanner file = new Scanner(new File("mfq5.txt"));
            String read;
            while(file.hasNextLine()){
                read = file.nextLine();
                String[] tokens = read.trim().split("[ ]+");
                Job job = new Job(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                inputQueue.insert(job);
            }
        }catch(FileNotFoundException ex){
            System.out.println("Input file not found! Exiting...");
            pw.print("Input file not found! Exiting...");
            System.exit(1);
        }
    } 

    /**
     * This method prints out the header of the job's Event, System Time, PID, CPU Time Needed, Total Time In System and the Lowest Level Queue using printf
     */
    public void outputHeader(){
        System.out.format("%-11s %-16s %-8s %-25s %-25s%s %20s\n", "Event", "System Time", "PID", "CPU Time Needed", "Total Time In System", "Lowest Level Queue", "Response Time");
        pw.format("%-11s %-16s %-8s %-25s %-25s%s %20s\n", "Event", "System Time", "PID", "CPU Time Needed", "Total Time In System", "Lowest Level Queue", "Response Time");
        pw.println();
    }
    /**
     * Method printArrivalMessage() prints the arrival message for each job as it enters the queue
     */
    private void printArrivalMessage(Job job, int systemClock){
        System.out.format("%-14s %-13s %-11s %-28s\n","Arrival", systemClock, job.getPid(), job.getCpuTimeRequired());
        pw.format("%-14s %-13s %-11s %-28s\n","Arrival", systemClock, job.getPid(), job.getCpuTimeRequired());
    }
    /**
     * Method printDepartMessage() prints a message when the job has finished
     */
    private void printDepartMessage(Job job, int systemClock, int departRT){
        System.out.format("%-14s %-13s %-11s %-28s %-28s%s %25s\n","Departure", systemClock, job.getPid(), "", (systemClock - job.getArrivalTime()), (job.getCurrentQueue() + 1), responseTime);
        pw.format("%-14s %-13s %-11s %-28s %-28s %-28s%s 25s\n","Departure", systemClock, job.getPid(), "", (systemClock - job.getArrivalTime()), (job.getCurrentQueue() + 1), responseTime);
        totalJobTime += systemClock - job.getArrivalTime();
    }
    /**
     * Method runSimulation() submits the jopbs into the cpu and preempts the jobs to lower level queues
     */
    public void runSimulation(){
        while(true){
            systemClock++;//start the System clock

            //submit new job to input queue
            while(!inputQueue.isEmpty() && ((Job)inputQueue.query()).getArrivalTime() == systemClock){
                Job nextJob = (Job) inputQueue.query();
                queues[0].insert(nextJob);
                inputQueue.remove();
                printArrivalMessage(nextJob, systemClock);
                //responseTime += systemClock - nextJob.getArrivalTime();
                numOfJobs++;
            }
            //check if there is a job on the cpu
            if(cpu.isBusy()){
                //decrement quantum clock
                //decrement job clock
                cpu.getJob().decrementCpuTimeRemaining();
                cpu.decrementClock();
                if(cpu.getJob().getCpuTimeRemaining() == 0){
                    //take job off of the cpu and output depart message
                    //reset the busyFlag
                    printDepartMessage(cpu.getJob(), systemClock, departRT);
                    cpu.setJob(null);
                    cpu.setBusyFlag(false);
                }else if(cpu.getQuantumClock() == 0 || !queues[0].isEmpty()){
                    //preempt
                    //remove the job from the cpu and send it to the next queue
                    //reset the busy flag
                    int currentQueue = cpu.getJob().getCurrentQueue();
                    if(currentQueue != 3){
                        queues[currentQueue + 1].insert(cpu.getJob());
                        cpu.getJob().setCurrentQueue(currentQueue + 1);
                    }else{
                        queues[currentQueue].insert(cpu.getJob());
                    }
                    cpu.getJob().setLastPreemption(systemClock);
                    cpu.setJob(null);
                    cpu.setBusyFlag(false);
                }
            }
            if(!cpu.isBusy()){
                //submit appropiate job to the cpu from queue 1-2-3-4
                //set quantum clock and busyflag
                if(!queues[0].isEmpty()){
                    Job nextJob = (Job)queues[0].remove();
                    cpu.setJob(nextJob);
                    cpu.setCpuQuantumClock(2);
                    cpu.setBusyFlag(true);
                    responseTime = systemClock - nextJob.getArrivalTime();
                    waitTime += systemClock - nextJob.getLastPreemption();
                }else if(!queues[1].isEmpty()){
                    Job nextJob = (Job)queues[1].remove();
                    cpu.setJob(nextJob);
                    cpu.setCpuQuantumClock(4);
                    cpu.setBusyFlag(true);
                    waitTime += systemClock - nextJob.getLastPreemption();
                }else if(!queues[2].isEmpty()){
                    Job nextJob = (Job)queues[2].remove();
                    cpu.setJob(nextJob);
                    cpu.setCpuQuantumClock(8);
                    cpu.setBusyFlag(true); 
                    waitTime += systemClock - nextJob.getLastPreemption();
                }else if(!queues[3].isEmpty()){
                    Job nextJob = (Job)queues[3].remove();
                    cpu.setJob(nextJob);
                    cpu.setCpuQuantumClock(16);
                    cpu.setBusyFlag(true); 
                    waitTime += systemClock - nextJob.getLastPreemption();
                }else if(inputQueue.isEmpty()){
                    break;
                }
            }
        }

    }
    /**
     * Method outStats() prints all of the Systems stats after the program is done
     */
    public void outStats(){
        DecimalFormat dec = new DecimalFormat("#.##");
        
        System.out.println();
        System.out.println("MFQ Stats: ");
        System.out.println();
        System.out.println("Total number of Jobs: " + numOfJobs);
        System.out.println("Total time of all jobs in the MFQ system: " + totalJobTime);
        System.out.println("Average response time: " + dec.format((double)responseTime/numOfJobs));
        System.out.println("Average turn around time of the Jobs: " + dec.format((double)totalJobTime/numOfJobs));
        System.out.println("Average wait time: " + dec.format((double)waitTime/numOfJobs));
        System.out.println("Average throughput for the system as a whole: " + dec.format((double)numOfJobs/totalJobTime));
        
        pw.println();
        pw.println("MFQ Stats: ");
        pw.println();
        pw.println("Total number of Jobs: " + numOfJobs);
        pw.println("Total time of all jobs in the MFQ system: " + totalJobTime);
        pw.println("Average response time: " + dec.format((double)responseTime/numOfJobs));
        pw.println("Average turn around time of the Jobs: " + dec.format((double)totalJobTime/numOfJobs));
        pw.println("Average wait time: " + dec.format((double)waitTime/numOfJobs));
        pw.println("Average throughput for the system as a whole: " + dec.format((double)numOfJobs/totalJobTime));
    }
}

