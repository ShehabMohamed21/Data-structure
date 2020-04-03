package Classes;

import java.util.EmptyStackException;

import Interface.IStack;
/**
 * this class is for implementation of stacks and there are many functions (push, pop, peek, isEmpty and size)
 * @author shehab mohamed saad
 * @version 1.0
 */

public class stack implements IStack{
	/**
	 * implementation of nodes
	 * @author shehab
	 *
	 */
	private class Node 
	{
		Object value;
		Node next;
		
		public Object getValue() 
		{
			return value;
			
		}
		
		
		public Node getNext() 
		{
			return next;
			
		}
		public void setValue(Object value) 
		{
			
			this.value = value ;
		}
		
		public void setNext(Node next) 
		{
			this.next = next;
			
		}
	}
	private int size;
	private Node top;
	
	public stack() 
	{
		size = 0 ;
		top = null ;
	}
	
	/**
	 * this function remove top element of the stack
	 * @return top 
	 */
	
	@Override
	public Object pop() throws EmptyStackException
	{
		if (size == 0) 
		{
			throw new EmptyStackException();
		}
		Object pop = top.getValue();
		top = top.getNext();
		size--;
		
		return pop;
	}

	/**
	 * this function show the top of the stack
	 * @return top of the stack
	 */
	@Override
	public Object peek() throws EmptyStackException
	{
		if (size == 0) 
		{
			throw new EmptyStackException();
		}
		return top.getValue();
	}
	
	/**
	 * this function insert elements into stack
	 * @param element object wanted to be inserted 
	 */
	@Override
	public void push(Object element) 
	{
		Node newNode = new Node();
		newNode.setValue(element);
		newNode.setNext(top);
		top = newNode;
		size++;
	}

	/**
	 * this function check if the stack is empty or not 
	 * @return true if it's empty and false if it's not empty
	 */
	@Override
	public boolean isEmpty() 
	{
		if (size == 0) 
		{
			return true;
		}
		else 
		{
		return false;
		}
	}

	/**
	 * this function show the size of the stack
	 * @return size of the stack
	 */
	@Override
	public int size() 
	{
		return size;
	}

}
