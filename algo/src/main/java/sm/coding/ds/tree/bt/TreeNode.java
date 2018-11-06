package sm.coding.ds.tree.bt;

public class TreeNode {
	
	public TreeNode() {}
	
	public TreeNode(int val) {
		this.val = val;
	}
	
	public int val;
	public TreeNode left;
	public TreeNode right;
	@Override
	public String toString() {
		return "Node=" + val + "";
	}
	
	
}

class GenericNode<T> {
	T val;
	GenericNode<T> left;
	GenericNode<T> right;
}

