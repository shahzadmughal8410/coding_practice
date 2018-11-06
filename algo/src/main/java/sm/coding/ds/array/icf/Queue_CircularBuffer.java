/**
 * 
 */
package sm.coding.ds.array.icf;

/**
 * @author shahzadmughal8410
 *
 */
public class Queue_CircularBuffer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<>(4);

		System.out.println("Queue full?: " + queue.isFull());
		System.out.println("Queue empty?: " + queue.isEmpty());

		queue.offer(1);
		queue.offer(2);
		queue.offer(3);

		System.out.println("Queue full?: " + queue.isFull());
		System.out.println("Queue empty?: " + queue.isEmpty());

		queue.offer(4);
		System.out.println("Queue full?: " + queue.isFull());
		System.out.println("Queue empty?: " + queue.isEmpty());

		System.out.println("Queue peek: " + queue.peek());

		int data = queue.poll();
		System.out.println("Dequeued element: " + data);

		System.out.println("Peek : " + queue.peek());

		data = queue.poll();
		System.out.println("Dequeued element: " + data);
		System.out.println("Peek : " + queue.peek());

		data = queue.poll();
		data = queue.poll();
		System.out.println("Dequeued element: " + data);
		System.out.println("Peek : " + queue.peek());
		System.out.println("Queue full?: " + queue.isFull());
		System.out.println("Queue empty?: " + queue.isEmpty());

	}

}

class Queue<T> {
	
	public static final int SPECIAL_EMPTY_VALUE = -1;
	private T[] queue;
	
	private int headIndex = SPECIAL_EMPTY_VALUE;
	private int tailIndex = SPECIAL_EMPTY_VALUE;
	
	public Queue(int size) {
		queue = (T[]) new Object[size];
	}
	
	public boolean isEmpty() {
		return headIndex ==SPECIAL_EMPTY_VALUE;
	}
	
	public boolean isFull() {
		int nextIndex = (tailIndex+1) % queue.length;
		return nextIndex == headIndex;
	}
	
	public void offer(T data) {
		if(isFull()) {
			return;
		}
		// get the next tail index and insert the new element there
		tailIndex = (tailIndex + 1) % queue.length;
		queue[tailIndex] = data;

		// This is the first element enqueued, set the head index
		// to the tail index.
		if (headIndex == SPECIAL_EMPTY_VALUE) {
			headIndex = tailIndex;
		}
	}
	
	public T poll() {
		if(isEmpty()) {
			return null;
		}
		T data = queue[headIndex];
		
		// This was the last element in the queue.
		if(headIndex == tailIndex) {
			headIndex = SPECIAL_EMPTY_VALUE;
		}else {
			// move the head to the next element - remember to wrap around to the beginning
			// of the array for the last element
			headIndex = (headIndex+1) % queue.length;
		}
		return data;
	}
	
	public T peek() {
		if(isEmpty()) {
			return null;
		}
		return queue[headIndex];
	}
	
} 
