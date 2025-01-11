
 /**
 * <h2>ObjectQueueInterface.java - Create an ObjectQueueInterface with isEmpty(), isFull(), clear(), resize(), clear(), insert(), remove()methods.</h2> 
 * <p>Algorithm:</p>
 * <ol>
 *   <li>The isEmpty() methos checks if the queue is empty.</li>
 *   <li>isFull() checks if the queue is full or not.</li>
 *   <li>clear() clears the entire queue of its contents.</li> 
 *   <li>insert() allows you to insert an object of any type into the queue.</li>
 *   <li>remove method allows you to remove any item from the queue.</li>
 *   <li>query() returns the item of the front of the queue.</li>
 *   <li>resize() method will resize the queue if it is full.</li>
 *   <li>.</li>
 *   <li>.</li>
 * </ol>
 * @author Brandon Figueroa
 * palomar ID 010881556
 */
public interface ObjectQueueInterface{
    
    /**
     * checks if the queue is empty or not
     * @return true if the queue is empty; otherwise, false
     */
    public boolean isEmpty();
    /**
     * checks if the queue is full or not
     * @return true if the queue is full; otherwise, false
     */
    public boolean isFull();
    /**
     * clears the queue to its initial state
     * all the elements
     */
    public void clear();
    /**
     * inserts an element to the queue
     * @param o - element of object type
     */
    public void insert(Object o);
    /**
     * removes and then returns the element at the front of the queue
     * @return top element
     */
    public Object remove();
    /**
     * returns the front element of the queue
     * @return top element without removing it
     */
    public Object query();
    
    
}
