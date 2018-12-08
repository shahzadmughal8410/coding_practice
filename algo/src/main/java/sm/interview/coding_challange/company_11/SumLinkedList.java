/**
 * 
 */
package sm.interview.coding_challange.company_11;

import java.util.Deque;
import java.util.LinkedList;

import sm.coding.ds.llstq.leetcode.linked_list.LinkedListPrinter;
import sm.coding.ds.llstq.leetcode.linked_list.ListNode;

/**
 * @author shahzadmughal8410
 *
 */
public class SumLinkedList {

	/**
	 *  l1: 	 7->2->9->null       
	 * 	l2: 		3->2->null      
	 * 	sum: 7->6->1->null
	 * 
	 * 
	 *  l1: 		9->9->9->9->null      
	 * 	l2: 		      9->9->null      
	 * 	sum: 1->0->0->9->8->null
	 * 
	 *     
	 */

	/**
	 * use stack to hold the linked list elements in reverse order. Add numbers from
	 * stack.
	 * 
	 * Build the result list while doing so. If carry > 0 add last element of result
	 * number, i.e. when its the case of overflow.
	 */
	public static ListNode sum(ListNode l1, ListNode l2) {
		if (null == l1) {
			return l2;
		}
		if (null == l2) {
			return l1;
		}

		Deque<Integer> s1 = new LinkedList<>();
		Deque<Integer> s2 = new LinkedList<>();

		ListNode n1 = l1;
		ListNode n2 = l2;
		
		// fill all elements of l1 to s1
		while (null != n1) {
			s1.push(n1.val);
			n1 = n1.next;
		}
		// fill all elements of l2 to s2
		while (null != n2) {
			s2.push(n2.val);
			n2 = n2.next;
		}

		ListNode tail = null;
		ListNode current = null;
		int carry = 0;
		while (!s1.isEmpty() || !s2.isEmpty()) {
			int a = 0;
			int b = 0;
			if (!s1.isEmpty()) {
				a = s1.pop();
			}
			if (!s2.isEmpty()) {
				b = s2.pop();
			}
			int listValue = (carry + a + b) % 10;
			carry = (carry + a + b) / 10;

			current = new ListNode(listValue);
			current.next = tail;
			tail = current;
		}

		if (carry > 0) {
			current = new ListNode(carry);
			current.next = tail;
			tail = current;
		}

		return current;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListNode l1 = new ListNode(9);
		l1.next = new ListNode(9);

		ListNode l2 = new ListNode(9);
		
		LinkedListPrinter.print(l1);
		LinkedListPrinter.print(l2);
		
		ListNode result = sum(l1, l2);
		LinkedListPrinter.print(result);
		
		
	}

}

