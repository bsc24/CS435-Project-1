package testers;

import static org.junit.Assert.*;

import org.junit.Test;

import question1.Iterative_BST;
import question1.Recursive_BST;

public class RecBSTester {

	@Test
	public void RecurseiveTests() throws Exception {
		Recursive_BST BSTree = new Recursive_BST();
		assertFalse(BSTree.deleteRec(2));
		assertTrue(BSTree.insertRec(2));		// insert 2
		assertFalse(BSTree.insertRec(2));
		
		assertFalse(BSTree.deleteRec(3));
		assertTrue(BSTree.insertRec(3));
		assertTrue(BSTree.deleteRec(3));
		
		assertEquals(BSTree.findMaxRec(), 2);
		assertEquals(BSTree.findMinRec(), 2);
		
		assertTrue(BSTree.insertRec(1));		// insert 1
		assertEquals(BSTree.findMinRec(), 1);
		assertEquals(BSTree.findMaxRec(), 2);
		
		assertTrue(BSTree.insertRec(9));		// insert 9
		assertEquals(BSTree.findMaxRec(), 9);
		assertEquals(BSTree.findMinRec(), 1);
		
		assertEquals(BSTree.findNextRec(2), 9);
		
		assertTrue(BSTree.insertRec(7));		// insert 7
		assertTrue(BSTree.insertRec(14));		// insert 14
		assertEquals(BSTree.findNextRec(7), 9);
		assertEquals(BSTree.findPrevRec(7), 2);
		
		assertEquals(BSTree.findPrevRec(2), 1);
		
		assertTrue(BSTree.deleteRec(2));		// remove 2
		assertFalse(BSTree.deleteRec(2));
		assertEquals(BSTree.getRoot().value, 7);
		
		assertTrue(BSTree.insertRec(2));		// insert 2
		assertTrue(BSTree.insertRec(0));		// insert 0
	}
	
	
	
	@Test(expected = Exception.class)
	public void findNextOfItemNotInRecTree() throws Exception {
		Recursive_BST BSTree = new Recursive_BST();
		BSTree.insertRec(1);
		BSTree.findNextRec(0);
	}

	@Test(expected = Exception.class)
	public void findNextOfEmptyRecTree() throws Exception {
		Recursive_BST BSTree = new Recursive_BST();
		BSTree.findNextRec(0);
	}
	
	
	@Test(expected = Exception.class)
	public void findPrevOfItemNotInRecTree() throws Exception {
		Recursive_BST BSTree = new Recursive_BST();
		BSTree.insertRec(1);
		BSTree.findNextRec(0);
	}
	
	@Test(expected = Exception.class)
	public void findPrevOfEmptyRecTree() throws Exception {
		Recursive_BST BSTree = new Recursive_BST();
		BSTree.findNextRec(0);
	}
}
