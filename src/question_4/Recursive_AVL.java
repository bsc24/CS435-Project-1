package question_4;

public class Recursive_AVL {
	
	private Node root;
	private int size;
	
	// Constructors
	public Recursive_AVL() {
		root = null;
	}
	
	
	// Other methods
	public Node getRoot() {		// Gets the root, NOT the root's value
		return root;
	}
	
	
	public int getSize() {
		return size;
	}
	
	
	public boolean has(float number) {
		return hasHelper(number, root);
	}
	
	private boolean hasHelper(float number, Node current) {
		if (current == null)
			return false;
		else if (number < current.value)
			return hasHelper(number, current.leftChild);
		else if (number > current.value)
			return hasHelper(number, current.rightChild);
		else
			return true;
	}
	
	
	private int height(Node current) {
		
		if (current == null)
			return 0;
		
		int leftHeight = height(current.leftChild);
		int rightHeight = height(current.rightChild);
		
		return 1 + Math.max(leftHeight, rightHeight);		// return one plus the greater between the left and right tree heights
	}
	
	
	// Balancing methods
	
	private int getNodeBF(Node current) {
		if (current == null)
			return 0;
		
		return height(current.leftChild) - height(current.rightChild);
	}
	
	
	private Node balance(Node current) {
		if (current == null)
			return null;
		
		
		current.leftChild = balance(current.leftChild);
		current.rightChild = balance(current.rightChild);
		
		int leftBF = getNodeBF(current.leftChild);
		int rightBF = getNodeBF(current.rightChild);
		int currentBF = getNodeBF(current);
		
		if (currentBF == 2) {
			if (leftBF == -1)
				current.leftChild = leftRotate(current.leftChild);

			current = rightRotate(current);
		}
		else if (currentBF == -2) {
			if (rightBF == 1)
				current.rightChild = rightRotate(current.rightChild);
			
			current = leftRotate(current);
		}
		
		return current;
	}
	
	
	private Node leftRotate(Node current) {
		Node holder = current.rightChild;
		current.rightChild = holder.leftChild;
		holder.leftChild = current;
		return holder;
	}
	
	
	private Node rightRotate(Node current) {
		Node holder = current.leftChild;
		current.leftChild = holder.rightChild;
		holder.rightChild = current;
		return holder;
	}
	
	
	public boolean insertRec(float number) {
		// Return true on successful insertion, return false when failing to insert
		
		if (this.has(number))
			return false;		// We can't insert a value already in the tree.
		else {
			root = insertRecHelper(number, root);
			root = balance(root);
			return true;
		}
	}
	
	private Node insertRecHelper(float number, Node current) {
		if (current == null) {
			size++;
			return new Node(number);
		} else if (number < current.value)
			current.leftChild = insertRecHelper(number, current.leftChild);
		else
			current.rightChild = insertRecHelper(number, current.rightChild);
		// Don't need to check whether it's equal, only call this function if the value is not in the tree already
		
		return current;
	}
	
	
	// Done
	public boolean deleteRec(float number) {
		// Return true on successful deletion, return false when failing to delete
		
		if (!this.has(number))
			return false;
		
		root = deleteRecHelper(number, root);
		root = balance(root);
		size--;
		return true;
	}
	
	public Node deleteRecHelper(float number, Node current) {
		if (number < current.value)
			current.leftChild = deleteRecHelper(number, current.leftChild);
		else if (number > current.value)
			current.rightChild = deleteRecHelper(number, current.rightChild);
		else {		// number == current.value
			if (current.leftChild == null && current.rightChild == null)	// We are removing a leaf node
				return null;
			else if (current.leftChild == null)		// We are removing a node with only right children
				return current.rightChild;
			else if (current.rightChild == null)	// We are removing a node with only left children
				return current.leftChild;
			else {		// We are removing a node with two children
				current.value = findMinRecHelper(current.rightChild);
				current.rightChild = deleteRecHelper(current.value, current.rightChild);	// This *should* be removing a leaf node
			}
		}
		
		return current;
	}
	
	
	// Done
	public float findNextRec(float number) throws Exception {
		float holder = findNextRecHelper(number, root);
		if (holder == number)
			throw new Exception();
		else
			return holder;
	}
	
	private float findNextRecHelper(float number, Node current) throws Exception {
		if (current == null)
			throw new Exception();
		
		float holder;
		
		if (number < current.value) {
			holder = findNextRecHelper(number, current.leftChild);
		}
		else if (number > current.value) {
			holder = findNextRecHelper(number, current.rightChild);
		}
		else {	// Otherwise, number == current.value
			if (current.rightChild == null)
				return number;
			else
				return findMinRecHelper(current.rightChild);	// The next node will be the min value in the right subtree
		}
		
		if ((current.value < holder || holder == number) && current.value > number)
			holder = current.value;
		
		return holder;
	}
	
	
	// Done
	public float findPrevRec(float number) throws Exception {
		float holder = findPrevRecHelper(number, root);
		if (holder == number)
			throw new Exception();
		else
			return holder;
	}
	
	private float findPrevRecHelper(float number, Node current) throws Exception {
		if (current == null)
			throw new Exception();
		
		float holder;
		
		if (number < current.value) {
			holder = findPrevRecHelper(number, current.leftChild);
		}
		else if (number > current.value) {
			holder = findPrevRecHelper(number, current.rightChild);
		}
		else {	// Otherwise, number == current.value
			if (current.leftChild == null)
				return number;
			else
				return findMaxRecHelper(current.leftChild);	// The next node will be the min value in the right subtree
		}
		
		if ((current.value > holder || holder == number) && current.value < number)
			holder = current.value;
		
		return holder;
	}
	
	
	// Done
	public float findMinRec() throws Exception {
		if (root == null) {
			System.out.println("No values in the AVL.");
			throw new Exception();
		}
		else
			return findMinRecHelper(root);
	}
	
	private float findMinRecHelper(Node current) {
		if (current.leftChild == null)
			return current.value;
		else
			return findMinRecHelper(current.leftChild);
	}
	
	
	// Done
	public float findMaxRec() throws Exception {
		if (root == null) {
			System.out.println("No values in the AVL.");
			throw new Exception();
		}
		else
			return findMaxRecHelper(root);
	}
	
	private float findMaxRecHelper(Node current) {
		if (current.rightChild == null)
			return current.value;
		else
			return findMaxRecHelper(current.rightChild);
	}
}
