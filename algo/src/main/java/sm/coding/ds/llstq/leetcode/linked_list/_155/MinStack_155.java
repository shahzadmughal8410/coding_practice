/**
 * 
 */
package sm.coding.ds.llstq.leetcode.linked_list._155;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author shahzadmughal8410
 *
 */
public class MinStack_155 {

	/**
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
	 */
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
/**
Submission
https://leetcode.com/submissions/detail/187675872/
You are here! 
Your runtime beats 98.57 % of java submissions.
 * @author shahzadmughal8410
 *
 */
class MinStack {
	Deque<Integer> value;
	Deque<Integer> min;
	
	public MinStack() {
		value = new LinkedList<>();
		min = new LinkedList<>();
	}
	
	public int peek() {
		return value.peek();
	}
	
	public void push(int v) {
		if(min.isEmpty() || min.peek() >= v) {
			min.push(v);
		}
		value.push(v);
	}
	
	public int pop() {
		int v = value.pop();
		if(v==min.peek()) {
			min.pop();
		}
		return v;
	}
	
    public int top() {
    		return peek();
    }
    
    public int getMin() {
        return min.peek();
    }
}
//
//class MinStackConstantSpace {
//	Deque<Integer> value;
//	int min;
//	
//	public MinStackConstantSpace() {
//		value = new LinkedList<>();
//	}
//	
//	public int peek() {
//		int v = value.peek();
//		if(v < min) {
//			return min;
//		}else {
//			return v;
//		}
//	}
//	
//	public void push(int v) {
//		if(!value.isEmpty() || v < min) {
//			value.push(2*v - min);
//			min = v;
//		}else {
//			value.push(v);
//		}
//	}
//	
//	public int pop() {
//		int v = value.pop();
//		if(v < min) {
//			int value = min;
//			min = min*2-v;
//			return value;
//		}else {
//			return v;
//		}
//	}
//	
//    public int top() {
//    		return peek();
//    }
//    
//    public int getMin() {
//        return min;
//    }
//}