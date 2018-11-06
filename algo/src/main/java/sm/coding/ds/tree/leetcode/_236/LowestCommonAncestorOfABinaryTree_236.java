/**
 * 
 */
package sm.coding.ds.tree.leetcode._236;

import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author shahzadmughal8410
 *
 */
public class LowestCommonAncestorOfABinaryTree_236 {

	/**
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of of nodes 5 and 1 is 3.
Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself
             according to the LCA definition.
Note:

All of the nodes' values will be unique.
p and q are different and both values will exist in the binary tree.

Submission
https://leetcode.com/submissions/detail/185266735/
You are here! 
Your runtime beats 98.90 % of java submissions.
	 */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    		// base case
    		if(null==root) {
    			return null;
    		}
    		// root of node matches any of either p or q
    		if(root==p || root==q) {
    			return root;
    		}
    		
    		// find either p or q exists in either of left or right sub tree
    		TreeNode leftLca = lowestCommonAncestor(root.left, p, q);
    		TreeNode rightLca = lowestCommonAncestor(root.right, p, q);
    		
    		// if both left and right lca is not null, means p and q 
    		// one of them exists in left sub tree and other exists in right sub tree
    		// so this node is the LCA as its parent of both
    		if(null!=leftLca && null!=rightLca) {
    			return root;
    		}
    		
    		// if either of let or right LCA is null, return the not null node
    		return null!=leftLca ? leftLca : rightLca;
    }


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
