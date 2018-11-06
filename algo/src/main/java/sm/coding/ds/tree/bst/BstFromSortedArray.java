/**
 * 
 */
package sm.coding.ds.tree.bst;

import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * 
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/discuss/
 * 
 * @author smughal
 *
 */
public class BstFromSortedArray {
	public static TreeNode sortedArrayToBST(int[] num) {
	    if (num.length == 0) {
	        return null;
	    }
	    TreeNode root = helper(num, 0, num.length - 1);
	    return root;
	}

	private static TreeNode helper(int[] num, int low, int high) {
	    if (low > high) { // Done
	        return null;
	    }
//	    int mid = (low + high) / 2;
	    int mid = low+ ( (high-low) / 2 );
	    TreeNode node = new TreeNode();
	    node.val = num[mid];
	    node.left = helper(num, low, mid - 1);
	    node.right = helper(num, mid + 1, high);
	    return node;
	}
	
	public static void main(String[] args) {
		int[] nums = {0,1,2,3,4,5,6,7};
		TreeNode root = sortedArrayToBST(nums);
		BinaryTreePrinter.preetyPrintTree(root);
	}

}
