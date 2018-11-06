/**
 * 
 */
package sm.coding.algo.practice.recursion.ctci._17_12;

/**
 * @author smughal
 *
 */
public class BiNode_BstToDoublyLinkedList_17_12 {

	
	/**
	 * 
17.12 BiNode: Consider a simple data structure called BiNode, which has pointers to two other nodes.
public class BiNode {
}
public BiNode nodel, node2;
public int data;
The data structure BiNode could be used to represent both a binary tree (where nodel is the left
node and node2 is the right node) or a doubly linked list (where nodel is the previous node and
node2 is the next node). Implement a method to convert a binary search tree (implemented with
BiNode) into a doubly linked list. The values should be kept in order and the operation should be
performed in place (that is, on the original data structure).
Hints: #509, #608, #646, #680, #701, #719
	 */
	public static BiNode[] bstToLinkedDoublyList(BiNode n) {
		BiNode[] head = new BiNode[1];
		BiNode[] tail = new BiNode[1];
		BiNode[] headTail = new BiNode[2];
		bstToLinkedDoublyListHelper(n, tail, head);
		headTail[0] = head[0];
		headTail[1] = tail[0];
		return headTail;
	}

	
	public static void bstToLinkedDoublyListHelper(BiNode n, BiNode[] previous, BiNode[] head) {
		if(null==n) {
			return ;
		}
		
		bstToLinkedDoublyListHelper(n.node1, previous, head);

		if(null!=previous[0]) {
			previous[0].node2 = n;
			n.node1 = previous[0];
			previous[0] = n;
		}

		if(head[0]==null) {
			head[0] = n;
			previous[0] = n;
		}		
		
		bstToLinkedDoublyListHelper(n.node2, previous, head);
}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BiNode root = new BiNode(4);
		root.node1 = new BiNode(2);
		root.node2 = new BiNode(5);
		
		root.node1.node1 = new BiNode(1);
		root.node1.node2 = new BiNode(3);

		root.node2.node1 = new BiNode(6);
		root.node2.node2 = new BiNode(7);
		
		BiNode[] headTail = bstToLinkedDoublyList(root);
		BiNode head = headTail[0];
		BiNode tail = headTail[1];
		
		BiNode node = head;
		System.out.println("Head to tail");
		while(null!=node) {
			System.out.print(node.data+"-->");
			node = node.node2;
		}
		System.out.println("null");

		node = tail;
		System.out.println("Tail to head");
		while(null!=node) {
			System.out.print(node.data+"-->");
			node = node.node1;
		}
		System.out.println("null");

	}

}

class BiNode {
	public BiNode node1, node2;
	public int data;
	
	public BiNode(int data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BiNode [data=" + data + "]";
	}
	
	
}

