/**
 * 
 */
package sm.coding.algo.sorting_searching.icf.group_numbers;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class GroupTheNumbers_Q6 {

	
	/**
Given an array A[], write a function that segregates even and odd numbers. 
The functions should put all even numbers first, and then odd numbers.
Example

Input  = {12, 34, 45, 9, 8, 90, 3}
Output = {12, 34, 8, 90, 45, 9, 3} 
In the output, the order of numbers can be changed, i.e., in the above example, 
34 can come before 12 and 3 can come before 9.
	 */
	
	public static void segregateEvenOdd(int arr[]) {
		int left = 0;
		int right = arr.length-1;
		
		//first first odd number from left
		while(left<right) {
			while( (arr[left] % 2) == 0 && left<right) {
				left++;
			}//found the first odd number from left
		
			// find first even number from right
			while( (arr[right] % 2) != 0 && left<right) {
				right--;
			}// found the first even number from right
			
			if(left<right) {
				swap(arr, left, right);
				left++;
				right--;
			}
		}
	}
	
	public static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;				
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[] {12, 34, 45, 9, 8, 90, 3};
		System.out.println(Arrays.stream(arr).boxed().collect(Collectors.toList()));
		segregateEvenOdd(arr);
		System.out.println(Arrays.stream(arr).boxed().collect(Collectors.toList()));

		arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		System.out.println(Arrays.stream(arr).boxed().collect(Collectors.toList()));
		segregateEvenOdd(arr);
		System.out.println(Arrays.stream(arr).boxed().collect(Collectors.toList()));

	}

}
