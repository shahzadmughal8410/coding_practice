/**
 * 
 */
package sm.coding.ds.llstq.leetcode.linked_list._138;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shahzadmughal8410
 *
 */
public class CopyListWithRandomPointer_138 {

	/**
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
Return a deep copy of the list.

Submission
https://leetcode.com/submissions/detail/187877864/
You are here! 
Your runtime beats 63.15 % of java submissions.
	 */
	
	public RandomListNode copyRandomList_BruteForce(RandomListNode head) {
		if(null==head) {
			return null;
		}
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		
		// clone a list like singly linked list and create mapping original-> cloned nodes
		RandomListNode node = head;
		while(null!=node) {
			RandomListNode clone = new RandomListNode(node.label);
			map.put(node, clone);
			node = node.next;
		}
		
		node = head;
		// set the pointers for cloned list
		while(null!=node) {
			// set the next and random pointers for cloned node
			map.get(node).next = map.get(node.next);
			map.get(node).random = map.get(node.random);
			node = node.next;
		}
		return map.get(head);
	}

	/**
Submission
https://leetcode.com/submissions/detail/187882358/
You are here! 
Your runtime beats 99.76 % of java submissions.
	 * @param head
	 * @return
	 */
	public RandomListNode copyRandomList(RandomListNode head) {
		if(null==head) {
			return null;
		}
		
		RandomListNode node = head;
		
		// 1- create clones and insert as next
		while(null!=node) {
			RandomListNode clone = new RandomListNode(node.label);
			clone.next = node.next;
			node.next = clone;
			node = clone.next;
		}
		
		node = head;
		
		// 2- link the random pointers of the cloned nodes
		while(node!=null) {
			node.next.random = (node.random!=null) ? node.random.next : null;
			node = node.next.next;
		}
		
		// 3- correct the next pointers of nodes 
		RandomListNode original = head;
		RandomListNode cloned = head.next;
		RandomListNode clonedHead = head.next;
		
		while(null!=original) {
			original.next = original.next.next;
			cloned.next = (null!= cloned.next) ? cloned.next.next : null;
			
			// update for next iteration
			original = original.next;
			cloned = cloned.next;
		}
		return clonedHead;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
}