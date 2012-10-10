

public class LinkedListEntry<T> {
	public T element;
	public LinkedListEntry<T> next;
	/**
	* Construct the list by overloading the constructor
	*/
	public LinkedListEntry(){
		this(null, null);
	}
		    
	public LinkedListEntry(T e, LinkedListEntry<T> n) {
		element = e;
		next    = n;
	}
}
