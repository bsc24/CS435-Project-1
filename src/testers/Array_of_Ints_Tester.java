package testers;

import org.junit.Test;

import question3.Arrays_of_Integers;

public class Array_of_Ints_Tester {

	public static void main(String args[]) {
		int[] size12 = Arrays_of_Integers.getRandomArray(12);

		System.out.print("Randomized Array: ");
		for (int i = 0; i < size12.length; i++)
			System.out.print(size12[i] + " ");		// Prints to make sure array is randomized

		System.out.println();
		size12 = Arrays_of_Integers.getSortedArray(12);
		
		System.out.print("Sorted array from n to 0: ");
		for (int i = 0; i < size12.length; i++)
			System.out.print(size12[i] + " ");
	}
}
