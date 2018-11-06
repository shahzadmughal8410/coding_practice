/**
 * 
 */
package sm.coding.ds.array.leetcode._581;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class ShortestUnsortedContinuousSubarray_581_Arr_2P {

	/**
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
You need to find the shortest such subarray and output its length.
Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.


Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.

 [10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60
 [0, 1, 15, 25, 7, 6, 30, 40, 50]
	 * @param args
	 */
	public static int findUnsortedSubarray(int[] nums) {
		if(null==nums || nums.length==0) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		boolean found = false;
		
		// find min of the sub array
		for(int i =1;i<nums.length;i++) {
			// as soon as we hit the nums[i]<nums[i-1], first min if found
			if(nums[i]<nums[i-1]) {
				found = true;
			}
			// once first min is found, then we find the min for sub array
			if(found) {
				min = Math.min(min, nums[i]);
			}
		}
		//reset found flag
		found = false;

		// find max of the sub array
		for(int i = nums.length-2; i>=0 ;i--) {
			// as soon as we hit the nums[i+1] > nums[i]), first max if found
			if(nums[i+1] > nums[i]) {
				found = true;
			}
			// once first max is found, then we find the max for sub array
			if(found) {
				max = Math.max(max,  nums[i]);
			}
		}

		int l;
		int r;
		// as soon as we hit the following condition we will get left array index
		for(l=0;l<nums.length;l++) {
			if(min < nums[l]) {
				break;
			}
		}

		// as soon as we hit the following condition we will get right array index
		for(r=nums.length-1; r >= 0;r--) {
			if(max > nums[r]) {
				break;
			}
		}
		System.out.println("start index="+l+", end index="+r);
		return r-l < 0 ? 0 : r-l+1;

	}
	
	public static void main(String[] args) {
		int nums[] = new int[] {10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60};
		int unsortedSubarray = SolutionDebug.findUnsortedSubarray(nums);
		System.out.println(" nums="+Arrays.stream(nums).boxed().map(i->String.format("%02d", i)).collect(Collectors.toList()));
		System.out.println("index="+IntStream.range(0, nums.length).boxed().map(i->String.format("%02d", i)).collect(Collectors.toList()));
		System.out.println("unsortedSubarray="+unsortedSubarray);

		nums = new int[] {0, 1, 15, 25, 7, 6, 30, 40, 50};
		unsortedSubarray = findUnsortedSubarray(nums);
		System.out.println(" nums="+Arrays.stream(nums).boxed().map(i->String.format("%02d", i)).collect(Collectors.toList()));
		System.out.println("index="+IntStream.range(0, nums.length).boxed().map(i->String.format("%02d", i)).collect(Collectors.toList()));
		System.out.println("unsortedSubarray="+unsortedSubarray);

	
		nums = new int[] {2, 6, 4, 8, 10, 9, 15};
		unsortedSubarray = findUnsortedSubarray(nums);
		System.out.println(" nums="+Arrays.stream(nums).boxed().map(i->String.format("%02d", i)).collect(Collectors.toList()));
		System.out.println("index="+IntStream.range(0, nums.length).boxed().map(i->String.format("%02d", i)).collect(Collectors.toList()));
		System.out.println("unsortedSubarray="+unsortedSubarray);
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

	public static int findUnsortedSubarray(int[] nums) {
		if(null==nums || nums.length==0) {
			return 0;
		}

		debug(" nums="+Arrays.stream(nums).boxed().map(i->String.format("%02d", i)).collect(Collectors.toList()));
		debug("index="+IntStream.range(0, nums.length).boxed().map(i->String.format("%02d", i)).collect(Collectors.toList()));
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		boolean found = false;
		
		debug("MIN loop");
		tableColumns("i:2", "nums[i]", "nums[i-1]", "nums[i]<nums[i-1]", "found", "min");
		debugColumns();
		// find min of the sub array
		for(int i =1;i<nums.length;i++) {
			debugRow(i, nums[i], nums[i-1],nums[i]+" < "+nums[i-1]+" = "+(nums[i]<nums[i-1]), found, min);
			// as soon as we hit the nums[i]<nums[i-1], first min if found
			if(nums[i]<nums[i-1]) {
				found = true;
			}
			// once first min is found, then we find the min for sub array
			if(found) {
				min = Math.min(min, nums[i]);
			}
		}
		//reset found flag
		found = false;
		
		debug("MAX loop");
		reset();
		tableColumns("i:2", "nums[i+1]", "nums[i]", "nums[i+1] > nums[i]", "found", "max");
		debugColumns();
		// find max of the sub array
		for(int i = nums.length-2; i>=0 ;i--) {
			debugRow(i, nums[i], nums[i+1], nums[i+1]+" > "+nums[i]+" = "+(nums[i+1] > nums[i]), found, max);
			// as soon as we hit the nums[i+1] > nums[i]), first max if found
			if(nums[i+1] > nums[i]) {
				found = true;
			}
			// once first max is found, then we find the max for sub array
			if(found) {
				max = Math.max(max,  nums[i]);
			}
		}

		int l;
		int r;
		
		debug("find LEFT index");
		reset();
		tableColumns("l:2", "min", "nums[l]", "min < nums[l]");
		debugColumns();
		// as soon as we hit the following condition we will get left array index
		for(l=0;l<nums.length;l++) {
			debugRow(l, min, nums[l], min+" < "+nums[l]+" = "+(min < nums[l]));
			if(min < nums[l]) {
				break;
			}
		}

		debug("find RIGHT index");
		reset();
		tableColumns("r:2", "max", "nums[r]", "min < nums[r]");
		debugColumns();
		// as soon as we hit the following condition we will get right array index
		for(r=nums.length-1; r >= 0;r--) {
			debugRow(r, max, nums[r], max+" > "+nums[r]+" = "+(max > nums[r]));
			if(max > nums[r]) {
				break;
			}
		}
		debug(" left index="+l+", value="+nums[l]+", min="+min);
		debug("right index="+r+", value="+nums[r]+", max="+max);
		debug("sub array indexes="+l+","+r);
		debug("sub array length ="+(r-l+1));

		debug(" nums="+Arrays.stream(nums).boxed().map(i->String.format("%02d", i)).collect(Collectors.toList()));
		debug("index="+IntStream.range(0, nums.length).boxed().map(i->String.format("%02d", i)).collect(Collectors.toList()));

		return r-l < 0 ? 0 : r-l+1;
	}
}
