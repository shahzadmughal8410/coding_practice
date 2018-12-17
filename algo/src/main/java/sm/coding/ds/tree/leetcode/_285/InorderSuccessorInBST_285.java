/**
 * 
 */
package sm.coding.ds.tree.leetcode._285;

import sm.coding.ds.tree.bst.BstOperations;
import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author smughal
 *
 */
public class InorderSuccessorInBST_285 {

	/**
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
Note: If the given node has no in-order successor in the tree, return null. 

Submission
https://leetcode.com/submissions/detail/195492429/
You are here! 
Your runtime beats 99.60 % of java submissions.


	 * @param args
	 */
	public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		if(null==p) {
			return null;
		}		
		TreeNode successor = null;
		while(null!=root) {
			if(p.val<root.val) {
				successor = root;
				root = root.left;						
			}else {
				root = root.right;
			}
		}
		return successor;
	}
	
	public static void main(String[] args) {
/*
       6               
      / \       
     /   \      
    /     \     
   /       \    
   3       8       
  / \     / \   
 /   \   /   \  
 2   5   7   9   
/   /         \ 
1   4         10 
                                 		
 */
		TreeNode root = new TreeNode(6);
		root.left = new TreeNode(3);
		root.right = new TreeNode(8);
		
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(5);

		root.left.left.left = new TreeNode(1);
		root.left.right.left = new TreeNode(4);
		
		root.right.left = new TreeNode(7);
		root.right.right = new TreeNode(9);
		root.right.right.right = new TreeNode(10);
		
		BinaryTreePrinter.preetyPrintTree(root);
		
		TreeNode successor = inorderSuccessor(root, root.left.right.left); // 4
		System.out.println("Successor of 4 is 5=>"+successor);// 5
		
		successor = inorderSuccessor(root, root.left); // 3
		System.out.println("Successor of 3 is 4=>"+successor); // 4

		successor = inorderSuccessor(root, root.right); // 8
		System.out.println("Successor of 8 is 9=>"+successor); // 9

		successor = inorderSuccessor(root, root); // 6
		System.out.println("Successor of 6 is 7=>"+successor); // 7

		successor = inorderSuccessor(root, null); // null
		System.out.println("Successor of null is null=>"+successor); // null

		successor = inorderSuccessor(null, root); // null
		System.out.println("Successor of null tree is null=>"+successor); // null

	}

}
