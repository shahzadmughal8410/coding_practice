/**
 * 
 */
package sm.coding.algo.practice.recursion.icf.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 
 * 1. Partition Equal Subset Sum Given a non-empty array containing only positive integers, 
 * find if the array can be partitioned into two subsets such that the sum of elements 
 * in both subsets is equal. 
 * 
 * Example 1: 
 * Input: [1, 5, 11, 5] 
 * Output: true 
 * Explanation: The array can be partitioned as [1, 5, 5] and [11]. 
 * 
 * Example 2: 
 * Input: [1, 2, 3, 5] 
 * Output: false 
 * Explanation: The array cannot be partitioned into equal sum subsets.
 * 
 * @author smughal
 *
 */
public class Test1 {
	
	static boolean split = false;
	
	public static boolean partition(int[] input, int start, ArrayList<Integer> choosen, HashMap<Integer, Object> sums) {
		System.out.println("Input="+Arrays.asList(input)+", start="+start+", choosen="+choosen+", sums="+sums+", split="+split);
		
		if(split) {
			return true;
		}
		
		if(start>=input.length) {
			int sum = 0;
			for(int i : choosen) {
				sum+=i;
			}
			if(sums.containsKey(sum) && !sums.get(sum).equals(choosen)) {
				boolean  match = sums.get(sum).equals(choosen);
				split = true;
				System.out.println("split sum="+sum+" first="+sums.get(sum)+", second="+choosen+", match="+match);
			}
			sums.put(sum, new ArrayList<>(choosen));
			return true;
		}else {
			for(int i=start; i<input.length;i++) {
				if(!split) {
					//choose
					int ch = input[i];
					choosen.add(ch);
					//explore with
					partition(input, start+1, choosen, sums);
					
					//un choose
					choosen.remove(choosen.size()-1);
					
					//explore without
					partition(input, start+1, choosen, sums);
				}
				
			}
		}
		System.out.println("sums="+sums);
		return split;
	}
	
	
	public static void main(String[] args) {
		int[] input = new int[] {1, 5, 11, 5};
//		int[] input = new int[] {1, 1};
//		int[] input = new int[] {1, 5,2};
		boolean result = partition(input, 0, new ArrayList<>(), new HashMap<>());
		System.out.println(result);
	}
}
