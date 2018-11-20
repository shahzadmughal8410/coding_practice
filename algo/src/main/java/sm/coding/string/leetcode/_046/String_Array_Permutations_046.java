package sm.coding.string.leetcode._046;

import java.util.ArrayList;
import java.util.List;

public class String_Array_Permutations_046 {

	/**
	 * 

Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)

	 * @param input
	 * @return
	 */
	private static List<String> permutationString(String input) {
		List<String> result = new ArrayList<>();
		permutationStringHelper(new StringBuilder(input), new StringBuilder(), result);
		return result;
	}

	private static void permutationStringHelper(StringBuilder input, StringBuilder soFar, List<String> result) {
		if (input.length() == 0) {
			result.add(soFar.toString());
		}
		else {
			for(int i=0; i<input.length(); i++) {
				//choose
				char ch = input.charAt(i);
				soFar.append(ch);
				input.deleteCharAt(i);
				
				// explore
				permutationStringHelper(input, soFar, result);
				
				//un-choose
				soFar = soFar.deleteCharAt(soFar.length()-1);
				input.insert(i, ch);
			}
		}
	}
	
	/**

https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/

Submission
https://leetcode.com/submissions/detail/190646792/
You are here! 
Your runtime beats 99.99 % of java submissions.
	 * @param arr
	 * @return
	 */
	public static List<List<Integer>> arrayPermute(int[] arr) {
		List<List<Integer>> result = new ArrayList<>();
		arrayPermuteHelper(arr, 0, arr.length - 1, result);
		return result;
	}

	public static void arrayPermuteHelper(int[] arr, int l, int r, List<List<Integer>> result) {
		if (l == r)
			result.add(arrToList(arr));
		else {
			for (int i = l; i <= r; i++) {
				swap(arr, l, i);
				arrayPermuteHelper(arr, l + 1, r, result);
				swap(arr, l, i);
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static List<Integer> arrToList(int[] nums){
		List<Integer> list = new ArrayList<>(nums.length);
		for(Integer i:nums) {
			list.add(i);
		}
		return list;
	}

	public static void main(String[] args) {
		List<String> result = permutationString("abc");
		result.forEach(r -> System.out.println(r));
		
		int[] arr = new int[] {1, 2, 3};
		List<List<Integer>> arrResult = arrayPermute(arr);
		arrResult.forEach(r->System.out.println(r));
	}

}
