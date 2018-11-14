/**
 * 
 */
package sm.coding.string.leetcode._067;

import java.util.regex.Pattern;

/**
 * @author shahzadmughal8410
 *
 */
public class AddBinary_067_Str {

	/**
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".

 https://leetcode.com/problems/add-binary/description/
Submission
https://leetcode.com/submissions/detail/152185783/
You are here! 
Your runtime beats 58.78 % of java submissions. 
	 * 
	 * @param args
	 */
	public static String addBinary(String s1, String s2) {
		
		int i = s1.length()-1;
		int j = s2.length()-1;
		int carry = 0;
		int resultLength = Math.max(s1.length(), s2.length())+1;
		char[] arr = new char[resultLength];
		int k = arr.length-1;
		arr[0] = '0'; // if result is not taking extra digit

		while(i>=0 || j>=0) {
			int sum = carry;
			
			if(i>=0) sum+= s1.charAt(i)-'0';
			if(j>=0) sum+= s2.charAt(j)-'0';
			
			arr[k--] = (sum % 2)==0?'0':'1';
			carry = sum/2;
			
			--i;--j;
		}

		if(carry==1) {
			arr[k] = '1';
		}
		// if first element of array is zero then skip it.
		return arr[0]=='0' ? new String(arr, 1, arr.length-1) : new String(arr);
		
	}

	public static String addBinary_BruteForce(String s1, String s2) {
		return Integer.toBinaryString((Integer.parseInt(s1, 2) + Integer.parseInt(s2, 2))) ;
	}

	public static void main(String[] args) {
		String s1 = "0"; // 42
		String s2 = "0"; // 38
//		System.out.printf("Add binary [%s]+[%s]=[%s] %n", s1, s2, addBinary(s1, s2));
		System.out.printf("Add binary [%s]+[%s]=[%s] %n", s1, s2, SolutionDebug.addBinary(s1, s2));
		System.out.printf("Add binary [%s]+[%s]=[%s]  (Brute force) %n", s1, s2, addBinary_BruteForce(s1, s2));

		System.out.printf("Add binary [%s]+[%s]=[%s] %n", Integer.parseInt(s1, 2), Integer.parseInt(s2, 2), Integer.parseInt(addBinary(s1, s2), 2));
		System.out.printf("Add binary [%s]+[%s]=[%s] (Brute force) %n", Integer.parseInt(s1, 2), Integer.parseInt(s2, 2), Integer.parseInt(addBinary_BruteForce(s1, s2), 2));

	}

}

class SolutionDebug{
	
	public static String addBinary(String s1, String s2) {
		
		int i = s1.length()-1;
		int j = s2.length()-1;
		int carry = 0;
		int resultLength = Math.max(s1.length(), s2.length())+1;
		char[] arr = new char[resultLength];
		int k = arr.length-1;
		arr[0] = '0'; // if result is not taking extra digit
		tableColumns("i:3","j:3", "carry", "sum", "k:3");
		while(i>=0 || j>=0) {
			int sum = carry;
			debugRow(i, j , carry, sum, k);
			
			if(i>=0) sum+= s1.charAt(i)-'0';
			if(j>=0) sum+= s2.charAt(j)-'0';
			
			arr[k--] = (sum % 2)==0?'0':'1';
			carry = sum/2;
			
			--i;--j;
		}
		
		if(carry==1) {
			arr[k] = '1';
		}
		// if first element of array is zero then skip it.
		return arr[0]=='0' ? new String(arr, 1, arr.length-1) : new String(arr);
		
	}
	
	static StringBuilder format = new StringBuilder();

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
		debugRow(cols);
	}
	
	public static void debugRow(Object ... cols) {
		debug(String.format(format.toString(), cols));
	}

	public static void debug(Object msg) {
		System.out.println("DEBUG: "+msg);
	}
}
