
/**
 * <h2>CPU.java - Create a CPU class that will identify the current job on the cpu, a quantum clock and a busyFlag.</h2> 
 * <p>Algorithm:</p>
 * <ol>
 *   <li>Create a single arg constructor that that passes the quantum clock.</li>
 *   <li>create a method of Job type that will get the current job on the cpu.</li>
 *   <li>create a method that will set a new job on the cpu.</li>
 *   <li>create a decrement clock that decrement the time on the cpu.</li>
 *   <li>create a method that will get the quantum clock on the cpu and another that will set a new time for the quantumclock.</li>
 *   <li>create a method that will get the current flag status of the cpu and another that will set new status for the cpu.</li>
 *   
 * </ol>
 * @author Brandon Figueroa
 * Palomar ID 010881556
 */
public class CPU
{
    private Job job;
    private int cpuQuantumClock;
    private boolean busyFlag;
    

    /**
     * Single Arg constructor
     * @param quantumClock is the clock set for the job
     */
    public CPU(int quantumClock){
        job = null;
        cpuQuantumClock = quantumClock;
        busyFlag = false;
        
    }
    /**
     * Method getJob() will return a job
     * @return job is the current job
     */
    public Job getJob(){
        return job;
    }
    /**
     * Method setJob will set a new job
     * @param newJob is the new job that will be set
     */
    public void setJob(Job newJob){
        this.job = newJob;
    }
    /**
     * Method decrementClock() decrements the clock for the cpu
     * 
     */
    public void decrementClock(){
        cpuQuantumClock--;
    }
    /**
     * Method getQuantumClock will return the quantume clock of the cpu
     * @retunr cpuQuantumClock of the cpu
     */
    public int getQuantumClock(){
        return cpuQuantumClock;
    }
    /**
     * method setCpuQuantumClock will set a new time for the cpu
     * @return cpuClock for the new time set for the cpu
     */
    public void setCpuQuantumClock(int cpuClock){
        cpuQuantumClock = cpuClock;
    }
    /**
     * Method isBuay() returns a boolean value of the cpu
     * @return busyFlag for the statsus of the cpu
     */
    public boolean isBusy(){
        return busyFlag;
    }
    /**
     * Method setBusyFlag sets a new boolean value for the cpu
     * @paran flag is the new boolean value of the cpu's status
     */
    public boolean setBusyFlag(boolean flag){
        return busyFlag = flag;
    }
    /**
     * Method String toString() return a print statement for all the methods
     * 
     */
    public String toString(){
        return "CPU{" + " job = " + job + ", QuantumClcok = " + cpuQuantumClock + ", BusyFlag = " + busyFlag + "}";
    }
}





