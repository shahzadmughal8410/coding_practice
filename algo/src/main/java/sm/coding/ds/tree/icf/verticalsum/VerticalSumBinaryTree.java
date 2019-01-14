/**
 * 
 */
package sm.coding.ds.tree.icf.verticalsum;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
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
public class VerticalSumBinaryTree {

	/**
Given a Binary Tree, find vertical sum of the nodes that are in same vertical line. Print all sums through different vertical lines.
Examples:

      1
    /   \
  2      3
 / \    / \
4   5  6   7
The tree has 5 vertical lines.

Vertical-Line-1 has only one node 4 => vertical sum is 4
Vertical-Line-2: has only one node 2=> vertical sum is 2
Vertical-Line-3: has three nodes: 1,5,6 => vertical sum is 1+5+6 = 12
Vertical-Line-4: has only one node 3 => vertical sum is 3
Vertical-Line-5: has only one node 7 => vertical sum is 7

So expected output is 4, 2, 12, 3 and 7

Submitted
	 */
	public static Map<Integer, Integer> verticalSum(TreeNode root) {
		Map<Integer, Integer> sum = new TreeMap<>();
		verticalSumHelper(root, 0, sum);
		return sum;
	}
	
	public static void verticalSumHelper(TreeNode node, int column, Map<Integer, Integer> sum) {
		
		if(null!=node) {
			// pre order
			// process node
			sum.put(column, sum.getOrDefault(column, 0)+node.val);
			
			verticalSumHelper(node.left, column-1, sum);
			verticalSumHelper(node.right, column+1, sum);
		}
		
	}
	
	public static LinkedListNode verticalSum_LinkedList(TreeNode root) {
		LinkedListNode llNode = new LinkedListNode();
		verticalSumHelper_LinkedList(root, llNode);
		
		// go to the first node in doubly linked list
		while(llNode.previous!=null) {
			llNode = llNode.previous;
		}
		
		return llNode;
	}
	
	public static void verticalSumHelper_LinkedList(TreeNode node, LinkedListNode llNode) {
		
		// pre order
		// process node
		llNode.val = llNode.val + node.val;
		
		if(node.left!=null) {
			if(llNode.previous==null) {
				llNode.previous = new LinkedListNode();
				llNode.previous.next = llNode;
			}
			verticalSumHelper_LinkedList(node.left, llNode.previous);
		}
		
		if(node.right!=null) {
			if(llNode.next==null) {
				llNode.next = new LinkedListNode();
				llNode.next.previous = llNode;
			}
			verticalSumHelper_LinkedList(node.right, llNode.next);
		}
		
	}
	
	public static List<Integer> verticalSum_BFS(TreeNode root) {
	    List<Integer> res = new ArrayList<>();
	    if (root == null) {
	        return res;
	    }
	    
	    Map<Integer, Integer> map = new HashMap<>();
	    Deque<TreeNode> q = new LinkedList<>();
	    Deque<Integer> cols = new LinkedList<>();

	    q.add(root); 
	    cols.add(0);

	    int min = 0;
	    int max = 0;
	    
	    while (!q.isEmpty()) {
	        TreeNode node = q.poll();
	        int col = cols.poll();
	        
	        map.put(col, map.getOrDefault(col, 0)+node.val);

	        if (node.left != null) {
	            q.add(node.left); 
	            cols.add(col - 1);
	            min = Math.min(min, col - 1);
	        }
	        
	        if (node.right != null) {
	            q.add(node.right);
	            cols.add(col + 1);
	            max = Math.max(max, col + 1);
	        }
	    }

	    for (int i = min; i <= max; i++) {
	        res.add(map.get(i));
	    }

	    return res;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode root = BSTHelper.createSampleBST();
		BinaryTreePrinter.preetyPrintTree(root);
		Map<Integer, Integer> sum = verticalSum(root);

		for(Map.Entry<Integer, Integer> entry: sum.entrySet()) {
			System.out.println("Column "+entry.getKey()+" has sum "+entry.getValue());
		}
		
		LinkedListNode llNode = verticalSum_LinkedList(root);
		
		while(null!=llNode) {
			System.out.println(llNode.val);
			llNode = llNode.next;
		}
		
		System.out.println("BFS\n"+verticalSum_BFS(root));
	}

}

class LinkedListNode {
	int val;
	LinkedListNode previous;
	LinkedListNode next;
}
