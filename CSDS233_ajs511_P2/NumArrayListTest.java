import static org.junit.Assert.*;

import org.junit.Test;

public class NumArrayListTest {

	@Test
	public void testConstructorsAndCapacity() {
		//test the default constructor (no params)
		NumArrayList numList1 = new NumArrayList();
		assertEquals("", numList1.toString());
		assertEquals(0, numList1.capacity());
		
		//test the constructor that takes input
		NumArrayList numList2 = new NumArrayList(10);
		assertEquals("", numList2.toString());
		assertEquals(10, numList2.capacity());
	}

	@Test
	public void testInsert() {
		//TEST 0
		NumArrayList numList1 = new NumArrayList();
		assertEquals(0, numList1.capacity());
		//can it add a space to the list when there is none
		numList1.insert(0, 1);
		assertEquals(1, numList1.size());
		assertEquals(1, numList1.capacity());
//__________________
		
		//TEST 1
		NumArrayList numList2 = new NumArrayList(1);
		numList2.insert(1, 1);
		//can it add space to the list when there is none (and the list already has a size greater than 0)
		assertEquals("1.0,", numList2.toString());
		assertEquals(1, numList2.size());
		assertEquals(1, numList2.capacity());
		
		//can shove over the values properly when there is not enough space in the list it add space to the list when there is none AND shove the values over properly
		numList2.insert(0, 2);
		assertEquals("2.0,1.0,", numList2.toString());
		assertEquals(2, numList2.size());
		assertEquals(2, numList2.capacity());
		
//__________________
		
		//TEST MANY
		NumArrayList numList3 = new NumArrayList(3);
		//test first, not specified
		numList3.insert(1, 3);
		assertEquals(3.0, numList3.lookup(0), 0);
		assertEquals(1, numList3.size());

		try {
			assertEquals(0, numList3.lookup(1), 0);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Congrats,  your code works!");
		}

		try {
			assertEquals(0, numList3.lookup(2), 0);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Congrats,  your code works!");
		}
		//test first, specified
		numList3.insert(0, 2);
		assertEquals("2.0,3.0,", numList3.toString());
		assertEquals(2, numList3.size());
		
		//test middle, space available
		numList3.insert(1, 1);
		assertEquals("2.0,1.0,3.0,", numList3.toString());
		assertEquals(3, numList3.size());
		
		//test middle, space unavailable
		numList3.insert(1, 4);
		assertEquals("2.0,4.0,1.0,3.0,", numList3.toString());
		
		//test middle, space available (take 2)
		NumArrayList numList4 = new NumArrayList(4);
		numList4.add(0);
		numList4.add(1);
		numList4.add(2);
		numList4.insert(1, 3);
		assertEquals("0.0,3.0,1.0,2.0,", numList4.toString());
		assertEquals(4, numList4.size());
		
		//test last, space available
		numList4.remove(3);
		numList4.insert(3, 4.0);
		assertEquals("0.0,3.0,1.0,4.0,", numList4.toString());
		assertEquals(4, numList4.size());
		
		//test last, space unavailable
		numList4.insert(10, 5.0);
		assertEquals("0.0,3.0,1.0,4.0,5.0,", numList4.toString());
		assertEquals(5, numList4.size());
		
	}

	@Test
	public void testAdd() {
		
		//test 0
		NumArrayList numList1 = new NumArrayList();
		numList1.add(1);
		assertEquals("1.0,", numList1.toString());
		assertEquals(1, numList1.size());
		assertEquals(1, numList1.capacity());
		
		
		//test 1
		NumArrayList numList2 = new NumArrayList(1);
		numList2.add(0);
		assertEquals("0.0,", numList2.toString());
		
		//test many
		NumArrayList numList3 = new NumArrayList(3);
		assertEquals(0, numList3.size());
		numList3.add(0);
		assertEquals(1, numList3.size());
		numList3.add(1);
		assertEquals(2, numList3.size());
		numList3.add(2);
		assertEquals(3, numList3.size());
		numList3.add(3);
		
		assertEquals("0.0,1.0,2.0,3.0,", numList3.toString());
		assertEquals(4, numList3.capacity());
		assertEquals(4, numList3.size());
		
	}
	
	@Test
	public void testRemove() {
		//test 0
		NumArrayList numList1 = new NumArrayList();
		numList1.remove(0);
		numList1.remove(1);
		assertEquals("", numList1.toString());
		
		//test 1
		NumArrayList numList2 = new NumArrayList(1);
		numList2.remove(0);
		assertEquals("", numList2.toString());
		numList2.add(0);
		numList2.remove(1);
		assertEquals("0.0,", numList2.toString());
		numList2.remove(0);
		assertEquals("", numList2.toString());
		
		//test many
		NumArrayList numList3 = new NumArrayList(3);
		numList3.add(0);
		numList3.add(1.4);
		numList3.add(2.4);
		
		//test middle
		numList3.remove(1);
		assertEquals(2.4, numList3.lookup(1), 0);
		assertEquals("0.0,2.4,", numList3.toString());
		
		//test first
		numList3.remove(0);
		assertEquals("2.4,", numList3.toString());
		
		//test last
		numList3.add(3.4);
		numList3.add(4.4);
		numList3.remove(2);
		assertEquals("2.4,3.4,", numList3.toString());
		assertEquals(2.4, numList3.lookup(0), 0);
		assertEquals(3.4, numList3.lookup(1), 0);
		
	}
	
	@Test
	public void testContains() {
		//TEST 0
			
		NumArrayList numList1 = new NumArrayList();
		assertEquals(false, numList1.contains(0));
		
		//TEST 1
		NumArrayList numList2 = new NumArrayList(1);
		assertEquals(false, numList2.contains(0));
		numList2.add(0);
		assertEquals(true, numList2.contains(0));
		
		//TEST MANY
		NumArrayList numList3 = new NumArrayList(3);
		assertEquals(false, numList3.contains(0));
		
		numList3.add(1);
		numList3.add(2);
		numList3.add(3);
		//test first
		assertEquals(true, numList3.contains(1));
		//test middle
		assertEquals(true, numList3.contains(2));
		//test last
		assertEquals(true, numList3.contains(3));
		
	}
	
	@Test
	public void testLookup() {
		//test 0
		NumArrayList numList1 = new NumArrayList();
		try {
			numList1.lookup(0);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Test 0 of lookup() successful");
		}
		
		//test 1
		NumArrayList numList2 = new NumArrayList(1);
		try {
			numList2.lookup(0);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Test 1 of lookup() successful");
		}
		
		numList2.add(1);
		assertEquals(1, numList2.lookup(0), 0);
		
		//test many
		
		NumArrayList numList3 = new NumArrayList(3);
		numList3.add(1);
		numList3.add(2);
		numList3.add(3);
		
		//test first
		assertEquals(1, numList3.lookup(0), 0);
		//test middle
		assertEquals(2, numList3.lookup(1), 0);
		//test last
		assertEquals(3, numList3.lookup(2), 0);
		
		try {
			numList2.lookup(3);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Test many of lookup() successful");
		}
	}
	
	
	@Test 
	public void testToString() {
		
		NumArrayList numArrayList = new NumArrayList(3);
		assertEquals("", numArrayList.toString());
		
		numArrayList.add(1);
		assertEquals("1.0,", numArrayList.toString());
		numArrayList.add(2);
		assertEquals("1.0,2.0,", numArrayList.toString());
		numArrayList.add(3);
		assertEquals("1.0,2.0,3.0,", numArrayList.toString());
	}
	
	@Test
	public void testEquals() {
		Object numList0 = new Object();
		
		NumArrayList numList1 = new NumArrayList(3);
		numList1.add(0);
		numList1.add(0);
		numList1.add(0);
		
		NumArrayList numList2 = new NumArrayList(3);
		numList2.add(0);
		numList2.add(0);
		numList2.add(0);
		
		
		NumArrayList numList3 = new NumArrayList(5);
		numList3.add(0);
		numList3.add(0);
		numList3.add(0);
		
		assertEquals(false,numList1.equals(numList0));
		assertEquals(true,numList1.equals(numList2));
		assertEquals(true,numList1.equals(numList3));
		
		numList2 = new NumArrayList(3);
		numList2.add(0);
		numList2.add(0);
		numList2.add(1);
		
		assertEquals(false ,numList1.equals(numList2));
		
		
		numList3.add(0);
		assertEquals(false, numList1.equals(numList3));
		
	}
	
	@Test
	public void testRemoveDuplicates() {
		//test 0
		NumArrayList numList0 = new NumArrayList();
		numList0.removeDuplicates();
		//for this one we know it works if the code compiles
		
		//test 1
		NumArrayList numList2 = new NumArrayList(1);
		numList2.add(1);
		numList2.removeDuplicates();
		assertEquals("1.0,", numList2.toString());
		assertEquals(1, numList2.size());
		
		//test many
		NumArrayList numList1 = new NumArrayList(5);
		numList1.add(1);
		numList1.add(2);
		numList1.add(2);
		numList1.add(3);
		numList1.add(3);
		
		numList1.removeDuplicates();
		
		assertEquals("1.0,2.0,3.0,", numList1.toString());
		assertEquals(3, numList1.size());
		
		numList1 = new NumArrayList(5);
		numList1.add(1);
		numList1.add(2);
		numList1.add(1);
		numList1.add(2);
		numList1.add(1);
		
		numList1.removeDuplicates();
		
		assertEquals("1.0,2.0,", numList1.toString());
		assertEquals(2, numList1.size());
		
		numList1 = new NumArrayList(5);
		numList1.add(1);
		numList1.add(2);
		numList1.add(3);
		numList1.add(4);
		numList1.add(5);
		assertEquals("1.0,2.0,3.0,4.0,5.0,", numList1.toString());
		assertEquals(5, numList1.size());
		
	}
	
	@Test
	public void testReverse() {
		
		NumArrayList numList0 = new NumArrayList();
		numList0.reverse();
		assertEquals("", numList0.toString());
		
		numList0.add(1);
		numList0.reverse();
		assertEquals("1.0,", numList0.toString());
		
		numList0.add(2);
		numList0.add(3);
		numList0.add(4);
		numList0.reverse();
		assertEquals("4.0,3.0,2.0,1.0,", numList0.toString());
		numList0.add(0);
		
		numList0.reverse();
		assertEquals("0.0,1.0,2.0,3.0,4.0,", numList0.toString());
		
	}
	

}
