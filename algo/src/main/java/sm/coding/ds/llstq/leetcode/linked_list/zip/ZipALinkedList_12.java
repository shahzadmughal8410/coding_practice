/**
 * 
 */
package sm.coding.ds.llstq.leetcode.linked_list.zip;

import sm.coding.ds.llstq.leetcode.linked_list.LinkedListPrinter;
import sm.coding.ds.llstq.leetcode.linked_list.ListNode;
import sm.coding.ds.llstq.leetcode.linked_list._206.ReverseLinkedList_206;

/**
 * @author shahzadmughal8410
 *
 */
public class ZipALinkedList_12 {

	/**
Zip!
Zip a linked list from it two ends.
e.g.
Input: 1->2->3->4->5->6
Output: 1->6->2->5->3->4

Given a linked list <1, 2, 3, 4, 5, 6>, zip of this linked list is defined as 1, 6 , 2, 5 , 3, 4. And the task is to achieve desired linked list using O(1) space. 

Suggested time: 30 minutes
Solution: http://programming-puzzle.blogspot.com/2014/02/zip-of-linked-list.html
[Extra credit: Zip two separate lists and unzip them back into original lists. i.e.
unzip(zip(L1,L2)) should return L1 and L2]
 
	 */
	public static void zip(ListNode node) {
		/*
		 * 1- Split list in two
		 * 2- reverse second list
		 * 3- merge two lists, laternate positions
		 */
		System.out.print("Input list     = ");
		LinkedListPrinter.print(node);
		ListNode l1 = node;
		ListNode l2 = split(l1);
		
		System.out.print("split list 1   = ");
		LinkedListPrinter.print(l1);
		System.out.print("split list 2   = ");
		LinkedListPrinter.print(l2);
		
		l2 = ReverseLinkedList_206.reverseList(l2);
		System.out.print("Reversed list 2= ");
		LinkedListPrinter.print(l2);
		
		ListNode c1 = l1;
		ListNode c2 = l2;
		ListNode nx1 = null;
		ListNode nx2 = null;
		
		while(c1!=null && c2!=null) {
			nx1 = c1.next;
			nx2 = c2.next;
			
			// update pointers for alternate nodes
			c1.next = c2;
			c2.next = nx1;
			
			c1 = nx1;
			c2 = nx2;
		}
		System.out.print("Zipped list   = ");
		LinkedListPrinter.print(node);
		
	}
	
	public static ListNode split(ListNode node) {
		ListNode slow = node;
		ListNode fast = node;
		ListNode middle = node;
		
		while(null!=fast && fast.next!=null) {
			middle = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode newHead = middle.next;
		middle.next = null;// slow is the mid point
		return newHead;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		head.next.next.next.next.next.next = new ListNode(7);
		head.next.next.next.next.next.next.next = new ListNode(8);
//		head.next.next.next.next.next.next.next.next = new ListNode(9);

		zip(head);
	}

}
