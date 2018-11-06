/**
 * 
 */
package sm.coding.ds.tree.icf.distance_two_nodes;

import sm.coding.ds.tree.bt.TreeNode;

/**
 * @author shahzadmughal8410
 *
 */
public class FindDistanceBetweenTwoNodesOfABinaryTree {

	/**
perm_identity
Find distance between two nodes of a Binary Tree
Find the distance between two keys in a binary tree, no parent pointers are given. 
Distance between two nodes is the minimum number of edges to be traversed to reach one node from other.
	 */
	public static int findDistance(TreeNode root, int n1, int n2) {
		TreeNode lca = findLca(root, n1, n2);
		int d1 = findHeight(lca, n1, 0);
		int d2 = findHeight(lca, n2, 0);
		return d1+d2;
	}
	
	public static TreeNode findLca(TreeNode root, int n1, int n2) {
		if(null==root) {
			return null;
		}
		
		if(root.val==n1 || root.val==n2) {
			return root;
		}
		
		TreeNode leftLca = findLca(root.left, n1, n2);
		TreeNode rightLca = findLca(root.right, n1, n2);
		
		if(null!=leftLca && null!=rightLca) {
			return root;
		}
		return leftLca!=null ? leftLca : rightLca;	
	}
	
	public static int findHeight(TreeNode root, int node, int distance) {
		if(root==null) {
			return -1;
		}
			
		if(root.val==node) {
			return distance;
		}
		
		int leftDistance = findHeight(root.left, node, distance+1);
		
		return leftDistance != -1 ? leftDistance : findHeight(root.right, node, distance+1);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        TreeNode  root = new TreeNode(1); 
        root.left = new TreeNode(2); 
        root.right = new TreeNode(3); 
        root.left.left = new TreeNode(4); 
        root.left.right = new TreeNode(5); 
        root.right.left = new TreeNode(6); 
        root.right.right = new TreeNode(7); 
        root.right.left.right = new TreeNode(8); 
         
        System.out.println("Dist(4, 5) = "+findDistance(root, 4, 5)); 
        System.out.println("Dist(4, 6) = "+findDistance(root, 4, 6)); 
        System.out.println("Dist(3, 4) = "+findDistance(root, 3, 4)); 
        System.out.println("Dist(2, 4) = "+findDistance(root, 2, 4)); 
        System.out.println("Dist(8, 5) = " +findDistance(root, 8, 5));	}

}
