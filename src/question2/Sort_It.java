package question2;

import java.util.ArrayList;

import question1.*;

public class Sort_It {
	
	// Done
	public static int[] sort(int[] inputArr) {
		Recursive_BST BSTree = new Recursive_BST();
		for (int i = 0; i < inputArr.length; i++)
			BSTree.insertRec(inputArr[i]);
		
		int[] retArr = new int[BSTree.getSize()];
		
		inOrder(BSTree.getRoot(), retArr, 0);
		
		return retArr;
	}
	
	
	// Done
	private static int inOrder(Node current, int[] retArr, int index) {
		
		if (current.leftChild != null)
			index = inOrder(current.leftChild, retArr, index);
		
		retArr[index++] = (int) current.value;
		
		if (current.rightChild != null)
			index = inOrder(current.rightChild, retArr, index);
		
		return index;
	}
	
}
