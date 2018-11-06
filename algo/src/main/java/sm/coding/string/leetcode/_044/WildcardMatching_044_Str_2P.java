/**
 * 
 */
package sm.coding.string.leetcode._044;

import java.util.regex.Pattern;

/**
 * @author shahzadmughal8410
 *
 */
public class WildcardMatching_044_Str_2P {

	/**
mplement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false

https://leetcode.com/problems/wildcard-matching/description/

 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static boolean isMatch(String str, String pattern) {
		int s = 0, p = 0, match = 0, starIdx = -1;

		while (s < str.length()) {
			// advancing both pointers
			if (p < pattern.length() && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))) {
				s++;
				p++;
			}
			// * found, only advancing pattern pointer
			else if (p < pattern.length() && pattern.charAt(p) == '*') {
				match = s;
				starIdx = p;
				p++;
			}
			// last pattern pointer was *, advancing string pointer
			else if (starIdx != -1) {
				p = starIdx + 1;
				match++;
				// match will be used to push the s pointer back to previous position for cases when we have *?, question mark after *
				s = match;
			} else {
				return false;
			}
		}
		// if pattern is not exhausted, then remaining pattern should be all *, otherwise it will be false.
		while (p < pattern.length() && pattern.charAt(p) == '*') {
			p++;
		}
		return p == pattern.length();
	}

	public static void main(String[] args) {
		System.out.println(String.format("%s, %s => %s", "geeksforgeeks", "ge?ks*", isMatch("geeksforgeeks", "ge?ks*"))); // Yes
		System.out.println(String.format("%s, %s => %s", "gee", "g*k", isMatch("gee", "g*k"))); // No
		System.out.println(String.format("%s, %s => %s", "abcd", "*c*d", isMatch("abcd", "*c*d"))); // Yes
		System.out.println(String.format("%s, %s => %s", "abcd", "*?c*d", isMatch("abcd", "*?c*d"))); // Yes

		System.out.println(String.format("%s, %s => %s", "aa", "aa", isMatch("aa", "aa"))); // yes
		System.out.println(String.format("%s, %s => %s", "aaa", "aa", isMatch("aaa", "aa"))); // no
		System.out.println(String.format("%s, %s => %s", "aa", "*", isMatch("aa", "*"))); // yes
		System.out.println(String.format("%s, %s => %s", "aa", "a*", isMatch("aa", "a*"))); // yes
		System.out.println(String.format("%s, %s => %s", "ab", "?*", isMatch("ab", "?*"))); // yes
		System.out.println(String.format("%s, %s => %s", "aab", "c*a*b", isMatch("aab", "c*a*b"))); // no

		System.out.println(String.format("%s, %s => %s", "aaaabbbbcccc", "a*", isMatch("aaaabbbbcccc", "a*"))); // yes
		System.out.println(String.format("%s, %s => %s", "abbc", "a*c", isMatch("abbc", "a*c"))); // yes
		System.out.println(String.format("%s, %s => %s", "abed", "?b*d**", isMatch("abed", "?b*d**"))); // yes

//		System.out.println(String.format("%s, %s => %s", "azab", "a*?ab", SolutionDebug.isMatch("azab", "a*?ab"))); // yes
		System.out.println(String.format("%s, %s => %s", "azab", "a*?ab", SolutionDebug.isMatch("aaaa", "***a"))); // yes

	}
}


class SolutionDebug{

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
	
	public static boolean isMatch(String str, String pattern) {
		int s = 0, p = 0, match = 0, starIdx = -1;
		tableColumns("s:3", "p:3", "match", "starIdx");
		while (s < str.length()) {
			debugRow(s, p, match, starIdx);
			// advancing both pointers
			if (p < pattern.length() && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))) {
				s++;
				p++;
			}
			// * found, only advancing pattern pointer
			else if (p < pattern.length() && pattern.charAt(p) == '*') {
				match = s;
				starIdx = p;
				p++;
			}
			// last pattern pointer was *, advancing string pointer
			else if (starIdx != -1) {
				p = starIdx + 1;
				match++;
				// match will be used to push the s pointer back to previous position for cases when we have *?, question mark after *
				s = match;
			} else {
				return false;
			}
		}
		debug("Checking for remaining pattern.");
		// if pattern is not exhausted, then remaining pattern should be all *, otherwise it will be false.
		while (p < pattern.length() && pattern.charAt(p) == '*') {
			debugRow(s, p, match, starIdx);
			p++;
		}
		return p == pattern.length();
	}
}