/**
 * 
 */
package sm.coding.ds.tree.leetcode._116;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author smughal
 *
 */
public class PopulatingNextRightPointersInEachNode_116_117_ConnectSibling {

	/**
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
   /          \
  8            9
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
   /          \
  8----------> 9 -> NULL
  
2-Loops
1- row by row
2- col by col

ICF2 iqbal bhai solution
https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/discuss/37828/O(1)-space-O(n)-complexity-Iterative-Solution

Submission - 117
https://leetcode.com/submissions/detail/186241584/
You are here! 
Your runtime beats 88.82 % of java submissions.

Submission - 116
https://leetcode.com/submissions/detail/186241894/
You are here! 
Your runtime beats 36.56 % of java submissions.

    */
	
	/* Sets next of all TreeLinkNodes of a tree with root as levelNode */
	public static void connect(TreeLinkNode levelNode) {

		if (levelNode == null)
			return;

		// Set next for root
		levelNode.next = null;

		// set next of all levels one by one
		while (levelNode != null) {
			TreeLinkNode sublingNode = levelNode;

			/*
			 * Connect all childrem TreeLinkNodes of levelNode and children TreeLinkNodes of all other
			 * TreeLinkNodes at same level as levelNode
			 */
			while (sublingNode != null) {
				// Set the next pointer for sublingNode's left child
				if (sublingNode.left != null) {

					// If q has right child, then right child is next of
					// p and we also need to set next of right child
					if (sublingNode.right != null)
						sublingNode.left.next = sublingNode.right;
					else
						sublingNode.left.next = getNextRight(sublingNode);
				}

				if (sublingNode.right != null)
					sublingNode.right.next = getNextRight(sublingNode);

				// Set next for other TreeLinkNodes in pre order fashion
				sublingNode = sublingNode.next;
			}

			// start from the first TreeLinkNode of next level
			if (levelNode.left != null)
				levelNode = levelNode.left;
			else if (levelNode.right != null)
				levelNode = levelNode.right;
			else
				levelNode = getNextRight(levelNode);
		}
	}

	/*
	 * This function returns the leftmost child of TreeLinkNodes at the same level as p.
	 * This function is used to getNExt right of p's right child If right child of
	 * is NULL then this can also be sued for the left child
	 */
	private static TreeLinkNode getNextRight(TreeLinkNode p) {
		TreeLinkNode temp = p.next;

		/*
		 * Traverse TreeLinkNodes at p's level and find and return the first TreeLinkNode's
		 * first child
		 */
		while (temp != null) {
			if (temp.left != null)
				return temp.left;
			if (temp.right != null)
				return temp.right;
			temp = temp.next;
		}

		// If all the TreeLinkNodes at p's level are leaf TreeLinkNodes then return NULL
		return null;
	}
	
	
	/**
Submission
https://leetcode.com/submissions/detail/186051047/
You are here! 
Your runtime beats 100.00 % of java submissions.     
	 * @param args
	 */
	public static void connect_Perfect_Binary_Tree(TreeLinkNode n) {
		if(null==n) {
			return;
		}
		TreeLinkNode levelNode = n;
		
		while(null!=levelNode) {
			TreeLinkNode siblingNode = levelNode;
			
			while(null!=siblingNode) {
				if(siblingNode.left!=null) {
					siblingNode.left.next = siblingNode.right;
					if(siblingNode.next!=null) {
						siblingNode.right.next = siblingNode.next.left;
					}
				}
				siblingNode = siblingNode.next;
			}
			levelNode = levelNode.left;
		}
	}
	
	
	public static void main(String[] args) {
		TreeLinkNode root = new TreeLinkNode(1);
		root.left = new TreeLinkNode(2);
		root.right = new TreeLinkNode(3);
		
		root.left.left = new TreeLinkNode(4);
		root.left.right = new TreeLinkNode(5);

		root.right.left = new TreeLinkNode(6);
		root.right.right = new TreeLinkNode(7);
		
		
		SolutionDebug.connect(root);
		
		System.out.println(root.val+"->"+root.next);
		System.out.println(root.left.val+"->"+root.left.next.val+"->"+root.left.next.next);
		System.out.println(root.left.left.val+"->"+root.left.left.next.val+"->"+root.left.left.next.next.val+"->"+root.left.left.next.next.next.val+"->"+root.left.left.next.next.next.next);

		
		root.left.left.left = new TreeLinkNode(8);
		root.right.right.right = new TreeLinkNode(9);

		connect(root);
		
		System.out.println(root.val+"->"+root.next);
		System.out.println(root.left.val+"->"+root.left.next.val+"->"+root.left.next.next);
		System.out.println(root.left.left.val+"->"+root.left.left.next.val+"->"+root.left.left.next.next.val+"->"+root.left.left.next.next.next.val+"->"+root.left.left.next.next.next.next);
		System.out.println(root.left.left.left.val+"->"+root.left.left.left.next.val);
	
	}

}

class TreeLinkNode extends TreeNode{
	public TreeLinkNode next;
	public TreeLinkNode left;
	public TreeLinkNode right;
	
	public TreeLinkNode(int val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return val+"";
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
	
	public static void connect(TreeLinkNode n) {
		debug("root="+n);
		if(null==n) {
			return;
		}
		TreeLinkNode levelNode = n;
		
		while(null!=levelNode) {
			debug("levelNode="+levelNode);
			TreeLinkNode siblingNode = levelNode;
			
			while(null!=siblingNode) {
				debug("levelNode="+levelNode+", siblingNode="+siblingNode);
				debug("siblingNode.left="+siblingNode.left);
				if(siblingNode.left!=null) {
					siblingNode.left.next = siblingNode.right;
					debug("Connected "+siblingNode.left+"-->"+siblingNode.right);
					debug("siblingNode.next="+siblingNode.next);
					if(siblingNode.next!=null) {
						siblingNode.right.next = siblingNode.next.left;
						debug("Connected "+siblingNode.right+"-->"+siblingNode.next.left);
					}
				}
				debug("next subling="+siblingNode.next);
				siblingNode = siblingNode.next;
			}
			debug("next level="+levelNode.left+"\n");
			levelNode = levelNode.left;
		}
	}

}