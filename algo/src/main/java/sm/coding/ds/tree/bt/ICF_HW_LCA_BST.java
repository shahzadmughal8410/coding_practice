/**
 * 
 */
package sm.coding.ds.tree.bt;

import sm.coding.ds.tree.bst.BstFromSortedArray;

/**
 * 
 * http://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/
 * @author smughal
 *
 */
public class ICF_HW_LCA_BST {
	
	public static TreeNode lca(TreeNode n, int n1, int n2) {
		if(n==null) {
			return null;
		}
		
		if(n1<n.val && n2<n.val) {
			return lca(n.left, n1, n2);
		}
		
		if(n1>n.val && n2>n.val) {
			return lca(n.right, n1, n2);
		}
		return n;
	}
	
	public static void main(String[] args) {
		int[] a = new int[] {1,2,3,4,5,6,7,8,9};
		TreeNode root = BstFromSortedArray.sortedArrayToBST(a);
		BinaryTreePrinter.preetyPrintTree(root);
		System.out.println("lca(root, 2,7)="+lca(root, 2,7).val);
		System.out.println("lca(root, 1,2)="+lca(root, 1,2).val);
		System.out.println("lca(root, 1,9)="+lca(root, 1,9).val);
		System.out.println("lca(root, 6,9)="+lca(root, 6,9).val);
	}

}
