package question1;

public class Iterative_BST {
	
	private Node root;
	private int size;
	
	// Constructors
	public Iterative_BST() {
		root = null;
	}
	
	
	// Other methods
	public Node getRoot() {
		return root;
	}
	
	
	public int getSize() {
		return size;
	}
	
	
	public boolean has(int number) {
		Node current = root;
		while (true) {
			if (current == null)
				return false;
			else if (number < current.value)
				current = current.leftChild;
			else if (number > current.value)
				current = current.rightChild;
			else
				return true;
		}
	}
	
	
	// Done
	public boolean insertIter(int number) {
		// Return true on successful insertion, return false when failing to insert
		// Unlike with the recursive version, I was able to leave out whether the tree had the value already
		
		if (root == null) {
			root = new Node(number);
			size++;
			return true;
		}
		
		Node current = root;
		
		while(true) {
			if (number < current.value) {
				if (current.leftChild == null)  {
					current.leftChild = new Node(number);
					size++;
					return true;
				}
				else
					current = current.leftChild;
			}
			else if (number > current.value) {
				if (current.rightChild == null) {
					current.rightChild = new Node(number);
					size++;
					return true;
				}
				else
					current = current.rightChild;
			}
			else
				return false;
		}
	}
	
	
	public boolean deleteIter(int number){
		if (root == null) {
			System.out.println("No values in the BST.");
			return false;
		}
		
		Node last = null;
		Node current = root;
		int whichChild = 0;		/*	Marks whether current is the left or right child of the last node
								 *	0 when current is the root of the BST
								 *	1 when current is the left child of last
								 *	2 when current is the right child of last
								 */
		
		while (true) {
			if (number < current.value) {
				if (current.leftChild == null)
					return false;
				else {
					last = current;
					current = current.leftChild;
					whichChild = 1;
				}
			}
			else if(number > current.value) {
				if (current.rightChild == null)
					return false;
				else {
					last = current;
					current = current.rightChild;
					whichChild = 2;
				}
			}
			else {		// We are at the value to remove
				if (current.leftChild == null && current.rightChild == null) {		// Has no children
					if (whichChild == 0)			// Removing the root of the BST
						root = null;
					else if (whichChild == 1)		// Removing the left child of last
						last.leftChild = null;
					else if (whichChild == 2)		// Removing the right child of last
						last.rightChild = null;
				}
				else if (current.leftChild == null && current.rightChild != null) {		// Only has a right child
					if (whichChild == 0)
						root = current.rightChild;
					else if (whichChild == 1)
						last.leftChild = current.rightChild;
					else if (whichChild == 2)
						last.rightChild = current.rightChild;
				}
				else if (current.leftChild != null && current.rightChild == null) {		// Only has a left child
					if (whichChild == 0)
						root = current.leftChild;
					else if (whichChild == 1)
						last.leftChild = current.leftChild;
					else if (whichChild == 2)
						last.rightChild = current.leftChild;
				}
				else {			// We have both a left and right child
					number = findMinOfNode(current.rightChild);
					current.value = number;
					last = current;
					current = current.rightChild;
					whichChild = 2;
					continue;
				}
				return true;
			}
		}
	}
	
	// Modified findMinIter for use in delete method
	private int findMinOfNode(Node current){
		while (current.leftChild != null)
			current = current.leftChild;
		
		return current.value;
	}
	
	
	// Done
	public int findNextIter(int number) throws Exception {
		if (root == null) {
			System.out.println("No values in the BST.");
			throw new Exception();
		}
		
		Node current = root;
		int smallestNextPassed = current.value;
		
		while (true) {
			if (number > current.value) {
				if (current.rightChild == null) {
					System.out.println("Cannot find next of a value not in the BST.");
					throw new Exception();
				}
				else
					current = current.rightChild;
			}
			else if (number < current.value) {
				smallestNextPassed = current.value;
				if (current.leftChild == null) {
					System.out.println("Cannot find next of a value not in the BST.");
					throw new Exception();
				}
				else
					current = current.leftChild;
			}
			else
				break;
		}
		
		if (current.rightChild == null)
			return smallestNextPassed;
		
		current = current.rightChild;
		while (current.leftChild != null)
			current = current.leftChild;
		
		return current.value;
	}
	
	
	// Done
	public int findPrevIter(int number) throws Exception {
		if (root == null) {
			System.out.println("No values in the BST.");
			throw new Exception();
		}
		
		Node current = root;
		int biggestPrevPassed = current.value;
		
		while (true) {
			if (number > current.value) {
				biggestPrevPassed = current.value;
				if (current.rightChild == null) {
					System.out.println("Cannot find previous of a value not in the BST.");
					throw new Exception();
				}
				else
					current = current.rightChild;
			}
			else if (number < current.value) {
				if (current.leftChild == null) {
					System.out.println("Cannot find previous of a value not in the BST.");
					throw new Exception();
				}
				else
					current = current.leftChild;
			}
			else
				break;
		}
		
		if (current.leftChild == null)
			return biggestPrevPassed;
		
		current = current.leftChild;
		while (current.rightChild != null)
			current = current.rightChild;
		
		return current.value;
	}
	
	
	// Done
	public int findMinIter() throws Exception {
		if (root == null) {
			System.out.println("No values in the BST.");
			throw new Exception();
		}
		
		Node current = root;
		while (current.leftChild != null)
			current = current.leftChild;
		
		return current.value;
	}
	
	
	// Done
	public int findMaxIter() throws Exception {
		if (root == null) {
			System.out.println("No values in the BST.");
			throw new Exception();	// Ideally, I would throw an exception here instead.
		}
		
		Node current = root;
		while (current.rightChild != null)
			current = current.rightChild;
		
		return current.value;
	}
}
