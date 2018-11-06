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
		String s = "";
		while(null!=node) {
			s+=node.val+"-->";
			node = node.next;
		}
		s+="null";
		System.out.println(s);
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
