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

public class ObjectQueue implements ObjectQueueInterface{
    private Object[] item;
    private int front;
    private int rear;
    private int count;
    /**
     * creates a new object of ObjectQueue class
     */
    public ObjectQueue() {
        item = new Object[1];
        front = 0;
        rear  = -1;
        count = 0;
    }

    /**
     * checks if the queue is empty or not
     * @return true if the queue is empty; otherwise, false
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * checks if the queue is full or not
     * @return true if the queue is full; otherwise, false
     */
    public boolean isFull() {
        return count == item.length;
    }

    /**
     * clears the queue to its initial state
     * all the elements
     */
    public void clear() {
        item = new Object[1];
        front = 0;
        rear  = -1;
        count = 0;
    }

    /**
     * inserts an element to the queue
     * @param o - element of object type
     */
    public void insert(Object o) {
        /**if(isEmpty()){
        new Exception("Remove Runtime Error: Queue Underflow").printStackTrace();
        System.exit(1);
        }*/
        if (isFull())
            resize(2 * item.length);
        rear = (rear+1) % item.length;
        item[rear] = o;
        ++count;
    }

    /**
     * removes and then returns the element at the front of the queue
     * @return top element
     */
    public Object remove() {
        if (isEmpty()) {
            new Exception("Remove Runtime Error: Queue Underflow").printStackTrace();
            System.exit(1);
        }
        Object temp = item[front];
        item[front] = null;
        front = (front+1) % item.length;
        --count;
        if (count == item.length/4 && item.length != 1)
            resize(item.length/2);
        return temp;
    }

    /**
     * returns the front element of the queue
     * @return top element without removing it
     */
    public Object query() {
        if (isEmpty()) {
            new Exception("Remove Runtime Error: Queue Underflow").printStackTrace();
            System.exit(1);
        }
        return item[front];
    }

    /**
     * private method resize, that resizes the stacks
     */
    private void resize(int size) {
        Object[] temp = new Object[size];
        for (int i = 0; i < count; ++i) {
            temp[i] = item[front];
            front = (front+1) % item.length;
        }
        front = 0;
        rear = count-1;
        item = temp;
    }
}
