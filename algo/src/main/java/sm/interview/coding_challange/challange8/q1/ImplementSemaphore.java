/**
 * 
 */
package sm.interview.coding_challange.challange8.q1;

/**
 * @author shahzadmughal8410
 *
 */
public class ImplementSemaphore {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
/**
http://tutorials.jenkov.com/java-concurrency/semaphores.html
https://stackoverflow.com/questions/33766797/how-to-implement-a-semaphore
*/	
class Semaphore{
	private int count;
	
	public synchronized void accquire() {
		while(count==0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count--;
	}
	
	public synchronized void relase() {
		count++;
		notify();
	}
}