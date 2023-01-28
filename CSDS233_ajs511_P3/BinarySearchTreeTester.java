import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Test;

public class BinarySearchTreeTester {

	@Test
	public void test() {
		BinarySearchTree<String, Integer> ash = new BinarySearchTree<String, Integer>();
	}
	
	@Test
	public void testInsert() {
		BinarySearchTree<Double, String> ash = new BinarySearchTree<Double, String>();
		
		//Test 0
		ArrayList<String> list = ash.inorderRec(); 
		try {
			list.get(0);
		}
		catch(Exception e) {
			System.out.println("Test 0 of insert() successful");
		}
		//Test 1
		ash.insert(1.0, "A");
		
		list = ash.inorderRec(); 
		assertEquals("A", list.get(0));
		try {
			list.get(1);
		}
		catch(Exception e) {
			System.out.println("Test 1 of insert() successful");
		}
		
		//Test many
		ash.insert(2.0, "S");
		ash.insert(2.0, "Y");
		ash.insert(0.0, "H");
		ash.insert(-.5, "E");
		ash.insert(1.5, "L");
		
		list = ash.inorderRec();
		
		assertEquals("E", list.get(0));
		assertEquals("H", list.get(1));
		assertEquals("A", list.get(2));
		assertEquals("L", list.get(3));
		assertEquals("S", list.get(4));
		assertEquals("Y", list.get(5));
		
		BinarySearchTree<Integer, String> tree= new BinarySearchTree<Integer, String>();
		tree.insert(5, "5");
	
	}
	
	@Test
	public void testSearch() {
		BinarySearchTree<Double, String> birch = new BinarySearchTree<Double, String>();
		//test 0
		try {
		birch.search(0.0);
		}
		catch(NoSuchElementException e) {
			System.out.println("test 0 of search() succesful");
		}
		
		//test many
		birch.insert(1.0, "A");
		birch.insert(2.0, "S");
		birch.insert(0.0, "H");
		birch.insert(1.5, "L");
		
		//test 'first' (root)
		assertEquals("A", birch.search(1.0));
		//test 'middle' (not root or leaf)
		assertEquals("S", birch.search(2.0));
		assertEquals("H", birch.search(0.0));
		//test last (leaf)
		assertEquals("L", birch.search(1.5));	
		
		try{
			birch.search(.33);
		}
		catch(NoSuchElementException e) {
			System.out.println("Test search() sucessful");
		}
		
	}
	
	@Test
	public void testDelete() {
		BinarySearchTree<Double, String> maple = new BinarySearchTree<Double, String>();
		//test 0
		maple.delete(-.5);
		
		//test many
		maple.insert(1.0, "A");
		maple.insert(2.0, "S");
		maple.insert(0.0, "H");
		maple.insert(1.5, "L");
		maple.insert(0.5, "E");
		maple.insert(-.5, "Y");
		
		
		//test last (leaf)
		ArrayList<String> list = maple.inorderRec();
		assertEquals("Y", list.get(0));
		maple.delete(-.5);
		
		list = maple.inorderRec();
		assertEquals("H", list.get(0));
		
		//inserts Y back in same location
		maple.insert(-.5, "Y");
		
		//test leaf (remove E)
		list = maple.inorderRec();
		assertEquals("E", list.get(2));
		
		maple.delete(0.5);
		list = maple.inorderRec();
		assertEquals("A", list.get(2));
		
		//inserts E back to original location
		maple.insert(.5, "E");
		
		//test middle (not leaf or root) w/ 2 children
		assertEquals("H", list.get(1));
		maple.delete(0.0);
		list = maple.inorderRec();
		assertEquals("E", list.get(1));
		assertEquals("Y", list.get(0));
		assertEquals("A", list.get(2));
		
		//test for when key is invalid
		maple.delete(-.25);
		assertEquals("E", list.get(1));
		assertEquals("Y", list.get(0));
		assertEquals("A", list.get(2));
		assertEquals("L", list.get(3));
		assertEquals("S", list.get(4));
		
		maple = new BinarySearchTree<Double, String>();
		
		maple.insert(1.0, "A");
		maple.insert(2.0, "S");
		maple.insert(0.0, "H");
		maple.insert(1.5, "L");
		maple.insert(0.5, "E");
		maple.insert(-.5, "Y");
		
		//test root 
		maple.delete(1.0);//if this works, S will become the root, L will become right of root
		list = maple.inorderRec();
		
		assertEquals("Y", list.get(0));
		assertEquals("H", list.get(1));
		assertEquals("E", list.get(2));
		assertEquals("L", list.get(3));
		assertEquals("S", list.get(4));
		
	}
	
	@Test
	public void testInOrderRec() {
		BinarySearchTree<Double, String> acorn = new BinarySearchTree<Double, String>();
		
		//Test 0
		ArrayList<String> acornList = acorn.inorderRec();
		try {
			acornList.get(0);
		}
		catch(Exception e) {
			System.out.println("Test 0 of inorderRec() is sucessful");
		}
		
		//Test many
		acorn.insert(1.0, "A");
		acorn.insert(2.0, "S");
		acorn.insert(0.0, "H");
		acorn.insert(1.5, "L");
		acorn.insert(0.5, "E");
		acorn.insert(-.5, "Y");
		
		acornList = acorn.inorderRec();
		assertEquals("Y", acornList.get(0));
		assertEquals("H", acornList.get(1));
		assertEquals("E", acornList.get(2));
		assertEquals("A", acornList.get(3));
		assertEquals("L", acornList.get(4));
		assertEquals("S", acornList.get(5));
		
		try {
			acornList.get(6);
		}
		catch(Exception e) {
			System.out.println("Test many of inorderRec() is sucessful");
		}
		
	}
	
	@Test 
	public void testKthSmallest() {
		BinarySearchTree<Double, String> evergreen = new BinarySearchTree<Double, String>();
		
		//Test 0
		try {
			evergreen.findkthSmallest(1);
		}
		catch(NoSuchElementException e) {
			System.out.println("Test 0 of findkthSmallest() succesful");
		}
		
		//Test many
		
		evergreen.insert(1.0, "A");
		evergreen.insert(2.0, "S");
		evergreen.insert(0.0, "H");
		evergreen.insert(1.5, "L");
		evergreen.insert(0.5, "E");
		evergreen.insert(-.5, "Y");
		
		
		assertEquals("Y", evergreen.findkthSmallest(1));
		assertEquals("H", evergreen.findkthSmallest(2));
		assertEquals("E", evergreen.findkthSmallest(3));
		assertEquals("A", evergreen.findkthSmallest(4));
		assertEquals("L", evergreen.findkthSmallest(5));
		assertEquals("S", evergreen.findkthSmallest(6));
		
		try {
			evergreen.findkthSmallest(7);
		}
		catch(NoSuchElementException e) {
			System.out.println("Test many of findkthSmallest() succesful");
		}
		
		
	}
	

}
