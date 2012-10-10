

public class LinkedList<T> implements BoundedStack<T>{
	private LinkedListEntry<T> top;
	private int size;
	private int maxsize;
	/**
     * Construct the stack.
     */
    public LinkedList(int maxsize){
        top = null;
        size = 0;
        this.maxsize = maxsize;
    }
    
    /**
     * Tests whether the stack is empty
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty(){
        return top == null;
    }
    
    /**
     * Pushes the object x onto the top of the stack
     *
     * @param x object to be pushed onto the stack.
     * @throws FullStackException if the stack is full
     */
    public void push(T x){
    	if(isFull())
            throw new FullStackException();
		size++;
        top = new LinkedListEntry<T>(x, top);
    }
    
    /**
     * Removes and returns the object at the top of the stack
     *
     * @return reference to the item at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T pop(){
        if(isEmpty())
            throw new EmptyStackException();
        T topItem = top.element;
        size--;
        top = top.next;
        return topItem;
    }
    
    /**
     * Returns the object at the top of the stack.
     *
     * @return reference to the item at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T top( ) {
        if(isEmpty())
            throw new EmptyStackException();
        return top.element;
    }
    
    /**
     * Returns the number of elements currently on the stack
     *
     * @return integer value representing the current size of the stack 
     */
    public int size(){
    	return size;
    }
    
    /**
     * Returns the maximum number of elements the stack can store
     *
     * @return integer value representing the number elements the stack may contain
     */
    public int capacity(){
    	return maxsize;
    }
    /**
     * Tests whether the stack is full.
     *
     * @return true if the stack is full, false otherwise
     */
    public boolean isFull(){
    	 return size == maxsize;
    }

	public void makeEmpty() {
		
	}
    
    
}