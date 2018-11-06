/**
 * 
 */
package sm.coding.ds.tree.icf.twomirror;

import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author shahzadmughal8410
 *
 */
public class CheckTwoTreesAreMirror {

	/**
Given two Binary Trees, write a function that returns true if two trees are mirror of each other, else false. 
For example, the function should return true for following input trees.
	 */
	public static boolean isMirror(TreeNode node1, TreeNode node2) {
		if(null==node1 && null==node2) {
			return true;
		}
		
		if(node1!=null && node2!=null) {
			return node1.val==node2.val && isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
		}
		return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
