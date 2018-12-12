/**
 * 
 */
package sm.coding.ds.array.leetcode._078;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import sm.coding.string.gfg.AllSubstringOfAString_Str_Arr_Sublists;

/**
 * @author shahzadmughal8410
 *
 */
public class Subsets_078_Str_Array_Recr_BckTrk_Bit_PowerSet_08_04 {

	/**
 Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

https://leetcode.com/problems/subsets/description/
https://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/

REMEMBER: string subset is different from all substrings see AllSubstringOfAString

 Sublist_PowerSet_Recr_BckTrk.java
 
 
	 * @param args
	 */
	public static List<String> allSubsets(String s){
		List<String> powerset = new ArrayList<>();
		if(null==s || s.length()==0) {
			return powerset;
		}
		
		for(int i =0; i< (1<<s.length());i++ ) {
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j<s.length(); j++) {
				if( (i & (1<<j) ) >0) {
					sb.append(s.charAt(j));
				}
			}
			powerset.add(sb.toString());
		}
		return powerset;
	}

	public static List<String> allSubsets_Recr(String s){
		List<String> powerset = new ArrayList<>();
		allSubsetsHelper_Recr(s, 0, "", powerset);
		return powerset;
	}
	
	public static void allSubsetsHelper_Recr(String s, int index, String sofar, List<String> powerset){
		if(index==s.length()) {
			powerset.add(sofar);
			return;
		}
		
		char c = s.charAt(index);
		allSubsetsHelper_Recr(s, index+1, sofar+c, powerset);
		allSubsetsHelper_Recr(s, index+1, sofar, powerset);
	}
	

	/**
Submission
https://leetcode.com/submissions/detail/173164577/
You are here!
Your runtime beats 100.00 % of java submissions
	 * @param arr
	 * @return
	 */
	public static List<List<Integer>> subsets(int[] arr){
		List<List<Integer>> sublist = new ArrayList<>();
		if(null==arr || arr.length==0) {
			return sublist;
		}

		for(int i =0; i< (1<<arr.length);i++ ) {
			List<Integer> subSet = new ArrayList<>();
			for(int j = 0; j<arr.length; j++) {
				if( (i & (1<<j) ) >0) {
					subSet.add(arr[j]);
				}
			}
			sublist.add(subSet);
		}
		return sublist;
	}
	
	/**
Submission
https://leetcode.com/submissions/detail/173165278/
You are here!
Your runtime beats 100.00 % of java submissions
	 * @param arr
	 * @return
	 */
	public static List<List<Integer>> subsets_Recur(int[] arr){
		List<List<Integer>> subSets = new ArrayList<>();
		subsetsHelper(arr, subSets, new ArrayList<>(), 0);
		return subSets;
		
	}

	public static void subsetsHelper(int[] arr, List<List<Integer>> subSets, List<Integer> sofar, int index) {
		// start==arr.length, means nothing to choose as we have already choosen all elements
		if(index == arr.length) { 
			subSets.add(new ArrayList<>(sofar));
			return;
		}
		//choose
		int choosen = arr[index];
		
		//explore with
		sofar.add(choosen);
		subsetsHelper(arr, subSets, sofar, index+1);
		
		//explore without //un-choose
		sofar.remove(sofar.size()-1);
		subsetsHelper(arr, subSets, sofar, index+1);
	}
	
	public static List<List<String>> powerSet(List<String> input){
		List<List<String>> subSets = new ArrayList<>();
		powerSetHelper(input, subSets, new ArrayList<>(), 0);
		return subSets;
		
	}
	
	public static void powerSetHelper(List<String> input, List<List<String>> subSets, List<String> sofar, int start) {
		if(start == input.size()) {
			subSets.add(new ArrayList<>(sofar));
			return;
		}
		//choose
		String choosen = input.get(start);
		
		//explore with
		sofar.add(choosen);
		powerSetHelper(input, subSets, sofar, start+1);
		
		//explore without //un-choose
		sofar.remove(sofar.size()-1);
		powerSetHelper(input, subSets, sofar, start+1);
	}
	
	public static void main(String[] args) {
		String s = "abcd";
		List<String> subsets = allSubsets(s);
		subsets.forEach(r->System.out.println("["+r+"]"));
		System.out.printf("ITR All sub sets of input of length %d are %d %n", s.length(), subsets.size());

		subsets = allSubsets_Recr(s);
		subsets.forEach(r->System.out.println("["+r+"]"));
		System.out.printf("REC All sub sets of input of length %d are %d %n", s.length(), subsets.size());

		int[] arr = new int[] {5,6,7};
//		List<List<Integer>> subLists = subsets(arr);
		List<List<Integer>> subLists = SolutionDebug.subsets(arr);
		subLists.forEach(r->System.out.println(r));
		System.out.printf("All sub lists of input of length %d are %d %n", arr.length, subLists.size());
		
//		subLists = subsets_Recur(arr);
		subLists = SolutionDebug.subsets_Recur(arr);
		subLists.forEach(r->System.out.println(r));
		System.out.printf("Recursion All sub lists of input of length %d are %d %n", arr.length, subLists.size());
		
		
		List<String> set = new ArrayList<>();
		set.add("A");
		set.add("B");
		set.add("C");
		set.add("D");

		List<List<String>> subSets = powerSet(set);
		subSets.forEach(sets->System.out.println(sets));
		System.out.printf("Total sub sets of set with %d elements are %d %n", set.size(), subSets.size());

	}

}

class SolutionDebug {

	static StringBuilder format = new StringBuilder();
	static String [] columns = new String[] {};
	public static void tableColumns(String ... cols) {
		for(int i = 0; i<cols.length;i++) {
			String c = cols[i];
			if(c.indexOf(":")!=-1) {
				String[] arr = c.split(Pattern.quote(":"));
				format.append("|%-").append(arr[1]).append("s ");
				cols[i] = arr[0];
			}else {
				format.append("|%-").append(c.length()).append("s ");
			}
			
		}
		format.append("|");
//		debugRow(cols);
		columns = cols;
	}
	
	public static void debugRow(Object ... cols) {
		debug(String.format(format.toString(), cols));
	}

	public static void debug(Object msg) {
		System.out.println("DEBUG: "+msg);
	}

	public static void debugColumns() {
		debugRow(columns);
	}
	
	public static void reset() {
		format = new StringBuilder();
		columns = new String[] {};
	}

	public static String indent = "|---";
	public static void debugRecr(Object msg) {
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
	
	public static List<List<Integer>> subsets(int[] arr){
		debug(Arrays.stream(arr).boxed().collect(Collectors.toList()));
		debug(IntStream.range(0, arr.length).boxed().collect(Collectors.toList()));
		List<List<Integer>> sublist = new ArrayList<>();
		if(null==arr || arr.length==0) {
			return sublist;
		}
		debug("(1<<arr.length)) => (1<<"+arr.length+")="+(1<<arr.length));
		tableColumns("Calls", "i:3", "j:3", "1<<j:18", "(i & (1<<j) ):33", "subSet:10", "sublist");
		debugColumns();
		int calls = 0;
		for(int i =0; i< (1<<arr.length);i++ ) {
			List<Integer> subSet = new ArrayList<>();
			for(int j = 0; j<arr.length; j++) {
				debugRow(calls, i, j, "1<<j => (1<<"+j+") = "+(1<<j), "(i & (1<<j) ) => ("+i+" & ("+(1<<j)+") ) = "+(i & (1<<j) ), subSet, sublist);
				if( (i & (1<<j) ) >0) {
					subSet.add(arr[j]);
				}
				++calls;
			}
			sublist.add(subSet);
		}
		debug(String.format("All sub lists of input of length %d are %d ", arr.length, sublist.size()));
		debug(sublist);
		return sublist;
	}
	
	public static List<List<Integer>> subsets_Recur(int[] arr){
		debug(Arrays.stream(arr).boxed().collect(Collectors.toList()));
		debug(IntStream.range(0, arr.length).boxed().collect(Collectors.toList()));
		List<List<Integer>> subSets = new ArrayList<>();
		subsetsHelper(arr, subSets, new ArrayList<>(), 0);
		debugRecr(String.format("All sub lists of input of length %d are %d ", arr.length, subSets.size()));
		debugRecr(subSets);
		return subSets;
		
	}
	static int count;
	public static void subsetsHelper(int[] arr, List<List<Integer>> subSets, List<Integer> sofar, int index) {
		debugRecr("count="+ (count++)+", index="+index+", sofar="+sofar+", subSets="+subSets);
		if(index == arr.length) {
			subSets.add(new ArrayList<>(sofar));
			debugRecr("added to subSets="+sofar);
			return;
		}
		//choose
		int choosen = arr[index];
		debugRecr("choosen="+choosen);
		
		String actual = incrementIndent();
		
		//explore with
		sofar.add(choosen);
		subsetsHelper(arr, subSets, sofar, index+1);
		
		//explore without //un-choose
		sofar.remove(sofar.size()-1);
		subsetsHelper(arr, subSets, sofar, index+1);
		
		setIndent(actual);
	}
}
