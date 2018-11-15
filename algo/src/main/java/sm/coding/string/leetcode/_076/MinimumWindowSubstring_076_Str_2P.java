/**
 * 
 */
package sm.coding.string.leetcode._076;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class MinimumWindowSubstring_076_Str_2P {

	/**
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

https://leetcode.com/problems/minimum-window-substring/description/
https://articles.leetcode.com/finding-minimum-window-in-s-which/
http://www.lifeincode.net/programming/leetcode-minimum-window-substring-java/
https://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/
 
Submission
MAP
https://leetcode.com/submissions/detail/189675854/
You are here! 
Your runtime beats 14.70 % of java submissions.
ARRAY
https://leetcode.com/submissions/detail/169028155/
You are here!
Your runtime beats 76.44 % of java submissions.

 
	 * @param args
	 */

	public static String minimumWindow_Map(String s, String t) {
		if(s==null || t==null || s.length()==0 || t.length()==0 || s.length()<t.length()) {
			return "";
		}
		
		Map<Character, Integer> pmap = new HashMap<>();
		// initialize the pattern frequency map
		for(int i=0;i<t.length();i++) {
			char c = t.charAt(i);
			pmap.put(c, pmap.getOrDefault(c, 0)+1);
		}

		Map<Character,Integer> foundMap = new HashMap<>();
		int start = 0;
		int minLength = Integer.MAX_VALUE;
		int startIndex = -1;
		int count = 0;
		
		for (int end = 0; end < s.length(); end++) {
			if (pmap.containsKey(s.charAt(end))) {
				foundMap.put(s.charAt(end), foundMap.getOrDefault(s.charAt(end), 0) + 1);
				if (foundMap.get(s.charAt(end)) <= pmap.get(s.charAt(end))) {
					++count;
				}
			}

			if (count == t.length()) {
				char c = s.charAt(start);
				while (foundMap.getOrDefault(c, 0) > pmap.getOrDefault(c, 0) || !pmap.containsKey(c)) {
					if (foundMap.getOrDefault(c, 0) > pmap.getOrDefault(c, 0)) {
						foundMap.put(c, foundMap.get(c) - 1);
					}
					++start;
					c = s.charAt(start);
				}
				int currentLength2 = end - start + 1;
				if (minLength > currentLength2) {
					minLength = currentLength2;
					startIndex = start;
				}
			}
		}
		return startIndex==-1 ? "": s.substring(startIndex, startIndex+minLength) ;
	}
	
	public static String minimumWindow_Array(String s, String t) {
		if(s==null || t==null || s.length()==0 || t.length()==0 || s.length()<t.length()) {
			return "";
		}
		int hash_pat[] = new int[256];
        int hash_str[] = new int[256];
		// initialize the pattern frequency map
		for(int i=0;i<t.length();i++) {
			hash_pat[t.charAt(i)]++;
		}

		int start = 0;
		int startIndex = -1;
		int count = 0;
		int minLength = Integer.MAX_VALUE;
		
		for (int j = 0; j < s.length() ; j++) {
			 hash_str[s.charAt(j)]++;
			 if (hash_pat[s.charAt(j)] != 0 && hash_str[s.charAt(j)] <= hash_pat[s.charAt(j)] ) {
	                count++;
			 }
			 if(count==t.length()) {
				while (hash_str[s.charAt(start)] > hash_pat[s.charAt(start)] || hash_pat[s.charAt(start)] == 0) {
					if (hash_str[s.charAt(start)] > hash_pat[s.charAt(start)]) {
						hash_str[s.charAt(start)]--;
					}
					start++;
				}
				// update window size
				int len_window = j - start + 1;
				if (minLength > len_window) {
					minLength = len_window;
					startIndex = start;
				}
			}
		}
		return startIndex==-1 ? "": s.substring(startIndex, startIndex+minLength) ;
	}
	
	public static void main(String[] args) {
		String s = "ADOBECODEBANC";
		String t = "ABC";

//		String s = "acbbaca";
//		String t = "aba";

//		String s = "this is a test string";
//		String t = "tist";

		System.out.println("Minimum window map="+SolutionDebug.minimumWindow_Map(s, t));
		System.out.println("Minimum window map="+minimumWindow_Map(s, t));
		System.out.println("Minimum window arr="+minimumWindow_Array(s, t));

	}
	
	public static void debug(Object msg) {
		System.out.println("DEBUG: "+msg);
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
	
	
	public static String minimumWindow_Map(String s, String t) {
		if(s==null || t==null || s.length()==0 || t.length()==0 || s.length()<t.length()) {
			return "";
		}
		debug("Pattern="+t);
		debug("Input="+s.chars().mapToObj( i -> (String.format("%2s", (char)i))).collect(Collectors.toList() ));
		debug("Index="+IntStream.range(0, s.length()).mapToObj( i -> (String.format("%2s", i))).collect(Collectors.toList()));
		Map<Character, Integer> pmap = new HashMap<>();
		// initialize the pattern frequency map
		for(int i=0;i<t.length();i++) {
			char c = t.charAt(i);
			pmap.putIfAbsent(c, 0);
			pmap.put(c, pmap.get(c)+1);
		}

		Map<Character,Integer> foundMap = new HashMap<>();
		int start = 0;
		int minLength = Integer.MAX_VALUE;
		int startIndex = -1;
		int count = 0;
		
		tableColumns("start", "end", "startIndex", "count", "minLength:10", "pmap:15", "foundMap:15");
		
		for (int end=0; end < s.length() ; end++) {
			debugRow(start, end, startIndex, count, minLength, pmap, foundMap);

			if(pmap.containsKey(s.charAt(end))) {
				foundMap.putIfAbsent(s.charAt(end), 0);
				foundMap.put(s.charAt(end), foundMap.get(s.charAt(end))+1);
				if(foundMap.get(s.charAt(end))<=pmap.get(s.charAt(end))) {
					++count;
				}
			}
			
			 if(count==t.length()) {
				char c = s.charAt(start);
				while(foundMap.getOrDefault(c, 0) > pmap.getOrDefault(c, 0) || !pmap.containsKey(c)) {
					if(foundMap.getOrDefault(c, 0) > pmap.getOrDefault(c, 0)) {
						foundMap.put(c, foundMap.get(c)-1);
					}
					++start;
					c = s.charAt(start);
				}
				int currentLength2 = end - start + 1;
				if(minLength>currentLength2) {
					minLength = currentLength2;
					startIndex = start;
				}
			}
		}
		return startIndex==-1 ? "": s.substring(startIndex, startIndex+minLength) ;
	}
}
