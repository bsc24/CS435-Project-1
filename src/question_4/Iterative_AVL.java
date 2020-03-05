package question_4;

import java.util.LinkedList;
import java.util.Queue;

public class Iterative_AVL {
	
	private Node root;
	private int size;
	private int levelsTraversed;
	
	// Constructors
	public Iterative_AVL() {
		root = null;
		levelsTraversed = 0;
	}
	
	
	// Other methods
	public Node getRoot() {
		return root;
	}
	
	
	public int getSize() {
		return size;
	}
	
	
	public int getLevelsTraversed() {
		return levelsTraversed;
	}
	
	
	public boolean has(float number) {
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
	
	
	private int height(Node current) {
		
		if (current == null)
			return 0;
		LinkedList<Node> nodeQueue = new LinkedList<Node>();
		LinkedList<Node> secondaryQueue = new LinkedList<Node>();
		
		int height = 0;
		
		nodeQueue.add(current);
		
		while (!nodeQueue.isEmpty()) {
			current = nodeQueue.removeFirst();
			
			if (current.leftChild != null)
				secondaryQueue.add(current.leftChild);

			if (current.rightChild != null)
				secondaryQueue.add(current.rightChild);
			
			if (nodeQueue.isEmpty()) {
				nodeQueue = secondaryQueue;
				height++;
				secondaryQueue = new LinkedList<Node>();
			}
		}
		
		return height;
	}
	
	
	// Balancing Methods
	
	private int getNodeBF(Node current) {
		return height(current.leftChild) - height(current.rightChild);
	}
	
	
	private Node balance(LinkedList<Node> nodeStack) {
		Node lastNode = null;
		while (!nodeStack.isEmpty()) {
			Node current = nodeStack.removeFirst();
			
			if (lastNode != null){
				if (lastNode.value < current.value)
					current.leftChild = lastNode;
				else
					current.rightChild = lastNode;
			}
			
			int currentBF = getNodeBF(current);
			
			if (currentBF == 2) {
				if (getNodeBF(current.leftChild) == -1)
					current.leftChild = leftRotate(current.leftChild);

				current = rightRotate(current);
			}
			else if (currentBF == -2) {
				if (getNodeBF(current.rightChild) == 1)
					current.rightChild = rightRotate(current.rightChild);
				
				current = leftRotate(current);
			}
			
			lastNode = current;
		}
		
		return lastNode;
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
	
	// Done
	public boolean insertIter(float number) {
		// Return true on successful insertion, return false when failing to insert
		// Unlike with the recursive version, I was able to leave out whether the tree had the value already
		
		if (root == null) {
			root = new Node(number);
			size++;
			return true;
		}
		
		Node current = root;
		LinkedList<Node> nodeStack = new LinkedList<Node>();
		
		while(true) {
			nodeStack.addFirst(current);
			if (number < current.value) {
				if (current.leftChild == null)  {
					current.leftChild = new Node(number);
					size++;
					break;
				}
				else
					current = current.leftChild;
			}
			else if (number > current.value) {
				if (current.rightChild == null) {
					current.rightChild = new Node(number);
					size++;
					break;
				}
				else
					current = current.rightChild;
			}
			else
				return false;

			levelsTraversed++;
		}
		
		root = balance(nodeStack);
		return true;
	}
	
	
	public boolean deleteIter(float number){
		if (root == null) {
			System.out.println("No values in the AVL.");
			return false;
		}
		
		Node last = null;
		Node current = root;
		LinkedList<Node> nodeStack = new LinkedList<Node>();
		int whichChild = 0;		/*	Marks whether current is the left or right child of the last node
								 *	0 when current is the root of the AVL
								 *	1 when current is the left child of last
								 *	2 when current is the right child of last
								 */
		
		while (true) {
			nodeStack.addFirst(current);
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
					if (whichChild == 0)			// Removing the root of the AVL
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
				break;
			}
		}
		
		nodeStack.removeFirst();
		
		root = balance(nodeStack);
		size--;
		return true;
	}
	
	// Modified findMinIter for use in delete method
	private float findMinOfNode(Node current){
		while (current.leftChild != null)
			current = current.leftChild;
		
		return current.value;
	}
	
	
	// Done
	public float findNextIter(float number) throws Exception {
		if (root == null) {
			System.out.println("No values in the AVL.");
			throw new Exception();
		}
		
		Node current = root;
		float smallestNextPassed = current.value;
		
		while (true) {
			if (number > current.value) {
				if (current.rightChild == null) {
					System.out.println("Cannot find next of a value not in the AVL.");
					throw new Exception();
				}
				else
					current = current.rightChild;
			}
			else if (number < current.value) {
				smallestNextPassed = current.value;
				if (current.leftChild == null) {
					System.out.println("Cannot find next of a value not in the AVL.");
					throw new Exception();
				}
				else
					current = current.leftChild;
			}
			else
				break;
		}
		
		if (smallestNextPassed == number)
			throw new Exception();
		
		if (current.rightChild == null)
			return smallestNextPassed;
		
		current = current.rightChild;
		while (current.leftChild != null)
			current = current.leftChild;
		
		return current.value;
	}
	
	
	// Done
	public float findPrevIter(float number) throws Exception {
		if (root == null) {
			System.out.println("No values in the AVL.");
			throw new Exception();
		}
		
		Node current = root;
		float biggestPrevPassed = current.value;
		
		while (true) {
			if (number > current.value) {
				biggestPrevPassed = current.value;
				if (current.rightChild == null) {
					System.out.println("Cannot find previous of a value not in the AVL.");
					throw new Exception();
				}
				else
					current = current.rightChild;
			}
			else if (number < current.value) {
				if (current.leftChild == null) {
					System.out.println("Cannot find previous of a value not in the AVL.");
					throw new Exception();
				}
				else
					current = current.leftChild;
			}
			else
				break;
		}
		
		if (biggestPrevPassed == number)
			throw new Exception();
		
		if (current.leftChild == null)
			return biggestPrevPassed;
		
		current = current.leftChild;
		while (current.rightChild != null)
			current = current.rightChild;
		
		return current.value;
	}
	
	
	// Done
	public float findMinIter() throws Exception {
		if (root == null) {
			System.out.println("No values in the AVL.");
			throw new Exception();
		}
		
		Node current = root;
		while (current.leftChild != null)
			current = current.leftChild;
		
		return current.value;
	}
	
	
	// Done
	public float findMaxIter() throws Exception {
		if (root == null) {
			System.out.println("No values in the AVL.");
			throw new Exception();	// Ideally, I would throw an exception here instead.
		}
		
		Node current = root;
		while (current.rightChild != null)
			current = current.rightChild;
		
		return current.value;
	}
}
