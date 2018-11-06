/**
 * 
 */
package sm.coding.ds.tree.leetcode._103;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author smughal
 *
 */
public class BinaryTreeZigzagLevelOrderTraversal_103 {

	/**
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
] 
	 * 
	 * @param args
	 */
	public static List<List<Integer>> zigzagLevelOrder(TreeNode root){
		if(null==root) {
			return null;
		}
		
		List<List<Integer>> result = new ArrayList<>();
		Deque<TreeNode> s1 = new ArrayDeque<>();
		Deque<TreeNode> s2 = new ArrayDeque<>();
		
		s1.push(root);
		
		while(!s1.isEmpty() || !s2.isEmpty()) {
			List<Integer> levelList = new ArrayList<>();
			while(!s1.isEmpty()) {
				TreeNode n = s1.pop();
				levelList.add(n.val);
				if(n.left!=null) {
					s2.push(n.left);
				}
				if(n.right!=null) {
					s2.push(n.right);
				}
			}
			result.add(levelList);
			levelList = new ArrayList<>();
			while(!s2.isEmpty()) {
				TreeNode n = s2.pop();
				levelList.add(n.val);
				if(n.right!=null) {
					s1.push(n.right);
				}
				if(n.left!=null) {
					s1.push(n.left);
				}
			}
			if(!levelList.isEmpty()) {
				result.add(levelList);
			}
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		BinaryTreePrinter.preetyPrintTree(root);
		
		List<List<Integer>> result = zigzagLevelOrder(root);
		System.out.println("Total levels="+result.size());
		result.forEach(r->System.out.println(r));
	}

}
