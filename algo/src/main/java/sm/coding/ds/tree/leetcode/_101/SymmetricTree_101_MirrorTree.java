/**
 * 
 */
package sm.coding.ds.tree.leetcode._101;

import java.util.Deque;
import java.util.LinkedList;

import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author shahzadmughal8410
 *
 */
public class SymmetricTree_101_MirrorTree {

	/**
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
   1
   / \
  2   2
 / \ / \
3  4 4  3


But the following [1,2,2,null,3,null,3] is not:
   1
   / \
  2   2
   \   \
   3    3


Note:
Bonus points if you could solve it both recursively and iteratively.
Submission
https://leetcode.com/submissions/detail/185148614/
You are here! 
Your runtime beats 30.49 % of java submissions.
	 */
	public static boolean isSymmetric_BruteForce(TreeNode root) {
		return isSymmetricHelper_BruteForce(root, root);
	}

	public static boolean isSymmetricHelper_BruteForce(TreeNode node1, TreeNode node2) {
        
		if(null==node1 && null==node2) {
			return true;
		}
		
		if(null!=node1 && null!=node2) {
			return node1.val==node2.val && isSymmetricHelper_BruteForce(node1.left, node2.right) && isSymmetricHelper_BruteForce(node1.right, node2.left);
		}
		
		return false;
    }
	
	/**
Submission
https://leetcode.com/submissions/detail/185151832/
You are here! 
Your runtime beats 67.29 % of java submissions.
	 * @param root
	 * @return
	 */
	public static boolean isSymmetric(TreeNode root) {
		Deque<TreeNode> q = new LinkedList<>();
		q.offer(root);
		q.offer(root);
		
		while(!q.isEmpty()) {
			TreeNode n1 = q.poll();
			TreeNode n2 = q.poll();
			
			if(null==n1 && null==n2) {
				continue;
			}
			if(n1==null || n2==null || n1.val!=n2.val) {
				return false;
			}
			
			q.add(n1.left);
			q.add(n2.right);
			q.add(n1.right);
			q.add(n2.left);			
		}
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
