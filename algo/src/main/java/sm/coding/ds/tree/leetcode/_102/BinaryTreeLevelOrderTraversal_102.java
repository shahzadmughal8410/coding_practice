/**
 * 
 */
package sm.coding.ds.tree.leetcode._102;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author smughal
 *
 */
public class BinaryTreeLevelOrderTraversal_102 {

	
	/**
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
] 
	 */
	public static List<List<Integer>> levelOrderNodes(TreeNode n){
		List<List<Integer>> result = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		
		if(n==null) return result;
		
		q.offer(n);
		
		while(!q.isEmpty()) {
			int level = q.size();
			List<Integer> levelList = new ArrayList<>();
			for(int i =0; i<level;i++) {
				TreeNode current = q.poll();
				if(current.left!=null)
					q.offer(current.left);
				if(current.right!=null)
					q.offer(current.right);
				levelList.add(current.val);
			}
			result.add(levelList);
		}
		
		return result;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(3);
		root.right = new TreeNode(2);

		root.left.left = new TreeNode(5);
		root.left.right = new TreeNode(1);

		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		
		BinaryTreePrinter.preetyPrintTree(root);
		
		List<List<Integer>> nodesAtLevel = levelOrderNodes(root);
		for(List<Integer> nodes:nodesAtLevel) {
			System.out.println(nodes);
		}

	}

}

//class Node {
//	public int val;
//	public Node left;
//	public Node right;
//	
//	public Node(int val) {
//		this.val = val;
//	}
//}
