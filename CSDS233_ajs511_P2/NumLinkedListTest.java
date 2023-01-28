import static org.junit.Assert.*;

import org.junit.Test;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class NumLinkedListTest {

	@Test
	public void testConstructorGetFrontGetBack() {
		NumLinkedList list1 = new NumLinkedList();
		System.out.println(list1);
	}
	
	
	@Test
	public void testIsEmpty() {
		NumLinkedList list1 = new NumLinkedList();
		assertEquals(true, list1.isEmpty());
		list1.add(1);
		assertEquals(false, list1.isEmpty());
	}
	
	@Test
	public void testToString() {
		NumLinkedList list1 = new NumLinkedList();
		//test 0
		assertEquals("", list1.toString());
		list1.add(1);
		
		//test 1
		assertEquals("1.0,", list1.toString());
		list1.add(2);
		list1.add(3);
		
		//test many
		assertEquals("1.0,2.0,3.0,", list1.toString());
	}
	
	@Test
	public void testAdd() {
		//test 0
		NumLinkedList list1 = new NumLinkedList();
		assertEquals("", list1.toString());
		assertEquals(0, list1.size());
		list1.add(1);
		
		//test 1
		assertEquals("1.0,", list1.toString());
		assertEquals(1, list1.size());
		
		//test many
		list1.add(2);
		list1.add(3);
		assertEquals("1.0,2.0,3.0,", list1.toString());
		assertEquals(3, list1.size());	
	}
	
	@Test
	public void testInsert() {
		NumLinkedList list1 = new NumLinkedList();
		
		//test 0
		list1.insert(2, 1);
		assertEquals("1.0,", list1.toString());
		assertEquals(1, list1.size());	
		
		//test first
		list1.insert(0, 5);
		assertEquals("5.0,1.0,", list1.toString());
		assertEquals(2, list1.size());	
		
		//test last
		list1.insert(-2, 5);
		assertEquals("5.0,1.0,5.0,", list1.toString());
		assertEquals(3, list1.size());	
		
		//test middle
		list1.insert(2, 6);
		assertEquals("5.0,1.0,6.0,5.0,", list1.toString());
		assertEquals(4, list1.size());	
	}
	
	@Test
	public void testRemove() {
		NumLinkedList list1 = new NumLinkedList();
		//Test 0–if no exceptions raised it passes
		list1.remove(0);
		
		//Test 1
		list1.add(0);
		list1.remove(0);
		assertEquals("", list1.toString());
		list1.add(0);
		list1.remove(-1);
		assertEquals("0.0,", list1.toString());
		list1.remove(0);
		
		//Test many
		list1.add(0);
		list1.add(1);
		list1.add(2);
		
		list1.remove(1);
		assertEquals("0.0,2.0,", list1.toString());
		
	}
	
	@Test
	public void testContains() {
		//Test 0
		NumLinkedList list1 = new NumLinkedList();
		assertEquals(false, list1.contains(0));
		//Test 1
		
		list1.add(0);
		assertEquals(true, list1.contains(0));
		assertEquals(false, list1.contains(1));
		
		//Test many
		list1.add(1);
		list1.add(2);
		assertEquals(true, list1.contains(0));
		assertEquals(true, list1.contains(1));
		assertEquals(true, list1.contains(2));
		assertEquals(false, list1.contains(3));
	}
	
	@Test
	public void testLookup() {
		
		NumLinkedList list1 = new NumLinkedList();
		//Test 0
		try {
			list1.lookup(0);
		}
		catch(NullPointerException e) {
			System.out.println("Test 0 of lookup successful");
		}
		//Test 1
		list1.add(1);
		try {
			list1.lookup(2);
		}
		catch(NullPointerException e) {
			System.out.println("Test 1 of lookup successful");
		}
		
		assertEquals(1.0, list1.lookup(0), 0);
		//Test many
		
		list1.add(2);
		list1.add(3);
		
		assertEquals(1.0, list1.lookup(0), 0);
		assertEquals(2.0, list1.lookup(1), 0);
		assertEquals(3.0, list1.lookup(2), 0);
	}
	
	@Test
	public void testRemoveDuplicates() {
		NumLinkedList list1 = new NumLinkedList();
		//Test 0–successful if no exceptions are rasied
		list1.removeDuplicates();
		//Test 1
		list1.add(1);
		list1.removeDuplicates();
		assertEquals("1.0,", list1.toString());
		//Test many
		list1.add(1);
		list1.add(2);
		list1.add(2);
		list1.add(3);
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.removeDuplicates();
		assertEquals("1.0,2.0,3.0,", list1.toString());
		
	}
	
	@Test
	public void testEquals() {
		NumLinkedList list1 = new NumLinkedList();
		NumLinkedList list2 = new NumLinkedList();
		String list3 = "1.0,2.0,3.0,";
		NumArrayList list4 = new NumArrayList();
		
		
		list1.add(1);
		list1.add(2);
		list1.add(3);
		//Test against an object that is not a NumList
		assertEquals(false, list1.equals(list3));
		
		//Test against an object that is a NumLinkedList
		assertEquals(false, list1.equals(list2));
		list2.add(1);
		list2.add(2);
		list2.add(3);
		assertEquals(true, list1.equals(list2));
		list2.add(4);
		assertEquals(false, list1.equals(list2));
		
		//Test against a different type of NumList
		assertEquals(false, list1.equals(list4));
		list4.add(1);
		list4.add(2);
		list4.add(3);
		assertEquals(true, list1.equals(list4));
		list4.add(4);
		assertEquals(false, list1.equals(list4));
	}
	
	@Test
	public void testIsSorted() {
		NumLinkedList list1 = new NumLinkedList();
		//Test 0
		assertEquals(true, list1.isSorted());
		
		//Test1
		list1.add(1);
		assertEquals(true, list1.isSorted());
		
		//Test many
		list1.add(2);
		list1.add(2.5);
		list1.add(3);
		list1.add(3);
		assertEquals(true, list1.isSorted());
		
		for(int i = 0; i<list1.size(); i++) {
			list1.remove(i);
		}
		
		list1.add(1);
		list1.add(0);
		list1.add(2);
		assertEquals(false, list1.isSorted());
		
	}
	
	@Test
	public void testReverse() {
		NumLinkedList list1 = new NumLinkedList();
		//Test 0–passes if no exceptions are raised
		list1.reverse();
		//Test 1–passes if the list is same either way
		list1.add(1);
		assertEquals("1.0,", list1.toString());
		//Test many
		list1.add(2);
		list1.add(3);
		list1.reverse();
		assertEquals("3.0,2.0,1.0,", list1.toString());
	}
	
	@Test
	public void testUnion() {
		NumLinkedList list1 = new NumLinkedList();
		NumLinkedList list2 = new NumLinkedList();
		
		//Test unsorted
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(-1);
		list1.add(-6);
		list1.add(-8);
		list1.add(4);
		
		list2.add(8);
		list2.add(9);
		list2.add(2);
		list2.add(-1);
		
		NumLinkedList union = NumLinkedList.union(list1, list2);
		//1, 2, 3, -1, -6, -8, 4, 8, 9
		
		assertEquals(1.0, union.lookup(0), 0);
		assertEquals(2.0, union.lookup(1), 0);
		assertEquals(3.0, union.lookup(2), 0);
		assertEquals(-1.0, union.lookup(3), 0);
		assertEquals(-6.0, union.lookup(4), 0);
		assertEquals(-8.0, union.lookup(5), 0);
		assertEquals(4.0, union.lookup(6), 0);
		assertEquals(8.0, union.lookup(7), 0);
		assertEquals(9.0, union.lookup(8), 0);
		
		try {
			union.lookup(9);
		}
		catch(NullPointerException e){
			System.out.println("Unsorted test of union successful");
			
		}
		
		//Test unsorted
		list1 = new NumLinkedList();
		list2 = new NumLinkedList();
		list1.add(1);
		list1.add(2);
		list1.add(2);
		list1.add(4);
		list1.add(4);
		
		list2.add(3);
		list2.add(3);
		list2.add(4);
		list2.add(4);
		list2.add(5);
		
		union = NumLinkedList.union(list1, list2);
		
		assertEquals(1.0, union.lookup(0), 0);
		assertEquals(2.0, union.lookup(1), 0);
		assertEquals(3.0, union.lookup(2), 0);
		assertEquals(4.0, union.lookup(3), 0);
		assertEquals(5.0, union.lookup(4), 0);
		
		try {
			union.lookup(5);
		}
		catch(NullPointerException e){
			System.out.println("Sorted test of union successful");
			
		}
		
		//Test 0
		list2 = new NumLinkedList();
		union = NumLinkedList.union(list1, list2);
		
		//assertEquals(1.0, union.lookup(0), 0);
		//assertEquals(2.0, union.lookup(1), 0);
		//assertEquals(3.0, union.lookup(2), 0);
		
		
		try {
			union.lookup(3);
		}
		catch(NullPointerException e){
			System.out.println("Test 0 of union successful");
			
		}
		
		//Test 1
		list2.add(2.5);
		union = NumLinkedList.union(list1, list2);
		
		//assertEquals(1.0, union.lookup(0), 0);
		//assertEquals(2.0, union.lookup(1), 0);
		//assertEquals(2.5, union.lookup(2), 0);
		//assertEquals(3.0, union.lookup(3), 0);
		
		try {
			union.lookup(4);
		}
		catch(NullPointerException e){
			System.out.println("Test 1 of union successful");
			
		}	
	}
	
	//testing method for Node methods and private NumLinkedList methods
	//figured out how to make everything public, just need to figure out how to make an instance of node
	//I think it's more complicated when the nested class constructor takes inputs and has a generic


}

/**
 * Testing checklist:
 * 	Classes I need to test:
 * 		Node
 * 		NumLinkedList
 * 		@Test
	public void testNodeAndPrivateNumLinkedListMethods() {
		NumLinkedList list1 = new NumLinkedList();
		
		Class cls = list1.getClass();
		Class nodeClass = cls.getDeclaredClasses()[0];
		
		
		Constructor nodeConstructor = nodeClass.getDeclaredConstructors()[0];
		nodeConstructor.setAccessible(true);
		
		Object[] arguments = {null, null, null};
		
		
	}
 */ 
