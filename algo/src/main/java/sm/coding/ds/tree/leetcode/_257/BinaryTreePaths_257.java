/**
 * 
 */
package sm.coding.ds.tree.leetcode._257;

import java.util.ArrayList;
import java.util.List;

import sm.coding.ds.tree.bst.BSTHelper;
import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author shahzadmughal8410
 *
 */
public class BinaryTreePaths_257 {

	/**
Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3

Submission
https://leetcode.com/submissions/detail/186246653/
You are here! 
Your runtime beats 94.70 % of java submissions.
	 * @param root
	 * @return
	 */
	public static List<String> binaryTreePaths(TreeNode root) {
    		List<String> paths = new ArrayList<>();
    		binaryTreePaths_Helper(root, "", paths);
    		return paths;
    }
    
    public static void binaryTreePaths_Helper(TreeNode node, String sofar, List<String> paths) {
    		if(null!=node) {
		    	if(node.left==null && node.right==null) {
		    		paths.add(sofar+node.val);
		    	}else{
		    		binaryTreePaths_Helper(node.left, sofar+node.val+"->", paths);
		    		binaryTreePaths_Helper(node.right, sofar+node.val+"->", paths);
		    	}
    		}
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode root = BSTHelper.createSampleBST();
		BinaryTreePrinter.preetyPrintTree(root);
		List<String> paths = binaryTreePaths(root);
		paths.forEach(p->System.out.println(p));
	}

}
