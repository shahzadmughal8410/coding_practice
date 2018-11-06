/**
 * 
 */
package sm.coding.ds.array.leetcode._189;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class RotateArray_189_Arr {

	/**
Rotate an array of n elements to the right by k steps.
For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
[show hint]
Hint:
Could you do it in-place with O(1) extra space?

 Links
https://leetcode.com/problems/rotate-array/solution/
https://www.geeksforgeeks.org/array-rotation/

	 * @param args
	 */
	public static void rotate(int[] nums, int k) {
		k = k % nums.length;
		int count = 0;
		for(int start = 0; count<nums.length; start++) {
			int currentIndex = start;
			int previousValue = nums[start];
			do {
				int nextIndex = (currentIndex + k) % nums.length;
				int temp = nums[nextIndex];
				nums[nextIndex] = previousValue;
				previousValue = temp;
				currentIndex = nextIndex;
				++count;
			}while(start!=currentIndex);
		}
    }
	
	
	public static void main(String[] args) {
		int[] arr = new int[] {1,2,3,4,5,6,7,8,9,10,11,12};
//		int[] arr = new int[] {1,2,3,4,5,6,7};
		System.out.println(Arrays.stream(arr).boxed().collect(Collectors.toList()));
		SolutionDebug.rotate(arr, 6);
//		rotate(arr, 3);
		System.out.println(Arrays.stream(arr).boxed().collect(Collectors.toList()));
	}

}

class SolutionDebug{

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
	
	public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        tableColumns("k:3", "start", "count", "curIdx", "nxtIdx", "prev", "temp", IntStream.range(0, nums.length).boxed().collect(Collectors.toList()).toString());
        debugColumns();
        for (int start = 0; count < nums.length; start++) {
            debugRow(k, start, count, "", "", "", "", Arrays.stream(nums).boxed().collect(Collectors.toList()));
            int curIdx = start;
            int prev = nums[start];
            do {
                int nxtIdx = (curIdx + k) % nums.length;
                int temp = nums[nxtIdx];
                debugRow(k, start, count, curIdx, nxtIdx, prev, temp, Arrays.stream(nums).boxed().collect(Collectors.toList()));
                nums[nxtIdx] = prev;
                prev = temp;
                curIdx = nxtIdx;
                count++;
            } while (start != curIdx);
        }
        debugRow(k, "", count, "", "", "", "", Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }
}
