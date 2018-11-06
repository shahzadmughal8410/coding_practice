/**
 * 
 */
package sm.coding.ds.tree.bt;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author smughal
 *
 */
public class LevelOrderTraversal {

	
	public static void levelOrderNodes(TreeNode n){
		Queue<TreeNode> q = new LinkedList<>();
		
		q.offer(n);
		
		while(!q.isEmpty()) {
			int level = q.size();
			for(int i =0; i<level;i++) {
				TreeNode current = q.poll();
				if(current.left!=null)
					q.offer(current.left);
				if(current.right!=null)
					q.offer(current.right);
				System.out.print(" "+current.val);
			}
			System.out.println();
		}
		
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
		
		levelOrderNodes(root);	
	}

}
