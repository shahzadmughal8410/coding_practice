/**
 * 
 */
package sm.coding.string.leetcode._043;

/**
 * @author shahzadmughal8410
 *
 */
public class MultiplyStrings_043 {

	/**

Medium
Given two non-negative integers num1 and num2 represented as strings, 
return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

Submission
https://leetcode.com/submissions/detail/197284346/
You are here! 
Your runtime beats 92.02 % of java submissions.

	 * @param num1
	 * @param num2
	 * @return
	 */
    public static String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int result[] = new int[m+n];
        
        // loop first number left to right
        for(int i=m-1; i>=0; i--) {
        		for(int j = n-1; j>=0; j--) {
        			int product = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
        			int p1 = i+j;
        			int p2 = i+j+1;
        			int sum = product + result[p2];// previous value at that position
        			
        			result[p1] = result[p1] + (sum / 10); // add into previous value
        			result[p2] = sum % 10; 
        		}
        }
        StringBuilder sb = new StringBuilder();
        for(int i:result) {
        		if( !(sb.length()==0 && i==0) ) {// ignore zeros in start
        			sb.append(i);
        		}
        }        
        return sb.length()==0 ? "0" : sb.toString();
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String num1 = "10";
		String num2 = "7";
		String result = multiply(num1, num2);
		System.out.println(num1+" * "+num2+" = "+result);

		num1 = "10";
		num2 = "10";
		result = multiply(num1, num2);
		System.out.println(num1+" * "+num2+" = "+result);

	}

}
