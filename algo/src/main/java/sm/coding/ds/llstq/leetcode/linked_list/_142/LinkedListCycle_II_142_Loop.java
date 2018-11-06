/**
 * 
 */
package sm.coding.ds.llstq.leetcode.linked_list._142;

import sm.coding.ds.llstq.leetcode.linked_list.ListNode;

/**
 * @author shahzadmughal8410
 *
 */
public class LinkedListCycle_II_142_Loop {

	/**
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?

Submission
https://leetcode.com/submissions/detail/186808858/
You are here! 
Your runtime beats 49.28 % of java submissions.
	 */
    public static ListNode detectCycle(ListNode head) {
    		ListNode slow = head;
    		ListNode fast = head;   		
    		while(fast!=null && fast.next!=null) {
    			slow = slow.next;
    			fast = fast.next.next;
    			if(slow==fast) { // cycle detected
    				while(slow!=head) { // when start == slow, its a cycle node, where cycle begins
    					slow = slow.next;
    					head = head.next;
    				}
    				return slow;
    			}    			
    		}
        return null;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
