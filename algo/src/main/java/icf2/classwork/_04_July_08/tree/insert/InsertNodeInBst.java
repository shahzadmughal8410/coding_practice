package icf2.classwork._04_July_08.tree.insert;

import sm.coding.ds.tree.bt.TreeNode;

public class InsertNodeInBst {

	/**
	 * https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
	 * 
	 * 
	 * T=O(log n)
	 * 
	 * @param root
	 * @param val
	 * @return
	 */
	TreeNode insertBst(TreeNode root, int val) {
		if(null==root) {
			root = new TreeNode(val);
			return root; 
		}
		
		if(val < root.val) {
			root.left = insertBst(root.left, val);
		}
		else if(val>root.val) {
			root.right = insertBst(root.right, val);
		}
		
		return root;
	}	
}


