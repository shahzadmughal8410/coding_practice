/**
 * 
 */
package sm.coding.algo.sorting.icf.string_sort;

/**
 * @author shahzadmughal8410
 *
 */
public class StringSort_Sort {

	/**
Given a string print it in sorted form.
- extended ASCII? Yes
- Case sensitive? Yes
- Can use library function? No

Input= "hello"
Output="ehllo"

Brute Force: Bubble sort : T=O(n2)
Merge Sort: T=O(n long n)

Can we do better?

Bucket Sort: T=O(n)


https://www.geeksforgeeks.org/sort-string-characters/
https://www.geeksforgeeks.org/bucket-sort-2/

	 */
	
	static void bucketSort(String input) {
		int[] range = new int[256];
		for(int i =0; i<input.length();i++) {
			int index = input.charAt(i);
			range[index] = range[index]+1;
		}
		
		for(int i =0 ; i<range.length;i++) {
			for(int j=0;j<range[i];j++) {
				System.out.print((char)i);
			}
		}
	}
	
	public static void main(String[] args) {
		bucketSort("hello");
	}
}
