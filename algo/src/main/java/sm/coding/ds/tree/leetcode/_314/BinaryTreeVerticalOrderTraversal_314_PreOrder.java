/**
 * 
 */
package sm.coding.ds.tree.leetcode._314;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import sm.coding.ds.tree.bst.BSTHelper;
import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author shahzadmughal8410
 *
 */
public class BinaryTreeVerticalOrderTraversal_314_PreOrder {

	
	/**
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
If two nodes are in the same row and column, the order should be from left to right.
Examples 1:
Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]


Examples 2:
Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]


Examples 3:
Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]

Submission
https://leetcode.com/submissions/detail/184789037/
You are here! 
Your runtime beats 22.99 % of java submissions.
	 */
	public static List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		Map<Integer, Map<Integer, List<Integer>>> columns = new TreeMap<>();
		
		verticalOrderHelper(root, columns, 0, 0);
		
		for(Map.Entry<Integer, Map<Integer, List<Integer>>> entry:columns.entrySet()) {
			Map<Integer, List<Integer>> levels = columns.get(entry.getKey());
			List<Integer> nodesAtLevel = new ArrayList<>();
			for(Map.Entry<Integer, List<Integer>> levelEntry: levels.entrySet()) {
				nodesAtLevel.addAll(levels.get(levelEntry.getKey()));
			}
			result.add(nodesAtLevel);
			
		}		
		return result;
    }

	public static void verticalOrderHelper(TreeNode node, Map<Integer, Map<Integer, List<Integer>>> columns, int column, int level) {		
		if(null!=node) {
			// preorder traversal
			//process node
			columns.putIfAbsent(column, new TreeMap<>());
			columns.get(column).putIfAbsent(level, new ArrayList<>());
			columns.get(column).get(level).add(node.val);
			columns.put(column, columns.get(column));
			
			verticalOrderHelper(node.left, columns, column-1, level+1);
			verticalOrderHelper(node.right, columns, column+1, level+1);			
		}
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode root = BSTHelper.createSampleBST();
		BinaryTreePrinter.preetyPrintTree(root);
		
		List<List<Integer>> result = verticalOrder(root);
		result.forEach(r->System.out.println(r));
		
	}

}
