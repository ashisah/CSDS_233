
/**
 * An array implementation of NumList
 * @author Ashley Sah
 *
 */
import java.lang.ArrayIndexOutOfBoundsException;

public class NumArrayList implements NumList {
	// represents the list of numbers
	private Double[] numList;
	// represents the size of the list
	private int size;

	/**
	 * will create a list of 0 doubles if not given a number
	 */
	public NumArrayList() {
		numList = new Double[0];
		size = 0;
	}

	/**
	 * will create a list of doubles of size i
	 * 
	 * @param i-the number of spaces in the list
	 */
	public NumArrayList(int i) {
		numList = new Double[i];
		size = 0;
	}

	/**
	 * @return the size of list, the size is the number of doubles in the array
	 */
	public int size() {
		return size;
	}

	/**
	 * @return the capacity of the numList (the maximum amt of numbers it can store)
	 */
	public int capacity() {
		return numList.length;
	}

	/**
	 * adds value to the list
	 */
	public void add(double value) {

		// if the array is filled up
		if (size == numList.length) {

			// initialize a new array with one more space than the old one and copy over the
			// old values
			Double[] saveNumList = numList;
			numList = new Double[numList.length + 1];

			for (int i = 0; i < saveNumList.length; i++) {
				numList[i] = saveNumList[i];
			}

			// add the value to the end of list
			numList[numList.length - 1] = value;

			// update the size of the list
			size = numList.length;
		}

		// otherwise (it's not filled up)
		else {
			int i = 0;
			// find a place in the list that is null
			while (numList[i] != null) {
				i++;
			}

			// once found, add the value there
			numList[i] = value;

			// update size value
			size++;
		}
	}

	/**
	 * inserts value at index i (where i=0 will insert it at the front)
	 */
	public void insert(int i, double value) {
		// if the insert value is greater than the length of the list

		if (i >= numList.length || numList.length ==0) {
			// run the add method (since add will add it to the end of the list)
			add(value);
		}

		// if the value at numList is empty
		else if (numList[i] == null) {
			add(value);
		}
		// insert the value at i, save the value that will be overwritten and then
		// insert that value at i+1
		else {
			double save = numList[i];
			numList[i] = value;
			//System.out.println("Inserted " + value + ", will now insert " + save );
			insert(i + 1, save);
		}
	}

	/**
	 * removes a number at index i
	 * 
	 * @param i–the index of the value we are removing, with 0 being the first number in the list
	 */
	public void remove(int i) {

		// remove the numList value at index i if i is a valid index
		if (i < numList.length) {
			numList[i] = null;

			int x = i + 1;
			while (x < numList.length && numList[x] != null) {
				numList[x - 1] = numList[x];
				x++;
			}
			
			numList[x-1] = null;
				
			size--;
		}
	}

	/**
	 * 
	 */
	public boolean contains(double value) {
		int x = 0;
		
		while(x<numList.length && numList[x]!=null) {
			if(numList[x] == value) {
				return true;
			}
			x++;
		}
		
		return false;
	}

	@Override
	/**
	 *@returns the value at index i, with 0 being the first number in the list
	 *@exception ArrayIndexOutOfBoundsException–if i is not a valid index, will return an ArrayOutOfBoundsException
	 */
	public double lookup(int i) {
		try {
			return numList[i];//this can also return a NullPointerException
		}
		catch(Exception e) {//no matter what exception is thrown (NullPointer or ArrayIndex) it will throw an ArrayIndex exception
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	/**
	 * removes duplicate numbers in the list
	 */
	public void removeDuplicates() {
		
		int i = 0;
		//while there are values in the list
		while(i<numList.length-1 && numList[i] != null) {
			
			int x = i+1;
	
			//while there are values in the list
			while(x<numList.length && numList[x]!=null) {
				//see if two values in the list are equal, if they are remove them
				if(numList[x].equals(numList[i])) {
					//System.out.println(numList[i] + " equals " + numList[x]);
					remove(x);
					x--;
				}
				x++;
				//move to the next value
			}
			
			i++;
			//move to the next value
		}
	}
	
	@Override
	/**
	 * checks if two NumLists are equal
	 */
	public boolean equals(Object otherList) {
		
		if (otherList instanceof NumList) {
			if (otherList.toString().equals(toString())) 
				return true;
			return false;
		}
		
		return false;
	}
	
	@Override
	/**
	 * returns the numList in string format
	 */
	public String toString() {
		StringBuilder list = new StringBuilder();
		
		if (capacity() != 0) {
			int x = 0;
			while(x<numList.length && numList[x]!= null) {
				list.append(numList[x] + ",");
				x++;
			}
		}
		
		return list.toString();
	}
	
	//Demonstrating functionality of NumArrayList
	public static void main(String[] args) {
		
		//demonstrate 
		System.out.println("Initializing an empty NumArrayList numList1:");
		NumArrayList numList1 = new NumArrayList();
		System.out.println("numList1 capacity: " + numList1.capacity());
		System.out.println(" ");
		System.out.println("Initializing an empty NumArrayList numList2:");
		NumArrayList numList2 = new NumArrayList(10);
		System.out.println("numList2 capacity: " + numList2.capacity());
		
		//demonstrate add method()
		System.out.println(" ");
		System.out.println("Can use the add() method to add methods numbers to the list");
		System.out.println("Will use add() to add 3.4, 5.6, 7.8 to numList1:");
		numList1.add(3.4);
		numList1.add(5.6);
		numList1.add(7.8);
		System.out.println(numList1);
		System.out.println("numList1 size: " + numList1.size());
		
		System.out.println("");
		//demonstrate remove method
		System.out.println("Can remove numbers from a list by specifying the index you wish to remove from");
		System.out.println("Will remove all the numbers in numList1: " + numList1);
		System.out.println("Removing first number:");
		numList1.remove(0);
		System.out.println("numList1 is now " + numList1);
		numList1.remove(1);
		System.out.println("Removing the last number: " + numList1);
		numList1.remove(0);
		System.out.println("Removing the remaining number (5.6)" + " \n" + "numList1's size is now " + numList1.size());
		
		//
		System.out.println("");
		
		//demonstrate lookup(), contains(), and insert()
		
		System.out.println("Adding numbers 1-5 to numList2");
		numList2.add(1);
		numList2.add(2);
		numList2.add(3);
		numList2.add(4);
		numList2.add(5);
		System.out.println("numList2 is now " + numList2);
		
		//lookup()
		System.out.println("Can look up numbers at specific indices with the lookup() function");
		System.out.println("Number at index 2 (3rd number): " + numList2.lookup(2));
		System.out.println("Number at index 3 (4th number): " + numList2.lookup(3));
		System.out.println("Looking for a number at an invalid index will raise an exception");
		System.out.println(" ");
		
		//contains()
		System.out.println("Can check if a list contains a certain value");
		System.out.println("numList2 contains 3.4: " + numList2.contains(3.4));
		System.out.println("numList2 contains 3.0: " + numList2.contains(3));
		System.out.println(" ");
		
		//insert()
		System.out.println("Can insert a number at index i of the list, will automatically shift all the values after index i over by one index:");
		numList2.insert(2, 3.5);
		System.out.println("Inserting 3.5 to numList2 at index 2: " + numList2);
		System.out.println(" ");
		System.out.println("If index i is greater than the size of the list, it will automatically add it to the end of the list:");
		numList2.insert(100, 10);
		System.out.println("Inserting 10 to numList2 at index 100: " + numList2);
		System.out.println("");
		
		
		//removeDuplicates()
		System.out.println("Adding 1, 2, and 3 multiple times to numList1");
		numList1.add(1);
		numList1.add(2);
		numList1.add(2);
		numList1.add(1);
		numList1.add(1);
		numList1.add(3);
		numList1.add(3);
		numList1.add(1);
		numList1.add(3);
		System.out.println("numList1 is now " + numList1);
		System.out.println("");
		System.out.println("We can now remove the repeating values in numList1 with the removeDuplicates() method");
		numList1.removeDuplicates();
		System.out.println("numList1 is now: " + numList1);
		
		
		
		
		
		
	}

}
