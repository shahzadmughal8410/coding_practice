/**
 * 
 */
package sm.coding.java.threading.deadlock_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author smughal
 *
 */
public class DeadlockDemo_Java8 {

	public Lock lock1 = new ReentrantLock();
	public Lock lock2 = new ReentrantLock();
	
	public void method1() {
		System.out.println("method1 waiting for lock1");
		lock1.lock();
		System.out.println("method1 lock1 accuired");
		System.out.println("method1 waiting for lock2");
		lock2.lock();
		System.out.println("method1 lock2 accuired");
		
		lock2.unlock();
		lock1.unlock();
	}

	public void method2() {
		System.out.println("method2 waiting for lock2");
		lock2.lock();
		System.out.println("method2 lock2 accuired");
		System.out.println("method2 waiting for lock1");
		lock1.lock();
		System.out.println("method2 lock1 accuired");
		
		lock1.unlock();
		lock2.unlock();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DeadlockDemo_Java8 sharedObject = new DeadlockDemo_Java8();
		
		Runnable r1 = ()-> {
			while(true) {
				sharedObject.method1();
				try {
					Thread.sleep(50);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}; 

		Runnable r2 = ()-> {
			while(true) {
				sharedObject.method2();
				try {
					Thread.sleep(50);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
			
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
	}

}
