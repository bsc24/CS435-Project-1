package question_5_6_7;

import question_1.*;
import question_3.Arrays_of_Integers;
import question_4.*;

public class Constructing_Trees {

	public static void main(String[] args) {
		
		int numElements = 10000;
		long startTime, BSTime, AVLime;
		int[] randArray = Arrays_of_Integers.getRandomArray(numElements);
		int[] sortedArray = Arrays_of_Integers.getSortedArray(numElements);
		
		System.out.println("Question 5");
		System.out.print("Starting part A... ");
		Recursive_BST recBSTree = createRecBST(randArray);
		Recursive_AVL recAVLee = createRecAVL(randArray);
		System.out.println("Finished");

		System.out.print("Starting part C... ");
		Iterative_BST iterBSTree = createIterBST(randArray);
		Iterative_AVL iterAVLee = createIterAVL(randArray);
		System.out.println("Finished");
		
		// Clearing out memory
		recBSTree = null;
		recAVLee = null;
		
		
		System.out.println("\nQuestion 6");
		System.out.println("Part B");
		System.out.println("\tLevels traversed by BST: " + iterBSTree.getLevelsTraversed());
		System.out.println("\tLevels traversed by AVL: " + iterAVLee.getLevelsTraversed());
		
		System.out.print("Starting part C... ");
		Iterative_BST iterBSTreeSorted = createIterBST(sortedArray);
		Iterative_AVL iterAVLeeSorted = createIterAVL(sortedArray);
		System.out.println("Finished");
		System.out.println("\tLevels traversed by BST: " + iterBSTreeSorted.getLevelsTraversed());
		System.out.println("\tLevels traversed by AVL: " + iterAVLeeSorted.getLevelsTraversed());

		// Clearing out memory
		iterBSTreeSorted = null;
		iterAVLeeSorted = null;
		
		
		System.out.println("\nQuestion 7");
		System.out.println("Starting part A...");
		startTime = System.currentTimeMillis();
		iterBSTree = createIterBST(randArray);
		for (int i = 0; i < numElements; i++)
			iterBSTree.deleteIter(randArray[i]);
		BSTime = System.currentTimeMillis();
		
		iterAVLee = createIterAVL(randArray);
		for (int i = 0; i < numElements; i++)
			iterAVLee.deleteIter(randArray[i]);
		AVLime = System.currentTimeMillis();
		AVLime -= BSTime;
		BSTime -= startTime;
		System.out.println("\tTime taken by BST: " + BSTime + "ms");
		System.out.println("\tTime taken by AVL: " + AVLime + "ms");
	}
	
	
	// BS Trees
	private static Recursive_BST createRecBST(int[] inputArray) {
		Recursive_BST recBSTree = new Recursive_BST();
		int numElements = inputArray.length;
		
		for (int i = 0; i < numElements; i++)
			recBSTree.insertRec(inputArray[i]);
		
		return recBSTree;
	}
	
	private static Iterative_BST createIterBST(int[] inputArray) {
		Iterative_BST iterBSTree = new Iterative_BST();
		int numElements = inputArray.length;

		for (int i = 0; i < numElements; i++)
			iterBSTree.insertIter(inputArray[i]);
		
		return iterBSTree;
	}
	
	
	// AVL Trees
	private static Recursive_AVL createRecAVL(int[] inputArray) {
		Recursive_AVL recAVLee = new Recursive_AVL();
		int numElements = inputArray.length;

		for (int i = 0; i < numElements; i++)
			recAVLee.insertRec(inputArray[i]);
		
		return recAVLee;
	}
	
	private static Iterative_AVL createIterAVL(int[] inputArray) {
		Iterative_AVL iterAVLee = new Iterative_AVL();
		int numElements = inputArray.length;

		for (int i = 0; i < numElements; i++)
			iterAVLee.insertIter(inputArray[i]);
		
		return iterAVLee;
	}
}
