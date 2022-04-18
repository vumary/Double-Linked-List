/**
 * Name: Prash Katukojwala, Mary Vu
 * Email: pkatukojwala@ucsd.edu, m2vu@ucsd.edu
 * Sources used: None
 * 
 * This file contains one class MyLinkedListCustomTester. This is a private
 * tester file for PA 3 and it uses JUnit 4.12 testing.
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * 
 * This class contains all the private test cases for PA 3 for
 * all the methods in MyLinkedList. It contains one instance variable,
 * which is an empty linked list that's used for various tests.
 * 
 * IMPORTANT: Do not change the method headers and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTester {
	MyLinkedList<Integer> emptyLst;

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */

	@Before
	public void setUp() throws Exception {
		emptyLst = new MyLinkedList<>();
	}

	/**
	 * Test the add method when adding null to empty and non-empty lists
	 */
	@Test
	public void testAdd() {
		try {
			emptyLst.add(null);

		} catch (NullPointerException e) {
			// Exception caught because null can't be added to empty list
		}

		MyLinkedList<Integer> threeInLst = new MyLinkedList<>();

		threeInLst.add(1);
		threeInLst.add(2);

		try {
			threeInLst.add(null);

		} catch (NullPointerException e) {
			// Expection is caught because null can't be added to nonempty list
		}

	}

	/**
	 * Test the add with index method when adding null to list
	 */
	@Test
	public void testAddWithIndexTestOne() {
		try {
			emptyLst.add(null);
			fail();
		} catch (NullPointerException e) {
			// Exception is caught because null can not be added
		}
	}

	/**
	 * Test the add with index method when prepending to a non-empty list
	 */
	@Test
	public void testAddWithIndexTestTwo() {
		MyLinkedList<Integer> nonEmpty = new MyLinkedList<>();
		nonEmpty.add(1);
		nonEmpty.add(2);
		nonEmpty.add(0, 0);
		assertEquals("Check head reference is updated",
				Integer.valueOf(0), nonEmpty.head.next.data);
		assertEquals("Check new node's previous points to head", nonEmpty.head,
				nonEmpty.head.getNext().getPrev());
		assertEquals("Check new node's next points to 2nd element", nonEmpty.getNth(1),
				nonEmpty.head.getNext().getNext());
		assertEquals("Check 2nd element's prev points to new node", nonEmpty.getNth(0),
				nonEmpty.head.getNext().getNext().getPrev());
		assertEquals("Check size of LL", 3, nonEmpty.size());
	}

	/**
	 * Test the get method when index is equal to size
	 */
	@Test
	public void testGet() {
		try {
			emptyLst.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// Exception is caught because can't retrieve index 0 in empty list
		}
		MyLinkedList<Integer> intLst = new MyLinkedList<>();
		intLst.add(1);
		intLst.add(4);
		assertEquals("Check last element is retrieved", Integer.valueOf(4), 
			intLst.get(intLst.size-1));
	}

	/**
	 * Test the getNth method when accessing elements out of bounds
	 */
	@Test
	public void testGetNth() {
		try {
			emptyLst.getNth(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// Exception is caught because can't access negative index
		}

		try {
			emptyLst.getNth(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// Exception is caught, can't access element when its index == size
		}
	}

	/**
	 * Test the set method when setting elements out of bounds
	 */
	@Test
	public void testSet() {
		MyLinkedList<String> strLst = new MyLinkedList<>();
		strLst.add("hello");
		try {
			strLst.set(1, "world");
			fail();
		} catch (IndexOutOfBoundsException e) {
			// Exception caught because can't set an element out of bounds
		}
		strLst.set(0, "hi");
		assertEquals("Check that last element has been replaced", "hi",
			 strLst.tail.getPrev().getElement());
	}

	/**
	 * Test the remove method when trying to remove out of bounds
	 */
	@Test
	public void testRemoveTestOne() {
		MyLinkedList<Character> charLst = new MyLinkedList<>();
		charLst.add('a');
		charLst.add('b');
		try {
			charLst.remove(2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// Exception caught because can't remove index out of bounds
		}
	}

	/**
	 * Test the remove method when removing in the middle of the list
	 */
	@Test
	public void testRemoveTestTwo() {
		MyLinkedList<Character> charLst = new MyLinkedList<>();
		charLst.add('a');
		charLst.add('b');
		charLst.add('c');
		charLst.remove(1);
		// check if a-next points to c
		assertEquals(charLst.tail.getPrev(), charLst.head.getNext().getNext());
		// check if c-prev points to a
		assertEquals(charLst.head.getNext(), charLst.tail.getPrev().getPrev());
		// check if 2nd element is equal to 'c'
		assertEquals('c', (char) charLst.head.getNext().getNext().getElement());
		// check size decrement
		assertEquals(2, charLst.size());
	}

	/**
	 * Test the clear method when list is already empty
	 */
	@Test
	public void testClear() {
		emptyLst.clear();
		assertEquals(emptyLst.head, emptyLst.tail.getPrev());
		assertEquals(emptyLst.tail, emptyLst.head.getNext());
		assertEquals(0, emptyLst.size);
		assertEquals(true, emptyLst.isEmpty());
	}

	/**
	 * Test the size method after various operations on the list
	 */
	@Test
	public void testSize() {
		MyLinkedList<Integer> intLst = new MyLinkedList<>();
		intLst.add(0);
		intLst.add(2);
		intLst.add(1, 1);
		intLst.remove(0);
		intLst.set(0, 11);
		assertEquals(2, intLst.size());
	}
}