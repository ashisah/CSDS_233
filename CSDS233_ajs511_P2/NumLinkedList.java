
public class NumLinkedList implements NumList {
	
	//size represents the number of elements in the LinkedList
	private int size = 0;
	//will act as  'special' nodes, cannot be removed
	private Node<Double> front = new Node<Double>(null, null, null);
	private Node<Double> back = new Node<Double>(null, front, null);

	//basic getter and setter methods for the nodes, kept private since I don't want users to access nodes
	private Node<Double> getFront(){
		return front;
	}

	private Node<Double> getBack(){
		return back;
	}
	
	private void setBack(Node<Double> back) {
		this.back = back;
	}
	
	private void setFront(Node<Double> front) {
		this.front = front;
	}
	
	/**
	 * checks if the linkedlist is empty
	 * @return boolean–true means the list is empty, false means it's not
	 */
	public boolean isEmpty() {
		if(size() == 0) {
			return true;
		}
		return false;
	}
		
	@Override
	public int size() {
		return size;
	}
	
	public int capacity() {
		return size();
	}

	@Override
	public void add(double value) {
		Node<Double> addNode = new Node<Double>(value, getBack().getPrevious(), getBack());
		size++;
	}

	@Override
	public void insert(int i, double value) {
		//if the index isn't valid, just add it to end of the LinkedList
		if (i>size-1 || i<0) {
			add(value);
		}
		//otherwise, get to node at index i-1, then add the value at i
		else {
			Node<Double> nodeptr = getFront();
			for(int index = 0; index<i; index++) {
				nodeptr = nodeptr.getNext();
			}
			
			Node<Double> addNode = new Node<Double>(value, nodeptr, nodeptr.getNext());
			size++;
			
		}
		
	}
	

	@Override
	public void remove(int i) {
		//if the index is valid
		if(i>-1 && i<=size-1) {
			//go to the value at i-1
			Node<Double> nodeptr = getFront();
			if(i == 0) {
				nodeptr.setNext(nodeptr.getNext().getNext());
				nodeptr.getNext().setPrevious(nodeptr);
				size--;
			}
			else {
				for (int index = 0; index < i; index++) {
					nodeptr = nodeptr.getNext();
				}

				// Step one: set nodeptr's next to nodeptr.getNext().getNext()
				nodeptr.setNext(nodeptr.getNext().getNext());
				// Step two: set nodeptr.getNext().getNext() previous to nodeptr [remember
				// nodeptr.getNext().getNext() is now nodeptr.getNext()]
				nodeptr.getNext().setPrevious(nodeptr);

				size--;
			}
		}
	}

	@Override
	public boolean contains(double value) {
		Node<Double> nodeptr = getFront();
		
		//goes down the list checking if any of the nodes contain value
		while(nodeptr != getBack()) {
			nodeptr = nodeptr.getNext();
			if(nodeptr.getElement() != null && nodeptr.getElement()==value) {
				return true;
			}
		}
		return false;
	}

	@Override
	public double lookup(int i) {
		Node<Double> nodeptr = getFront();
		//code will automatically throw a NullPointer exception if it's an invalid index
		
		for(int index = -1; index<i; index++) {
			nodeptr = nodeptr.getNext();
		}
		return nodeptr.getElement();
	}

	@Override
	public void removeDuplicates() {

		Node<Double> nodeptrOut = getFront().getNext();

		while (nodeptrOut != back) {
			if (nodeptrOut.getElement() != null) {
				Node<Double> nodeptrIn = nodeptrOut.getNext();
				while (nodeptrIn != back) {
					// if the elements are equal, remove the second node (nodeptrIn)
					if (nodeptrIn.getElement() != null && nodeptrIn.getElement().equals(nodeptrOut.getElement())) {
						nodeptrIn.getPrevious().setNext(nodeptrIn.getNext());
						nodeptrIn.getNext().setPrevious(nodeptrIn.getPrevious());
						size--;
					}
					nodeptrIn = nodeptrIn.getNext();
				}
			}
			// move to the next node
			nodeptrOut = nodeptrOut.getNext();
		}

	}
	
	public boolean isSorted() {
		Node<Double> nodeptr = getFront().getNext();
		
		if(isEmpty()) {
			return true;
		}
		
		while(nodeptr.getNext() != back) {
			if(nodeptr.getElement() > nodeptr.getNext().getElement()) {//if next node has an double with a value smaller than the previous element
				return false;//it's  not sorted
			}
			nodeptr = nodeptr.getNext(); //otherwise, move the nodeptr up
		}
		
		return true;
	}
	/**
	 * revereses the elements in a list
	 */
	public void reverse() {
		//don't want to do anything for these edge cases (when it's empty)
		if (!isEmpty()) {
			Node<Double> start = getFront().getNext();
			Node<Double> end = getBack().getPrevious();
			//starting at the two ends of the list

			while (start.getPrevious() != end && start != end) {//while there are still elements to reverse
				Double startElement = start.getElement();
				start.setElement(end.getElement());
				end.setElement(startElement);
				
				
				start = start.getNext();//move the start nodeptr forward
				end = end.getPrevious();//move tne end nodeptr back
			}
		}
		
	}
	
	
	public static NumLinkedList union(NumLinkedList list1, NumLinkedList list2) {
		
		NumLinkedList unionList = new NumLinkedList();
		//sorted lists
		if(list1.isSorted() && list2.isSorted()) {
			
			Node<Double> nodeptr1 = list1.getFront().getNext();
			Node<Double> nodeptr2 = list2.getFront().getNext();
			
			int i = 0;
			int j= 0;
			
			//this loop will terminate after we've run to the end of the shortest list
			while (i < list1.size() && j < list2.size()) {

				if (nodeptr1.getElement().equals(nodeptr2.getElement())) {
					nodeptr1 = nodeptr1.getNext();
					i++;
				}

				else if (nodeptr2.getElement() < nodeptr1.getElement()) {
					unionList.add(nodeptr2.getElement());
					nodeptr2 = nodeptr2.getNext();
					j++;
				}

				else if (nodeptr1.getElement() < nodeptr2.getElement()) {
					unionList.add(nodeptr1.getElement());
					nodeptr1 = nodeptr1.getNext();
					i++;
				}

			}
			
			//should add whatever remains in the other list:
			
			while(nodeptr1 != list1.getBack()) {
				unionList.add(nodeptr1.getElement());
				nodeptr1 = nodeptr1.getNext();
			}
			
			while(nodeptr2 != list2.getBack()) {
				unionList.add(nodeptr2.getElement());
				nodeptr2 = nodeptr2.getNext();
				
			}
			
			unionList.removeDuplicates();
			
			return unionList;
		
		}
		
		//unsorted lists
		else {
			System.out.println("unsorted way: ");
			unionList.setFront(list1.getFront());
			unionList.setBack(list1.getBack());
			//now unionList is the same as list1
			
			unionList.getBack().getPrevious().setNext(list2.getFront().getNext());
			//here unionList is retrieving the node that comes just before the back node of list1
			//and is setting it's next pointer to the that comes after list2's front node
			
			unionList.getBack().getPrevious().getNext().setPrevious(unionList.getBack().getPrevious());
			//keep in mind that unionList.getBack().getPrevious() still points to the node that's just before the back node in list1
			//when we retrieve unionList.getBack().getPrevious().getNext() is goes to the node that comes after list2's front node
			//now we set the previous nodeptr of the node after list2's front node to unionList.getBack().getPrevious()
			
			unionList.setBack(list2.getBack());
			//finally, we set the back of unionList to the back node of list2
			
			unionList.removeDuplicates();
			//we go ahead and remove any duplicate values
			
			return unionList;
				
		}
	}
	
	/**
	 * returns a string representation of the list
	 */
	public String toString() {
		StringBuilder list = new StringBuilder();
		if (!isEmpty()) {
			Node<Double> nodeptr = getFront().getNext();

			while (nodeptr != getBack()) {
				list.append(nodeptr.getElement() + ",");
				nodeptr = nodeptr.getNext();
			}
		}
		
		return list.toString();
	}
	
	/**
	 * checks if two lists are equal
	 */
	public boolean equals(Object otherList) {
		
		if(otherList instanceof NumList) {
			return otherList.toString().equals(toString());
		}
		else
			return false;
	}
	
	//tests all private methods in NumLinkedList and the methods in Node
	public void test() {
		
		Node<Double> front = new Node<Double>(2.0, null, null);
		Node<Double> back = new Node<Double>(4.0, null, null);
		Node<Double> middle = new Node<Double>(3.0, front, back);
		
		boolean constructorWorks = (front.getNext()== middle && middle.getPrevious()==front && back.getPrevious()==middle && middle.getNext()==back);
		if(!constructorWorks) {
			throw new RuntimeException();
		}
		
		boolean getElementWorks = (middle.getElement()==3.0);
		if(!getElementWorks) {
			throw new RuntimeException();
		}
		
		middle.setElement(-2.5);
		
		boolean setElementWorks = (middle.getElement()==-2.5);
		if(!setElementWorks) {
			throw new RuntimeException();
		}
		
		boolean getNextWorks = middle.getNext()==back && back.getNext()==null;
		if(!getNextWorks) {
			throw new RuntimeException();
		}
		
		middle.setNext(front); //now a circular loop
		
		boolean setNextWorks = (middle.getNext()==front);
		if(!setNextWorks) {
			throw new RuntimeException();
		}
		
		middle.setNext(back);

		boolean getPreviousWorks = (middle.getPrevious()==front  &&  front.getPrevious()==null && back.getPrevious()==middle);
		if(!getPreviousWorks) {
			throw new RuntimeException();
		}
		
		front.setPrevious(back);
		
		boolean setPreviousWorks = (front.getPrevious()==back);
		if(!setPreviousWorks) {
			throw new RuntimeException();
		}
		
		
		front = new Node<Double>(null, null, null);
		back  = new Node<Double>(null, null, null);
		
		
		//test for getFront() and setFront()
		front.setNext(getFront().getNext());
		getFront().getNext().setPrevious(front);
		setFront(front);//replacing one null node for another won't make a difference
		
		boolean getFrontWorks = (front == getFront());
		boolean setFrontWorks = getFrontWorks;
		if(!(getFrontWorks && setFrontWorks)) {
			throw new RuntimeException();
		}
		
		//let's also just check we didn't mess up our linkedlist:
		boolean itWorks = ( getFront().getNext().getPrevious() == getFront() );
		if(!itWorks) {
			throw new RuntimeException();
		}

		
		//test for getBack() and setBack()
		back.setPrevious(getBack().getPrevious());
		getBack().getPrevious().setNext(back);
		setBack(back);//replacing one null node for another won't make a difference

		boolean getBackWorks  = (back == getBack());
		boolean setBackWorks = getBackWorks;
		if(!(getBackWorks && setBackWorks)) {
			throw new RuntimeException();
		}
		
		itWorks = ( getBack().getPrevious().getNext() == getBack() );
		if(!itWorks) {
			throw new RuntimeException();
		}
	
		
	}
	/**
	 * 
	 * @author Ashley Sah
	 * Nodes (the objects that make up the linked list)
	 * @param <T>–The type stored in the node
	 */
	private class Node<T> 
	{
		
		private T element;
		private Node<T> previous;
		private Node<T> next;
		
		//constructor
		
		public Node(T element, Node<T> previous, Node<T> next) 
		{
			this.element = element;
			this.previous = previous;
			this.next = next;
			
			if(next != null)
				next.setPrevious(this);
			
			if(previous !=null)
				previous.setNext(this);
		}
		
		//setter methods
		public void setElement(T element) {
			this.element = element;
		}
		
		public void setPrevious(Node<T> previous) {
			this.previous = previous;
		}
		
		public void setNext(Node<T> next) {
			this.next = next;
		}
		
		//getter methods
		public T getElement() {
			return element;
		}
		
		public Node<T> getPrevious(){
			return previous;
		}
		
		public Node<T> getNext(){
			return next;
		}

	}

	public static void main(String[] args) {
		System.out.println("NumLinkedList is a Linked List implementation of NumList\n");
		System.out.println("Basic functions that can be completed with NumList:");
		System.out.println("\tsize()–returns size of list \n\tcapacity()–returns size of the list \n\tinsert(value, index)–inserts value at index (index 0 is the first element of the list)");
		System.out.println("\tremove(index)–removes whatever element is at index i, throws a NullPointerException if you provide an invalid index");
		System.out.println("\tisEmpty()–checks if the list is empty \n\tisSorted()–checks if the list is sorted()");
		System.out.println("\tremoveDuplicates()–removes duplicate valies");
		System.out.println("\tequals(NumList other)–checks if NumList other is equal to list calling the equals() function");
		
		NumLinkedList list1 = new NumLinkedList();
		list1.add(1); list1.add(2); list1.add(3); list1.add(4); list1.add(5); 
		System.out.println("\nHere is a basic list: " + list1);
		
		//demonstrating add(), remove(), insert(), isSorted()
		System.out.println("\nIs it sorted? " + list1.isSorted());
		list1.insert(2, 6.0);
		System.out.println("\nLet's insert 6 at index 2 (the third spot): " + list1);
		list1.remove(2);
		System.out.println("\nLet's remove it: " + list1);
		list1.add(6);
		System.out.println("\nInstead we'll add 6 to the end with the add method: " + list1);
		
		//demonstrating removeDuplicates()
		NumLinkedList list2 = new NumLinkedList();
		list2.add(3);
		list2.add(3);
		list2.add(4);
		list2.add(4);
		list2.add(5);
		
		System.out.println("\n--------------------------------------");
		System.out.println("Let's create a new list: " + list2);
		list2.removeDuplicates();
		System.out.println("\nLet's remove the duplicate values: " + list2);
		
		System.out.println("\n--------------------------------------");
		System.out.println("Other functions that can be used with NumLinkedList: \n\n\treverse()–reverses the elements in a list [1,2,3] -> [3,2,1]");
		System.out.println("\n\tunion(list1, list2–returns a list with all the elements from list1 and list2 (removes any duplicate values)");
		System.out.println("\n*Important to note: list1 and list2 must be NumLinkedLists*");
		
		//demonstrating reverse()
		System.out.println("\n--------------------------------------");
		
		list1 = new NumLinkedList();
		list1.add(0);list1.add(1);list1.add(2);list1.add(3);list1.add(4);
		System.out.println("Here's a basic list " + list1);
		list1.reverse();
		System.out.println("\nLet's reverse it: ");
		System.out.println("\n\t\t" + list1);
		
		//demonstration of union
		System.out.println("\n--------------------------------------");
		list1 = new NumLinkedList();
		list1.add(1);
		list1.add(2);
		list1.add(2);
		list1.add(3);
		list1.add(3);
		
		list2 = new NumLinkedList();
		list2.add(3);
		list2.add(3);
		list2.add(4);
		list2.add(4);
		list2.add(5);
		
		System.out.println("Let's use union() to combine the two Linked Lists: " + list1 + " and "+ list2);
		NumLinkedList list4 = NumLinkedList.union(list1, list2);
		
		System.out.println("\nHere is our new list: " + list4);
		System.out.println("\nKeep in mind our old lists are now: " + list1 + " and " +  list2);
		
		//since this method does not throw any exceptions, all the methods in Node and private methods in NumLinkedList work :D
		list1.test();
		
		
	}

}
