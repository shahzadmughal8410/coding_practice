/**
 * 
 */
package sm.coding.algo.dp.leetcode._097;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class InterleavingString_097_Recr_BckTrk_DP {

	/**
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
	 */

	/**
Submission
https://leetcode.com/submissions/detail/180105273/
Time Limit Exceeded
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
    public static boolean isInterleave_BruteForce(String s1, String s2, String s3) {
	    	// if s3 is not equals s1+s2 size
		if(s1.length()+s2.length() != s3.length()) {
	    		return false;
	    	}
	    	return isInterleaveHelper_BruteForce(s1, s2, s3, s1.length()-1, s2.length()-1, s3.length()-1); 
    }

    public static boolean isInterleaveHelper_BruteForce(String s1, String s2, String s3, int i, int j, int k) {
	    // if we exhausted all the strings, means s3 is interleaving of s1 and s2		    	
	    	if(i<0 && j<0 && k<0) {
	    		return true;
	    	}
	    	
	    	boolean firstMatch = false;
	    	boolean secondMatch = false;
	    	// if s1 character matches with s3
	    	if(i>=0) {
	    		firstMatch = s1.charAt(i)==s3.charAt(k) && isInterleaveHelper_BruteForce(s1, s2, s3, i-1, j, k-1);
	    	}
	    	// if s2 character matches with s3
	    	if(j>=0) {
	    		secondMatch = s2.charAt(j)==s3.charAt(k) && isInterleaveHelper_BruteForce(s1, s2, s3, i, j-1, k-1);
	    	}
	    	// if any of the s1 and s2 character matches with s3
	    	return firstMatch || secondMatch ;	    			
    }
    
    /**
Submission
https://leetcode.com/submissions/detail/180105467/
You are here! 
Your runtime beats 96.12 % of java submissions.
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave_Memoization(String s1, String s2, String s3) {
	    	// if s3 is not equals s1+s2 size
		if(s1.length()+s2.length() != s3.length()) {
	    		return false;
	    	}

	    	Boolean[][] dp = new Boolean[s1.length()+1][s2.length()+1];// works with Boolean[s1.length()][s2.length()], using +1 version to make it more similar to DP
    		return isInterleaveHelper_Memoization(s1, s2, s3, s1.length()-1, s2.length()-1, s3.length()-1, dp); 
    }

	public static boolean isInterleaveHelper_Memoization(String s1, String s2, String s3, int i, int j, int k, Boolean[][] dp) {
	    	
	    // if we exhausted all the strings, means s3 is interleaving of s1 and s2		    	
	    	if(i<0 && j<0 && k<0) {
	    		return true;
	    	}
	    	
	    	if(i>=0 && j>=0 && dp[i][j]!=null) {
	    		return dp[i][j];
	    	}

	    	boolean firstMatch = false;
	    	boolean secondMatch = false;
	    	// if s1 character matches with s3
	    	if(i>=0) {
	    		firstMatch = s1.charAt(i)==s3.charAt(k) && isInterleaveHelper_Memoization(s1, s2, s3, i-1, j, k-1, dp);
	    	}
	    	// if s2 character matches with s3
	    	if(j>=0) {
	    		secondMatch = s2.charAt(j)==s3.charAt(k) && isInterleaveHelper_Memoization(s1, s2, s3, i, j-1, k-1, dp);
	    	}
	    	if(i>=0 && j>=0) {
	    		dp[i][j] = firstMatch || secondMatch;
	    	}
	    	// if any of the s1 and s2 character matches with s3
	    	return firstMatch || secondMatch;	    			
	}
	
	/**
Submission
https://leetcode.com/submissions/detail/180105743/
You are here! 
Your runtime beats 18.41 % of java submissions.
Runtime (ms)
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	public static boolean isInterleave_DP(String s1, String s2, String s3) {
		// if s3 is not equals s1+s2 size
		if(s1.length()+s2.length() != s3.length()) {
			return false;
		}

		boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
		dp[0][0] = true;
		
		for(int i =1; i<dp.length; i++) {
			if(s1.charAt(i-1)==s3.charAt(i-1) && dp[i-1][0]) {
				dp[i][0] = true;
			}
		}
		
		for(int j =1; j<dp[0].length; j++) {
			if(s2.charAt(j-1) == s3.charAt(j-1) && dp[0][j-1]) {
				dp[0][j] = true;
			}
		}
		
		for(int i =1; i<dp.length; i++) {
			for(int j=1; j<dp[0].length; j++) {
				boolean firstMatch =  s1.charAt(i-1) == s3.charAt(i+j-1) && dp[i-1][j];
				boolean secondMatch = s2.charAt(j-1) == s3.charAt(i+j-1) && dp[i][j-1];
				dp[i][j] = firstMatch || secondMatch;
			}			
		}
		
		return dp[dp.length-1][dp[0].length-1];
	}
	
	/**
Submission
https://leetcode.com/submissions/detail/180105976/
You are here! 
Your runtime beats 13.33 % of java submissions.

	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	public static boolean isInterleave_DP_Optimized(String s1, String s2, String s3) {
		// if s3 is not equals s1+s2 size
		if(s1.length()+s2.length() != s3.length()) {
			return false;
		}

		boolean[] dp = new boolean[s2.length()+1];
		dp[0] = true;

		for(int j =1; j<dp.length; j++) {
			if(s2.charAt(j-1) == s3.charAt(j-1) && dp[j-1]) {
				dp[j] = true;
			}
		}
		
		for(int i =1; i <= s1.length(); i++) {
			dp[0] =  s1.charAt(i-1) == s3.charAt(i-1) && dp[0];
			for(int j=1; j<dp.length; j++) {
				boolean firstMatch =  s1.charAt(i-1) == s3.charAt(i+j-1) && dp[j];
				boolean secondMatch = s2.charAt(j-1) == s3.charAt(i+j-1) && dp[j-1];
				dp[j] = firstMatch || secondMatch;
			}			
		}
		return dp[dp.length-1];
	}
	
	/**
This solution works for non common characters in first and second string.

Input: str1 = "AB",  str2 = "CD"
Output:
    ABCD
    ACBD
    ACDB
    CABD
    CADB
    CDAB

Input: str1 = "AB",  str2 = "C"
Output:
    ABC
    ACB
    CAB
    
Note that the above approach doesn’t work if A and B have some characters in common. 
For example, if string A = “AAB”, string B = “AAC” and string C = “AACAAB”, then the above method will return false
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
    public static boolean isInterleave_Non_Common(String s1, String s2, String s3) {
	    	int i = 0; 
	    	int j = 0; 
	    	int k = 0; 
    	
    	    // Iterate through all characters of C. 
    	    while (k != s3.length()) 
    	    { 
    	        // Match first character of C with first character 
    	        // of A. If matches them move A to next  
    	        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) 
    	            i++; 
    	  
    	        // Else Match first character of C with first  
    	        // character of B. If matches them move B to next  
    	        else if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) 
    	            j++; 
    	   
    	        // If doesn't match with either A or B, then return 
    	        // false 
    	        else
    	            return false; 
    	          
    	        // Move C to next for next iteration 
    	        k++; 
    	    } 
    	  
    	    // If A or B still have some characters, then length of 
    	    // C  is smaller than sum of lengths of A and B, so  
    	    // return false 
    	    if (i!=s1.length() || j!=s2.length()) 
    	        return false; 
    	  
    	    return true; 
    	} 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s1;
		String s2;
		String s3;
		
		s1 = "ABC";
		s2 = "CDE";
		s3 = "ACBDCE";		
		System.out.println(s1+", "+s2+", "+s3+"  NR Is interleaving="+isInterleave_Non_Common(s1, s2, s3));		
		System.out.println(s1+", "+s2+", "+s3+"  BF Is interleaving="+isInterleave_BruteForce(s1, s2, s3));
		System.out.println(s1+", "+s2+", "+s3+"  MM Is interleaving="+isInterleave_Memoization(s1, s2, s3));
		System.out.println(s1+", "+s2+", "+s3+"  DP Is interleaving="+isInterleave_DP(s1, s2, s3));
		System.out.println(s1+", "+s2+", "+s3+" DPO Is interleaving="+isInterleave_DP_Optimized(s1, s2, s3));

		s1 = "AAB";
		s2 = "AAC";
		s3 = "AACAAB";		
		System.out.println(s1+", "+s2+", "+s3+"  BF Is interleaving="+isInterleave_BruteForce(s1, s2, s3));
		System.out.println(s1+", "+s2+", "+s3+"  MM Is interleaving="+isInterleave_Memoization(s1, s2, s3));
		System.out.println(s1+", "+s2+", "+s3+"  DP Is interleaving="+isInterleave_DP(s1, s2, s3));
		System.out.println(s1+", "+s2+", "+s3+" DPO Is interleaving="+isInterleave_DP_Optimized(s1, s2, s3));

		s1 = "aabcc";
		s2 = "dbbca";
		s3 = "aadbbbaccc";		
		System.out.println(s1+", "+s2+", "+s3+"  BF Is interleaving="+isInterleave_BruteForce(s1, s2, s3));
		System.out.println(s1+", "+s2+", "+s3+"  MM Is interleaving="+isInterleave_Memoization(s1, s2, s3));
		System.out.println(s1+", "+s2+", "+s3+"  DP Is interleaving="+isInterleave_DP(s1, s2, s3));
		System.out.println(s1+", "+s2+", "+s3+" DPO Is interleaving="+isInterleave_DP_Optimized(s1, s2, s3));

		s1 = "aabcc";
		s2 = "dbbca";
		s3 = "aadbbcbcac";		
		System.out.println(s1+", "+s2+", "+s3+"  BF Is interleaving="+isInterleave_BruteForce(s1, s2, s3));
		System.out.println(s1+", "+s2+", "+s3+"  MM Is interleaving="+isInterleave_Memoization(s1, s2, s3));
		System.out.println(s1+", "+s2+", "+s3+"  DP Is interleaving="+SolutionDebug.isInterleave_DP(s1, s2, s3));
		System.out.println(s1+", "+s2+", "+s3+" DPO Is interleaving="+SolutionDebug.isInterleave_DP_Optimized(s1, s2, s3));

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

	public static String grid(boolean grid[][]) {
		return grid(grid, null, null);
	}

	public static String grid(boolean grid[][], List rowHeader, List colHeader) {
		int padding = 5;
		StringBuilder output = new StringBuilder("\n");
		// output index of columns
		output.append("-");
		output.append( String.format(" | %1$-"+padding+"s ", "-") );
		int underline = 0;
		for(int i =0; i<grid[0].length; i++) {
			output.append( String.format(" | %1$-"+padding+"s ", i) );
		}
		output.append("|\n");
		underline = output.length();
		
		if(null!=colHeader) {
			output.append("-");
			output.append( String.format(" | %1$-"+padding+"s ", "-") );
			for(int i =0; i<grid[0].length; i++) {
				output.append( String.format(" | %1$-"+padding+"s ", colHeader.get(i)) );
			}
		}

		output.append("|\n");
		IntStream.range(0, underline).forEach(i->output.append("-"));
		output.append("\n");
		
		for(int r=0; r<grid.length; r++) {
			// output index of rows
			output.append( r+ String.format(" | %1$-"+padding+"s ", (rowHeader!=null ? rowHeader.get(r) : "" ) )  );
			
			// grid data
			for(int c=0; c<grid[r].length; c++) {
				output.append( String.format(" | %1$-"+padding+"s ", grid[r][c]) );
			}
			output.append("|\n");
		}
		return output.toString();
	}
	
	public static List asList(Object [] list, int padding, Object value) {
		List l = new ArrayList<>();
		for(int i=0; i<padding; i++) {
			l.add(value);
		}
		l.addAll(Arrays.asList(list));
		return l;
	}

	public static boolean isInterleave_DP(String s1, String s2, String s3) {
		reset();
		debug("s1="+s1 +", s2="+s2+", s3="+s3);
		// if s3 is not equals s1+s2 size
		if(s1.length()+s2.length() != s3.length()) {
			debug("Length mismatch not an interleaving");
			return false;
		}

		boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
		dp[0][0] = true;
		
		for(int i =1; i<dp.length; i++) {
			if(s1.charAt(i-1)==s3.charAt(i-1) && dp[i-1][0]) {
				dp[i][0] = true;
			}
		}
		debug("First Column set DP"+grid(dp, asList(s1.chars().mapToObj(c -> (char)c).toArray(Character[]::new), 1, '-'),asList(s2.chars().mapToObj(c -> (char)c).toArray(Character[]::new), 1, '-')));
		
		for(int j =1; j<dp[0].length; j++) {
			if(s2.charAt(j-1) == s3.charAt(j-1) && dp[0][j-1]) {
				dp[0][j] = true;
			}
		}
		debug("First Row set DP"+grid(dp, asList(s1.chars().mapToObj(c -> (char)c).toArray(Character[]::new), 1, '-'),asList(s2.chars().mapToObj(c -> (char)c).toArray(Character[]::new), 1, '-')));

		tableColumns("i:3", "j:3", "s1.charAt(i-1) == s3.charAt(i+j-1) && dp[i-1][j]:101", "s2.charAt(j-1) == s3.charAt(i+j-1) && dp[i][j-1]:101", "dp[i][j]:18");
		debugColumns();
		for(int i =1; i<dp.length; i++) {
			for(int j=1; j<dp[0].length; j++) {
				boolean firstMatch =  s1.charAt(i-1) == s3.charAt(i+j-1) && dp[i-1][j];
				boolean secondMatch = s2.charAt(j-1) == s3.charAt(i+j-1) && dp[i][j-1];
				dp[i][j] = firstMatch || secondMatch;
				debugRow(i, j, "s1.charAt("+i+"-1) == s3.charAt("+i+"+"+j+"-1) && dp["+i+"-1]["+j+"] => s1.charAt("+(i-1)+") == s3.charAt("+(i+j-1)+") && dp["+(i-1)+"]["+j+"] = "+ (s1.charAt(i-1) == s3.charAt(i+j-1) && dp[i-1][j])
						, "s2.charAt("+j+"-1) == s3.charAt("+i+"+"+j+"1) && dp["+i+"]["+j+"-1] => s2.charAt("+(j-1)+") == s3.charAt("+(i+j-1)+") && dp["+i+"]["+(j-1)+"] = "+(s2.charAt(j-1) == s3.charAt(i+j-1) && dp[i][j-1])
						, "dp["+i+"]["+j+"] = "+(dp[i][j]));
			}			
			debug(i+" iteration DP"+grid(dp, asList(s1.chars().mapToObj(c -> (char)c).toArray(Character[]::new), 1, '-'),asList(s2.chars().mapToObj(c -> (char)c).toArray(Character[]::new), 1, '-')));
			debug("s1="+s1 +", s2="+s2+", s3="+s3);
		}
		debug("DP Result"+grid(dp, asList(s1.chars().mapToObj(c -> (char)c).toArray(Character[]::new), 1, '-'),asList(s2.chars().mapToObj(c -> (char)c).toArray(Character[]::new), 1, '-')));
		return dp[dp.length-1][dp[0].length-1];
	}
	
	public static boolean isInterleave_DP_Optimized(String s1, String s2, String s3) {
		reset();
		debug("\n\n\n");
		debug("s1="+s1 +", s2="+s2+", s3="+s3);
		// if s3 is not equals s1+s2 size
		if(s1.length()+s2.length() != s3.length()) {
			debug("Length mismatch not an interleaving");
			return false;
		}

		boolean[] dp = new boolean[s2.length()+1];
		dp[0] = true;

		for(int j =1; j<dp.length; j++) {
			if(s2.charAt(j-1) == s3.charAt(j-1) && dp[j-1]) {
				dp[j] = true;
			}
		}
		
		debug("First Iteration DP\n"+Arrays.toString(dp));
		
		tableColumns("i:3", "j:3", "s1.charAt(i-1) == s3.charAt(i+j-1) && dp[j]:95", "s2.charAt(j-1) == s3.charAt(i+j-1) && dp[j-1]:96", "dp[i]:14");
		debugColumns();

		for(int i =1; i <= s1.length(); i++) {
			dp[0] =  s1.charAt(i-1) == s3.charAt(i-1) && dp[0];
			for(int j=1; j<dp.length; j++) {
				boolean firstMatch =  s1.charAt(i-1) == s3.charAt(i+j-1) && dp[j];
				boolean secondMatch = s2.charAt(j-1) == s3.charAt(i+j-1) && dp[j-1];
				dp[j] = firstMatch || secondMatch;
				debugRow(i, j, "s1.charAt("+i+"-1) == s3.charAt("+i+"+"+j+"-1) && dp["+j+"] => s1.charAt("+(i-1)+") == s3.charAt("+(i+j-1)+") && dp["+j+"] = "+ (s1.charAt(i-1) == s3.charAt(i+j-1) && dp[j])
						, "s2.charAt("+j+"-1) == s3.charAt("+i+"+"+j+"1) && dp["+j+"-1] => s2.charAt("+(j-1)+") == s3.charAt("+(i+j-1)+") && dp["+(j-1)+"] = "+(s2.charAt(j-1) == s3.charAt(i+j-1) && dp[j-1])
						, "dp["+j+"] = "+dp[j]);
			}			
			debug(i+" Iteration DP\n"+Arrays.toString(dp));
		}
		debug("Result Iteration DP\n"+Arrays.toString(dp));
		return dp[dp.length-1];
	}
}
