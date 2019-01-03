/**
 * 
 */
package sm.coding.ds.llstq.leetcode.linked_list._002;

import sm.coding.ds.llstq.leetcode.linked_list.ListNode;

/**
 * @author shahzadmughal8410
 *
 */
public class AddTwoNumbers_002 {

	/**
Medium
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.
Example:
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.


Submission
https://leetcode.com/submissions/detail/198663859/
You are here! 
Your runtime beats 28.58 % of java submissions.

	 */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	    	if(null==l1) {
	    		return l2;
	    	}
	    	if(null==l2) {
	    		return l1;
	    	}
        ListNode head = null;
        ListNode current = null;
        
        int carry = 0;
        while(l1!=null || l2!=null) {
        		int a = 0;
        		int b = 0;
        		if(l1!=null) {
        			a = l1.val;
        			l1 = l1.next;
        		}
        		if(l2!=null) {
        			b = l2.val;
        			l2 = l2.next;
        		}
        		
        		int sum = a+b+carry;
        		int value = sum % 10;
        		carry = sum /10;
        		
        		if(null==head) {
        			head = new ListNode(value);
        			current = head;
        		}else {
        			ListNode next = new ListNode(value);
        			current.next = next;
        			current = next;
        		}
        }
        
        if(carry>0) {
			ListNode next = new ListNode(carry);
			current.next = next;
        }
        
        return head;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
