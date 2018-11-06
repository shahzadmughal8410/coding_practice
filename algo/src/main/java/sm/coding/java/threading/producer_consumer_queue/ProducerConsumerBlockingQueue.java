/**
 * 
 */
package sm.coding.java.threading.producer_consumer_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author smughal
 *
 */
public class ProducerConsumerBlockingQueue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int totalMessages = 10;
		BlockingQueue<Integer> sharedQueue = new LinkedBlockingDeque<>(totalMessages/3);
		
		Producer p = new Producer(sharedQueue, totalMessages);
		Consumer c = new Consumer(sharedQueue, totalMessages);
		
		Thread pt = new Thread(p);
		Thread ct = new Thread(c);
		pt.start();
		ct.start();

	}

}

class Producer implements Runnable{
	private BlockingQueue<Integer> sharedQueue;
	private int totalMessages;

	public Producer(BlockingQueue<Integer> sharedQueue, int totalMessages) {
		this.sharedQueue = sharedQueue;
		this.totalMessages = totalMessages;
	}
	
	public void produce(int i ) throws InterruptedException {
		sharedQueue.put(i);
	}

	@Override
	public void run() {
		for(int i = 0; i<=totalMessages; i++) {
			try {
				produce(i);
				System.out.println("Produced="+i);
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}

class Consumer implements Runnable {
	private BlockingQueue<Integer> sharedQueue;
	private int totalMessages;

	public Consumer(BlockingQueue<Integer> sharedQueue, int totalMessages) {
		this.sharedQueue = sharedQueue;
		this.totalMessages = totalMessages;
	}
	
	public int consume() throws InterruptedException {
		return sharedQueue.take();
	}

	@Override
	public void run() {
		while(true) {
			int result;
			try {
				result = consume();
				System.out.println("Consumed="+result);
				if(result==totalMessages) {
					break;
				}
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}