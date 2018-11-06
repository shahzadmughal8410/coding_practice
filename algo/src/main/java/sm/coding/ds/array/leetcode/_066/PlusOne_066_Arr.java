/**
 * 
 */
package sm.coding.ds.array.leetcode._066;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class PlusOne_066_Arr {

	/**
Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
You may assume the integer do not contain any leading zero, except the number 0 itself.
The digits are stored such that the most significant digit is at the head of the list.
Links
https://leetcode.com/problems/plus-one/discuss/24082/My-Simple-Java-Solution 
https://www.geeksforgeeks.org/add-1-number-represented-array/


Similar questions
https://leetcode.com/problems/plus-one-linked-list/description/ 
https://leetcode.com/problems/add-binary/description/  
	 * 
	 * @param args
	 */
	public static int[] plusOne(int[] nums) {
		if(null==nums || nums.length==0) {
			return new int[] {1};
		}
		
		for(int i =nums.length-1; i>=0;i--) {
			if(nums[i]<9) {
				nums[i] +=1;
				return nums;
			}else {
				nums[i]=0;
			}
		}
		
		int[] result = new int[nums.length+1];
		result[0]=1;
		return result;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {1,2,3};
		System.out.printf("plus one %s", Arrays.stream(nums).boxed().collect(Collectors.toList()));
		int[] numsPlusOne = plusOne(nums);
		System.out.printf(" --> %s %n", Arrays.stream(numsPlusOne).boxed().collect(Collectors.toList()));

		nums = new int[] {9,9,9};
		System.out.printf("plus one %s", Arrays.stream(nums).boxed().collect(Collectors.toList()));
		numsPlusOne = plusOne(nums);
		System.out.printf(" --> %s %n", Arrays.stream(numsPlusOne).boxed().collect(Collectors.toList()));

		nums = new int[] {1,9,9,9};
		System.out.printf("plus one %s", Arrays.stream(nums).boxed().collect(Collectors.toList()));
		numsPlusOne = plusOne(nums);
		System.out.printf(" --> %s %n", Arrays.stream(numsPlusOne).boxed().collect(Collectors.toList()));

		nums = new int[] {0,0};
		System.out.printf("plus one %s", Arrays.stream(nums).boxed().collect(Collectors.toList()));
		numsPlusOne = plusOne(nums);
		System.out.printf(" --> %s %n", Arrays.stream(numsPlusOne).boxed().collect(Collectors.toList()));

	}

}
