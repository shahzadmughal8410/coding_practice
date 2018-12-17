/**
 * 
 */
package sm.coding.algo.practice.recursion.leetcode._050;

import java.util.regex.Pattern;

/**
 * @author shahzadmughal8410
 *
 */
public class ImplementPowerFunction_Q1_050 {

	
	/**
	 * Improved recursive will work for both positive and negative powers.
	 * 
	 * T=O (log y) , where y is the power

Submission
https://leetcode.com/submissions/detail/195491469/
You are here! 
Your runtime beats 99.31 % of java submissions.
	 */
	public static double myPow(double x, int y) {
		if(y==0) {
			return 1;
		}
		double temp = myPow(x, y/2); // do recursion only once and store the value
		
		// if y is even
		if(y%2==0) {
			return temp*temp;
		}
		else { // if y is odd
			if(y>0) { //if y is positive
				return temp * temp * x;
			}else { // if y is negative
				return (temp * temp) /x;
			}
		}
	}

	
	/**
	 * Plain recursion, works for both positive and negative powers.
	 * 
	 * T=O(n)
	 * S=O(n)
	 * @param x
	 * @param y
	 * @return
	 */
	public static double powerPlainRecursion(double x, int y) {		
		return y<0 ? 1/powerPlainRecursionHelper(x, y * -1) : powerPlainRecursionHelper(x, y);
	}

	/**
	 * Works only for positive values of y
	 * @param x
	 * @param y
	 * @return
	 */
	public static double powerPlainRecursionHelper(double x, int y) {
		if(y==0) {
			return 1;
		}
		return x * powerPlainRecursionHelper(x, y-1);
	}
	/**
	 * Iterative function, works for both positive and negative powers. 
	 * @param x
	 * @param y
	 * @return
	 */
	public static double powerPlainIterative(double x, int y) {		
		if(y==0) {
			return 1;
		}		
		int positiveY = y<0 ? y*-1 : y;
		double result = x;
		for(int i =1 ; i< positiveY ; i++) {
			result*= x;
		}
		return y<0 ? 1/result : result;
	}
	
	public static void main(String[] args) {
		double x; 
		int y; 
		String msg = "%s^%s Resursion %s, Plain Recursion=%s, Iterative=%s %n%n";
		
		x = 2; y = 1;
		System.out.printf(msg, x, y, myPow(x, y), powerPlainRecursion(x, y), powerPlainIterative(x, y));
		
		x = 2; y = 5;
		System.out.printf(msg, x, y, myPow(x, y), powerPlainRecursion(x, y), powerPlainIterative(x, y));
		
		x = 2; y = -5;
		System.out.printf(msg, x, y, myPow(x, y), powerPlainRecursion(x, y), powerPlainIterative(x, y));

		x = 2; y = 10;
		System.out.printf(msg, x, y, myPow(x, y), powerPlainRecursion(x, y), powerPlainIterative(x, y));
		
		x = 2; y = -10;
		System.out.printf(msg, x, y, myPow(x, y), powerPlainRecursion(x, y), powerPlainIterative(x, y));
		

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
	
	public static double powerRecursion(double x, int y) {
		debugRecr("x=["+x+"], y=["+y+"]");
		if(y==0) {
			return 1;
		}

		String actualIndent = incrementIndent();
		double temp = powerRecursion(x, y/2); // do recursion only once and store the value
		setIndent(actualIndent);
		debugRecr("temp="+temp);
		// if y is even
		debugRecr("y%2==0 --> "+y+"%2 ["+(y%2)+"] ==0 = " + (y%2==0));
		if(y%2==0) {
			return temp*temp;
		}
		else { // if y is odd
			debugRecr("y>0 --> "+y+">0 = "+(y>0));
			if(y>0) { //if y is positive
				debugRecr("x * temp * temp --> "+temp+" * "+temp+" * "+x+" = "+(temp * temp * x));
				return x * temp * temp;
			}else { // if y is negative
				debugRecr("(temp * temp) / x --> ("+temp+" * "+temp+") / "+x+" = "+(x * temp * temp));
				return (temp * temp) /x;
			}
		}
	}
}
