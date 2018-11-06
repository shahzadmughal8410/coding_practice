/**
 * 
 */
package sm.coding.ds.tree.bst;

import sm.coding.ds.tree.bt.BinaryTreeTraversal;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author smughal
 *
 */
public class BstOperations {
	
	/**
	 * 
	 * @param n - root node
	 * @param data - data to insert in bst
	 * @return Intact input Node object its its not null, New node object with value set to data is its null
	 */
	public static TreeNode insert(TreeNode n, int data) {
		if(null==n) {
			n = new TreeNode(data);
			return n; 
		}
		
		if(data < n.val) {
			n.left = insert(n.left, data);
		}
		else if(data>n.val) {
			n.right = insert(n.right, data);
		}
		
		return n;
	}
	
	public static void main(String[] args) {
		testInsertInExistingTree();
		testInsertNullRoot();
		testInsertDuplicateNode();
	}
	
	public static void testInsertInExistingTree() {
		TreeNode root = BSTHelper.createSampleBST();
		System.out.println("Before insertion=");
		BinaryTreeTraversal.inOrder_Dfs(root);
		
		insert(root, 13);
		insert(root, -2);
		insert(root, 9);
		
		System.out.println();
		System.out.println("After insertion=");
		BinaryTreeTraversal.inOrder_Dfs(root);
	}
	
	public static void testInsertNullRoot() {
		TreeNode root = null;
		System.out.println();
		System.out.println("Before insertion=");
		BinaryTreeTraversal.inOrder_Dfs(root);
		
		root = insert(root, 13);
		insert(root, -2);
		insert(root, 9);
		
		System.out.println();
		System.out.println("After insertion=");
		BinaryTreeTraversal.inOrder_Dfs(root);
	}

	public static void testInsertDuplicateNode() {
		TreeNode root = BSTHelper.createSampleBST();
		System.out.println();
		System.out.println("Before insertion=");
		BinaryTreeTraversal.inOrder_Dfs(root);
		
		insert(root, 1);
		insert(root, -2);
		insert(root, -2);
		
		System.out.println();
		System.out.println("After insertion=");
		BinaryTreeTraversal.inOrder_Dfs(root);
	}

}
