/**
 * Name: Prash Katukojwala, Mary Vu
 * Email: pkatukojwala@ucsd.edu, m2vu@ucsd.edu
 * Sources used: None
 * 
 * This file contains two classes, MyLinkedList and a nested Node class.
 * It imports AbstractList from Java's built in library. 
 */

import java.util.AbstractList;

/**
 * This is our implementation of a doubly linked list that stores the size,
 * sentinel head, and sentinel tail. It contains multiple methods that serve
 * as operations to do on the linked list.
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int size;
	Node head;
	Node tail;

	/**
	 * A Node class that holds data and references to previous and next Nodes.
	 */
	protected class Node {
		E data;
		Node next;
		Node prev;

		/**
		 * Constructor to create singleton Node
		 * 
		 * @param element Element to add, can be null
		 */
		public Node(E element) {
			// Initialize the instance variables
			this.data = element;
			this.next = null;
			this.prev = null;
		}

		/**
		 * Set the parameter prev as the previous node
		 * 
		 * @param prev - new previous node
		 */
		public void setPrev(Node prev) {
			this.prev = prev;
		}

		/**
		 * Set the parameter next as the next node
		 * 
		 * @param next - new next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		/**
		 * Set the parameter element as the node's data
		 * 
		 * @param element - new element
		 */
		public void setElement(E element) {
			this.data = element;
		}

		/**
		 * Accessor to get the next Node in the list
		 * 
		 * @return the next node
		 */
		public Node getNext() {
			return this.next;
		}

		/**
		 * Accessor to get the prev Node in the list
		 * 
		 * @return the previous node
		 */
		public Node getPrev() {
			return this.prev;
		}

		/**
		 * Accessor to get the Nodes Element
		 * 
		 * @return this node's data
		 */
		public E getElement() {
			return this.data;
		}
	}

	// Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is defined */
	/**
	 * Intializes an empty linked list with a sentinel head, sentinel tail, and
	 * defaults size to 0
	 */
	public MyLinkedList() {
		this.head = new Node(null);
		this.tail = new Node(null);
		this.head.setNext(this.tail);
		this.tail.setPrev(this.head);
		this.size = 0;
	}

	@Override
	/**
	 * Returns the number of nodes not including sentinel head and tail
	 * 
	 * @return Number of nodes being stored
	 */
	public int size() {
		return this.size;
	}

	@Override
	/**
	 * Get data within the node at position index
	 * 
	 * @param index The position in question
	 * @return Data at index
	 * @throws IndexOutOfBoundsException When index is less than 0 or greater
	 * than or equal to size
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node nextInList = this.head;
		for (int i = 0; i <= index; i++) {
			nextInList = nextInList.getNext();
		}
		return nextInList.getElement();
	}

	/**
	 * Add a node into this list by index.
	 * 
	 * @param index The position in question
	 * @param data  Element that will be added
	 * @throws NullPointerException The input index can be any integer in
	 * between zero and the number of elements(inclusive on both ends)
	 * @throws IndexOutOfBoundsException When index is less than zero or
	 * index is greater than size
	 */
	@Override
	public void add(int index, E data) {
		if (data == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		}
		this.size++;
		//Inserting the new node at correct index
		Node newNode = new Node(data);
		Node nodeAtIndex = this.getNth(index);
		newNode.setPrev(nodeAtIndex.getPrev());
		newNode.setNext(nodeAtIndex);
		nodeAtIndex.getPrev().setNext(newNode);
		nodeAtIndex.setPrev(newNode);
	}

	/**
	 * Add a node a the end of the list
	 * 
	 * @param data Element that will be added
	 * @return Always returns true
	 * @throws NullPointerException When data is null
	 */
	public boolean add(E data) {
		if (data == null) {
			throw new NullPointerException();
		}
		//Inserting the new node at the end of LL
		Node newNode = new Node(data);
		newNode.setPrev(this.tail.getPrev());
		newNode.setNext(this.tail);
		this.tail.getPrev().setNext(newNode);
		this.tail.setPrev(newNode);
		this.size++;
		return true;
	}

	/**
	 * Set the element for the node at index to data and return the element
	 * that was previously stored
	 * 
	 * @param index The position in question
	 * @param data  Element that will replace the element previously stored
	 * @return Element that was replaced
	 * @throws NullPointerException When data is null
	 */
	public E set(int index, E data) {
		if (data == null) {
			throw new NullPointerException();
		}
		E oldElement = this.get(index);
		this.getNth(index).setElement(data);
		return oldElement;
	}

	/**
	 * Remove the node from the position index in this list and return
	 * the data within the removed node
	 * 
	 * @param index The position in question
	 * @return Element that was removed
	 */
	public E remove(int index) {
		Node oldNode = this.getNth(index);
		oldNode.getPrev().setNext(oldNode.getNext());
		oldNode.getNext().setPrev(oldNode.getPrev());
		this.size--;
		return (E) oldNode.getElement();
	}

	/**
	 * Removes all nodes from the list
	 */
	public void clear() {
		this.head.setNext(this.tail);
		this.tail.setPrev(this.head);
		this.size = 0;
	}

	/**
	 * Determine if the list is empty
	 * 
	 * @return If list is empty return true, otherwise return false
	 */
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * A helper method that returns the Node at a specified position
	 * 
	 * @param index The position in question
	 * @return Node at index
	 * @throws IndexOutOfBoundsException When index is less than 0 or index is
	 * greater than or equal to the number of elements in the list
	 */
	protected Node getNth(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		//Looping until we reach the node at specified index
		Node nextInList = this.head;
		for (int i = 0; i <= index; i++) {
			nextInList = nextInList.getNext();
		}
		return (Node) nextInList;
	}
}