/**
 * 
 */
package sm.coding.ds.tree.bt;

import sm.coding.ds.tree.bst.BstFromSortedArray;

/**
 * http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 * 
 * @author smughal
 *
 */
public class ICF_HM_LCA_BT {

	public static TreeNode lca(TreeNode n, int n1, int n2) {
		if(n==null) {
			return null;
		}
		if(n.val==n1 || n.val==n2) {
			return n;
		}
		
		TreeNode left = lca(n.left, n1, n2);
		TreeNode right = lca(n.right, n1, n2);
		
		if(left!=null && right!=null) {
			return n;
		}
		if(left!=null) {
			return left;
		}else {
			return right;
		}
	}
	
	public static void main(String[] args) {
		int[] a = new int[] {1,9,5,4,3,6,10,8,11};
		TreeNode root = BstFromSortedArray.sortedArrayToBST(a);
		BinaryTreePrinter.preetyPrintTree(root);
		System.out.println("lca(root, 9,10)="+lca(root, 9,10).val);
		System.out.println("lca(root, 4,6)="+lca(root, 4,6).val);
		System.out.println("lca(root, 1,9)="+lca(root, 1,9).val);
		System.out.println("lca(root, 9,11)="+lca(root, 9,11).val);
		System.out.println("lca(root, 6,11)="+lca(root, 6,11).val);
	}
}
