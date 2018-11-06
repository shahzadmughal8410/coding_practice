/**
 * 
 */
package sm.coding.ds.tree.icf.kthlargest;

import sm.coding.ds.tree.bst.BSTHelper;
import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * 
 * Given the BST return the kth largest value. T=O
For example, in the following BST, if k = 3, then output should be 14, and if k = 5, then output should be 10

                  20
                 /   \
               8     22
             /   \ 
           4     12 
                 /   \
               10   14

 * @author smughal
 *
 */
public class KthLargestElementBST {
	/**
	 * As integers are pass by value, so we cannot pass the k as int. we can do either of the following approaches
	 * 
	 *  1- use Global variable count and do ++ and compare with k
	 *  2- use Class to pass k so that it would be pass by value, and use the came -- operation and check for zero
	 *  3- use array to pass the k so that it would be pass by value, and use the came -- operation and check for zero
	 * @param n
	 * @param c
	 */
	public static int kthLargestInBST(TreeNode n, int k) {
		int[] kthLargest = new int[1];
		kthLargestInBST(n, k, new Count(), kthLargest);
		return kthLargest[0];
	}
	public static void kthLargestInBST(TreeNode n, int k, Count c, int[] kthLargest) {
		if(null==n || c.val==k) {
			return;
		}
		kthLargestInBST(n.right, k, c, kthLargest);
		c.val++;
		if(c.val==k) {
			kthLargest[0] = n.val;
			return;
		}
		kthLargestInBST(n.left, k, c, kthLargest);		
	}

	public static void main(String[] args) {
		TreeNode root = BSTHelper.createSampleBST();
		BinaryTreePrinter.preetyPrintTree(root);
		
		int k = 1;
		System.out.println(k+" largest element is : "+kthLargestInBST(root, k));
		
		k = 2;
		System.out.println(k+" largest element is : "+kthLargestInBST(root, k));

		k = 3;
		System.out.println(k+" largest element is : "+kthLargestInBST(root, k));
		
		k = 5;
		System.out.println(k+" largest element is : "+kthLargestInBST(root, k));
		

	}
}

class Count {
	int val;
}

