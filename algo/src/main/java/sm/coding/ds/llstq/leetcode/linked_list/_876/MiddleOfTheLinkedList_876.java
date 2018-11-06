/**
 * 
 */
package sm.coding.ds.llstq.leetcode.linked_list._876;

import sm.coding.ds.llstq.leetcode.linked_list.ListNode;

/**
 * @author shahzadmughal8410
 *
 */
public class MiddleOfTheLinkedList_876 {

	/**
Given a non-empty, singly linked list with head node head, return a middle node of linked list.
If there are two middle nodes, return the second middle node.
 
Example 1:
Input: [1,2,3,4,5]
Output: Node 3 from this list (Serialization: [3,4,5])
The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
Note that we returned a ListNode object ans, such that:
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.


Example 2:
Input: [1,2,3,4,5,6]
Output: Node 4 from this list (Serialization: [4,5,6])
Since the list has two middle nodes with values 3 and 4, we return the second one.


 
Note:
The number of nodes in the given list will be between 1 and 100.

Submission
https://leetcode.com/submissions/detail/187757428/
You are here! 
Your runtime beats 99.54 % of java submissions.
	 */
    public ListNode middleNode(ListNode head) {
        if(null==head) {
        		return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while(null!=fast && fast.next!=null) {
        		slow = slow.next;
        		fast = fast.next.next;
        }
        
        return slow;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
