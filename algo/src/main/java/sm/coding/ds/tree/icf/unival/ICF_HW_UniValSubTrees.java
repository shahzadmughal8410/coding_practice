/**
 * 
 */
package sm.coding.ds.tree.icf.unival;

import sm.coding.ds.tree.bst.BstFromSortedArray;
import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * 
 * Given a binary tree count the unival sub trees.
 * 
 * 1- With counter class:
 * http://www.geeksforgeeks.org/find-count-of-singly-subtrees/ 2- With counter
 * array: https://discuss.leetcode.com/topic/20721/my-concise-java-solution
 * 
 * @author smughal
 *
 */
public class ICF_HW_UniValSubTrees {
	public static int countUnivalSubtrees(TreeNode root) {
		int[] count = new int[1];
		helper(root, count);
		return count[0];
	}

	private static boolean helper(TreeNode node, int[] count) {
		if (node == null) {
			return true;
		}
		boolean left = helper(node.left, count);
		boolean right = helper(node.right, count);

		if (left==false || right==false) {
			return false;
		}
		if (node.left != null && node.val != node.left.val) {
			return false;
		}
		if (node.right != null && node.val != node.right.val) {
			return false;
		}
		count[0]++;
		return true;
	}

	public static void main(String[] args) {
		int[] array = new int[] {1,1,1,2,1,2,2};
//		int[] array = new int[] {1,2,3,3};
		TreeNode root = BstFromSortedArray.sortedArrayToBST(array);
		
//		TreeNode root = new TreeNode(2);
//		root.left = new TreeNode(1);
//		root.right = new TreeNode(2);
//		root.left.left = new TreeNode(1);
//		root.left.right = new TreeNode(1);
//		root.right.left = new TreeNode(1);
//		root.right.right = new TreeNode(2);
		
		BinaryTreePrinter.preetyPrintTree(root);
		System.out.println(countUnivalSubtrees(root));
	}
}
