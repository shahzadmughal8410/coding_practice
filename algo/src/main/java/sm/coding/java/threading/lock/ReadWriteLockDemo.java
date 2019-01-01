/**
 * 
 */
package sm.coding.java.threading.lock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author shahzadmughal8410
 *
 */
public class ReadWriteLockDemo {

	static final int READER_SIZE = 10;
	static final int WRITER_SIZE = 2;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] initialElements = { 33, 28, 86, 99 };

		ReadWriteList<Integer> sharedList = new ReadWriteList<>(initialElements);

		for (int i = 0; i < WRITER_SIZE; i++) {
			new Writer(sharedList, i).start();
		}

		for (int i = 0; i < READER_SIZE; i++) {
			new Reader(sharedList, i).start();
		}
	}

}

class ReadWriteList<E> {
	private List<E> list = new ArrayList<>();
	private ReadWriteLock rwLock = new ReentrantReadWriteLock();

	public ReadWriteList(E... initialElements) {
		list.addAll(Arrays.asList(initialElements));
	}

	public void add(E element) {
		Lock writeLock = rwLock.writeLock();
		writeLock.lock();

		try {
			list.add(element);
		} finally {
			writeLock.unlock();
		}
	}

	public E get(int index) {
		Lock readLock = rwLock.readLock();
		readLock.lock();

		try {
			return list.get(index);
		} finally {
			readLock.unlock();
		}
	}

	public int size() {
		Lock readLock = rwLock.readLock();
		readLock.lock();

		try {
			return list.size();
		} finally {
			readLock.unlock();
		}
	}

}

class Writer extends Thread {
	private ReadWriteList<Integer> sharedList;

	public Writer(ReadWriteList<Integer> sharedList, int i) {
		super("Writer-"+i);
		this.sharedList = sharedList;
	}

	public void run() {
		while (true) {
			Random random = new Random();
			int number = random.nextInt(100);
			sharedList.add(number);

			try {
				Thread.sleep(100);
				System.out.println(getName() + " -> put: " + number);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}

class Reader extends Thread {
	private ReadWriteList<Integer> sharedList;

	public Reader(ReadWriteList<Integer> sharedList, int i) {
		super("Reader-"+i);
		this.sharedList = sharedList;
	}

	public void run() {
		while (true) {
			Random random = new Random();
			int index = random.nextInt(sharedList.size());
			Integer number = sharedList.get(index);

			System.out.println(getName() + " -> get: " + number);

			try {
				Thread.sleep(100);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}
