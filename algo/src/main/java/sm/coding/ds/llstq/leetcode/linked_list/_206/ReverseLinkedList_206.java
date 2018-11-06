/**
 * 
 */
package sm.coding.ds.llstq.leetcode.linked_list._206;

import sm.coding.ds.llstq.leetcode.linked_list.LinkedListPrinter;
import sm.coding.ds.llstq.leetcode.linked_list.ListNode;

/**
 * @author shahzadmughal8410
 *
 */
public class ReverseLinkedList_206 {

	/**
Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?

https://www.geeksforgeeks.org/reverse-a-linked-list/ 

Submission
https://leetcode.com/submissions/detail/187856840/
You are here! 
Your runtime beats 100.00 % of java submissions.
	 */
    public static ListNode reverseList(ListNode head) {        
		ListNode previous = null;
		ListNode current = head;
		ListNode next = null;
    		while(current!=null) {
    			next = current.next; // hold next pointer
    			current.next = previous; // move current's next pointer to backword direction
    			previous = current; // move previous
    			current = next;// move current
    		}
    		return previous; //always the last element reversed i.e. becomes HEAD of reversed list
    }
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListNode head = new ListNode(3);
		head.next = new ListNode(2);
		head.next.next = new ListNode(1);
		LinkedListPrinter.print(head);
		
		head = reverseList(head);
		LinkedListPrinter.print(head);		
	}

}
