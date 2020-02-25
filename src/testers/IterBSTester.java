package testers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import question1.Iterative_BST;

public class IterBSTester {
	@Test
	public void IterativeTests() throws Exception {
		Iterative_BST BSTree = new Iterative_BST();
		assertTrue(BSTree.insertIter(2));
		assertFalse(BSTree.insertIter(2));
		
		assertEquals(BSTree.findMaxIter(), 2);
		assertEquals(BSTree.findMinIter(), 2);
		
		assertTrue(BSTree.insertIter(8));
		assertTrue(BSTree.insertIter(14));
		assertTrue(BSTree.insertIter(12));
		assertTrue(BSTree.insertIter(13));
		assertTrue(BSTree.insertIter(10));
		assertTrue(BSTree.insertIter(11));
		assertTrue(BSTree.insertIter(18));

		assertEquals(BSTree.findMaxIter(), 18);
		assertEquals(BSTree.findMinIter(), 2);
		
		assertEquals(BSTree.findPrevIter(10), 8);
		assertEquals(BSTree.findNextIter(11), 12);
		
		assertTrue(BSTree.deleteIter(14));
		assertFalse(BSTree.deleteIter(14));
		
		assertTrue(BSTree.deleteIter(11));
		assertFalse(BSTree.deleteIter(11));

		assertTrue(BSTree.deleteIter(8));
		assertFalse(BSTree.deleteIter(8));

		assertEquals(BSTree.findMaxIter(), 18);
		assertTrue(BSTree.deleteIter(18));
		assertFalse(BSTree.deleteIter(18));
		assertEquals(BSTree.findMaxIter(), 13);
		
	}

	@Test(expected = Exception.class)
	public void findNextOfItemNotInIterTree() throws Exception {
		Iterative_BST BSTree = new Iterative_BST();
		BSTree.insertIter(1);
		BSTree.findNextIter(0);
	}

	@Test(expected = Exception.class)
	public void findNextOfEmptyIterTree() throws Exception {
		Iterative_BST BSTree = new Iterative_BST();
		BSTree.findNextIter(0);
	}
	
	
	@Test(expected = Exception.class)
	public void findPrevOfItemNotInIterTree() throws Exception {
		Iterative_BST BSTree = new Iterative_BST();
		BSTree.insertIter(1);
		BSTree.findNextIter(0);
	}
	
	@Test(expected = Exception.class)
	public void findPrevOfEmptyIterTree() throws Exception {
		Iterative_BST BSTree = new Iterative_BST();
		BSTree.findNextIter(0);
	}
}
