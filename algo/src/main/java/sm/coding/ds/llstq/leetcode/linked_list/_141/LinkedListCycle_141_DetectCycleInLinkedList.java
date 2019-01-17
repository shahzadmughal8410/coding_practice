/**
 * 
 */
package sm.coding.ds.llstq.leetcode.linked_list._141;

import sm.coding.ds.llstq.leetcode.linked_list.LinkedListPrinter;
import sm.coding.ds.llstq.leetcode.linked_list.ListNode;

/**
 * @author shahzadmughal8410
 *
 */
public class LinkedListCycle_141_DetectCycleInLinkedList {

	/**
Given a linked list, determine if it has a cycle in it.
Follow up:
Can you solve it without using extra space?

Submission
https://leetcode.com/submissions/detail/186800914/
You are here! 
Your runtime beats 100.00 % of java submissions.
	 */
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null) {
        		slow = slow.next;
        		fast = fast.next.next;
        		if(slow==fast) {
        			System.out.println("cycle detected at="+slow.val);
        			return true;
        		}
        }
        return false;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListNode head = new ListNode(3);
		ListNode n1 = head;

		ListNode two = new ListNode(2);
		n1.next = two;
		n1 = n1.next;
		n1.next = new ListNode(0);
		n1 = n1.next;
		n1.next = new ListNode(-4);
		n1 = n1.next;

		LinkedListPrinter.print(head);
		
		//cycle
		n1.next = two;
		System.out.println("Cycle at 2");
//		LinkedListPrinter.print(head);
		
		System.out.println("Has cycle="+hasCycle(head));
	}

}
