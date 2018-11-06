package sm.coding.algo.practice.recursion.ctci._08_03;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MagicIndex_08_03 {

	/**
	 * 
8.3 Magic Index: A magic index in an array A [e ... n -1] is defined to be an index such that A[ i] =
i. Given a sorted array of distinct integers, write a method to find a magic index, if one exists, in
array A.
FOLLOW UP
What if the values are not distinct?
Hints: # 170, #204, #240, #286, #340
 
	 */
	public static int findMagicIndex_Distinct(int[] arr) {
		return magicHelper(arr, 0, arr.length-1);
	}
	
	public static int magicHelper(int[] arr, int start, int end) {
		if(start>end) {
			return -1;
		}
		
		int mid = (start+end)/2;
		
		if(mid==arr[mid]) {
			return mid;
		}else if(arr[mid]<mid){
			return magicHelper(arr, mid+1, end);
		}else {
			return magicHelper(arr, start, mid-1);
		}
	}
	
	public static void main(String[] args) {
		int[] arr =   new int[]{-40, -20, -1, 1, 2, 3, 5, 7, 9, 12, 13};
		int[] index = new int[]{  0,   1,  2, 3, 4, 5, 6, 7, 8,  9, 10};
		
		System.out.println("array="+Arrays.stream(arr).boxed().collect(Collectors.toList()));
		System.out.println("index="+Arrays.stream(index).boxed().collect(Collectors.toList()));
		System.out.println(findMagicIndex_Distinct(arr));
	}
}
