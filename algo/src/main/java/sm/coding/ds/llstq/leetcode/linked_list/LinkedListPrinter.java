/**
 * 
 */
package sm.coding.ds.llstq.leetcode.linked_list;

/**
 * @author shahzadmughal8410
 *
 */
public class LinkedListPrinter {

	public static void print(ListNode node) {
		while(null!=node) {
			System.out.print(node.val+"-->");
			node = node.next;
		}
		System.out.print("null");
		System.out.println();
	}

	public static String toString(ListNode node) {
		String s = "";
		while(null!=node) {
			s+=node.val+"-->";
			node = node.next;
		}
		s+="null";
		return s;
	}

}
