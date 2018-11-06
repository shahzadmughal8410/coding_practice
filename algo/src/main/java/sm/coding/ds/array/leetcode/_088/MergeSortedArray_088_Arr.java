/**
 * 
 */
package sm.coding.ds.array.leetcode._088;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class MergeSortedArray_088_Arr {

	/**
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.

Links
https://leetcode.com/problems/merge-sorted-array/description/
https://www.geeksforgeeks.org/merge-two-sorted-arrays/ 

Similar questions
https://www.geeksforgeeks.org/merge-two-sorted-arrays-o1-extra-space/ 
https://leetcode.com/problems/merge-two-sorted-lists/ 
https://www.geeksforgeeks.org/merge-k-sorted-arrays/ 

Submission
https://leetcode.com/submissions/detail/167331035/
You are here! 
Your runtime beats 99.94 % of java submissions.
 
	 * @param args
	 */
	public static void merge(int[] a, int m, int[] b, int n) {
		int i = m-1;
		int j = n-1;
		int k = m+n-1;
		
		// traverse in reverse order
		while(i>=0 && j>=0) {
			// as we are traversing from end, we need to pick the bigger value
			// and push that to the end of array a
			if(a[i]>b[j]) {
				a[k--] = a[i--];
			}else {
				a[k--] = b[j--];
			}
		}
		// if a2 is not completely exhuasted, then fill the remaining elements.
		// if a1 is not exuasted the elements are already sorted, nothing to do.
		// at a time either a1 or a2 would have remaining elements
		while(j>=0) {
			a[k--] = b[j--]; 
		}
	}
	
	public static void main(String[] args) {
		int[] a = new int[] {1,3,5,7,9,
								0,0,0,0,0};//extra space to merge b
		System.out.println("a="+Arrays.stream(a).boxed().collect(Collectors.toList()));
		int[] b = new int[] {2,4,6,8,10};
		
		merge(a, 5, b, 5);
		System.out.println("b="+Arrays.stream(b).boxed().collect(Collectors.toList()));
		System.out.println("a="+Arrays.stream(a).boxed().collect(Collectors.toList()));

		a = new int[] {2,6,8,
				0,0,0};//extra space to merge b
		System.out.println("a="+Arrays.stream(a).boxed().collect(Collectors.toList()));
		b = new int[] {2,6,8};
		
		merge(a, 3, b, 3);
		System.out.println("b="+Arrays.stream(b).boxed().collect(Collectors.toList()));
		System.out.println("a="+Arrays.stream(a).boxed().collect(Collectors.toList()));
	}

}
