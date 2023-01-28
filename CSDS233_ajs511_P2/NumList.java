/**
 * represents a list of doubles 
 * @author Ashley Sah
 *
 */
public interface NumList {
	
	/**
	 * returns the size of the NumList
	 * @returns size of the NumList
	 */
	int size();
	
	/**
	 * returns the capacity of the NumList
	 * the capacity represents how much space is left in numList
	 * @return the capacity of the NumList 
	 */
	default int capacity() {
		return 0;
	}
	
	/**
	 * adds the value to the end of the list
	 * @param value the value added to the list
	 */
	void add(double value);
	
	/**
	 * inserts value at index i
	 * @param i–the index where value is inserted
	 * @param value–the double you want to insert into the list
	 */
	void insert(int i , double value);
	
	/**
	 * removes the value at index i, if there is no value at index i, does nothing
	 * @param i the index of the value that will be removed
	 */
	void remove(int i);
	
	/**
	 * checks if value is in the NumList
	 * @param value–checks if value is in list
	 * @return true if value is in NumList, false if not in NumList
	 */
	boolean contains(double value);
	
	/**
	 * 
	 * @param i–the index of the NumList we are looking at
	 * @return the value at index i (if there is a value)
	 * @exception Exception–if there is no value at index i, will throw an out of bounds exception
	 */
	double lookup(int i);
	
	/**
	 * removes duplicates in the list
	 */
	void removeDuplicates();

}
