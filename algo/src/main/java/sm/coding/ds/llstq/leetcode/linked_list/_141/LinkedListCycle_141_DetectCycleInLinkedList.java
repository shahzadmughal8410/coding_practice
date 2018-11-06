/**
 * 
 */
package sm.coding.ds.llstq.leetcode.linked_list._141;

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
        			return true;
        		}
        }
        return false;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
