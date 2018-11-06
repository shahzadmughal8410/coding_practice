/**
 * 
 */
package sm.interview.nisum.walm.round1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author smughal
 *
 */
public class N_Sum {

	/**
	 * 
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

	 * @param args
	 */
	/*
	 * Above was basically a 3 sum problem variation as described here https://www.geeksforgeeks.org/find-triplets-array-whose-sum-equal-zero/
	 * But i came up with a solution to N sum, the interview solution has some bugs, after fixing it, I found the 
	 * Solution to N sum problem. This is N sum problem, I am unable to find it on Internet :)
	 */
	public static List<List<Integer>> findSum(int[] input, int targetSum, int numbersInSum){
	    List<List<Integer>> result = new ArrayList<>();
	    findSumHelper(input, targetSum, numbersInSum, new ArrayList<>(), 0, result, 0);  
	    return result;
	}

	public static void findSumHelper(int[] input, int targetSum, int numbersInSum, List<Integer> selected, int sumSoFar, List<List<Integer>> result, int startIndex){
	    if(selected.size()==numbersInSum){
	    		if(sumSoFar==targetSum) {
	    			// if duplicated needs to be removed here, i.e. if input have duplicates, use set instead of list, Set<List<Integer>>
		        result.add(new ArrayList(selected));
		        return;
	    		}
	    		return; // pruning, don't try to explore options that has length greater than numbers
	    }
	    
	    //
	    for(int i=startIndex;i<input.length;i++){
	        int choosen = input[i];
	        selected.add(choosen);
	        findSumHelper(input, targetSum, numbersInSum, selected, sumSoFar+choosen, result, i+1);
	        selected.remove(selected.size()-1);        
	    }
	}
	
	
	public static void main(String[] args) {
		// 2 sum problem https://www.geeksforgeeks.org/count-pairs-with-given-sum/
//		int[] input = new int[] {1, 5, 7, -1};
//		int numbers = 2;
//		int target = 6;
		
		// 3 sum problem https://www.geeksforgeeks.org/find-a-triplet-that-sum-to-a-given-value/
//		int[] input = new int[] {1, 4, 45, 6, 10, 8};
//		int numbersInSum = 3;
//		int targetSum = 22; 

		// 3 sum targetSum as zero https://www.geeksforgeeks.org/find-triplets-array-whose-sum-equal-zero/
//		int[] input = new int[] {0, -1, 2, -3, 1};
//		int numbersInSum = 3;
//		int targetSum = 0; 
		
		// 4 sum
		// https://leetcode.com/problems/4sum/description/
		// https://www.geeksforgeeks.org/find-four-elements-that-sum-to-a-given-value-set-2/
//		int[] input = new int[] {1, 0, -1, 0, -2, 2};
//		int numbersInSum = 4;
//		int targetSum = 0; 
		
		// its actually and N sum solution, its not the best for 2,3,4 sum but it will work for 0-N sum
		int[] input = new int[] {1, 0, -1, 0, -2, 2, 4, 3, -3, -4, 5, -5};
		int numbersInSum = 5;
		int targetSum = 13; // won't have distinct values because of two zeros in input.
		/*
[0, 1, 3, 4, 5]=13 //
[0, 1, 3, 4, 5]=13 // repeated because of two zeros in input
[-1, 2, 3, 4, 5]=13
		 */

//		List<List<Integer>> result = findSum(input, targetSum, numbersInSum);
		// calling the same solution with debug statements
		List<List<Integer>> result = SolutionDebug.findSum(input, targetSum, numbersInSum);
		System.out.println("Touple of length("+numbersInSum+") having sum="+targetSum+" total count="+result.size());
		result.forEach(r->{
			Collections.sort(r);
			System.out.println(r+"="+r.stream().mapToInt(i->i).sum());		
		});

	}

	

}

class SolutionDebug {
	
	public static String indent = "|---";
	public static void debug(Object msg) {
		System.out.println("DEBUG "+indent+">"+msg);
	}
	public static String incrementIndent() {
		String indentActual = indent;
		indent = indent+"|---";
		return indentActual;
	}
	public static void setIndent(String newIndent) {
		indent = newIndent;
	}
	
	public static List<List<Integer>> findSum(int[] input, int targetSum, int numbersInSum){
	    List<List<Integer>> result = new ArrayList<>();
	    findSumHelper(input, targetSum, numbersInSum, new ArrayList<>(), 0, result, 0);  
	    return result;
	}

	public static void findSumHelper(int[] input, int targetSum, int numbersInSum, List<Integer> selected, int sumSoFar, List<List<Integer>> result, int startIndex){
		debug("sumSoFar="+sumSoFar+", startIndex="+startIndex+", selected="+selected);
	    if(selected.size()==numbersInSum){
	    		if(sumSoFar==targetSum) {
	    			// if duplicated needs to be removed, i.e. if input have duplicates, use set instead of list
		        result.add(new ArrayList(selected));
		        debug("added="+selected);
		        return;
	    		}
	    		return; // pruning, don't try to explore options that has length greater than three
	    }
	    
	    //
	    for(int i=startIndex;i<input.length;i++){
	        int choosen = input[i];
	        selected.add(choosen);
	        debug("i="+i+", choosen="+choosen);
	        String actual = incrementIndent();
	        findSumHelper(input, targetSum, numbersInSum, selected, sumSoFar+choosen, result, i+1);
	        setIndent(actual);
	        selected.remove(selected.size()-1);        
	    }
	}
}


