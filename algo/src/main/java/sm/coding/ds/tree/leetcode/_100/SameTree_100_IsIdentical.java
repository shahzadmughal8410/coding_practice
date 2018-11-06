/**
 * 
 */
package sm.coding.ds.tree.leetcode._100;

import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author shahzadmughal8410
 *
 */
public class SameTree_100_IsIdentical {
	
	/**
	 * https://www.geeksforgeeks.org/write-c-code-to-determine-if-two-trees-are-identical/
	 * 
	 * T=O(n), where n is the size on minimum/smaller tree
Submission
https://leetcode.com/submissions/detail/185036326/
You are here! 
Your runtime beats 98.96 % of java submissions.

	 * @param node1
	 * @param node2
	 * @return
	 */
	boolean isSameTree(TreeNode node1, TreeNode node2) {
		
		if(null==node1 && null==node2) {
			return true;
		}
		
		if(null!=node1 && null!=node2) {
			return node1.val==node2.val && isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right);
		}
			
		return false;
	}
}

