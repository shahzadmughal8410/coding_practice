package sm.coding.ds.tree.bt;

import java.util.Deque;
import java.util.LinkedList;

import sm.coding.ds.tree.bst.BSTHelper;

/**
 * Tree traversal are same for binary tree and binary search tree(BST).
 * 
 * @author smughal
 *
 */
public class BinaryTreeTraversal {

	/**
	 * Visiting order for inOrder is Left, Current, Right i.e. LCR
	 * @param n
	 */
	public static void inOrder_Dfs(TreeNode n) {
		if(n!=null) {
			inOrder_Dfs(n.left);
			// process node
			visit(n);
			inOrder_Dfs(n.right);
		}
	}
	
	public static void inOrder_Iterative(TreeNode node) {
        // Base Case 
	    if(node == null) {
	    		return;	    
	    }
	    Deque<TreeNode> stack = new LinkedList<>();
	    while(node != null || !stack.isEmpty()){
	        while(node != null){
	            stack.push(node);
	            node = node.left;
	        }
	        node = stack.pop();
	        //process node
	        visit(node);
	        node = node.right;	        
	    }
	}


	/**
	 * Visiting order for preOrder is Current, Left, Right i.e. CLR
	 * @param node
	 */
	public static void preOrder_Dfs(TreeNode node) {
		if(node!=null) {
			// process node
			visit(node);
			preOrder_Dfs(node.left);
			preOrder_Dfs(node.right);
		}
	}
	
	public static void preOrder_Iterative(TreeNode node) {
        // Base Case 
        if (node == null) { 
            return; 
        } 
  
        // Create an empty stack and push root to it 
	    Deque<TreeNode> stack = new LinkedList<>();
        stack.push(node); 
  
        /* Pop all items one by one. Do following for every popped item 
         a) print it 
         b) push its right child 
         c) push its left child 
         Note that right child is pushed first so that left is processed first */
        while (!stack.isEmpty()) { 
              
            // Pop the top item from stack and print it 
            TreeNode currentNode = stack.pop();
            visit(currentNode);
  
            // Push right and left children of the popped node to stack 
            if (currentNode.right != null) { 
                stack.push(currentNode.right); 
            } 
            if (currentNode.left != null) { 
                stack.push(currentNode.left); 
            } 
        } 		
	}


	/**
	 * Visiting order for preOrder is Left, Right, Current i.e. LRC
	 * @param node
	 */
	public static void postOrder_Dfs(TreeNode node) {
		if(node!=null) {
			postOrder_Dfs(node.left);
			postOrder_Dfs(node.right);
			// process node
			visit(node);
		}
	}
	
	public static void postOrder_Iterative(TreeNode node) {
		// base case
		if (node == null)
			return;

		// Create two stacks
		Deque<TreeNode> s1 = new LinkedList<>();
		Deque<TreeNode> s2 = new LinkedList<>();

		// push root to first stack
		s1.push(node);

		// Run while first stack is not empty
		while (!s1.isEmpty()) {
			// Pop an item from s1 and push it to s2
			TreeNode currentNode = s1.pop();
			s2.push(currentNode);

			// Push left and right children of
			// removed item to s1
			if (currentNode.left != null) {
				s1.push(currentNode.left);
			}
			if (currentNode.right != null) {
				s1.push(currentNode.right);
			}
		}

		// Print all elements of second stack
		while (!s2.isEmpty()) {
			TreeNode CurrentNode = s2.pop();
			visit(CurrentNode);
		}
	}


	public static void visit(TreeNode n) {
		System.out.print(n.val+",");
	}
	
	/**
	 * Level order traversal.
	 * Its a BFS.
	 * @param n
	 */
	public static void levelOrderTraversal_Bfs(TreeNode n) {
		if(null==n) {
			return;
		}
		Deque<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(n);
		
		while(!q.isEmpty()) {	
			TreeNode current = q.poll();
			// process node
			visit(current);
			
			if(current.left!=null) {
				q.offer(current.left);
			}
			if(current.right!=null) {
				q.offer(current.right);
			}
		}
	
	}
	
	public static void main(String[] args) {
		TreeNode root = BSTHelper.createSampleBST();
		BinaryTreePrinter.preetyPrintTree(root);
		System.out.print("In Order    =");
		inOrder_Dfs(root);
		System.out.println();

		System.out.print("InOrder Itr =");
		inOrder_Iterative(root);
		System.out.println();
		
		System.out.print("Pre Order   =");
		preOrder_Dfs(root);
		System.out.println();
		
		System.out.print("PreOrder Itr=");
		preOrder_Iterative(root);
		System.out.println();
		
		System.out.print("Post Order  =");
		postOrder_Dfs(root);
		System.out.println();
		
		System.out.print("PostOrder it=");
		postOrder_Dfs(root);
		System.out.println();
		
		System.out.print("Level Order =");
		levelOrderTraversal_Bfs(root);
	}
}
