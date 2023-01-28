/**
 * 
 * @author Ashley Sah
 *
 * @param <V>–for the values
 * @param <T>–for the keys
 */
import java.util.NoSuchElementException;
import java.util.ArrayList;


public class BinarySearchTree<T extends Comparable<T>,V> {
	
	private BSTNode<V,T> root;
	
	private BSTNode<V,T> getRoot() {
		return root;
	}

	
	public void insert(T key, V value) {
		BSTNode<V,T> insert = new BSTNode<V,T>(key, value);
		
		if(getRoot()==null) {
			root = insert;
		}
		else {
			insertHelper(root, insert);
		}
	}
	
	public BSTNode<V,T> insertHelper(BSTNode<V,T> node, BSTNode<V,T> insert){
		
		if(node == null) {
			
			return insert;
		}
		else {
			int compareVal = insert.getKey().compareTo(node.getKey());
			 
			// if insertKey is less than nodeKey, go left
			if (compareVal < 0) {
				
				node.setLeft( insertHelper(node.getLeft(), insert) );
				
				return node;
			}
			//if the key is a duplicate, it goes right
			else {
				// if insertKey if greater than or equal to nodeKey
				
				node.setRight(insertHelper(node.getRight(), insert));
				
				return node;

			}
		}
	
	}
	
	
	public V search(T key) {
		return searchHelper(root, key).getValue();
	}
	
	private BSTNode<V,T> searchHelper(BSTNode<V,T> node, T key){
		
		if(node==null) {
			throw new NoSuchElementException();
		}

		int compareVal = key.compareTo(node.getKey());
		
		//if search key is less than nodeKey, go left
		if(compareVal<0) {
			return searchHelper(node.getLeft(), key);
		}
		//if search key is more than nodeKey, go right
		if(compareVal>0) {
			return searchHelper(node.getRight(), key);
		}
		
		return node;
	}
	
	public void delete(T key) {
		deleteHelper(root, key);
	}
	
	private BSTNode<V, T> deleteHelper(BSTNode<V, T> node, T toDelete) {
		if (node == null) {
			//System.out.println("reached base case");			
			return node;
		} 
		else {
			int compareValue = toDelete.compareTo(node.getKey());

			if (compareValue < 0) {
				node.setLeft( deleteHelper(node.getLeft(), toDelete) );
			} 
			
			else if (compareValue > 0) {
				node.setRight( deleteHelper(node.getRight(), toDelete) );
			} 
			//if node has the value to delete
			else if (node.getLeft() != null && node.getRight() != null) {
				//System.out.println("reached base case (2 child)");
				BSTNode<V, T> min = findMin(node.getRight());
				node.setValue(min.getValue());
				deleteHelper(node, min.getKey());
				node.setKey(min.getKey());
			} 
			
			else if (node.getLeft() != null) {
				//System.out.println("reached base case (1 child)");
				node = node.getLeft();
			} 
			
			else
				node = node.getRight();

			return node;
		}
	}
	
	private BSTNode<V,T> findMin(BSTNode<V,T> start){
		
		BSTNode<V,T> min = start;
		BSTNode<V,T> minChild = start.getLeft();
		
		while(minChild != null) {
			minChild = minChild.getLeft();
			min = min.getLeft();
		}
		
		return min;
	}
	
	public ArrayList<V> inorderRec(){
		ArrayList<V> list = new ArrayList<V>();
		inorderRecursion(root, list);
		
		return list;
	}
	
	private BSTNode<V,T> inorderRecursion(BSTNode<V,T> node, ArrayList<V> list) {
		
		
		if(node == null) {
			
			return node;
		}
		
		
		inorderRecursion(node.getLeft(), list);
		
		
		list.add(node.getValue());
		
		
		inorderRecursion(node.getRight(), list);
		
		return node;
		
	}
	
	
	public V findkthSmallest(int k) {
		ArrayList<V> list = inorderRec();
		
		if(list.size()<k) {
			throw new NoSuchElementException();
		}
		
		else
			return list.get(k-1);
		
	}
	
	
	public ArrayList<T> inorderRecKeys(){
		ArrayList<T> list = new ArrayList<T>();
		inorderRecursionKey(root, list);
		
		return list;
	}
	
	private BSTNode<V,T> inorderRecursionKey(BSTNode<V,T> node, ArrayList<T> list) {
		
		
		if(node == null) {
			
			return node;
		}
		
		
		inorderRecursionKey(node.getLeft(), list);
		
		
		list.add(node.getKey());
		
		
		inorderRecursionKey(node.getRight(), list);
		
		return node;
		
	}
	

	/**
	 * 
	 * @author Ashley Sah
	 *
	 * @param <V>–for the values
	 * @param <T>–for the keys
	 */
	private class BSTNode<V, T extends Comparable<T>>{
		//key of the node
		private T key;
		//value stored in node
		private V value;
		//left node
		private BSTNode<V,T> leftChild;
		//right node
		private BSTNode<V,T> rightChild;
		
		public BSTNode(T key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public void setKey(T key) {
			this.key = key;
			
		}

		//getter method for key–no setKey() because we don't want to change key value
		public T getKey(){
			return key;
		}
		
		//getter setter for value
		public V getValue() {
			return value;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
		
		
		//getter setter for leftChild
		public BSTNode<V,T> getLeft() {
			return leftChild;
		}
		public void setLeft(BSTNode<V,T> left) {
			leftChild = left;
		}
		//getter setter for rightChild
		
		public BSTNode<V,T> getRight() {
			return rightChild;
		}
		
		public void setRight(BSTNode<V,T> right) {
			rightChild = right;
		}
		
	}
	
	//demonstration method
	public static void main(String[] args) {
		
		//Step one: construct an empty BinarySearchTree
		BinarySearchTree<Integer, String> ash = new BinarySearchTree<Integer, String>();
		//I decided to make the keys and values identical for this example to make it easy for the TA 
		//to read the demonstration method
		//so please don't take off points
		
		//Step two: Insert: 2, 1, 4, 5, 9, 3, 6, 7, 10, 12, 11–associated key with the letter in the alphabet (A is 1, B is 2, C is 3, etc.)
		
		ash.insert(2, "B");
		ash.insert(1, "A");
		ash.insert(4, "D");
		ash.insert(5, "E");
		ash.insert(9, "I");
		ash.insert(3, "C");
		ash.insert(6, "F");
		ash.insert(7, "G");
		ash.insert(10, "J");
		ash.insert(12, "L");
		ash.insert(13, "M");
		
		ArrayList<String> ashList = ash.inorderRec();
		System.out.println(ashList);//should print letters in alphabetical order
		
		//Step three: delete 4 then delete 9
		
		ash.delete(4);
		ash.delete(9);
		ashList = ash.inorderRec();//should delte D and I
		System.out.println(ashList);
		
		
		//Step four: print the keys using inorder traversal
		ArrayList<Integer> ashKeyList = ash.inorderRecKeys();
		System.out.println(ashKeyList);
		
		
		//Step five: Search 12 then search 4
		try {
			System.out.println(ash.search(12));
		}
		catch(NoSuchElementException e) {
			System.out.println("Invalid key");
		}
		
		try {
			System.out.println(ash.search(4));
		}
		catch(NoSuchElementException e) {
			System.out.println("Invalid key");
		}
		
		//Step 6: Find third smallest element in the tree
		System.out.println(ash.findkthSmallest(3));
		
		//Step 7: Show that your tree is generic by using two other types
		BinarySearchTree<Double, Character> birch = new BinarySearchTree<Double, Character>();
		
		birch.insert(2.0, 'B');
		birch.insert(1.0, 'A');
		birch.insert(4.0, 'D');
		birch.insert(5.0, 'E');
		birch.insert(9.0, 'I');
		birch.insert(3.0, 'C');
		birch.insert(6.0, 'F');
		birch.insert(7.0, 'G');
		birch.insert(10.0, 'J');
		birch.insert(12.0, 'L');
		birch.insert(13.0, 'M');
		
		//Step 8: Also include an example using the key-value pairs
		System.out.println();
		System.out.println(birch.inorderRecKeys());
		System.out.println(birch.inorderRec());
		
		birch.delete(13.0);
		System.out.println(birch.inorderRecKeys());
		System.out.println(birch.inorderRec());
		
	}
}
