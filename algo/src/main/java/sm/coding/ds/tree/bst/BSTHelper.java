package sm.coding.ds.tree.bst;

import sm.coding.ds.tree.bt.TreeNode;

public class BSTHelper {

	public static TreeNode createSampleBST() {
		TreeNode root = new TreeNode();
		root.val = 4;
		
		root.left = new TreeNode();
		root.left.val = 2;
		root.right = new TreeNode();
		root.right.val = 6;
		
		root.left.left = new TreeNode();
		root.left.left.val = 1;
		root.left.right = new TreeNode();
		root.left.right.val = 3;

		root.right.left = new TreeNode();
		root.right.left.val = 5;
		root.right.right = new TreeNode();
		root.right.right.val = 7;

		return root;
	}
}
