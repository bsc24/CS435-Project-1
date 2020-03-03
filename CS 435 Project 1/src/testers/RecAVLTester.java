package testers;

import static org.junit.Assert.*;

import org.junit.Test;

import question_4.Recursive_AVL;

public class RecAVLTester {

	@Test
	public void RecurseiveTests() throws Exception {
		Recursive_AVL AVLee = new Recursive_AVL();
		assertFalse(AVLee.deleteRec(2));
		assertTrue(AVLee.insertRec(2));		// insert 2
		assertFalse(AVLee.insertRec(2));
		
		assertFalse(AVLee.deleteRec(3));
		assertTrue(AVLee.insertRec(3));
		assertTrue(AVLee.deleteRec(3));
		
		assertEquals(AVLee.findMaxRec(), 2, 0);
		assertEquals(AVLee.findMinRec(), 2, 0);
		
		assertTrue(AVLee.insertRec(1));		// insert 1
		assertEquals(AVLee.findMinRec(), 1, 0);
		assertEquals(AVLee.findMaxRec(), 2, 0);
		
		assertTrue(AVLee.insertRec(9));		// insert 9
		assertEquals(AVLee.findMaxRec(), 9, 0);
		assertEquals(AVLee.findMinRec(), 1, 0);
		
		assertEquals(AVLee.findNextRec(2), 9, 0);
		
		assertTrue(AVLee.insertRec(7));		// insert 7
		assertTrue(AVLee.insertRec(8));		// insert 8
		assertTrue(AVLee.insertRec(14));		// insert 14
		assertEquals(AVLee.findNextRec(7), 8, 0);
		assertEquals(AVLee.findPrevRec(7), 2, 0);
		
		assertEquals(AVLee.findPrevRec(2), 1, 0);
		
		assertTrue(AVLee.deleteRec(2));		// remove 2
		assertFalse(AVLee.deleteRec(2));
		assertEquals(AVLee.getRoot().value, 8, 0);
		
		assertTrue(AVLee.insertRec(2));		// insert 2
		assertTrue(AVLee.insertRec(0));		// insert 0

		assertTrue(AVLee.deleteRec(7));		// remove 7
		System.out.println("OK");
	}
	
	
	
	@Test(expected = Exception.class)
	public void findNextOfItemNotInRecTree() throws Exception {
		Recursive_AVL AVLee = new Recursive_AVL();
		AVLee.insertRec(1);
		AVLee.findNextRec(0);
	}

	@Test(expected = Exception.class)
	public void findNextOfEmptyRecTree() throws Exception {
		Recursive_AVL AVLee = new Recursive_AVL();
		AVLee.findNextRec(0);
	}
	
	@Test(expected = Exception.class)
	public void findNextOfLargestItemInRecTree() throws Exception {
		Recursive_AVL AVLee = new Recursive_AVL();
		AVLee.insertRec(1);
		AVLee.findNextRec(1);
	}
	
	
	@Test(expected = Exception.class)
	public void findPrevOfItemNotInRecTree() throws Exception {
		Recursive_AVL AVLee = new Recursive_AVL();
		AVLee.insertRec(1);
		AVLee.findPrevRec(0);
	}
	
	@Test(expected = Exception.class)
	public void findPrevOfEmptyRecTree() throws Exception {
		Recursive_AVL AVLee = new Recursive_AVL();
		AVLee.findPrevRec(0);
	}
	
	@Test(expected = Exception.class)
	public void findPrevOfSmallestItemInRecTree() throws Exception {
		Recursive_AVL AVLee = new Recursive_AVL();
		AVLee.insertRec(1);
		AVLee.findPrevRec(1);
	}
	
	
}
