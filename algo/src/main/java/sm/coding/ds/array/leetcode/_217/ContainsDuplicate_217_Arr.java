/**
 * 
 */
package sm.coding.ds.array.leetcode._217;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class ContainsDuplicate_217_Arr {

	/**
Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
Links
https://leetcode.com/problems/contains-duplicate/solution/ 
https://www.geeksforgeeks.org/check-given-array-contains-duplicate-elements-within-k-distance/  
	 * @param args
	 */
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int n :nums) {
	        if(!set.add(n)) {
	        		return true;
	        }
        }
        return false;
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {10, 5, 3, 4, 3, 5, 6};
		System.out.println("Contains duplicate="+containsDuplicate(nums)+", nums="+Arrays.stream(nums).boxed().collect(Collectors.toList()));

		nums = new int[] {1,2,3,4,5};
		System.out.println("Contains duplicate="+containsDuplicate(nums)+", nums="+Arrays.stream(nums).boxed().collect(Collectors.toList()));

	}

}
