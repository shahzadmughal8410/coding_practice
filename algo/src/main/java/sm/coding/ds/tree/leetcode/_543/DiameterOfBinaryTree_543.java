/**
 * 
 */
package sm.coding.ds.tree.leetcode._543;

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
public class DiameterOfBinaryTree_543 {

	/**
Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
Example:
Given a binary tree 
         1
         / \
        2   3
       / \     
      4   5    


Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
Note: The length of path between two nodes is represented by the number of edges between them.

Submission
https://leetcode.com/submissions/detail/184936380/
You are here! 
Your runtime beats 10.74 % of java submissions.
	 */
    public static int diameterOfBinaryTree_BruteForce(TreeNode root) {
        if(null==root) {
        		return 0;
        }
        
        int leftHeight = height_BruteForce(root.left);
        int rightHeight = height_BruteForce(root.right);
        
        int leftDia = diameterOfBinaryTree_BruteForce(root.left);
        int rightDia = diameterOfBinaryTree_BruteForce(root.right);
        
//        return Math.max(1+leftHeight+rightHeight, Math.max(leftDia, rightDia)); for Vertices +1 is needed
        return Math.max(leftHeight+rightHeight, Math.max(leftDia, rightDia)); // edges are always -1 then vertices
    }
    
    public static int height_BruteForce(TreeNode node) {
    		if(null==node) {
    			return 0;
    		}
    		
    		int leftHeight = height_BruteForce(node.left);
    		int rightHeight = height_BruteForce(node.right);
    		
    		return 1+ Math.max(leftHeight, rightHeight);
    }
    
    /**
Submission
https://leetcode.com/submissions/detail/184944088/
You are here! 
Your runtime beats 51.52 % of java submissions.
     * @param root
     * @return
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        Height h = new Height();
        h.val = 1;
        height(root, h);
        return h.val - 1;
    }
    public static int height(TreeNode node, Height h) {
        if (node == null) {
        		return 0;
        }
        int leftHeight = height(node.left, h);
        int rightHeight = height(node.right, h);
        h.val = Math.max(h.val, leftHeight+rightHeight+1);
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode root = BSTHelper.createSampleBST();
		BinaryTreePrinter.preetyPrintTree(root);
		
		System.out.println("BF Diameter is = "+diameterOfBinaryTree_BruteForce(root));
//		System.out.println("OP Diameter is = "+diameterOfBinaryTree(root));
		System.out.println("OP Diameter is = "+SolutionDebug.diameterOfBinaryTree(root));
//		System.out.println("BF Diameter is = "+SolutionDebug.diameterOfBinaryTree(root));

	}

}

class Height {
	int val;
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
	
    public static int diameterOfBinaryTree_BruteForce(TreeNode root) {
    	debugRecr("root="+root);
        if(null==root) {
        		debugRecr("root null returning 0 diameter.");
        		return 0;
        }
        
        int leftHeight = height_BruteForce(root.left);
        int rightHeight = height_BruteForce(root.right);
        debugRecr("leftHeight="+leftHeight+", rightHeight="+rightHeight);
        
        String actual = incrementIndent();
        int leftDia = diameterOfBinaryTree_BruteForce(root.left);
        int rightDia = diameterOfBinaryTree_BruteForce(root.right);
        setIndent(actual);
        debugRecr("leftDia="+leftDia+", rightDia="+rightDia+", max="+Math.max(leftHeight+rightHeight, Math.max(leftDia, rightDia)));
//        return Math.max(1+leftHeight+rightHeight, Math.max(leftDia, rightDia)); for Vertices +1 is needed
        return Math.max(leftHeight+rightHeight, Math.max(leftDia, rightDia)); // edges are always -1 then vertices
    }
    
    public static int height_BruteForce(TreeNode node) {
    		debugRecr("node="+node);
    		if(null==node) {
    			debugRecr("node null returning height zero.");
    			return 0;
    		}
    		String actual = incrementIndent();
    		int leftHeight = height_BruteForce(node.left);
    		int rightHeight = height_BruteForce(node.right);
    		setIndent(actual);
    		debugRecr("leftHeight="+leftHeight+", rightHeight="+rightHeight);
    		debugRecr("Height="+(1+ Math.max(leftHeight, rightHeight)));
    		return 1+ Math.max(leftHeight, rightHeight);
    }
    
    
    public static int diameterOfBinaryTree(TreeNode root) {
    		reset();
        Height h = new Height();
        h.val = 1;
        height(root, h);
        debugRecr("Final diameter = "+ (h.val-1));
        return h.val - 1;
    }
    public static int height(TreeNode node, Height h) {
    		debugRecr(node);
        if (node == null) {
        		debugRecr("Node null returning zero.");
        		return 0;
        }
        String actual = incrementIndent();
        int leftHeight = height(node.left, h);
        int rightHeight = height(node.right, h);
        setIndent(actual);
        debugRecr("leftHeight="+leftHeight+", rightHeight="+rightHeight+", height="+h.val);
        h.val = Math.max(h.val, leftHeight+rightHeight+1);
        int result = 1 + Math.max(leftHeight, rightHeight);
        debugRecr("result="+result+", height="+h.val);
        return result;
    }
}
