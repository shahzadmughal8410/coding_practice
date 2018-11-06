/**
 * 
 */
package sm.coding.ds.llstq.leetcode.linked_list._203;

/**
 * @author shahzadmughal8410
 *
 */
public class RemoveLinkedListElements_203 {

	/**
Remove all elements from a linked list of integers that have value val.

Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5

Submission
https://leetcode.com/submissions/detail/184462667/
You are here! 
Your runtime beats 50.59 % of java submissions.
	 */
    public static ListNode removeElements(ListNode head, int val) {
    		// if head needs to be removed
        while (head != null && head.val == val) {
        		head = head.next;
        }
        // at this point head will never have a value that needs to be removed 
        ListNode curr = head;
        while (curr != null && curr.next != null) {
        		// if next node has the value to be removed, remove next node
            if (curr.next.val == val) {
            		curr.next = curr.next.next;
            }
            else {
            		curr = curr.next;            
            }
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

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
