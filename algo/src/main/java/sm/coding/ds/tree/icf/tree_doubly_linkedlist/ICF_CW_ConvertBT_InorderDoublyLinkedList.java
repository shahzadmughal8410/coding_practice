/**
 * 
 */
package sm.coding.ds.tree.icf.tree_doubly_linkedlist;

import sm.coding.ds.tree.bst.BstFromSortedArray;
import sm.coding.ds.tree.bt.BinaryTreePrinter;
import sm.coding.ds.tree.bt.TreeNode;

/**
 * 
 * Convert binary tree to in-order doubly linked list.
  		1
       / \
      2   5
     / \ / \
    3  4 6  7

3<->2<->4<->1<->6<->5<->7

 * @author smughal
 *
 */
public class ICF_CW_ConvertBT_InorderDoublyLinkedList {

	static TreeNode previous;
	static TreeNode head;
	
	public static void convert(TreeNode node) {
		if(node==null) {
			return ;
		}
		
		convert(node.left);
		if(previous==null) {
			head = node;
		}else {
			node.left = previous;
			previous.right = node;
		}
		
		previous = node;
		convert(node.right);
	}
	
	/* Function to print nodes in a given doubly linked list */
    static void printList(TreeNode node)
    {
    		TreeNode tail = null;
    		System.out.println("Printing in forward direction");
        while (node != null) 
        {
            System.out.print(node.val + " ");
            tail = node;
            node = node.right;
            if(null!=node) {
            		tail = node;
            }
        }
        System.out.println();
		System.out.println("Printing in backward direction");
        while(tail!=null) {
            System.out.print(tail.val+" ");
        		tail = tail.left;
        }
    }
  
    // Driver program to test above functions
    public static void main(String[] args) 
    {
		int[] a = new int[] {1,2,3,4,5,6,7,8,9};
		TreeNode root = BstFromSortedArray.sortedArrayToBST(a);
		BinaryTreePrinter.preetyPrintTree(root);

        // convert to DLL
		convert(root);
          
        // Print the converted List
        printList(head);
  
    }
}
