/**
 * 
 */
package sm.coding.algo.practice.recursion.ctci._08_03;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author smughal
 *
 */
public class MagicIndex_WithDuplicatesElements_08_03 {

	/**
8.3 Magic Index: A magic index in an array A [e ... n -1] is defined to be an index such that A[ i] =
i. Given a sorted array of distinct integers, write a method to find a magic index, if one exists, in
array A.
FOLLOW UP
What if the values are not distinct?
Hints: # 170, #204, #240, #286, #340
 
	 * @param args
	 */
	public static int findMagicIndex(int[] arr) {
		return findMagicIndexHelper(arr, 0, arr.length-1);
	}
	
	public static int findMagicIndexHelper(int[] arr, int start, int end) {
		if(start>end) {
			return -1;
		}
		
		int midIndex = (start+end)/2;
		int midValue = arr[midIndex];
		
		if(midIndex==arr[midIndex]) {
			return midIndex;
		}
		
		int leftIndex = Math.min(midIndex-1, midValue);
		int leftMatch = findMagicIndexHelper(arr, start, leftIndex);
		if(leftMatch>=0) {
			return leftMatch;
		}
		
		int rightIndex = Math.max(midIndex+1, midValue);
		int right = findMagicIndexHelper(arr, rightIndex, end);
		
		return right;
		
	}
	
	
	
	public static void main(String[] args) {
		int[] arr =   new int[]{-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13};
		int[] index = new int[]{  0,  1, 2, 3, 4, 5, 6, 7, 8,  9, 10};
		
		System.out.println("array="+Arrays.stream(arr).boxed().collect(Collectors.toList()));
		System.out.println("index="+Arrays.stream(index).boxed().collect(Collectors.toList()));
		System.out.println(findMagicIndex(arr));
	}

}
