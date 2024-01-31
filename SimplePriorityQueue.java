package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
/**
 * a priority queue class that gives fast access and deletion 
 * it can use natural ordering or custom logic via a comparator
 *
 * @version 1/29/04
 * @author Archer Fox and Alex Dean
 */
public class SimplePriorityQueue<E extends Comparable<? super E>> implements PriorityQueue<Object> {

    public static final int ARRAY_LENGTH = 15;
    private E[] priorityQueue;
    private int elementCount;
	
    private Comparator<? super E> comparator;

    /**
     * Constructs an empty priority queue with the default array length.
     */
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue() {
        priorityQueue = (E[]) new Comparable[ARRAY_LENGTH];
        elementCount = 0;
    }

    /**
     * Constructs an empty priority queue with the default array length and a specified comparator.
     *
     * @param cmp the comparator to determine the order of elements
     */
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(Comparator<? super E> cmp) {
        priorityQueue = (E[]) new Comparable[ARRAY_LENGTH];
        elementCount = 0;
        this.comparator = cmp;
    }

    /**
     * Retrieves the maximum element in the priority queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    public E findMax() throws NoSuchElementException {
        if (elementCount == 0) {
            throw new NoSuchElementException("The PriorityQueue is empty");
        }
        return priorityQueue[elementCount - 1];
    }

    /**
     * Removes and returns the maximum element from the priority queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    public E deleteMax() throws NoSuchElementException {
        if (elementCount == 0) {
            throw new NoSuchElementException("The PriorityQueue is empty");
        }
        priorityQueue[elementCount - 1] = null;
        elementCount--;
        return null;
    }

    /**
     * Inserts the specified element into the priority queue.
     *
     * @param item the element to insert
     */
    @Override
    @SuppressWarnings("unchecked")
    public void insert(Object item) {
        if (elementCount == 0) {
            priorityQueue[0] = (E) item;
            elementCount++;
            return;
        }

        if (elementCount == priorityQueue.length) {
            doubleBackingArray();
        }
        int location = binarySearch(((E) item));

        for (int i = elementCount - 1; i > elementCount; i--) {
            priorityQueue[i + 1] = priorityQueue[i];
        }
        priorityQueue[location + 1] = (E) item;
        elementCount++;
    }

    /**
     * Inserts all elements from the specified collection into the priority queue.
     *
     * @param coll the collection containing elements to insert
     */
    @Override
    @SuppressWarnings("unchecked")
    public void insertAll(Collection coll) {
        for (Object o : coll) {
            insert((E) o);
        }
    }

    /**
     * Checks if the priority queue contains the specified element.
     *
     * @param item the element to check for
     * @return true if the element is in the priority queue, false otherwise
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object item) {
        int index = binarySearch((E) item);
        if (compare(priorityQueue[index], (E) item) == 0) 
            return true;
        return false;
    }

    /**
     * Returns the number of elements in the priority queue.
     *
     * @return the number of elements
     */
    @Override
    public int size() {
        return elementCount;
    }

    /**
     * Checks if the priority queue is empty.
     *
     * @return true if the priority queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Clears the priority queue, removing all elements.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        priorityQueue = (E[]) new Comparable[ARRAY_LENGTH];
        elementCount = 0;
    }

    /**
     * Performs a binary search to find the correct position for the target element.
     *
     * @param target the element to search for
     * @return the index where the element should be inserted
     */
    private int binarySearch(E target) {
        int left = 0;
        int right = elementCount - 1;
        int middle = (right + left) / 2;
        int diff;
		//runs binary search check
        while (left <= right) {
            if(priorityQueue[middle] == null){
                return middle;
            }
            diff = compare(priorityQueue[middle], target);
            if (diff > 0) {
                right = middle - 1;
            } else if (diff < 0) {
                left = middle + 1;
            } else {
                return middle;
            }
            middle = (right + left + 1) / 2;
        }
        return middle;
    }

    /**
     * Compares e1 and e2 with natural ordering or using a comparator if the comparator field is not null
     * @param e1
     * @param e2
     * @return positive int, negative int, or zero based on result of comparison
     */
    private int compare(E e1, E e2) {
        if (comparator == null) {
            return e1.compareTo(e2);
        } else {
            return comparator.compare(e1, e2);
        }
    }

    /**
     * Doubles the size of the internal array when it is full.
     */
    @SuppressWarnings("unchecked")
    private void doubleBackingArray() {
        E[] queueLarge = (E[]) new Comparable[priorityQueue.length * 2];
		//creates new backing array
        for (int i = 0; i < elementCount - 1; i++) {
            queueLarge[i] = priorityQueue[i];
        }

        priorityQueue = queueLarge;
    }
}