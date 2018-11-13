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
			if(nums[i]<9) { // this digit is less then 9 then simply add 1 in it and return
				nums[i] +=1;
				return nums;
			}else { // the digit is 9 so make this digit zero
				nums[i]=0;
			}
		}
		// if we are reahing here, it means all the digits in number weere 9, i.e. 99 or 99999
		// for this case it will be overflow and we need new array to store number +1 the actual array size
		int[] result = new int[nums.length+1];
		result[0]=1;// mke first digit as 1, rest of them will be zeros i.e. for 99 it will become 100
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
