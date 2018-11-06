/**
 * 
 */
package sm.coding.string.gfg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class AllSubstringOfAString_Str_Arr_Sublists {

	/**
	 * 
Given a string as an input. We need to write a program that will print all non-empty substrings of that given string.

Examples:

Input :  abcd
Output :  a 
          b
          c
          d
          ab
          bc
          cd
          abc
          bcd
          abcd

https://www.geeksforgeeks.org/program-print-substrings-given-string/
 
	 * @param args
	 */
	public static List<String> allSubstring(String s) {
		List<String> subStrs = new ArrayList<>();
        for (int i = 0; i < s.length(); i++)
            for (int j = i+1; j <= s.length(); j++)
            		subStrs.add(s.substring(i, j));
        return subStrs;
    }
	
	public static List<List<Integer>> allSublists(int[] arr) {
		List<List<Integer>> subStrs = new ArrayList<>();
        for (int i = 0; i < arr.length; i++)
            for (int j = i+1; j <= arr.length; j++)
            		subStrs.add( Arrays.stream(Arrays.copyOfRange(arr, i,j)).boxed().collect(Collectors.toList()) );
        return subStrs;
    }
	
	public static void main(String[] args) {
		String s = "ABCD";
		List<String> substrings = allSubstring(s);
		substrings.forEach(r->System.out.println("["+r+"]"));
		System.out.printf("Total substring of string with length %d are %d %n", s.length(), substrings.size());

		int[] arr = {1,2,3,4};
		List<List<Integer>> subsLists = allSublists(arr);
		subsLists.forEach(r->System.out.println(r));
		System.out.printf("Total sublists of array with length %d are %d %n", arr.length, subsLists.size());

	}

}
