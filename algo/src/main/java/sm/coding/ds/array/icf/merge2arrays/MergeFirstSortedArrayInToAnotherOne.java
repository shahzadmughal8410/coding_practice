/**
 * 
 */
package sm.coding.ds.array.icf.merge2arrays;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class MergeFirstSortedArrayInToAnotherOne {

	/**
iven two sorted arrays, the task is to merge them in a sorted manner.
Examples:
Input :  arr1[] = { 1, 3, 4, 5}  
         arr2[] = {2, 4, 6, 8}
Output : arr3[] = {1, 2, 3, 4, 5, 6, 7, 8}

Input  : arr1[] = { 5, 8, 9}  
         arr2[] = {4, 7, 8}
Output : arr3[] = {4, 5, 7, 8, 8, 9}

Links
https://www.geeksforgeeks.org/merge-two-sorted-arrays/ 
	 * @param args
	 */
	public static int[] merge(int[] arr1, int[] arr2){
		int[] arr3 = new int[arr1.length+arr2.length];
		
		int i = 0;
		int j = 0;
		int k = 0;
		
		while(i<arr1.length && j<arr2.length) {
			if(arr1[i]<arr2[j]) {
				arr3[k++] = arr1[i++];
			} else {
				arr3[k++] = arr2[j++];
			}
		}
		
		while(i<arr1.length) {
			arr3[k++] = arr1[i++];
		}
		while(j<arr2.length) {
			arr3[k++] = arr2[j++];
		}
		return arr3;
	}
	
	
	public static void main(String[] args) {
		int[] arr1 = {1, 3, 5, 7};     
        int[] arr2 = {2, 4, 6, 8};     
        int[] arr3 = merge(arr1, arr2);
        System.out.println("arr1="+Arrays.stream(arr1).boxed().collect(Collectors.toList()));
        System.out.println("arr2="+Arrays.stream(arr2).boxed().collect(Collectors.toList()));
        System.out.println("arr3="+Arrays.stream(arr3).boxed().collect(Collectors.toList()));

		arr1 = new int[] {1, 1, 1, 1};     
        arr2 = new int[] {2, 2, 2, 2};     
        arr3 = merge(arr1, arr2);
        System.out.println("arr1="+Arrays.stream(arr1).boxed().collect(Collectors.toList()));
        System.out.println("arr2="+Arrays.stream(arr2).boxed().collect(Collectors.toList()));
        System.out.println("arr3="+Arrays.stream(arr3).boxed().collect(Collectors.toList()));

		arr1 = new int[] {2, 2, 2, 2};     
        arr2 = new int[] {2, 2, 2, 2};     
        arr3 = merge(arr1, arr2);
        System.out.println("arr1="+Arrays.stream(arr1).boxed().collect(Collectors.toList()));
        System.out.println("arr2="+Arrays.stream(arr2).boxed().collect(Collectors.toList()));
        System.out.println("arr3="+Arrays.stream(arr3).boxed().collect(Collectors.toList()));

		arr1 = new int[] {1,2,3,4,5,6,7,8,9};     
        arr2 = new int[] {5,6,7,8};     
        arr3 = merge(arr1, arr2);
        System.out.println("arr1="+Arrays.stream(arr1).boxed().collect(Collectors.toList()));
        System.out.println("arr2="+Arrays.stream(arr2).boxed().collect(Collectors.toList()));
        System.out.println("arr3="+Arrays.stream(arr3).boxed().collect(Collectors.toList()));

		arr1 = new int[] {5,6,7,8};     
        arr2 = new int[] {1,2,3,4,5,6,7,8,9};     
        arr3 = SolutionDebug.merge(arr1, arr2);
        System.out.println("arr1="+Arrays.stream(arr1).boxed().collect(Collectors.toList()));
        System.out.println("arr2="+Arrays.stream(arr2).boxed().collect(Collectors.toList()));
        System.out.println("arr3="+Arrays.stream(arr3).boxed().collect(Collectors.toList()));

        
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
	
	public static int[] merge(int[] arr1, int[] arr2){
		int[] arr3 = new int[arr1.length+arr2.length];
		
		int i = 0;
		int j = 0;
		int k = 0;
		
		tableColumns("i:3", "j:3", "k:3", "arr1[i]<arr2[j]");
		debugColumns();
		while(i<arr1.length && j<arr2.length) {
			debugRow(i, j, k, arr1[i]+"<"+arr2[j]+"="+(arr1[i]<arr2[j]));
			if(arr1[i]<arr2[j]) {
				arr3[k++] = arr1[i++];
			} else {
				arr3[k++] = arr2[j++];
			}
		}
		
		while(i<arr1.length) {
			arr3[k++] = arr1[i++];
		}
		while(j<arr2.length) {
			arr3[k++] = arr2[j++];
		}
		return arr3;
	}
	
}