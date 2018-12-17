package sm.interview.coding_challange.challange_13;

import java.util.Deque;
import java.util.LinkedList;


public class Implement_MaxStack {

	/**

	 */
	

	
	public static void main(String[] args) {
//		MaxStack stack = new MaxStack();
		MaxStack_Generic<Integer> stack = new MaxStack_Generic<>();
		
		stack.push(5);
		stack.push(7);
		stack.push(1);
		stack.push(2);
		
		while(!stack.isEmpty()) {
			int max = stack.max();
			int value = stack.pop();
			System.out.println("max="+max+", value="+value);
		}
	}
}


class MaxStack {
	
	Deque<Integer> maxStack = new LinkedList<>();
	Deque<Integer> valStack = new LinkedList<>();
	
	public void push(int i) {
		if(maxStack.isEmpty() || i>=maxStack.peek()) {
			maxStack.push(i);
		}
		valStack.push(i);
	}
	
	public int pop() {
		int value = valStack.pop();
		if(value==maxStack.peek()) {
			maxStack.pop();
		}
		return value;
	}
	
	public int peek() {
		return valStack.peek();
	}
	
	public int max() {
		return maxStack.peek();
	}
	
	public boolean isEmpty() {
		return valStack.isEmpty();
	}
}

class MaxStack_Generic<T extends Comparable<T>> {
	
	Deque<T> maxStack = new LinkedList<>();
	Deque<T> valStack = new LinkedList<>();
	
	public void push(T i) {
		if(maxStack.isEmpty() || i.compareTo(maxStack.peek())>=0) {
			maxStack.push(i);
		}
		valStack.push(i);
	}
	
	public T pop() {
		T value = valStack.pop();
		if(value.compareTo(maxStack.peek())==0) {
			maxStack.pop();
		}
		return value;
	}
	
	public T peek() {
		return valStack.peek();
	}
	
	public T max() {
		return maxStack.peek();
	}
	
	public boolean isEmpty() {
		return valStack.isEmpty();
	}
}