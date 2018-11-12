/**
 * 
 */
package sm.coding.algo.sorting_searching.icf.merge_sort;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class MergeSort_Sort {

	static void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length-1);
	}
	
	static void mergeSort(int[] arr, int start, int end) {
		if(start<end) {
//			int mid = (start+end) /2;
			int mid = ((end-start) / 2) +start;
			mergeSort(arr, start, mid);
			mergeSort(arr, mid+1, end);
			
			merge(arr, start, mid, end);
		}
	}
	
	static void merge(int[] arr, int start, int mid, int end) {
		int[] left = new int[mid+1-start];
		int[] right = new int[end-mid] ;
		
		for(int i=0; i<left.length;i++) {
			left[i] = arr[start+i];
		}
		for(int i=0; i<right.length;i++) {
			right[i] = arr[mid+1+i];
		}
		
		int i = 0;
		int j = 0;
		int k = start;
		
		while(i<left.length && j<right.length) {
			if(left[i]<=right[j]) {
				arr[k++] = left[i++];
			} else {
				arr[k++] = right[j++];
			}
		}
		
		while(i<left.length) {
			arr[k++] = left[i++];
		}

		while(j<right.length) {
			arr[k++] = right[j++];
		}		
	}

	public static void main(String[] args) {
		int arr[] = new int[] {9,2,8,5,2,4,2,8};
		System.out.println("Original="+Arrays.stream(arr).boxed().collect(Collectors.toList()));
		mergeSort(arr);
		System.out.println("Sorted  ="+Arrays.stream(arr).boxed().collect(Collectors.toList()));

		arr = new int[] {9,2,8,5,2,4,2,8,10};
		System.out.println("Original="+Arrays.stream(arr).boxed().collect(Collectors.toList()));
		mergeSort(arr);
		System.out.println("Sorted  ="+Arrays.stream(arr).boxed().collect(Collectors.toList()));
	}
}
