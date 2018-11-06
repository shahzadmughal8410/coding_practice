/**
 * 
 */
package sm.coding.ds.llstq.leetcode.linked_list._160;

import java.util.regex.Pattern;

import sm.coding.ds.llstq.leetcode.linked_list.LinkedListPrinter;
import sm.coding.ds.llstq.leetcode.linked_list.ListNode;

/**
 * @author shahzadmughal8410
 *
 */
public class IntersectionOfTwoLinkedLists_160 {

	/**
Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:
A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3


begin to intersect at node c1.

Notes:
If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.

Submission
https://leetcode.com/submissions/detail/187016868/
You are here! 
Your runtime beats 100.00 % of java submissions.
	 */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        
        while(a!=b) {
	    		a =  (a==null) ? headB : a.next;
	    		b =  (b==null) ? headA : b.next;
        }
        return a;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListNode headA = new ListNode(13);
		headA.next = new ListNode(105);
		headA.next.next = new ListNode(23);
		headA.next.next.next = new ListNode(33);
		headA.next.next.next.next = new ListNode(43);
		headA.next.next.next.next.next = new ListNode(53);
		headA.next.next.next.next.next.next = new ListNode(63);

		ListNode headB = new ListNode(11);
		headB.next = new ListNode(110);
		headB.next.next = new ListNode(21);
		headB.next.next.next = headA.next.next.next.next;
		
		LinkedListPrinter.print(headA);
		LinkedListPrinter.print(headB);

		SolutionDebug.getIntersectionNode(headA, headB);
		
	}

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
	
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        String strA = "";
        String strB = "";
        
        while(a!=b) {
//	    		a =  (a==null) ? headB : a.next;
//	    		b =  (b==null) ? headA : b.next;
        		if(a==null) {
        			a = headB;
        			strA+= "  "+a.val+"  ";
        		}else {
        			strA+= a.val+"-->";
        			a = a.next;
        		}
        		
        		if(b==null) {
        			b = headA;
        			strB+= "  "+b.val+"  ";
        		}else {
        			strB+= b.val+"-->";
        			b = b.next;
        		}
        }
        String remaining = LinkedListPrinter.toString(a);
        System.out.println(strA+remaining);
        System.out.println(strB+remaining);
        return a;
    }
}
