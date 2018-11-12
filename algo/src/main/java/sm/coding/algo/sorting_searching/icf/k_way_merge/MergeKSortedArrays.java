/**
 * 
 */
package sm.coding.algo.sorting_searching.icf.k_way_merge;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class MergeKSortedArrays {

	/**
Given k sorted arrays of size n each, merge them and print the sorted output.
Example:
Input:
k = 3, n =  4
arr[][] = { {1, 3, 5, 7},
            {2, 4, 6, 8},
            {0, 9, 10, 11}} ;

Output: 0 1 2 3 4 5 6 7 8 9 10 11

	 */
	public static int[] merge(int[][] arrays) {
		if(null==arrays || arrays.length==0) {
			return null;
		}
		
		int[] result;
		PriorityQueue<HeapEntry> minHeap = new PriorityQueue<>(arrays.length);
		int totalLenmgth = 0;
		for(int i=0; i<arrays.length;i++) {
			if(arrays[i]!=null && arrays[i].length>0) {
				// store total length for creating the result array
				totalLenmgth+=arrays[i].length;
			}
			minHeap.offer(new HeapEntry(arrays[i][0], i, 0));
		}
		
		result = new int[totalLenmgth];
		
		int i=0;
		while(!minHeap.isEmpty()) {
			HeapEntry min = minHeap.poll();
			result[i++] = min.val;
			int newIndex = min.index+1; 
			if( newIndex < arrays[min.array].length) {
				minHeap.offer(new HeapEntry(arrays[min.array][newIndex], min.array, newIndex));
			}
		}
		
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] arrays = { 
				{ 1, 3, 5, 7, 13 }, 
				{ 2, 4, 6, 8 }, 
				{ 0, 9, 10, 11, 12 } 
				};
		int[] result = merge(arrays);		
		System.out.println("Sortede array="+Arrays.stream(result).boxed().collect(Collectors.toList()));

	}

}

class HeapEntry implements Comparable<HeapEntry>{
	int val;
	int array;
	int index;
	
	
	
	public HeapEntry(int val, int array, int index) {
		this.val = val;
		this.array = array;
		this.index = index;
	}



	@Override
	public int compareTo(HeapEntry o) {
		return this.val - o.val;
	}
	
}