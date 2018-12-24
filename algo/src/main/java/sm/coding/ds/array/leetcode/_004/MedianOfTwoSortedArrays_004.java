/**
 * 
 */
package sm.coding.ds.array.leetcode._004;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class MedianOfTwoSortedArrays_004 {

	/**

Hard
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

Submission
https://leetcode.com/submissions/detail/196150072/
You are here! 
Your runtime beats 95.63 % of java submissions.

	 */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    		// we will make nums1 always the smaller length array 
    		if(nums1.length > nums2.length) {
    			int[] tmp = nums1;
    			nums1 = nums2;
    			nums2 = tmp;
    		}
    		
    		int a = nums1.length;
    		int b = nums2.length;
    		
    		int lo = 0;
    		int high = a;
    		
    		while(lo<=high) {
    			
    			int partitionA = (lo+high)/2;
    			int partitionB = ( (a+b+1)/2 ) - partitionA;
    			
    			int maxLeftA = (partitionA == 0) ? Integer.MIN_VALUE : nums1[partitionA-1];
    			int minRightA = (partitionA == a) ? Integer.MAX_VALUE : nums1[partitionA];
    			
    			int maxLeftB = (partitionB == 0) ? Integer.MIN_VALUE : nums2[partitionB-1];
    			int minRightB = (partitionB == b) ? Integer.MAX_VALUE : nums2[partitionB];
    			
    			if(maxLeftA <= minRightB && maxLeftB<=minRightA) {
    				
    				if( (a+b) % 2== 0) {
    					return  ( (double)Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB) ) / 2 ;// note 2.0 to make it double
    				}else {
    					return (double) Math.max(maxLeftA, maxLeftB);
    				}
    			}else if (maxLeftA > minRightB) {
    				high = partitionA - 1;
    			}else {
    				lo = partitionA + 1;
    			}
    		}
    		// if we reach here means both arrays are not sorted
        return Double.NEGATIVE_INFINITY;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums1 = new int[]{2, 5, 9, 15, 20, 35, 52};
		int[] nums2 = new int[]{4, 25, 70, 80};
		
//		double median = findMedianSortedArrays(nums1, nums2);
		double median = SolutionDebug.findMedianSortedArrays(nums1, nums2);
		System.out.println("median="+median);

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

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		// we will make nums1 always the smaller length array 
		if(nums1.length > nums2.length) {
			int[] tmp = nums1;
			nums1 = nums2;
			nums2 = tmp;
		}
		
		debug("num1="+Arrays.stream(nums1).boxed().collect(Collectors.toList()));
		debug("num2="+Arrays.stream(nums2).boxed().collect(Collectors.toList()));
		
		int a = nums1.length;
		int b = nums2.length;
		
		debug("a="+a);
		debug("b="+b);
		
		int lo = 0;
		int high = a;

		debug("lo="+lo);
		debug("high="+high);
		
//		tableColumns("lo", "high", "partitionA", "partitionA");
//		debugColumns();

		while(lo<=high) {
			
			int partitionA = (lo+high)/2;
			int partitionB = ( (a+b+1)/2 ) - partitionA;
			
			int maxLeftA = (partitionA == 0) ? Integer.MIN_VALUE : nums1[partitionA-1];
			int minRightA = (partitionA == a) ? Integer.MAX_VALUE : nums1[partitionA];
			
			int maxLeftB = (partitionB == 0) ? Integer.MIN_VALUE : nums2[partitionB-1];
			int minRightB = (partitionB == b) ? Integer.MAX_VALUE : nums2[partitionB];
			
			String outputA = "nums1 = ";
			for(int i=0; i<partitionA;i++) {
				outputA+=" "+nums1[i];
			}
			outputA+=" |" ;
			for(int i=partitionA; i<nums1.length;i++) {
				outputA+=" "+nums1[i];
			}
			
			String outputB = "nums2 = ";
			for(int i=0; i<partitionB;i++) {
				outputB+=" "+nums2[i];
			}
			outputB+=" |" ;
			for(int i=partitionB; i<nums2.length;i++) {
				outputB+=" "+nums2[i];
			}
			
			debug(outputA);
			debug(outputB);
						
			if(maxLeftA <= minRightB && maxLeftB<=minRightA) {
				
				if( (a+b) % 2== 0) {
					return  ( (double)Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB) ) / 2 ;// note 2.0 to make it double
				}else {
					return (double) Math.max(maxLeftA, maxLeftB);
				}
			}else if (maxLeftA > minRightB) {
				high = partitionA - 1;
			}else {
				lo = partitionA + 1;
			}
		}
		// if we reach here means both arrays are not sorted
    return Double.NEGATIVE_INFINITY;
}
}
