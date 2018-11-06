/**
 * 
 */
package sm.coding.ds.tree.ctci._04_04;

import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author smughal
 *
 */
public class CheckBalance_04_04 {

	/**
4.4 Check Balanced: Implement a function to check if a binary tree is balanced. For the purposes of
this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any
node never differ by more than one.
Hints: #21, #33, #49, # 105, #124
 
	 * @param args
	 */
	public static boolean isBalance(TreeNode n) {
		return checkHeight(n)!=Integer.MIN_VALUE;
	}
	
	public static int checkHeight(TreeNode n) {
		if(n==null) {
			return -1;
		}
		
		int leftHeight = checkHeight(n.left);
		if(leftHeight==Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}
		int rightHeight = checkHeight(n.right);
		if(rightHeight==Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}
		
		int heigtDiff = leftHeight - rightHeight;
		if(Math.abs(heigtDiff)>1) {
			return Integer.MIN_VALUE;
		}else {
			return Math.max(leftHeight, rightHeight)+1;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		root.left.right = new TreeNode(4);
		
		root.right.right = new TreeNode(6);

		root.right.right.left = new TreeNode(4);
		BinaryTreePrinter.preetyPrintTree(root);
		System.out.println("Balanced="+isBalance(root));

		
		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(2);
		root2.right = new TreeNode(3);
		
		root2.left.right = new TreeNode(4);
		root2.left.left = new TreeNode(5);
		
		root2.right.right = new TreeNode(6);
		root2.right.left = new TreeNode(7);

		root2.right.right.left = new TreeNode(4);

		
		BinaryTreePrinter.preetyPrintTree(root2);
		System.out.println("Balanced="+isBalance(root2));

	}

}


class SolutionDebug {
	public static String indent = "|---";
	public static void debug(Object msg) {
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
	
	
	public static boolean isBalance(TreeNode n) {
		return checkHeight(n)!=Integer.MIN_VALUE;
	}
	
	public static int checkHeight(TreeNode n) {
		debug("n="+n);
		if(n==null) {
			return -1;
		}
		String actual = incrementIndent();
		int leftHeight = checkHeight(n.left);
		setIndent(actual);
		debug("leftHeight="+leftHeight);
		if(leftHeight==Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}
		actual = incrementIndent();
		int rightHeight = checkHeight(n.right);
		setIndent(actual);
		debug("rightHeight="+rightHeight);
		if(rightHeight==Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}
		
		int heigtDiff = leftHeight - rightHeight;
		debug("heigtDiff="+heigtDiff);
		if(Math.abs(heigtDiff)>1) {
			return Integer.MIN_VALUE;
		}else {
			return Math.max(leftHeight, rightHeight)+1;
		}
	}
}