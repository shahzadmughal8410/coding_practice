/**
 * 
 */
package sm.coding.ds.tree.leetcode._105;

import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author smughal
 *
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal_105 {

	/**
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

 
	 * @param args
	 */
	public static TreeNode createTree(int[] preOrder, int[] inOrder) {
		return createTree(preOrder, inOrder, new int[1], 0, inOrder.length-1);
	}

	private static TreeNode createTree(int[] preOrder, int[] inOrder, int[] preCount, int start, int end) {
		if(start>end) {
			return null;
		}
		
		int rootIndex = 0;
		for(int i =0; i<inOrder.length;i++) {
			if(inOrder[i]==preOrder[preCount[0]]) {
				rootIndex = i;
				break;
			}
		}
		
		TreeNode root = new TreeNode(preOrder[preCount[0]]);
		++preCount[0];
		root.left = createTree(preOrder, inOrder, preCount, start, rootIndex-1);
		root.right = createTree(preOrder, inOrder, preCount, rootIndex+1, end);
		
		return root;
	}

	
	public static void main(String[] args) {
		int[] inOrder = new int[] {2,6,1,5,4,7};
		int[] preOrder = new int[] {5,6,2,1,7,4};
		
		TreeNode root = createTree(preOrder, inOrder);
		
		BinaryTreePrinter.preetyPrintTree(root);

	}

}
