/**
 * 
 */
package sm.coding.algo.dp.leetcode._010;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class RegularExpressionMatching_010 {

	/**

Hard

1989

411

Favorite

Share
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false

Submission
https://leetcode.com/submissions/detail/197397561/
You are here! 
Your runtime beats 97.56 % of java submissions.

	 */
    public static boolean isMatch(String s, String p) {
        char[] text = s.toCharArray();
        char[] pattern = p.toCharArray();
        boolean dp[][] = new boolean[text.length + 1][pattern.length + 1];

        dp[0][0] = true;// if both text and pattern is empty, its a match
        
        //Deals with patterns like a* or a*b* or a*b*c*
        // i.e. above cases match against empty string
        for (int i = 1; i < dp[0].length; i++) {
            if (pattern[i-1] == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }
        
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
            		// same character or match with '.'
                if (pattern[j - 1] == text[i - 1] || pattern[j - 1] == '.') {
                    dp[i][j] = dp[i-1][j-1];// current is true only if the previous match is true
                } else if (pattern[j - 1] == '*')  {
                		if(dp[i][j - 2]) {// case where we consider 'a*' as empty
                			dp[i][j] = dp[i][j - 2]; 
                		}
                		else if (pattern[j - 2] == text[i - 1] || pattern[j-2] == '.') {
                        dp[i][j] = dp[i - 1][j];
                    }
                }                 
//                else dp[i][j] = false;// no need of else as by default its false 
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String text = "xaabyc";
		String pattern = "xa*b.c";
//		boolean match = isMatch(text, pattern);
		boolean match = SolutionDebug.isMatch(text, pattern);
		System.out.println("text    ="+text);
		System.out.println("pattern ="+pattern);
		System.out.println("match   ="+match);

		text = "aa";
		pattern = "a";
//		match = isMatch(text, pattern);
		match = SolutionDebug.isMatch(text, pattern);
		System.out.println("text    ="+text);
		System.out.println("pattern ="+pattern);
		System.out.println("match   ="+match);

		text = "aab";
		pattern = "c*a*b";
//		match = isMatch(text, pattern);
		match = SolutionDebug.isMatch(text, pattern);
		System.out.println("text    ="+text);
		System.out.println("pattern ="+pattern);
		System.out.println("match   ="+match);

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
		return grid(grid, null, null, "*", "@");
	}

	public static String grid(boolean grid[][], List rowHeader, List colHeader, String rowHeading, String colHeading) {
		int padding = 5;
		StringBuilder output = new StringBuilder("\n");
		// output index of columns
		output.append("index");
		output.append( String.format(" | %1$-"+Math.max(padding, rowHeading.length()+colHeading.length())+"s ", "-") );
		int underline = 0;
		for(int i =0; i<grid[0].length; i++) {
			output.append( String.format(" | %1$-"+padding+"s ", i) );
		}
		output.append("|\n");
		underline = output.length();
		
		if(null!=colHeader) {
			output.append("-");
			output.append( String.format("     | %1$-"+Math.max(padding, rowHeading.length()+colHeading.length())+"s ", rowHeading+colHeading) );
			for(int i =0; i<grid[0].length; i++) {
				output.append( String.format(" | %1$-"+padding+"s ", colHeader.get(i)) );
			}
		}

		output.append("|\n");
		IntStream.range(0, underline).forEach(i->output.append("-"));
		output.append("\n");
		
		for(int r=0; r<grid.length; r++) {
			// output index of rows
			output.append( r+ String.format("     | %1$"+Math.max(padding, rowHeading.length()+colHeading.length())+"s ", (rowHeader!=null ? rowHeader.get(r) : "" ) )  );
			
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
	
    public static boolean isMatch(String s, String p) {
		reset();
    		debug("string  = "+s);
		debug("pattern = "+p);
        char[] text = s.toCharArray();
        char[] pattern = p.toCharArray();
        boolean dp[][] = new boolean[text.length + 1][pattern.length + 1];

        dp[0][0] = true;// if both text and pattern is empty, its a match
        
        //Deals with patterns like a* or a*b* or a*b*c*
        // i.e. above cases match against empty string
        for (int i = 1; i < dp[0].length; i++) {
            if (pattern[i-1] == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }
        
        debug("DP first row initialized\n"+grid(dp, asList(s.chars().mapToObj(c -> (char)c).toArray(Character[]::new), 1, '-'), asList(p.chars().mapToObj(c -> (char)c).toArray(Character[]::new), 1, '-'), "text ", "/pattern"));

        tableColumns("i:3", "j:3", 
        		"pattern[j - 1] == text[i - 1] || pattern[j - 1] == '.':77",
        		"pattern[j - 1] == '*':45",
        		"dp[i][j - 2]:20",
        		"pattern[j - 2] == text[i - 1] || pattern[j-2] == '.':77"
        		);
        debugColumns();
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
            	
            		debugRow(i, j, 
            				"pattern["+(j - 1)+"] == text["+(i - 1)+"] || pattern["+(j - 1)+"] == '.' ==> "+pattern[j - 1]+" == "+text[i - 1]+" || "+pattern[j - 1]+" == '.' ==> "+(pattern[j - 1] == text[i - 1] || pattern[j - 1] == '.') ,
            				"pattern["+(j - 1)+"] == '*' ==> "+pattern[j - 1]+" == '*' ==> "+(pattern[j - 1] == '*'),
            				!(pattern[j - 1] == '*') ? "--" : "dp["+i+"]["+(j - 2)+"] ==> "+(dp[i][j - 2]),
            				!(pattern[j - 1] == '*') ? "--" : "pattern["+(j - 2)+"] == text["+(i - 1)+"] || pattern["+(j - 2)+"] == '.' ==> "+pattern[j - 2]+" == "+text[i - 1]+" || "+pattern[j - 2]+" == '.' ==> "+(pattern[j - 2] == text[i - 1] || pattern[j - 2] == '.') 
            				);
            	
            		// same character or match with '.'
                if (pattern[j - 1] == text[i - 1] || pattern[j - 1] == '.') {
                    dp[i][j] = dp[i-1][j-1];// current is true only if the previous match is true
                } else if (pattern[j - 1] == '*')  {
                		if(dp[i][j - 2]) {// case where we consider 'a*' as empty
                			dp[i][j] = dp[i][j - 2]; 
                		}
                		else if (pattern[j - 2] == text[i - 1] || pattern[j-2] == '.') {
                        dp[i][j] = dp[i - 1][j];
                    }
                }                 
//                else dp[i][j] = false;// no need of else as by default its false 
            }
        }
        debug("DP result\n"+grid(dp, asList(s.chars().mapToObj(c -> (char)c).toArray(Character[]::new), 1, '-'), asList(p.chars().mapToObj(c -> (char)c).toArray(Character[]::new), 1, '-'), "text ", "/pattern"));

        return dp[dp.length-1][dp[0].length-1];
    }
}
