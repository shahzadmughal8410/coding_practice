/**
 * 
 */
package sm.coding.ds.tree.leetcode._108;

import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author shahzadmughal8410
 *
 */
public class ConvertSortedArrayToBinarySearchTree_108 {

	/**
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of everynode never differ by more than 1.
Example:
Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
 
Submission
https://leetcode.com/submissions/detail/185177719/
You are here! 
Your runtime beats 100.00 % of java submissions.
	 */
    public static TreeNode sortedArrayToBST(int[] nums) {
    		return sortedArrayToBST_Helper(nums, 0, nums.length-1);
    }
    
    public static TreeNode sortedArrayToBST_Helper(int[] nums, int start, int end) {
    		if(start>end) { // (end<start) can be used as well
    			return null;
    		}
//        int mid = (start+end) /2;
        int mid =  ( (end-start) /2 ) + start;
        TreeNode root = new TreeNode(nums[mid]);
        root.left =  sortedArrayToBST_Helper(nums, start, mid-1);
        root.right = sortedArrayToBST_Helper(nums, mid+1, end);        
        return root;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums = new int[] {-10,-3,0,5,9};
		TreeNode root = sortedArrayToBST(nums);
		BinaryTreePrinter.preetyPrintTree(root);

	}

}
