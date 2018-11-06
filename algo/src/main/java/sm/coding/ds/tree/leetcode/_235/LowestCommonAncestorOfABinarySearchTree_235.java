/**
 * 
 */
package sm.coding.ds.tree.leetcode._235;

import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author shahzadmughal8410
 *
 */
public class LowestCommonAncestorOfABinarySearchTree_235 {

	/**
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
       _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5


Example 1:
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.


Example 2:
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself 
             according to the LCA definition.


Note:
All of the nodes' values will be unique.
p and q are different and both values will exist in the BST.

Submission
https://leetcode.com/submissions/detail/185482336/
You are here! 
Your runtime beats 99.98 % of java submissions.
	 */
    public static TreeNode lowestCommonAncestor_BruteForce(TreeNode root, TreeNode p, TreeNode q) {
    		if(null==root) {
    			return null;
    		}
    		// If both p and q are smaller than root, then LCA lies in left 
    		if(p.val<root.val && q.val<root.val) {
    			return lowestCommonAncestor_BruteForce(root.left, p, q);
    		}
		// If both p and q are greater than root, then LCA lies in right 	
    		if( p.val>root.val && q.val>root.val) {
    			return lowestCommonAncestor_BruteForce(root.right, p, q);
    		}
    		// value is between node left and node right then node is the LCA
    		return root;
    }
    
    /**
Submission
https://leetcode.com/submissions/detail/185483888/
You are here! 
Your runtime beats 99.98 % of java submissions.

     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    		while(null!=root) {
        		// If both p and q are smaller than root, then LCA lies in left 
    			if(p.val<root.val && q.val<root.val) {
    				root = root.left;
    				// If both p and q are greater than root, then LCA lies in right 	
    			}else if(p.val>root.val && q.val>root.val) {
    				root = root.right;
    			} else {
    				// root is the LCA
    				return root;
    			}
    		}    		
    		return null;
    }

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
