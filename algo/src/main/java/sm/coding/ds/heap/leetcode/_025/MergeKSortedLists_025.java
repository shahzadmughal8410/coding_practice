/**
 * 
 */
package sm.coding.ds.heap.leetcode._025;

import java.util.List;
import java.util.PriorityQueue;

import sm.coding.ds.llstq.leetcode.linked_list.ListNode;

/**
 * @author shahzadmughal8410
 *
 */
public class MergeKSortedLists_025 {

	
	/**

Hard
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
Example:
Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6

Submission
https://leetcode.com/submissions/detail/194153548/
You are here! 
Your runtime beats 24.45 % of java submissions.
	 */
	public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null || lists.length==0) { 
        		return null;
        }
        // priority queue with comparator needs the size
        PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.length, (a,b) -> Integer.compare(a.val, b.val));
        
        ListNode dummy = new ListNode(0);
        ListNode previous=dummy;
        
        // add head of all lists in queue
        for (ListNode node:lists) {
            if (node!=null) {
                queue.add(node);
            }
        }
            
        while (!queue.isEmpty()){
        		ListNode current = queue.poll(); 
            previous.next = current;
            previous=current;
            
            if (current.next!=null)
                queue.add(current.next);
        }
        return dummy.next;
    }	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
