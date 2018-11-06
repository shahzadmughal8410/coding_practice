/**
 * 
 */
package sm.coding.ds.tree.icf.unival;

import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author shahzadmughal8410
 *
 */
public class FindCountOfSingleValuedSubtrees {

	/**
Given a binary tree, write a program to count the number of Single Valued Subtrees. A Single Valued Subtree is one in which all the nodes have same value. Expected time complexity is O(n).

Example:

Input: root of below tree
              5
             / \
            1   5
           / \   \
          5   5   5
Output: 4
There are 4 subtrees with single values.


Input: root of below tree
              5
             / \
            4   5
           / \   \
          4   4   5                
Output: 5
There are five subtrees with single values.

	 */
	public static int countUnival(TreeNode root) {
		Count c = new Count();
		countUnival_Helper(root, c);
		return c.val;
	}
	
	public static boolean countUnival_Helper(TreeNode node, Count c) {
		// null means unival
		if(null==node) {
			return true;
		}
		
		// check if left and right subtrees are unival
		boolean leftUnival = countUnival_Helper(node.left, c);
		boolean righttUnival = countUnival_Helper(node.right, c);
		
		// if any of left or right subtree is not unival then this tree is not unival
		if(!leftUnival || !righttUnival) {
			return false;
		}
		
		// if left subtree doesn't match with node then this tree not unival
		if(node.left!=null && node.left.val != node.val) {
			return false;
		}
		
		// if right subtree doesn't match with node then this tree not unival
		if(node.right!=null && node.right.val != node.val) {
			return false;
		}
		
		c.val++;
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(5);

		BinaryTreePrinter.preetyPrintTree(root);

		System.out.println("Unival tree count=" + countUnival(root));

	}

}

class Count {
	int val;
}