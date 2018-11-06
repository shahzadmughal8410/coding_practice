/**
 * 
 */
package sm.coding.ds.llstq.leetcode.linked_list._150;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author shahzadmughal8410
 *
 */
public class EvaluateReversePolishNotation_150 {

	/**
Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, /. Each operand may be an integer or another expression.
Note:
Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:
Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9


Example 2:
Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6


Example 3:
Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22

Submission
https://leetcode.com/submissions/detail/187634482/
You are here! 
Your runtime beats 47.53 % of java submissions.
	 */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        // variables needed as we do (b operator a) 
        int a;
        int b;
        for(String s:tokens) {
        		switch(s) {
				case "+":
					a = stack.pop();
					b = stack.pop();
					stack.push(b+a);
				break;

				case "-":
					a = stack.pop();
					b = stack.pop();
					stack.push(b-a);
				break;
	
				case "*":
					a = stack.pop();
					b = stack.pop();
					stack.push(b*a);
				break;
	
				case "/":
					a = stack.pop();
					b = stack.pop();
					stack.push(b/a);
				break;

	    			default:
	    			stack.push(Integer.parseInt(s));
	    			break;
        		}
        }
        return stack.pop();
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
