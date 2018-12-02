/**
 * 
 */
package sm.coding.algo.dp.leetcode._072;

import java.util.regex.Pattern;

/**
 * @author shahzadmughal8410
 *
 */
public class EditDistance_Recr_BckTrk_DP_072 {

	/**
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
You have the following 3 operations permitted on a word:
Insert a character
Delete a character
Replace a character
Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')


Example 2:
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')


Links
https://leetcode.com/problems/edit-distance/description/
https://www.geeksforgeeks.org/edit-distance-dp-5/ 
	 */
	
	/**
Submission
https://leetcode.com/submissions/detail/176462699/
Time Limit Exceeded
Last executed input:
"dinitrophenylhydrazine"
"benzalphenylhydrazone"

	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int minDistance_BruteForce(String s1, String s2) {
		return minDistanceHelper_BruteForce(s1, s2, s1.length(), s2.length());
	}

	public static int minDistanceHelper_BruteForce(String word1, String word2, int m, int n) {
		if(word1.equals(word2)) {
			return 0;
		}
		if (m == 0 || n==0){
			return Math.abs(m-n);
		}
		
		// base case
		if(word1.charAt(m-1) == word2.charAt(n-1)) {
			return minDistanceHelper_BruteForce(word1, word2, m-1, n-1);
		}
		else {
			// recursive case
			return 1 + min (
						minDistanceHelper_BruteForce(word1, word2, m, n-1), //insert
						minDistanceHelper_BruteForce(word1, word2, m-1, n), //delete
						minDistanceHelper_BruteForce(word1, word2, m-1, n-1) //update
					);
		}
	}

	/**
Submission
https://leetcode.com/submissions/detail/192896758/
You are here! 
Your runtime beats 75.64 % of java submissions.

	 * @param word1
	 * @param word2
	 * @return
	 */
	public static int minDistance_DP(String word1, String word2) {
		if(word1.equals(word2)) {
			return 0;
		}
		int m = word1.length();
		int n = word2.length();
		
		if (m == 0 || n==0){
			return Math.abs(m-n);
		}
		
		int[][] dp = new int[m+1][n+1];
		
		// first row
		for(int i=1; i<dp.length;i++) {
			dp[i][0] = i;
		}
		// first column
		for(int j=1; j<dp[0].length;j++) {
			dp[0][j] = j;
		}
		// rest of the table
		for(int i=1; i<dp.length;i++) {
			for(int j=1; j<dp[0].length;j++) {
				// if character is same means NO change i.e 0 cost
				if(word1.charAt(i-1) == word2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				}
				else {
					dp[i][j] = 1+ min (
								dp[i][j-1], // insert
								dp[i-1][j], // remove
								dp[i-1][j-1] //replace
							);					
				}
			}
		}
		return dp[dp.length-1][dp[0].length-1];
	}
	
	public static int min(int a, int b, int c) {
        if (a<=b && a<=c) return a;
        if (b<=a && b<=c) return b;
        else return c;
        // following can  be used as well
//        return Math.min(a, Math.min(b, c));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        String str1 = "sunday";
        String str2 = "saturday";
//        System.out.println("BF="+ minDistance_BruteForce( str1 , str2) );	
//        System.out.println("DP="+ minDistance_DP( str1 , str2) );	
        System.out.println("BF="+ SolutionDebug.minDistance_BruteForce( str1 , str2) );	
        System.out.println("DP="+ SolutionDebug.minDistance_DP( str1 , str2) );	

        str1 = "horse";
		str2 = "ros";
        System.out.println("BF="+ minDistance_BruteForce( str1 , str2) );	
        System.out.println("DP="+ minDistance_DP( str1 , str2) );	

        str1 = "intention";
		str2 = "execution";
        System.out.println("BF="+ minDistance_BruteForce( str1 , str2) );	
        System.out.println("DP="+ minDistance_DP( str1 , str2) );	
        
        str1 = "dinitrophenylhydrazine";
        	str2 = "benzalphenylhydrazone";
        System.out.println("DP="+ minDistance_DP( str1 , str2) );	
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

	public static String grid(int grid[][]) {
		StringBuilder output = new StringBuilder("\n");
		for(int r=0; r<grid.length; r++) {
			for(int c=0; c<grid[r].length; c++) {
				output.append("|"+grid[r][c]);
			}
			output.append("|\n");
		}
		return output.toString();
	}
	
	public static int minDistance_BruteForce(String s1, String s2) {
		return minDistanceHelper_BruteForce(s1, s2, s1.length(), s2.length());
	}

	public static int minDistanceHelper_BruteForce(String word1, String word2, int m, int n) {
		debugRecr("word1="+word1+", word2="+word2+", m="+m+", n="+n);
		if(word1.equals(word2)) {
			return 0;
		}
		if (m == 0 || n==0){
			debugRecr("m="+m+", n="+n+", Math.abs("+m+"-"+n+")="+(Math.abs(m-n))) ;
			int result = Math.abs(m-n);
			return result;
		}
		
		// base case
		if(word1.charAt(m-1) == word2.charAt(n-1)) {
			debugRecr("Characters same at word1.charAt(m-1) == word2.charAt(n-1) => word1.charAt("+m+"-1) == word2.charAt("+n+"-1) => "+(word1.charAt(m-1)+"=="+word2.charAt(n-1)));
			return minDistanceHelper_BruteForce(word1, word2, m-1, n-1);
		}
		else {
			String actual = incrementIndent();
			// recursive case
			int min = 1 + min (
						minDistanceHelper_BruteForce(word1, word2, m, n-1), //insert
						minDistanceHelper_BruteForce(word1, word2, m-1, n), //delete
						minDistanceHelper_BruteForce(word1, word2, m-1, n-1) //update
					);
			setIndent(actual);
			debugRecr("min="+min);
			return min;
		}
	}
	
	public static int minDistance_DP(String word1, String word2) {
		reset();
		debug("word1="+word1+", word2="+word2);
		if(word1.equals(word2)) {
			return 0;
		}
		int m = word1.length();
		int n = word2.length();
		
		if (m == 0 || n==0){
			debug("Any one m or n is Zero") ;
			return Math.abs(m-n);
		}
		
		int[][] dp = new int[m+1][n+1];
		
		// first column
		for(int i=1; i<dp.length;i++) {
			dp[i][0] = i;
		}
		debug("DP first column\n"+grid(dp));
		// first row
		for(int j=1; j<dp[0].length;j++) {
			dp[0][j] = j;
		}
		debug("DP first row\n"+grid(dp));
		// rest of the table
		for(int i=1; i<dp.length;i++) {
			for(int j=1; j<dp[0].length;j++) {
				// if character is same means NO change i.e 0 cost
				if(word1.charAt(i-1) == word2.charAt(j-1)) {
					debug("Character match at (i-1)="+(i-1)+", (j-1)="+(j-1));
					dp[i][j] = dp[i-1][j-1];
				}
				else {
					dp[i][j] = 1+ min (
								dp[i][j-1], // insert
								dp[i-1][j], // remove
								dp[i-1][j-1] //replace
							);					
				}
			}
		}
		debug("DP table\n"+grid(dp));
		return dp[dp.length-1][dp[0].length-1];
	}
	
	public static int min(int a, int b, int c) {
        if (a<=b && a<=c) return a;
        if (b<=a && b<=c) return b;
        else return c;
        // following can  be used as well
//        return Math.min(a, Math.min(b, c));
	}
	
}