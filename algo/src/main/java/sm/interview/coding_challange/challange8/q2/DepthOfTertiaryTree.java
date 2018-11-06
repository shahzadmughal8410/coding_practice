/**
 * 
 */
package sm.interview.coding_challange.challange8.q2;

import java.util.LinkedList;
import java.util.List;

/**
 * @author shahzadmughal8410
 *
 */
public class DepthOfTertiaryTree {

	/**

T = O(n)
S = O(n)

	 * @param n
	 * @return
	 */
	public static int depth(TreeNode n) {
		if(null==n) {
			return 0;
		}
		
		int maxDepth = 0;
		for(TreeNode c:n.childs) {
			int childDepth = depth(c);
			maxDepth = Math.max(maxDepth, childDepth);
		}
		
		return maxDepth+1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		
		root.childs.add(new TreeNode(2));
		root.childs.add(new TreeNode(3));
		
		root.childs.get(0).childs.add(new TreeNode(4));

		root.childs.get(0).childs.get(0).childs.add(new TreeNode(5));
		

		System.out.println("Max depth="+depth(root));
	}

}

class TreeNode {
	int val;
	List<TreeNode> childs;
	
	public TreeNode(int val) {
		this.val = val;
		childs = new LinkedList<>();
	}
}
