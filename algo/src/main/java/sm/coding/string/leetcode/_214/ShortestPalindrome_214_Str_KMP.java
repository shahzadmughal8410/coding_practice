/**
 * 
 */
package sm.coding.string.leetcode._214;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class ShortestPalindrome_214_Str_KMP {

	/**
	 * 
	 * 
 Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".

https://leetcode.com/problems/shortest-palindrome/description/
https://www.geeksforgeeks.org/minimum-number-appends-needed-make-string-palindrome/
https://www.geeksforgeeks.org/dynamic-programming-set-28-minimum-insertions-to-form-a-palindrome/ 
https://leetcode.com/problems/implement-strstr/description/ 
 
	 * @param args
	 */
	public static String shortestPalindrome(String input) {
		if(null==input || input.isEmpty()) {
			return "";
		}
		String reverse = reverse(input);
		String pattern = input+"#"+reverse;
		
		int[] lps = lps(pattern);
		
		String shortestPalindrome = reverse.substring(0, input.length()-lps[pattern.length()-1])+input;
		return shortestPalindrome;
	}
	
	public static int[] lps(String p) {		
		int[] lps = new int[p.length()];
		int i = 1;
		int j = 0;
		lps[0] = 0;
		
		while(i<p.length()) {		
			if(p.charAt(i) == p.charAt(j)) {
				lps[i] = j+1;
				++i;
				++j;
			}else {
				if(j!=0) {
					j = lps[j-1]; 
				}else {
					lps[i] = 0;
					++i;
				}
			}
		}
		return lps;
	}
	
	public static String reverse(String s) {
		return new StringBuilder(s).reverse().toString();
	}
	
	
	public static void main(String[] args) {
		String s = "ABACUS";
		String palindrome = SolutionDebug.shortestPalindrome(s);		
		System.out.printf("shortest palindrome is [%s]-> [%s] %n", s, palindrome);

		s = "aacecaaa";
		palindrome = shortestPalindrome(s);		
		System.out.printf("shortest palindrome is [%s]-> [%s] %n", s, palindrome);

		s = "abcd";
		palindrome = shortestPalindrome(s);		
		System.out.printf("shortest palindrome is [%s]-> [%s] %n", s, palindrome);

	}

}

class SolutionDebug{

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
	
	public static String shortestPalindrome(String input) {
		if(null==input || input.isEmpty()) {
			return "";
		}
		String reverse = ShortestPalindrome_214_Str_KMP.reverse(input);
		String pattern = input+"#"+reverse;
		
		int[] lps = lps(pattern);
		debug("reverse="+reverse.chars().mapToObj(k -> (char) k).collect(Collectors.toList()));
		debug("indexes="+IntStream.range(0, reverse.length()).boxed().collect(Collectors.toList()).toString());
		debug("input.length()="+input.length()+", pattern.length()-1="+(pattern.length()-1)+", lps[pattern.length()-1]="+lps[pattern.length()-1]);
		debug("reverse.substring(0,"+(input.length()-lps[pattern.length()-1])+") ==>"+reverse.substring(0, input.length()-lps[pattern.length()-1]));
		String shortestPalindrome = reverse.substring(0, input.length()-lps[pattern.length()-1])+input;
		return shortestPalindrome;
	}
	
	public static int[] lps(String p) {		
		int[] lps = new int[p.length()];
		int i = 1;
		int j = 0;
		lps[0] = 0;
		tableColumns("j:3", "i:3", "charAt(j)", "charAt(i)", "equals", IntStream.range(0, p.length()).boxed().collect(Collectors.toList()).toString());
		debugColumns();
		while(i<p.length()) {		
			debugRow(j, i , p.charAt(j), p.charAt(i), p.charAt(j)==p.charAt(i), Arrays.stream(lps).boxed().collect(Collectors.toList()));
			if(p.charAt(i) == p.charAt(j)) {
				lps[i] = j+1;
				++i;
				++j;
			}else {
				if(j!=0) {
					j = lps[j-1]; 
				}else {
					lps[i] = 0;
					++i;
				}
			}
		}
		debug("PTR="+p.chars().mapToObj(k -> (char) k).collect(Collectors.toList()));
		debug("LPS="+Arrays.stream(lps).boxed().collect(Collectors.toList()));
		return lps;
	}
}
