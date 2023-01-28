import static org.junit.Assert.*;

import org.junit.Test;

public class SortTester {

	@Test
	public void testQuickSort() {
		Sort sort = new Sort();
		int[] arr0 = new int[0];
		int[] arr1 = {(int)(Math.random()*5)};
		int[] arr100 =  sort.randomArray(100, 0, 100);
		
		sort.quickSort(arr0);
		assertEquals(0, arr0.length);
		sort.quickSort(arr1);
		assertEquals(1, arr1.length);
		sort.quickSort(arr100);
		
		for(int i = 1; i<arr100.length; i++) {
			assertEquals(true, arr100[i-1]<=arr100[i]);
		}
		
		int[] reverseArr = new int[100];
		for(int i = 0; i<reverseArr.length; i++) {
			reverseArr[i] = reverseArr.length-i;
		}
		
		sort.quickSort(reverseArr);
		for(int i = 1; i<arr100.length; i++) {
			assertEquals(true, reverseArr[i-1]<=reverseArr[i]);
		}
		
		int[] repeats = new int[10];
		for(int i=0; i<repeats.length; i++) {
			repeats[i] = 6;
		}
		
		sort.quickSort(repeats);
		for(int i = 1; i<repeats.length; i++) {
			assertEquals(true, repeats[i-1]<=repeats[i]);
		}
		
	}
	
	@Test
	public void testInsertionSort() {
		Sort sort = new Sort();
		int[] arr0 = new int[0];
		int[] arr1 = {(int)(Math.random()*5)};
		int[] arr100 =  sort.randomArray(100, 0, 100);
		
		sort.insertionSort(arr0);
		assertEquals(0, arr0.length);
		sort.insertionSort(arr1);
		assertEquals(1, arr1.length);
		sort.insertionSort(arr100);
		
		for(int i = 1; i<arr100.length; i++) {
			assertEquals(true, arr100[i-1]<=arr100[i]);
		}
		
		int[] reverseArr = new int[100];
		for(int i = 0; i<reverseArr.length; i++) {
			reverseArr[i] = reverseArr.length-i;
		}
		
		sort.insertionSort(reverseArr);
		for(int i = 1; i<arr100.length; i++) {
			assertEquals(true, reverseArr[i-1]<=reverseArr[i]);
		}
		
	}
	
	@Test
	public void testMergeSort() {
		Sort sort = new Sort();
		int[] arr0 = new int[0];
		int[] arr1 = {(int)(Math.random()*5)};
		int[] arr100 =  sort.randomArray(100, 0, 100);
		
		sort.mergeSort(arr0);
		assertEquals(0, arr0.length);
		sort.mergeSort(arr1);
		assertEquals(1, arr1.length);
		sort.mergeSort(arr100);
		
		
		for(int i = 1; i<arr100.length; i++) {
			assertEquals(true, arr100[i-1]<=arr100[i]);
		}
		
		int[] reverseArr = new int[100];
		for(int i = 0; i<reverseArr.length; i++) {
			reverseArr[i] = reverseArr.length-i;
		}
		
		
		sort.mergeSort(reverseArr);
		for(int i = 1; i<arr100.length; i++) {
			assertEquals(true, reverseArr[i-1]<=reverseArr[i]);
		}
		
		int[] repeats = new int[10];
		for(int i=0; i<repeats.length; i++) {
			repeats[i] = 6;
		}
		
		sort.mergeSort(repeats);
		for(int i = 1; i<repeats.length; i++) {
			assertEquals(true, repeats[i-1]<=repeats[i]);
		}
		
	}

}
