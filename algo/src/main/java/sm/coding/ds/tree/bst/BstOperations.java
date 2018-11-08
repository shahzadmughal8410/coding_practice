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

	public static TreeNode deleteNode(TreeNode root, int key) {
	    if(root == null){
	        return null;
	    }
	    if(key < root.val){
	        root.left = deleteNode(root.left, key);
	    }else if(key > root.val){
	        root.right = deleteNode(root.right, key);
	    }else{
	        if(root.left == null){
	            return root.right;
	        }else if(root.right == null){
	            return root.left;
	        }
	        
	        TreeNode minNode = findMin(root.right);
	        root.val = minNode.val;
	        root.right = deleteNode(root.right, root.val);
	    }
	    return root;
	}

	/**
Steps:

    Recursively find the node that has the same value as the key, while setting the left/right nodes equal to the returned subtree
    Once the node is found, have to handle the below 4 cases

    node doesn't have left or right - return null
    node only has left subtree- return the left subtree
    node only has right subtree- return the right subtree
    node has both left and right - find the minimum value in the right subtree, set that value to the currently found node, then recursively delete the minimum value in the right subtree

https://leetcode.com/problems/delete-node-in-a-bst/discuss/93296/Recursive-Easy-to-Understand-Java-Solution
	 * @param node
	 * @return
	 */
	private static TreeNode findMin(TreeNode node){
	    while(node.left != null){
	        node = node.left;
	    }
	    return node;
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
