/**
 * 
 */
package sm.coding.ds.tree.leetcode._098;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import sm.coding.ds.tree.bst.BSTHelper;
import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author shahzadmughal8410
 *
 */
public class ValidateBinarySearchTree_098_PreOrder {

	/**
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:

Input:
    2
   / \
  1   3
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6
Output: false
Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
             is 5 but its right child's value is 4.

Submission
https://leetcode.com/submissions/detail/184479310/
You are here! 
Your runtime beats 50.04 % of java submissions.
	 */
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }
    
    public static boolean isValidBSTHelper(TreeNode node, double min, double max) {
    		if(null==node) {
    			return true;
    		}
    		
    		if(node.val<=min || node.val>=max) {
    			return false;
    		}
    		
    		return isValidBSTHelper(node.left, min, node.val) && isValidBSTHelper(node.right, node.val, max);
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode root = BSTHelper.createSampleBST();
		BinaryTreePrinter.preetyPrintTree(root);
		System.out.println("Is BST="+isValidBST(root));
		
		root.val = 8;
		BinaryTreePrinter.preetyPrintTree(root);
		System.out.println("Is BST="+SolutionDebug.isValidBST(root));
	}

}


class SolutionDebug {

	static StringBuilder format = new StringBuilder();
	static String [] columns = new String[] {};
	public static void tableColumns(String ... cols) {
		for(int i = 0; i<cols.length;i++) {
			String c = cols[i];
			if(c.indexOf(":")!=-1) {
				String[] arr = c.split(Pattern.quote(":"));
				format.append("|%-").append(arr[1]).append("s ");
				cols[i] = arr[0];
			}else {
				format.append("|%-").append(c.length()).append("s ");
			}
			
		}
		format.append("|");
//		debugRow(cols);
		columns = cols;
	}
	
	public static void debugRow(Object ... cols) {
		debug(String.format(format.toString(), cols));
	}

	public static void debug(Object msg) {
		System.out.println("DEBUG: "+msg);
	}

	public static void debugColumns() {
		debugRow(columns);
	}
	
	public static void reset() {
		format = new StringBuilder();
		columns = new String[] {};
	}

	public static String indent = "|---";
	public static void debugRecr(Object msg) {
		System.out.println("DEBUG "+indent+">"+msg);
	}
	public static String incrementIndent() {
		String indentActual = indent;
		indent = indent+"|---";
		return indentActual;
	}
	public static void setIndent(String newIndent) {
		indent = newIndent;
	}

	public static String grid(Integer grid[][]) {
		return grid(grid, null, null, "-", "-");
	}

	public static String grid(Integer grid[][], List rowHeader, List colHeader, String rowHeading, String colHeading) {
		int padding = 5;
		StringBuilder output = new StringBuilder("\n");
		// output index of columns
		output.append("index");
		output.append( String.format(" | %1$-"+Math.max(padding, rowHeading.length()+colHeading.length())+"s ", "-") );
		int underline = 0;
		for(int i =0; i<grid[0].length; i++) {
			output.append( String.format(" | %1$-"+padding+"s ", i) );
		}
		output.append("|\n");
		underline = output.length();
		
		if(null!=colHeader) {
			output.append("-");
			output.append( String.format("     | %1$-"+Math.max(padding, rowHeading.length()+colHeading.length())+"s ", rowHeading+colHeading) );
			for(int i =0; i<grid[0].length; i++) {
				output.append( String.format(" | %1$-"+padding+"s ", colHeader.get(i)) );
			}
		}

		output.append("|\n");
		IntStream.range(0, underline).forEach(i->output.append("-"));
		output.append("\n");
		
		for(int r=0; r<grid.length; r++) {
			// output index of rows
			output.append( r+ String.format("     | %1$-"+Math.max(padding, rowHeading.length()+colHeading.length())+"s ", (rowHeader!=null ? rowHeader.get(r) : "" ) )  );
			
			// grid data
			for(int c=0; c<grid[r].length; c++) {
				output.append( String.format(" | %1$-"+padding+"s ", grid[r][c]) );
			}
			output.append("|\n");
		}
		return output.toString();
	}
	
	public static List asList(Object [] list, int padding, Object value) {
		List l = new ArrayList<>();
		for(int i=0; i<padding; i++) {
			l.add(value);
		}
		l.addAll(Arrays.asList(list));
		return l;
	}
	
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }
    
    public static boolean isValidBSTHelper(TreeNode node, double min, double max) {
    		if(null==node) {
    			debugRecr("Node null returning true.");
    			return true;
    		}
    		debugRecr("Node="+node.val+", left="+node.left+", right="+node.right +", min="+min+", max="+max);
    		
    		if(node.val<=min || node.val>=max) {
    			debugRecr("BST check failed, node="+node.val+", min="+min+", max="+max);
    			return false;
    		}
    		String actual = incrementIndent();
    		boolean leftSubtreeValid =  isValidBSTHelper(node.left, min, node.val);
    		boolean rightSubTreeValid = isValidBSTHelper(node.right, node.val, max);
    		setIndent(actual);
    		debugRecr("leftSubtreeValid="+leftSubtreeValid+", rightSubTreeValid="+rightSubTreeValid);
    		return leftSubtreeValid && rightSubTreeValid;
    }
	
}