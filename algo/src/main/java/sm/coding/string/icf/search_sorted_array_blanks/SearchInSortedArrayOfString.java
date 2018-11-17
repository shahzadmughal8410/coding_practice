/**
 * 
 */
package sm.coding.string.icf.search_sorted_array_blanks;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 
 * Search in sorted array of strings which may contain blanks
Given an array of strings. The array has both empty and non-empty strings. All non-empty strings are in sorted order. Empty strings can be present anywhere between non-empty strings.

Examples:

Input : arr[] = {
"for", "", "", "", "geeks", 
"ide", "", "practice", "" , 
"", "quiz", "", ""};
str = "quiz"
Output : 10
The string "quiz" is present at index 10 in 
given array
int mid = (right+left)/2;
int mid = left + ((right-left)/2);

 * @author smughal
 *
 */
public class SearchInSortedArrayOfString {

	
	public static int search(String[] arr, String s) {
		
		int left = 0;
		int right = arr.length-1;
		
		while(left<=right) {
			
			int mid = ((right-left)/2 ) +left;
			int distance=1;
			// expand around to find first non blank string
			while(arr[mid].length()==0 && mid-distance>=left && mid+distance<=right) {

				
				if(arr[mid-distance].length()>0) {
					mid = mid-distance;
					break;
				}
				if(arr[mid+distance].length()>0) {
					mid = mid+distance;
					break;
				}
				++distance;
			}
			
			// normal binary search
			if(arr[mid].equals(s)) {
				return mid;
			}else if(arr[mid].compareTo(s) > 0) {
				right = mid-1;
			}else {
				left=mid+1;
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		String[] arr = new String[] {"for", "", "", "", "geeks", "ide", "", 
                "practice", "" , "", "quiz", "", "watch"};
		
		System.out.println(Arrays.stream(arr).collect(Collectors.toList()));
		
		String s = "quiz";
		int result = -2;
		result = search(arr, s);
		System.out.println("index of "+s+" is ="+result);

		s = "for";
		result = search(arr, s);
		System.out.println("index of "+s+" is ="+result);

		s = "geeks";
		result = search(arr, s);
		System.out.println("index of "+s+" is ="+result);

		s = "watch";
		result = search(arr, s);
		System.out.println("index of "+s+" is ="+result);
	}
}
