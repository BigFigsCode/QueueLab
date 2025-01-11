/**
 * <h2>Job.java - Create a Job class that will identify each process, time entered, cpu time required and remaining and currentqueue.</h2> 
 * <p>Algorithm:</p>
 * <ol>
 *   <li>create a full constructor that will pass the arrivatiime, pid and cpu time required for the job.</li>
 *   <li>create a  method method that will get the arrival time and another that will set the arrivaltime.</li>
 *   <li>create a method that will get the pid and another that will set the pid for the job.</li>
 *   <li>create a method that will get the cpu time required for the job and another that will decrement the time from the cpu.</li>
 *   <li>creat a method that will get the cpu time remaining.</li>
 *   <li>create a method that will get the current queue and another that will set a new queue value.</li>
 *   <li>create a method that will get the last preemption and another that will set a new last preemption.</li>
 *   
 * </ol>
 * @author Brandon Figueroa
 * Palomar ID 010881556
 */
public class Job
{
    private int pid;
    private int arrivalTime;
    private int cpuTimeRequired;
    private int cpuTimeRemaining;
    private int currentQueue;
    private int preemption;

    /**
     * Full constructor passing 3 arguments
     * @param arrivalTime is the arrival time of the job
     * @param pid is the pid of the job
     * @param cpuTimeRequired is the time required for the job at the cpu
     */
    public Job(int arrivalTime, int pid, int cpuTimeRequired)
    {
        this.arrivalTime = arrivalTime;
        this.pid = pid;
        this.cpuTimeRequired = cpuTimeRequired;
        cpuTimeRemaining = cpuTimeRequired;
        currentQueue = 0;
        preemption = arrivalTime;
    }
    /**
     * Method getArrivalTime() returns the arrivalTime of the job
     * @return arrivalTime of the job
     */
    public int getArrivalTime(){
        return this.arrivalTime;
    }
    /**
     * Method setArrivalTime() sets a new arrival time for the job
     * @param arrivaltime is the new arrival time of the job
     */
    public void setArrivalTime(int arrivalTime){
        this.arrivalTime = arrivalTime;
    }
    /**
     * Method getPid() return the pid of the job
     * @return pid is the pid of the job
     */
    public int getPid(){
        return this.pid;
    }
    /**
     * Method setPid() passes a new pid for the job
     * @param is the new pid of the job
     */
    public void setPid(int pid){
        this.pid = pid;
    }
    /**
     * Method getCputTimeRequired() returns the new time required for the cpu
     * @return cpuTimeRequired returns the time required for the job
     */
    public int getCpuTimeRequired(){
        return cpuTimeRequired;
    }
    /**
     * Method decrementCpuTimeRequired decrements the time for the cpu
     */
    public void decrementCpuTimeRemaining(){
        cpuTimeRemaining--;
    }
    /**
     * Method getCpuTimeRemaining() returns the time remaining for the cpu
     * @return the time remaining for the job
     */
    public int getCpuTimeRemaining(){
        return cpuTimeRemaining;
    }
    /**
     * Method getCurrentQueue returns the current queue
     * @return currentQueue of where the job is
     */
    public int getCurrentQueue(){
        return this.currentQueue;
    }
    /**
     * Method setCurrentQueue sets a new queue for the job
     * @param q is the new currentQueue
     */
    public void setCurrentQueue(int q){
        this.currentQueue = q;
    }
    /**
     * Method getLastPreemption returns the last preemption
     * @return preemption of the job
     */
    public int getLastPreemption(){
        return preemption;
    }
    /**
     * Method setlastPreemption sets a new preemption
     * @param preemtion is the new preemption
     */
    public void setLastPreemption(int preemption){
        this.preemption = preemption;
    }
    /**
     * Method toString returns pid, arrivaTime, and cutTimeRequired
     * @return pid is the pid of the job
     * @return arrivalTime is the arrival time of the job
     * @return cpuTimeRequired is the time requuired for the job
     */
    public String toString(){
        return "Job{ " + "pid = " + this.pid + ", arrivalTime = " + this.arrivalTime + ", cpuTimeRequired = " + this.cpuTimeRequired + "}";
    }
}
