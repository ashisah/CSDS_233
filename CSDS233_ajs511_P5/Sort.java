
public class Sort {
	
	/**
	 * The idea of insertionSort is you look at each element and look at how far to the left it can be placed
	 * The first loop is going through and looking at each element
	 * The second loop is going through all the elements that come before that element and checking how far it can move the element back
	 * This method works because if you start with the first element, then first two, then first three, you can guarantee that the beginning part of the array is sorted
	 * @param arr
	 */
	void insertionSort(int[] arr) {
		
		//int j = the index of the element we are about to sort
		for(int j = 1; j<arr.length; j++) {
			//int k = the index of the elements we compare to element a[j];
			int k;
			//look at the number after k, if it is more than the number at k+1, swap the numbers at k and k+1
			//repeat until the number at k is less than the number at k+1 (keep in mind that on the first pass a[k+1] = a[j]
			for(k = j-1; k>=0 && arr[k+1]<arr[k]; k--) {
				int tmp = arr[k];
				arr[k] = arr[k+1];
				arr[k+1] = tmp;
				//probably not that efficient that I just keep swapping it, could probably just move the elements a[k] up and place the value at a[j] at a[k]
			}
		}
	}
	
	/**
	 * quickSort is a recursive algorithim, it takes 3 steps:
	 * 	-establish a pivot point
	 * 	-puts numbers smaller than pivot on left side, numbers larger than pivot on right side
	 *  -places pivot in appropriate spot
	 * 	-repeats the above three steps but on the left half of the array and right half of the array
	 * The idea behind quickSort makes sense:
	 * After sorting the numbers based on the pivot, you know that one part of the array has numbers smaller than a certain value
	 * and the other part of the array has numbers larger than a certain value
	 * If you keep repeating the process on the resulting 'sub-arrays'
	 * You eventually will get the smallest numbers on the left and largest numbers on the right
	 * @param arr
	 */
	
	void quickSort(int[] arr) {
		//calls helper method (all of quickSort implemented here)
		quickSortHelper(arr, 0, arr.length-1);
	}
	
	//because we take the low and high array values, we can avoid the space requirements needed in mergeSort
	/**
	 * @param arr
	 * @param low–the left-most index of the array we are partitioning
	 * @param high–the right-most index of the array we are partitioning
	 */
	private void quickSortHelper(int[] arr, int low, int high) {
		
		//Base case: if low is greater than or equal to high, we are only looking at a single element, so it should just return
		if(low >= high) {
			return;
		}
		
		//pick the pivot to be the last element in the array
		int pivot = arr[high];
		
		//set left pointer to be the low index
		int left = low;
		
		//set right pointer to be the high index
		int right = high;
		
		while(left<right) {
			//move left pointer up until it finds a number greater than or equal to the pivot (or surpasses the right pointer)
			while(arr[left]<pivot && left<right) {
				left++;
			}
			//move right pointer down until it finds a number smaller than the pivot (or surpasses the left pointer)
			while(arr[right]>=pivot && right>left) {
				right--;
			}
			
			//if (only if) the left<right, swap the elements 
			if(left<right) {
				swap(arr, left, right);
		    }
			//otherwise left>=right, do nothing here, it will exit the loop
		}
		//outside the loop left==right (the partition)
		
		
		//swap the pivot back to it's appropriate place (the number it would stop at would be greater than the pivot)
		swap(arr, high, right);
		
		//run quickSort on the left half of the array (which has elements less than the pivot)
		//the lefthalf of the array is from the lowest index up to (but not including) to the partition (left-1)
		quickSortHelper(arr, low, left-1);
		//run quickSort on right half of the array (which has elements equal to or greater than the pivot)
		//the rightHalf of the array is from after the parition (left+1)
		quickSortHelper(arr, left+1, high);
		
	}
	
	private void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}
	
	/**
	 * The idea of MergeSort is simple, we keep splitting the input array into two halves
	 * Once arr is split into array sizes of 1, we start the actual mergeSort
	 * As we peice back the arrays together, we put them in sorted order
	 * @param arr
	 */

	void mergeSort(int[] arr) {
		//stop recursion once arr is length of 0
		if(arr.length == 1 || arr.length ==0) {
			return;
		}
		//establishing the half way point
		int middle = arr.length/2;
		//allocating space for the left half of the array
		int[] leftArr = new int[middle];
		//allocatign space for the right half of the array
		int[] rightArr = new int[arr.length-middle];
		
		//add in values from left half of the array to the leftArray
		for(int i = 0; i<middle; i++) {
			leftArr[i] = arr[i];
			
		}
		
		//add in values from right half of the array to the rightArray
		for(int i = middle; i<arr.length; i++) {
			rightArr[i-middle] = arr[i];
		}
		
		//will continuously split the array until it gets to a size of 1 (base case)
		mergeSort(leftArr); //keep in mind with the recusive call it's leftArr that is the 'main' array now
		mergeSort(rightArr); //keep in mind with the recusive call it's leftArr that is the 'main' array now
		
		//once recursion for splitting the array is complete, start recrustively merging the arrays
		merge(arr, leftArr, rightArr);
		//when we get to the line above that means leftArr and rightArr as two sorted subArrays (becuase their halves were mergeSorted before we get to this line)
	}
	
	/**
	 * 
	 * @param arr
	 * @param leftArr–already sorted from previous iterations of merge()
	 * @param rightArr–already sorted from previous iterations of merge()
	 */
	private static void merge(int[] arr, int[] leftArr, int[] rightArr) {
		
		int leftSize = leftArr.length;
		int rightSize = rightArr.length;
		
		//index of leftHalf of the array
		int leftPos = 0;
		//index of the rightHalf of the array
		int rightPos = 0;
		//index of int[] arr (which will be sorted by the end of this algorithim
		int arrPos = 0;
		
		//Keep in mind that although we are overwriting the values in int[] arr, that leftArr and rightArr have all the same values
		
		//if the value we're looking at in the leftArray is less than the value we are looking at in the rightArray
		while(leftPos<leftSize && rightPos<rightSize) {
			//add it to the main array
			if(leftArr[leftPos]<rightArr[rightPos]) {
				arr[arrPos] = leftArr[leftPos];
				//increment the leftArray's index (so we're looking at the next element)
				leftPos++;
				//increment the mainArray's index (so we're looking at the next empty space)
				arrPos++;
			}
			//if the value we're looking at in the rightArray is less than or equal to the value we are looking at in the leftArray
			//add it to the main array
			else {
				arr[arrPos] = rightArr[rightPos];
				//increment the rightArray's index (so we're looking at the next element)
				rightPos++;
				//increment the mainArray's index (so we're looking at the next empty space)
				arrPos++;
			}
		}
		
		//if there are still elements in the leftArray, add them all in
		if (leftPos < leftSize) {
			while (leftPos < leftSize) {
				arr[arrPos++] = leftArr[leftPos++];
			}
		} else {
			//otherwise if there are still elements in the rightArray, add them all in
			while (rightPos < rightSize) {
				arr[arrPos++] = rightArr[rightPos++];
			}
		}
		
	}
	
	
	
	
	
	
	int[] randomArray(int n, int a, int b) {
		
		if(a>b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		int[] randArray = new int[n];
		
		for(int i = 0; i<n; i++) {
			randArray[i] = ((int)(Math.random()*(b-a+1)))+a;
		}
		
		return randArray;
	}
	
	public static void main(String[] args) {
		Sort sort = new Sort();
		//10, 20, 50, 100, 200, 500, 1000, 2000, and 5000
		long startTime;
		long endTime;
		
		//Initializing arrays
		int[] rand10Array = sort.randomArray(10, 0, 10);
		int[] rand20Array = sort.randomArray(20, 0, 20);
		int[] rand50Array = sort.randomArray(50, 0, 50);
		int[] rand100Array= sort.randomArray(100, 0, 100);
		int[] rand200Array= sort.randomArray(200, 0, 200);
		int[] rand500Array= sort.randomArray(500, 0, 500);
		int[] rand1000Array= sort.randomArray(1000, 0, 1000);
		int[] rand2000Array= sort.randomArray(2000, 0, 2000);
		int[] rand5000Array= sort.randomArray(5000, 0, 5000);
		
		//Initializing MergeSort times:
		long merge10time;
		long merge20time;
		long merge50time;
		long merge100time;
		long merge200time;
		long merge500time;
		long merge1000time;
		long merge2000time;
		long merge5000time;
		
		//Collecting mergeSort times
		startTime = System.nanoTime();
		sort.mergeSort(rand10Array);
		endTime = System.nanoTime();
		merge10time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.mergeSort(rand20Array);
		endTime = System.nanoTime();
		merge20time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.mergeSort(rand50Array);
		endTime = System.nanoTime();
		merge50time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.mergeSort(rand100Array);
		endTime = System.nanoTime();
		merge100time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.mergeSort(rand200Array);
		endTime = System.nanoTime();
		merge200time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.mergeSort(rand500Array);
		endTime = System.nanoTime();
		merge500time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.mergeSort(rand1000Array);
		endTime = System.nanoTime();
		merge1000time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.mergeSort(rand2000Array);
		endTime = System.nanoTime();
		merge2000time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.mergeSort(rand5000Array);
		endTime = System.nanoTime();
		merge5000time = endTime-startTime;
		
		//Initializing arrays
		rand10Array = sort.randomArray(10, 0, 10);
		rand20Array = sort.randomArray(20, 0, 20);
		rand50Array = sort.randomArray(50, 0, 50);
		rand100Array= sort.randomArray(100, 0, 100);
		rand200Array= sort.randomArray(200, 0, 200);
		rand500Array= sort.randomArray(500, 0, 500);
		rand1000Array= sort.randomArray(1000, 0, 1000);
		rand2000Array= sort.randomArray(2000, 0, 2000);
		rand5000Array= sort.randomArray(5000, 0, 5000);
		
		//Initializing QuickSort times
		long quick10time;
		long quick20time;
		long quick50time;
		long quick100time;
		long quick200time;
		long quick500time;
		long quick1000time;
		long quick2000time;
		long quick5000time;
		
		//Collecting quickSort times
		startTime = System.nanoTime();
		sort.quickSort(rand10Array);
		endTime = System.nanoTime();
		quick10time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.quickSort(rand20Array);
		endTime = System.nanoTime();
		quick20time = endTime-startTime;

		startTime = System.nanoTime();
		sort.quickSort(rand50Array);
		endTime = System.nanoTime();
		quick50time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.quickSort(rand100Array);
		endTime = System.nanoTime();
		quick100time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.quickSort(rand200Array);
		endTime = System.nanoTime();
		quick200time = endTime-startTime;

		startTime = System.nanoTime();
		sort.quickSort(rand500Array);
		endTime = System.nanoTime();
		quick500time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.quickSort(rand1000Array);
		endTime = System.nanoTime();
		quick1000time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.quickSort(rand2000Array);
		endTime = System.nanoTime();
		quick2000time = endTime-startTime;

		startTime = System.nanoTime();
		sort.quickSort(rand5000Array);
		endTime = System.nanoTime();
		quick5000time = endTime-startTime;
		
		//Initializing arrays
		rand10Array = sort.randomArray(10, 0, 10);
		rand20Array = sort.randomArray(20, 0, 20);
		rand50Array = sort.randomArray(50, 0, 50);
		rand100Array= sort.randomArray(100, 0, 100);
		rand200Array= sort.randomArray(200, 0, 200);
		rand500Array= sort.randomArray(500, 0, 500);
		rand1000Array= sort.randomArray(1000, 0, 1000);
		rand2000Array= sort.randomArray(2000, 0, 2000);
		rand5000Array= sort.randomArray(5000, 0, 5000);
		
		//Initializing InsertionSort times
		long insert10time;
		long insert20time;
		long insert50time;
		long insert100time;
		long insert200time;
		long insert500time;
		long insert1000time;
		long insert2000time;
		long insert5000time;
		
		//Collecting insertionSort times
		startTime = System.nanoTime();
		sort.insertionSort(rand10Array);
		endTime = System.nanoTime();
		insert10time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.insertionSort(rand20Array);
		endTime = System.nanoTime();
		insert20time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.insertionSort(rand50Array);
		endTime = System.nanoTime();
		insert50time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.insertionSort(rand100Array);
		endTime = System.nanoTime();
		insert100time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.insertionSort(rand200Array);
		endTime = System.nanoTime();
		insert200time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.insertionSort(rand500Array);
		endTime = System.nanoTime();
		insert500time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.insertionSort(rand1000Array);
		endTime = System.nanoTime();
		insert1000time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.insertionSort(rand2000Array);
		endTime = System.nanoTime();
		insert2000time = endTime-startTime;
		
		startTime = System.nanoTime();
		sort.insertionSort(rand5000Array);
		endTime = System.nanoTime();
		insert5000time = endTime-startTime;
		
		//Printing out results
		System.out.println("merge10time: " + merge10time);
		System.out.println("merge20time: " + merge20time);
		System.out.println("merge50time: " + merge50time);
		System.out.println("merge100time: " + merge100time);
		System.out.println("merge200time: " + merge200time);
		System.out.println("merge500time: " + merge500time);
		System.out.println("merge1000time: " + merge1000time);
		System.out.println("merge2000time: " + merge2000time);
		System.out.println("merge5000time: " + merge5000time);
		
		System.out.println();
		
		System.out.println(merge10time);
		System.out.println(merge20time);
		System.out.println(merge50time);
		System.out.println(merge100time);
		System.out.println(merge200time);
		System.out.println(merge500time);
		System.out.println(merge1000time);
		System.out.println(merge2000time);
		System.out.println(merge5000time);
		
		System.out.println();
		
		System.out.println("quick10time: " + quick10time);
		System.out.println("quick20time: " + quick20time);
		System.out.println("quick50time: " + quick20time);
		System.out.println("quick100time: " + quick100time);
		System.out.println("quick200time: " + quick200time);
		System.out.println("quick500time: " + quick500time);
		System.out.println("quick1000time: " + quick1000time);
		System.out.println("quick2000time: " + quick2000time);
		System.out.println("quick5000time: " + quick5000time);
		
		System.out.println();
		
		System.out.println(quick10time);
		System.out.println(quick20time);
		System.out.println(quick50time);
		System.out.println(quick100time);
		System.out.println(quick200time);
		System.out.println(quick500time);
		System.out.println(quick1000time);
		System.out.println(quick2000time);
		System.out.println(quick5000time);
		
		System.out.println();
		
		System.out.println("insert10time: " + insert10time);
		System.out.println("insert20time: " + insert20time);
		System.out.println("insert50time: " + insert50time);
		System.out.println("insert100time: " + insert100time);
		System.out.println("insert200time: " + insert200time);
		System.out.println("insert500time: " + insert500time);
		System.out.println("insert1000time: " + insert1000time);
		System.out.println("insert2000time: " + insert2000time);
		System.out.println("insert5000time: " + insert5000time);
		
		System.out.println();
		
		System.out.println(insert10time);
		System.out.println(insert20time);
		System.out.println(insert50time);
		System.out.println(insert100time);
		System.out.println(insert200time);
		System.out.println(insert500time);
		System.out.println(insert1000time);
		System.out.println(insert2000time);
		System.out.println(insert5000time);
		
	}
}
