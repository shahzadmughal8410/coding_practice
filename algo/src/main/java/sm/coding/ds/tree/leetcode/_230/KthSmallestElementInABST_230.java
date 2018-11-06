/**
 * 
 */
package sm.coding.ds.tree.leetcode._230;

import sm.coding.ds.tree.bst.BSTHelper;
import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author shahzadmughal8410
 *
 */
public class KthSmallestElementInABST_230 {

	/**
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
How would you optimize the kthSmallest routine?

Submission
https://leetcode.com/submissions/detail/185186754/
You are here! 
Your runtime beats 63.17 % of java submissions.
	 */
    public static int kthSmallest(TreeNode root, int k) {
		int[] kthSmallest = new int[1];
		kthSmallest_Helper(root, k, new Count(), kthSmallest);
		return kthSmallest[0];
	}
	public static void kthSmallest_Helper(TreeNode node, int k, Count c, int[] kthSmallest) {
		if(null==node || c.val==k) {
			return;
		}
		kthSmallest_Helper(node.left, k, c, kthSmallest);
		c.val++;
		if(c.val==k) {
			kthSmallest[0] = node.val;
			return;
		}
		kthSmallest_Helper(node.right, k, c, kthSmallest);		
	}	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode root = BSTHelper.createSampleBST();
		BinaryTreePrinter.preetyPrintTree(root);
		
		int k = 1;
		System.out.println(k+" largest element is : "+kthSmallest(root, k));
		
		k = 2;
		System.out.println(k+" largest element is : "+kthSmallest(root, k));

		k = 3;
		System.out.println(k+" largest element is : "+kthSmallest(root, k));
		
		k = 5;
		System.out.println(k+" largest element is : "+kthSmallest(root, k));
	}

}

class Count {
	int val;
}
