/**
 * 
 */
package sm.coding.string.leetcode._028.kmp;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class Implement_strStr_028_Str_KMP {

	/**
 Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2

Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1

 https://leetcode.com/problems/implement-strstr/description/
 http://www.cplusplus.com/reference/cstring/strstr/
	 * 
	 * 
	 * p =   [a, b, c, d, a, b, c, a]
	 * lps = [0, 0, 0, 0, 1, 2, 3, 1]	
	 * 
	 * p =   [a, a, b, a, a, b, a, a, a]
	 * lps = [0, 1, 0, 1, 2, 3, 4, 5, 2]
	 *  
	 * s=abcbcglx
	 * p =   [b, c, g, l]
	 * lps = [0, 0, 0, 0]
	 * 
	 * 
	 * s=    [a, b, c, x, a, b, c, d, a, b, c, d, a, b, c, y]
	 * p =   [a, b, c, d, a, b, c, y]
	 * lps = [0, 0, 0, 0, 1, 2, 3, 0]
	 * 
	 * p =   [a, c, a, c, a, b, a, c, a, c, a, b, a, c, a,  c,  a, c]
	 * lps = [0, 0, 1, 2, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 4]
	 * 
	 * @param args
	 */
	public static boolean kmp(String s, String p) {
		if(null==s || s.length()==0 || null==p || p.length()==0) {
			return false;
		}
		
		int i = 0;
		int j = 0;
		int[] lps = preProcessPattern(p);
		
		while(i<s.length() && j<p.length()) {
			if(s.charAt(i)==p.charAt(j)) {
				i++;
				j++;
			}
			else {
				if(j!=0) {
					// if mismatch and j!=0 than set j to j-1
					j = lps[j-1];
				}else {
					i++;
				}
				
			}
		}
		
		if(j==p.length()) {
			return true;
		}
		
		return false;
	}
	
	
	public static int[] preProcessPattern(String p) {
		int j = 0;
		int i = 1;
		int[] lps = new int[p.length()];
		lps[0] = 0;
		
		while(i<p.length()) {
			if(p.charAt(j)==p.charAt(i)) {
				lps[i]=j+1;
				j++;
				i++;
			}else {
				// if character not match and j!= 0  than decide the new j value, 
				// set j to the value of j-1 
				// and continue with next iteration
				if(j!=0) {
					j = lps[j-1];
				}else {
					// if j==0 than no prefix is found set i -0
					lps[i] = 0;
					i++;
				}
			}
		}
		return lps;
	}
	
	public static void main(String[] args) {
//		String pattern = "abcdabca";
//		String pattern = "aabaabaaa";
//		String pattern = "acacabacacabacacac";
//		String pattern = "bcgl";
		
		String text = "abcxabcdabcdabcy";
		String pattern = "abcdabcy";
		
		System.out.println(text.chars().mapToObj(i -> (char) i).collect(Collectors.toList()));
		System.out.println(pattern.chars().mapToObj(i -> (char) i).collect(Collectors.toList()));
//		System.out.println(kmp(text, pattern));
		System.out.println(SolutionDebug.kmp(text, pattern));

	}

}

class SolutionDebug {

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
	
	public static void reset() {
		format.setLength(0);
	}
	
	public static boolean kmp(String s, String p) {
		if(null==s || s.length()==0 || null==p || p.length()==0) {
			return false;
		}
		
		int i = 0;
		int j = 0;
		int[] lps = preProcessPattern(p);
		debug(p.chars().mapToObj(k -> (char) k).collect(Collectors.toList()));
		debug(Arrays.stream(lps).boxed().collect(Collectors.toList()));
		reset();
		tableColumns("i:3", "j:3",  "charAt(i)", "charAt(j)", "equals");
		
		while(i<s.length() && j<p.length()) {
			debugRow(i, j , s.charAt(i), p.charAt(j), s.charAt(i)==p.charAt(j));
			if(s.charAt(i)==p.charAt(j)) {
				i++;
				j++;
			}
			else {
				if(j!=0) {
					j = lps[j-1];
				}else {
					i++;
				}
				
			}
		}
		
		if(j==p.length()) {
			return true;
		}
		
		return false;
	}
	
	public static int[] preProcessPattern(String p) {
		int j = 0;
		int i = 1;
		int[] lps = new int[p.length()];
		lps[0] = 0;
		tableColumns("j:3", "i:3", "charAt(j)", "charAt(i)", "equals", IntStream.range(0, p.length()).boxed().collect(Collectors.toList()).toString());
		while(i<p.length()) {
			debugRow(j, i , p.charAt(j), p.charAt(i), p.charAt(j)==p.charAt(i), Arrays.stream(lps).boxed().collect(Collectors.toList()));
			if(p.charAt(j)==p.charAt(i)) {
				lps[i]=j+1;
				j++;
				i++;
			}else {
				// if character not match and j!= 0  than decide the new j value, 
				// set j to the value of j-1 
				// and continue with next iteration
				if(j!=0) {
					j = lps[j-1];
				}else {
					// if j==0 than no prefix is found set i -0
					lps[i] = 0;
					i++;
				}
			}
		}
		return lps;
	}
}
