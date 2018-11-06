/**
 * 
 */
package sm.coding.ds.tree.leetcode._173;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import sm.coding.ds.tree.bst.BSTHelper;
import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * 
 * 
Tree Iterator!
(Facebook Question)
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the
root node of a BST.
1. Calling next() will return the next smallest number in the BST.
2. Calling hasNext() should return whether the next element exists.
Both functions should run in average O(1) time and uses O(h) memory, where h is the height
of the tree.
[Iterator is a concept in higher level languages like C++ or Java. You probably can
tell what it is. If you want to know more, please feel free to Google for the concept.]
Solutions:
1. With parent pointer: http://stackoverflow.com/questions/12850889/in-order-iterator-forbinary-tree
2. Without parent pointer, but with stack: https://leetcode.com/discuss/20001/my-solutions-in-3-languages-with-stack
3. Without parent pointer, but with stack: https://www.programcreek.com/2014/04/leetcode-binary-search-tree-iterator-java/

Choice of the solution will depend on what the interviewer asks you to do. #2 is generally
preferred i.e. without assuming there is a parent pointer.
Interview Time: 30 minutes.


 * @author smughal
 *
 */
public class BinarySearchTreeIterator_173 {
    /**
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
     * @param args
     */
    public static void main(String[] args) {
		TreeNode root = BSTHelper.createSampleBST();
		System.out.println("BST");
		BinaryTreePrinter.preetyPrintTree(root);
		System.out.println();
		System.out.println("BST Iterator");
		BSTIterator itr = new BSTIterator(root);
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
}

/**
Submission
https://leetcode.com/submissions/detail/186272968/
You are here! 
Your runtime beats 100.00 % of java submissions.
 * @author shahzadmughal8410
 *
 */
class BSTIterator {
    private Deque<TreeNode> stack = new LinkedList<>();

    public BSTIterator(TreeNode root) {
		pushAllLeft(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode tmpNode = stack.pop();
        pushAllLeft(tmpNode.right);
        return tmpNode.val;
    }
    
    private void pushAllLeft(TreeNode node) {
	    	while(node!=null) {
	    		stack.push(node);
	    		node = node.left;
	    	}
    }
}