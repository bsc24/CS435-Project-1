package testers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import question_4.Iterative_AVL;

public class IterAVLTester {
	@Test
	public void IterativeTests() throws Exception {
		Iterative_AVL AVLree = new Iterative_AVL();
		assertTrue(AVLree.insertIter(2));		// insert 2
		assertFalse(AVLree.insertIter(2));
		
		assertEquals(AVLree.findMaxIter(), 2, 0);
		assertEquals(AVLree.findMinIter(), 2, 0);
		
		assertTrue(AVLree.insertIter(8));		// insert 8
		assertTrue(AVLree.insertIter(14));		// insert 14
		assertTrue(AVLree.insertIter(12));		// insert 12
		assertTrue(AVLree.insertIter(13));		// insert 13
		assertTrue(AVLree.insertIter(10));		// insert 10
		assertTrue(AVLree.insertIter(11));		// insert 11
		assertTrue(AVLree.insertIter(18));		// insert 18

		assertEquals(AVLree.findMaxIter(), 18, 0);
		assertEquals(AVLree.findMinIter(), 2, 0);
		
		assertEquals(AVLree.findPrevIter(10), 8, 0);
		assertEquals(AVLree.findNextIter(11), 12, 0);
		
		assertTrue(AVLree.deleteIter(14));
		assertFalse(AVLree.deleteIter(14));
		
		assertTrue(AVLree.deleteIter(13));
		assertFalse(AVLree.deleteIter(13));

		assertTrue(AVLree.deleteIter(18));
		assertFalse(AVLree.deleteIter(18));

		assertEquals(AVLree.findMaxIter(), 12, 0);
		assertTrue(AVLree.deleteIter(12));
		assertFalse(AVLree.deleteIter(12));
		assertEquals(AVLree.findMaxIter(), 11, 0);
	}

	@Test(expected = Exception.class)
	public void findNextOfItemNotInIterTree() throws Exception {
		Iterative_AVL AVLree = new Iterative_AVL();
		AVLree.insertIter(1);
		AVLree.findNextIter(0);
	}

	@Test(expected = Exception.class)
	public void findNextOfEmptyIterTree() throws Exception {
		Iterative_AVL AVLree = new Iterative_AVL();
		AVLree.findNextIter(0);
	}
	
	@Test(expected = Exception.class)
	public void findNextOfLargestItemInIterTree() throws Exception {
		Iterative_AVL AVLree = new Iterative_AVL();
		AVLree.insertIter(1);
		AVLree.findNextIter(1);
	}
	
	
	@Test(expected = Exception.class)
	public void findPrevOfItemNotInIterTree() throws Exception {
		Iterative_AVL AVLree = new Iterative_AVL();
		AVLree.insertIter(1);
		AVLree.findPrevIter(0);
	}
	
	@Test(expected = Exception.class)
	public void findPrevOfEmptyIterTree() throws Exception {
		Iterative_AVL AVLree = new Iterative_AVL();
		AVLree.findPrevIter(0);
	}
	
	@Test(expected = Exception.class)
	public void findPrevOfSmallestItemInIterTree() throws Exception {
		Iterative_AVL AVLree = new Iterative_AVL();
		AVLree.insertIter(1);
		AVLree.findPrevIter(1);
	}
}
