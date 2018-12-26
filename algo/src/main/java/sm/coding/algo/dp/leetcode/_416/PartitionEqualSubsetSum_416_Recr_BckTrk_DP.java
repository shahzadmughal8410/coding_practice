/**
 * 
 */
package sm.coding.algo.dp.leetcode._416;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class PartitionEqualSubsetSum_416_Recr_BckTrk_DP {

	/**
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.

Submission
https://leetcode.com/submissions/detail/197048275/
Time Limit Exceeded
Last executed input:
[100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,
100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,
100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,
100,100,100,100,100,100,100,100,100,100,100,100,100,100]
	 */
    public static boolean canPartition_BruteForce(int[] nums) {
    		int sum = 0;
        for(int i =0; i<nums.length;i++) {
        		sum+=nums[i];
        }
        
        if(sum%2 != 0) {
        		return false;
        }
        sum = sum/2;
        return canPartitionHelper_BruteForce(nums, sum, nums.length);
    }
	
    public static boolean canPartitionHelper_BruteForce(int[] nums, int sum, int index) {
        if(sum==0) {
        		return true;
        }
        if(index==0 && sum!=0) {
        		return false;
        }
        
        if(nums[index-1] > sum) {
        		return canPartitionHelper_BruteForce(nums, sum, index-1);
        }
        else {
        		return canPartitionHelper_BruteForce(nums, sum, index-1) || canPartitionHelper_BruteForce(nums, sum-nums[index-1], index-1);
        }
    }

    /**
Submission
https://leetcode.com/submissions/detail/197048545/
You are here! 
Your runtime beats 44.01 % of java submissions.
     * @param nums
     * @return
     */
    public static boolean canPartition_DP(int[] nums) {
		int sum = 0;
	    for(int i =0; i<nums.length;i++) {
	    		sum+=nums[i];
	    }
	    
	    if(sum%2 != 0) {
	    		return false;
	    }
        sum = sum/2;

	    boolean dp[][] = new boolean[nums.length+1][sum+1];
	    
	    dp[0][0] = true;
	    
	    for(int i =1; i<dp.length;i++) {
	    		for(int j=1; j<dp[0].length;j++) {
	    			if(nums[i-1] > j) {
	    				dp[i][j] = dp[i-1][j];
	    			}else {
	    				dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
	    			}
	    		}	    	
	    }
	    return dp[dp.length-1][dp[0].length-1];
    }

    /**
Submission
https://leetcode.com/submissions/detail/197048725/
You are here! 
Your runtime beats 51.81 % of java submissions.
     * @param nums
     * @return
     */
    public static boolean canPartition_DP_Optimized(int[] nums) {
		int sum = 0;
	    for(int i =0; i<nums.length;i++) {
	    		sum+=nums[i];
	    }
	    
	    if(sum%2 != 0) {
	    		return false;
	    }
        sum = sum/2;
        boolean [] dp = new boolean[sum+1];
        dp[0]=true;
        for(int i=1; i<=nums.length;++i){
            for(int j=sum; j>=0;--j){
                if(nums[i-1] <= j){
                    dp[j]=dp[j-nums[i-1]]||dp[j];
                }
            }
        }
        return dp[dp.length-1];
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums;

		nums = new int[] {1, 5, 11, 5} ;
		System.out.println("BF can partation="+canPartition_BruteForce(nums));
		System.out.println("DP can partation="+canPartition_DP(nums));
		
		nums = new int[] {1, 3, 7, 3} ;
		System.out.println("DP can partation="+SolutionDebug.canPartition_DP(nums));
		System.out.println("DP can partation="+SolutionDebug.canPartition_DP_Optimized(nums));

		nums = new int[] {1, 2, 3, 5} ;
		System.out.println("BF can partation="+canPartition_BruteForce(nums));
		System.out.println("DP can partation="+canPartition_DP(nums));

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
		StringBuilder output = new StringBuilder("\n");
		for(int r=0; r<grid.length; r++) {
			for(int c=0; c<grid[r].length; c++) {
				output.append("|"+grid[r][c]);
			}
			output.append("|\n");
		}
		return output.toString();
	}
	
    public static boolean canPartition_DP(int[] nums) {
    		debug("nums="+Arrays.stream(nums).boxed().collect(Collectors.toList()));
		int sum = 0;
	    for(int i =0; i<nums.length;i++) {
	    		sum+=nums[i];
	    }
	    
	    if(sum%2 != 0) {
	    		return false;
	    }
        sum = sum/2;

	    boolean dp[][] = new boolean[nums.length+1][sum+1];
	    
	    dp[0][0] = true;
	    
	    for(int i =1; i<dp.length;i++) {
	    		for(int j=1; j<dp[0].length;j++) {
	    			if(j-nums[i-1]>=0) {
	    				dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
	    			}else {
	    				dp[i][j] = dp[i-1][j];
	    			}
	    		}	    	
	    }
	    debug("DP="+grid(dp));
	    return dp[dp.length-1][dp[0].length-1];
    }
    
    public static boolean canPartition_DP_Optimized(int[] nums) {
		debug("nums="+Arrays.stream(nums).boxed().collect(Collectors.toList()));
		int sum = 0;
	    for(int i =0; i<nums.length;i++) {
	    		sum+=nums[i];
	    }
	    
	    if(sum%2 != 0) {
	    		return false;
	    }
        sum = sum/2;
        boolean [] dp = new boolean[sum+1];
        dp[0]=true;
        for(int i=1; i<=nums.length;++i){
            for(int j=sum; j>=0;--j){
                if(j-nums[i-1]>=0){
                    dp[j]=dp[j-nums[i-1]]||dp[j];
                }
            }
            debug("dp="+Arrays.toString(dp));
        }
        return dp[dp.length-1];
    }
}