/**
 * 
 */
package sm.coding.algo.sorting.icf.merge_sort;

/**
 * @author shahzadmughal8410
 *
 */
public class MergeSort_Sort {

	static void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length);
	}
	
	static void mergeSort(int[] arr, int start, int end) {
		if(end<start) {
			return;
		}
		
		int mid = (start+end) / 2;
		mergeSort(arr, start, mid);
		mergeSort(arr, mid+1, end);
		
		merge(arr, start, mid, end);
	}
	
	static void merge(int[] arr, int start, int mid, int end) {
		int[] leftArray = new int[mid+1-start];
		int[] rightArray = new int[end-mid] ;
		
		for(int i=0; i<leftArray.length;i++) {
			leftArray[i] = arr[start+i];
		}
		for(int i=0; i<rightArray.length;i++) {
			rightArray[i] = arr[mid+1+i];
		}
		
		int leftIdx = 0;
		int rightIdx = 0;
		int idx = start;
		
		while(leftIdx<leftArray.length && rightIdx<rightArray.length) {
			if(leftArray[leftIdx]<=rightArray[rightIdx]) {
				arr[idx++] = leftArray[leftIdx++];
			} else {
				arr[idx++] = rightArray[rightIdx++];
			}
		}
		
		while(leftIdx<leftArray.length) {
			arr[idx++] = leftArray[leftIdx++];
		}

		while(rightIdx<rightArray.length) {
			arr[idx++] = rightArray[rightIdx++];
		}		
	}

}
