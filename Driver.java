import java.io.*;
import java.util.Scanner;
/**
 * <h2>Driver.java - Create a main method and run the mfq class methods.</h2> 
 * <p>Algorithm:</p>
 * <ol>
 *   <li>Import java.io* and java.util.Scanner.</li>
 *   <li>Instantiate a Scanner object and read in the "csis.txt" file provided.</li>
 *   <li>Then instantiate the MFQ class.</li> 
 *   <li>call getJobs() method.</li>
 *   <li>call outputHeader() method.</li>
 *   <li>call runSimulation() method.</li>
 *   <li>call outPutStats() method.</li>
 *   <li>Finally close the Scanner.</li>
 *   <li>Finally close the Scanner.</li>
 * </ol>
 * @author Brandon Figueroa
 * Palomar ID 010881556
 */
public class Driver
{
    /**
     * This static method uses an object of mfq to run the methods within the mfq class that simulate whats happening as the jobs enter the cpu and leave the cpu
     * @param args a string of arguments
     */
    public static void main(String[]args) throws IOException{
        PrintWriter pw = new PrintWriter( new FileWriter("csis.txt"));
        MFQ mfq = new MFQ(pw);
        mfq.getJobs();
        mfq.outputHeader();
        mfq.runSimulation();
        mfq.outStats();
        pw.close();
    }
}
