package question3;

import java.util.Random;

public class Arrays_of_Integers {

	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] size12 = getRandomArray(12);
		
		for (int i = 0; i < size12.length; i++)
			System.out.print(size12[i] + " ");

		System.out.println();
		size12 = getSortedArray(12);
		
		for (int i = 0; i < size12.length; i++)
			System.out.print(size12[i] + " ");
	}
	*/
	
	public static int[] getRandomArray(int n) {
		Random rand = new Random();
		int[] retArr = new int[n];
		
		for (int i = 0; i < n; i++)
			retArr[i] = i;
		
		for (int i = 0; i < n; i++) {
			int randNum = rand.nextInt(n);
			int holder = retArr[i];
			retArr[i] = retArr[randNum];
			retArr[randNum] = holder;
		}
			
		return retArr;
	}
	
	
	public static int[] getSortedArray(int n) {
		int[] retArr = new int[n];
		
		for (int i = 0; i < retArr.length; i++)
			retArr[i] = n--;
		
		return retArr;
	}

}
