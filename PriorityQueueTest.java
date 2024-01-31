package assign03;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;


public class PriorityQueueTest {
	SimplePriorityQueue<Integer> intQueue;
	SimplePriorityQueue<String> stringQueue;
	SimplePriorityQueue<Integer> oneQueue;
	@BeforeEach
	void setup() {
		//set up integer list
		ArrayList<Integer> intList = new ArrayList<>();
		intList.add(8);
		intList.add(2);
		intList.add(3);
		intList.add(4);
		intList.add(1);
		intQueue = new SimplePriorityQueue<>();	
		intQueue.insertAll(intList);

		//set up single item list
		oneQueue = new SimplePriorityQueue<>();
		oneQueue.insert(10);

		//set up string list
		ArrayList<String> stringList = new ArrayList<>();
		stringList.add("hi");
		stringList.add("hello");
		stringList.add("bonjour");
		stringList.add("hola");
		stringQueue = new SimplePriorityQueue<>();	
		stringQueue.insertAll(stringList);
	}
	
	@Test
	void testContains() {
		assertTrue(intQueue.contains(1));
	}
	
	@Test
	void testFindMax() {
		assertEquals(8, intQueue.findMax());
	}

	@Test
	void testDeleteMax() {
		intQueue.deleteMax();
		assertEquals(4, intQueue.findMax());
	}
	
	@Test
	void testMaxItem() {
		assertEquals(intQueue.findMax(), 8);
	}

	@Test
	void testMaxWithOneItem() {
		assertEquals(oneQueue.findMax(), 10);
	}
	@Test
	void testDeleteOneItem() {
		oneQueue.deleteMax();
		assertEquals(oneQueue.size(), 0);
	}

	@Test
	void testMaxMultiple() {
		intQueue.insert(8);
		assertEquals(intQueue.findMax(), 8);
	}

	//tests max method after inserting multiple items
	@Test
	void testMaxAfterInsert() {
		intQueue.insert(1);
		intQueue.insert(3);
		intQueue.insert(2);
		assertTrue(intQueue.findMax().equals(8));
	}

	@Test
	void testMaxAfterInsertLargerNumber() {
		intQueue.insert(1);
		intQueue.insert(3);
		intQueue.insert(2);
		intQueue.insert(9);
		assertTrue(intQueue.findMax().equals(9));
	}


	@Test
	void testSize() {
		assertEquals(5, intQueue.size());
	}

	@Test
	void testIsEmpty() {
		intQueue.clear();
		assertTrue(intQueue.isEmpty());
	}

	@Test
	void testClear() {
		intQueue.clear();
		assertEquals(0, intQueue.size());
	}
	
	//this test checks for if the double array will expand how many items our queue can hold
	@Test
	void testDoubleArrayInreases() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(8);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(1);
		list.add(8);
		list.add(2);
		list.add(1);
		list.add(8);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(1);
		list.add(8);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(3);
		list.add(4);
		list.add(1);
		intQueue.insertAll(list);
		assertEquals(25, intQueue.size());
	}

	@Test 
	void testInsertWhenArrayFull() {
		intQueue.insert(10);
		intQueue.insert(10);
		intQueue.insert(10);
		intQueue.insert(10);
		intQueue.insert(10);
		intQueue.insert(10);
		intQueue.insert(10);
		intQueue.insert(10);
		intQueue.insert(10);
		intQueue.insert(10);
		//array is now full
		intQueue.insert(5);
		//as long as no IndexOutOfBounds exception is thrown this test is successful
	}
	
	@Test
	void testContainsString() {
		assertTrue(stringQueue.contains("hi"));
	}

	@Test
	void testLargestString() {
		assertEquals("hola", stringQueue.findMax());
	}

	@Test
	void testDeleteMaxString() {
		stringQueue.deleteMax();
		assertEquals("hi", stringQueue.findMax());
	}

	//this test checks with custom comparator
	@Test
	void testSortWithComparator() {
		Comparator<String> sortByLastLetter = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.charAt(o2.length() - 1) - o1.charAt(o1.length() - 1);
			}
		};
		SimplePriorityQueue<String> testQueue = new SimplePriorityQueue<>(sortByLastLetter);
		testQueue.insert("hi");
		testQueue.insert("hello");
		testQueue.insert("bonjour");
		testQueue.insert("hola");
		assertEquals("hola", testQueue.findMax());
	}

	@Test
	void testFindMaxThrows(){
		SimplePriorityQueue<String> empty = new SimplePriorityQueue<>();
		assertThrows(NoSuchElementException.class, () -> empty.findMax());
	}

	@Test
	void testDeleteMaxThrows(){
		SimplePriorityQueue<String> empty = new SimplePriorityQueue<>();
		assertThrows(NoSuchElementException.class, () -> empty.deleteMax());
	}
	@Test
	void containsWithFirstElement(){
		assertTrue(intQueue.contains(1));
	}
}