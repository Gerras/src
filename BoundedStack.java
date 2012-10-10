 /**
 *
 * @author kevin.brauen
 */
public interface BoundedStack<T> extends StackInt<T> {
    
    /**
     * returns the number of elements currently on the stack
     * 
     * @return The number of elements currently on the stack
     */
    public int size();

    /**
     * returns the maximum number of elements the stack can store
     *
     * @return The max number of elements the stack can store
     */
    public int capacity();

    /**
     * returns true if the number of elements in the stack is equal
     * to the stack's capacity, false otherwise
     */
    public boolean isFull();

     /**
     * Tests whether the stack is empty. Return true if the stack is empty
     * otherwise; returns false
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty();

    /**
     * Pushes the object x onto the top of the stack.
     *
     * @param x object to be pushed onto the stack.
     */
    public void push(T x);

    /**
     * Returns the object at the top of the stack without removing it.
     * condition: If called when the stack is full throws a
     * FullStackException
     * post: the stack remains unchanged
     *
     * @return reference to the item at the top of the stack
     * @throws FullStackException if the stack is full
     */
    public T top();

    /**
     * Returns the object at the top of the stack and removes it
     * post-condition: the stack is one item smaller
     *
     * @return reference to the item at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T pop();
}
