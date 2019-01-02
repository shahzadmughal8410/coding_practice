 /**
 * 
 */
package sm.coding.ds.llstq.leetcode.stack._227;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author shahzadmughal8410
 *
 */
public class BasicCalculatorII_227 {

	/**
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.

Submission
https://leetcode.com/submissions/detail/187752082/
You are here! 
Your runtime beats 83.75 % of java submissions.
	 */
    public static int calculate(String s) {
        if(null==s || s.length()==0) {
        		return 0;
        }
        Deque<Integer> stack = new LinkedList<>();
        int num = 0;
        char op =  '+'; // by default set operator to plus
        for(int i =0; i<s.length();i++) {
        		char c = s.charAt(i);
        		// to parse consecutive digits
        		if(Character.isDigit(c)) {
        			num = num * 10 + (c-'0');
        		}
        		
        		if( (!Character.isDigit(c) && ' '!=c ) || i == s.length()-1) {
        			// process previous op with last num, and update this operator i.e. op=c
        			switch(op) {
	    				case '+':
	    					stack.push(num);
	    					break;
	    				case '-':
	    					stack.push(-num);
	    					break;
	    				case '*':
	    					stack.push(stack.pop()*num);
	    					break;
	    				case '/':
	    					stack.push(stack.pop()/num);
	    					break;
        			}        			 
				op = c;  // set operator 
				num = 0; // reset num to 0
        		}        		
        }
		int result = 0;
		while(!stack.isEmpty()) {
			result+=stack.pop();
		}
		return result;
    }
	
    /**
Submission
https://leetcode.com/submissions/detail/198595129/
You are here! 
Your runtime beats 78.77 % of java submissions.
     * @param s
     * @return
     */
    public static int calculate_NoStack(String s) {
        if(null==s || s.length()==0) {
        		return 0;
        }
        int num = 0;
        int previous = 0;
        int result = 0;
        char op =  '+'; // by default set operator to plus
        for(int i =0; i<s.length();i++) {
        		char c = s.charAt(i);
        		// to parse consecutive digits
        		if(Character.isDigit(c)) {
        			num = num * 10 + (c-'0');
        		}
        		
        		if( (!Character.isDigit(c) && ' '!=c ) || i == s.length()-1) {
        			// process previous op with last num, and update this operator i.e. op=c
        			switch(op) {
	    				case '+':
	    					result +=previous;
	    					previous=num;
	    					break;
	    				case '-':
	    					result+=previous;
	    					previous = -num;
	    					break;
	    				case '*':
	    					previous = previous * num;
	    					break;
	    				case '/':
	    					previous = previous/num;
	    					break;
        			}        			 
				op = c;  // set operator 
				num = 0; // reset num to 0
        		}        		
        }
        result+=previous;// add the last number stored as previous
		return result;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "5+2*3";
		int result = calculate(s);
		System.out.println("Stack    = "+s+" -> "+result);
		result = calculate_NoStack(s);
		System.out.println("No Stack = "+s+" -> "+result);
		
		s = "3+2*2";
		result = calculate(s);
		System.out.println("Stack    = "+s+" -> "+result);
		result = calculate_NoStack(s);
		System.out.println("No Stack = "+s+" -> "+result);
		
		s = "1 0 0 * 1 0 ";
		result = calculate(s);
		System.out.println("Stack    = "+s+" -> "+result);
		result = calculate_NoStack(s);
		System.out.println("No Stack = "+s+" -> "+result);
		
		s = "1 0 0 + 1 0 ";
		result = calculate(s);
		System.out.println("Stack    = "+s+" -> "+result);
		result = calculate_NoStack(s);
		System.out.println("No Stack = "+s+" -> "+result);
		
	}

}
