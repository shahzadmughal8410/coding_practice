/**
 * 
 */
package sm.coding.java.threading.semaphore;

/**
 * 
 * Semaphore wuth the upper bbound.
 * 
 * @author shahzadmughal8410
 *
 */
public class BoundedSemaphore {

	private int signals = 0; // currentg count
	private int limit = 0; // limit

	public BoundedSemaphore(int maxLimit) {
		this.limit = maxLimit;
	}

	public synchronized void take() throws InterruptedException {
		while (this.signals == limit) {
			wait();
		}
		this.signals++;
		this.notify();
	}

	public synchronized void release() throws InterruptedException {
		while (this.signals == 0) {
			wait();
		}
		this.signals--;
		this.notify();
	}
}
