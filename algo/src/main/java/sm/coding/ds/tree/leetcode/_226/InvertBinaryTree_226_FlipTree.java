/**
 * 
 */
package sm.coding.ds.tree.leetcode._226;

import java.util.Deque;
import java.util.LinkedList;

import sm.coding.ds.tree.bst.BSTHelper;
import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author shahzadmughal8410
 *
 */
public class InvertBinaryTree_226_FlipTree {

	/**
Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
Trivia:
This problem was inspired by this original tweet by Max Howell:

Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.

Submission
https://leetcode.com/submissions/detail/186359247/
You are here! 
Your runtime beats 100.00 % of java submissions.
	 */
    public static TreeNode invertTree(TreeNode root) {
    		invertTree_Helper(root);
    		return root;
    }

    public static void invertTree_Helper(TreeNode node) {
    		if(null!=node) {
    			// processing part
    			TreeNode tmp = node.left;
    			node.left = node.right;
    			node.right = tmp;
    			
    			invertTree_Helper(node.left);
    			invertTree_Helper(node.right);
    		}
    }
    
    
    public static TreeNode invertTree_Iterative(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return root;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode root = BSTHelper.createSampleBST();
		BinaryTreePrinter.preetyPrintTree(root);
		
		invertTree(root);
		BinaryTreePrinter.preetyPrintTree(root);

		invertTree_Iterative(root);
		BinaryTreePrinter.preetyPrintTree(root);

	}

}
