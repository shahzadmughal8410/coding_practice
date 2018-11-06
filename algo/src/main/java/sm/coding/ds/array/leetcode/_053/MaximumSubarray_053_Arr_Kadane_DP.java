/**
 * 
 */
package sm.coding.ds.array.leetcode._053;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class MaximumSubarray_053_Arr_Kadane_DP {

	/**
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
Example:
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.


Links
https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
https://www.geeksforgeeks.org/divide-and-conquer-maximum-sum-subarray/ 
http://www.akira.ruc.dk/~keld/teaching/algoritmedesign_f03/Artikler/05/Bentley84.pdf 


Similar questions
https://www.geeksforgeeks.org/maximum-subarray-sum-using-prefix-sum/  

Submission
https://leetcode.com/submissions/detail/155262011/
	 * 
	 * @param args
	 */
	
	public static int maximumSumSubarray_Kadane(int[] arr) { //leetcode submission fails because of negative test cases.
		int maxSoFar = 0;
		int maxEndingHere = 0;
		for(int i =0; i<arr.length;i++) {
			maxEndingHere+=arr[i];
			
			if(maxEndingHere < 0) {
				maxEndingHere = 0;
			}else if(maxSoFar < maxEndingHere) {
				maxSoFar = maxEndingHere;
			}
		}
		return maxSoFar;
	}
	
	public static int[] maximumSumSubarray_Kadane_Indexes(int[] arr) {
		int maxSoFar = 0;
		int maxEndingHere = 0;
		int[] result = new int[3];
		int start = 0;
		int end = 0;
		int s = 0;;
		for(int i =0; i<arr.length;i++) {
			maxEndingHere+=arr[i];
			
			if(maxEndingHere < 0) {
				maxEndingHere = 0;
				s = i+1;
			}else if(maxSoFar < maxEndingHere) {
				maxSoFar = maxEndingHere;
				start = s;
				end = i;
			}
		}
		result[0] = start;
		result[1] = end;
		result[2] = maxSoFar;
		return result;
	}

	/**
Submission
https://leetcode.com/submissions/detail/176320868/
You are here! 
Your runtime beats 25.56 % of java submissions.
	 * @param arr
	 * @return
	 */
	public static int maxSubArray(int[] arr) { // Leetcode Accepted solution https://leetcode.com/submissions/detail/150671214/ 
		int maxSoFar = arr[0];
		int maxEndingHere = arr[0];;
		for(int i =1; i<arr.length;i++) {
			maxEndingHere = Math.max(arr[i], maxEndingHere+arr[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}
		return maxSoFar;
	}
	
	public static int[] maximumSumSubarray_AllNegativeValues_Indexes(int[] arr) {
		int maxSoFar = arr[0];
		int maxEndingHere = arr[0];;
		int[] result = new int[3];
		int start = 0;
		int end = 0;
		int s = 0;
		for(int i =1; i<arr.length;i++) {
			maxEndingHere = Math.max(arr[i], maxEndingHere+arr[i]);
			if(maxEndingHere==arr[i]) {
				s = i;
			}
			
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
			if(maxSoFar==maxEndingHere) {
				start = s;
				end = i;
			}
			
		}
		result[0] = start;
		result[1] = end;
		result[2] = maxSoFar;
		return result;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {-2,1,-3,4,-1,2,1,-5,4}; // sum=6, start=3, end=6
//		int[] arr = new int[] {-2, -3, 4, -1, -2, 1, 5, -3}; // sum=7, start=2, end=6
//		int[] arr = new int[] {-2, -5, 6, -2, -3, 1, 5, -6}; // sum=7, start=2, end=6
//		int[] result = maximumSumSubarray_Kadane_Indexes(arr);
		int[] result = SolutionDebug.maximumSumSubarray_Kadane_Indexes(arr);
		System.out.println("Kadane Max sum subarray = "+Arrays.stream(result).boxed().collect(Collectors.toList())+", "+Arrays.stream(arr).boxed().collect(Collectors.toList()));

		result = maximumSumSubarray_AllNegativeValues_Indexes(arr);
		result = SolutionDebug.maximumSumSubarray_AllNegativeValues_Indexes(arr);
		System.out.println("All Negative Max sum subarray = "+Arrays.stream(result).boxed().collect(Collectors.toList())+", "+Arrays.stream(arr).boxed().collect(Collectors.toList()));

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
	
	public static int[] maximumSumSubarray_Kadane_Indexes(int[] arr) {
		int maxSoFar = 0;
		int maxEndingHere = 0;
		int[] result = new int[3];
		int start = 0;
		int end = 0;
		int s = 0;;
		tableColumns("maxSoFar", "maxEndingHere", "i", "arr[i]", "Math.max(arr[i], maxEndingHere+arr[i])", "start", "end", "s:3");
		debugColumns();
		for(int i =0; i<arr.length;i++) {
			debugRow(maxSoFar, maxEndingHere, i, arr[i], "Math.max("+arr[i]+", "+maxEndingHere+"+("+arr[i]+") )="+(Math.max(arr[i], maxEndingHere+arr[i])), start, end, s);
			maxEndingHere+=arr[i];
			
			if(maxEndingHere < 0) {
				maxEndingHere = 0;
				s = i+1;
			}else if(maxSoFar < maxEndingHere) {
				maxSoFar = maxEndingHere;
				start = s;
				end = i;
			}
		}
		result[0] = start;
		result[1] = end;
		result[2] = maxSoFar;
		debug("Start index="+result[0]);
		debug("End index="+result[1]);
		debug("Max Sum="+result[2]);
		debug("Array="+Arrays.stream(arr).boxed().collect(Collectors.toList()));
		debug("Max sub array="+Arrays.stream(Arrays.copyOfRange(arr, result[0], result[1]+1)).boxed().collect(Collectors.toList()));
		return result;
	}
	
	public static int[] maximumSumSubarray_AllNegativeValues_Indexes(int[] arr) {
		int maxSoFar = arr[0];
		int maxEndingHere = arr[0];;
		int[] result = new int[3];
		int start = 0;
		int end = 0;
		int s = 0;
		reset();
		tableColumns("maxSoFar", "maxEndingHere", "i", "arr[i]", "Math.max(arr[i], maxEndingHere+arr[i])", "start", "end", "s:3");
		debugColumns();
		for(int i =1; i<arr.length;i++) {
			debugRow(maxSoFar, maxEndingHere, i, arr[i], "Math.max("+arr[i]+", "+maxEndingHere+"+("+arr[i]+") )="+(Math.max(arr[i], maxEndingHere+arr[i])), start, end, s);
			maxEndingHere = Math.max(arr[i], maxEndingHere+arr[i]);
			if(maxEndingHere==arr[i]) {
				s = i;
			}
			
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
			if(maxSoFar==maxEndingHere) {
				start = s;
				end = i;
			}
			
		}
		result[0] = start;
		result[1] = end;
		result[2] = maxSoFar;
		debug("Start index="+result[0]);
		debug("End index="+result[1]);
		debug("Max Sum="+result[2]);
		debug("Array="+Arrays.stream(arr).boxed().collect(Collectors.toList()));
		debug("Max sub array="+Arrays.stream(Arrays.copyOfRange(arr, result[0], result[1]+1)).boxed().collect(Collectors.toList()));
		return result;
	}
}
