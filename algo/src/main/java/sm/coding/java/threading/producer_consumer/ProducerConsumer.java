/**
 * 
 */
package sm.coding.java.threading.producer_consumer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author smughal
 *
 */
public class ProducerConsumer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Deque<Integer> sharedQueue = new LinkedList<>();
		int totalMessages = 10;
		
		Producer producer = new Producer(sharedQueue, totalMessages/3, totalMessages);
		Consumer consumer = new Consumer(sharedQueue, totalMessages);
		
		Thread pt = new Thread(producer);
		Thread ct = new Thread(consumer);
		
		pt.start();
		ct.start();
	}

}

class Producer implements Runnable {
	private Deque<Integer> sharedQueue;
	private int capacity;
	private int totalMessages;
	
	public Producer(Deque<Integer> sharedQueue, int capacity, int totalMessages) {
		this.sharedQueue = sharedQueue;
		this.capacity = capacity;
		this.totalMessages = totalMessages;
	}
	
	public void produce(int i) throws Exception{
		while(sharedQueue.size()==capacity) {
			synchronized (sharedQueue) {
				System.out.println("Quque is full "+sharedQueue.size());
				sharedQueue.wait();
			}
		}
		
		synchronized (sharedQueue) {
			sharedQueue.offer(i);
			System.out.println("Produces="+i);
			sharedQueue.notifyAll();
		}
	}

	@Override
	public void run() {
		System.out.println("Producer started");
		for(int i=0; i<=totalMessages;i++) {
			try {
				produce(i);
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
}

class Consumer implements Runnable {
	private Deque<Integer> sharedQueue;
	private int totalMessages;
	
	public Consumer(Deque<Integer> sharedQueue, int totalMessages) {
		this.sharedQueue = sharedQueue;
		this.totalMessages = totalMessages;
	}
	
	public int consume() throws Exception{
		while(sharedQueue.isEmpty()) {
			synchronized (sharedQueue) {
				System.out.println("Queue is empty");
				sharedQueue.wait();
			}
		}
		synchronized (sharedQueue) {
			int result = sharedQueue.poll();
			System.out.println("Consumed="+result);
			sharedQueue.notifyAll();
			return result;
		}
	}

	@Override
	public void run() {
		System.out.println("Consumer started");
		while(true) {
			try {
				int result = consume();
				if(result==totalMessages) {
					break;
				}
				Thread.sleep(80);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
