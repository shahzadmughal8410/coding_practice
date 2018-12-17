/**
 * 
 */
package sm.coding.ds.array.leetcode._042;

import java.util.regex.Pattern;

/**
 * @author shahzadmughal8410
 *
 */
public class TrappingRainWater_042 {

	/**
Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 
6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image! 

https://leetcode.com/problems/trapping-rain-water/description/
https://www.geeksforgeeks.org/trapping-rain-water/

Submission
https://leetcode.com/submissions/detail/195350371/
You are here! 
Your runtime beats 95.71 % of java submissions.
	 * @param args
	 */
	public static int trap(int[] height) {
		if(null==height || height.length==0) {
			return 0;
		}
		int left = 0;
		int right = height.length-1;
		int leftMax = 0;
		int rightMax = 0;
		int water = 0;
		
		while(left<right) {
			// left height is small from right, measure water from left to right
			if(height[left] < height[right]) {
				if(height[left]>=leftMax) {// if current left is > maxLeft update it
					leftMax = height[left];
				}else {// it means we found a height less then we saw previous on left side, height difference is amount of water
					water+= leftMax - height[left];
				}
				++left;
			}else { // right is <= left,measure water from left to write
				if(height[right]>=rightMax) {// current right height >= maxRight update it 
					rightMax = height[right];
				}else {// height is less then the waht we saw earlier on right side, height difference is the water captured
					water+= rightMax - height[right];
				}
				--right;
			}
		}
		return water;
	}
	
	
	public static void main(String[] args) {
		int[] height = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
//		int[] height = new int[] {2, 0, 2};
//		int[] height = new int[] {3, 0, 0, 2, 0, 4};
		
		System.out.println("Water="+SolutionDebug.trap(height));

	}
	
}

class SolutionDebug {

	static StringBuilder format = new StringBuilder();
	static String [] columns = new String[] {};
	public static void tableColumns(String ... cols) {
		for(int i = 0; i<cols.length;i++) {
			String c = cols[i];
			if(c.indexOf("^")!=-1) {
				String[] arr = c.split(Pattern.quote("^"));
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
	
	public static int trap(int[] height) {
		if(null==height || height.length==0) {
			return 0;
		}
		int left = 0;
		int right = height.length-1;
		int leftMax = 0;
		int rightMax = 0;
		int water = 0;
		tableColumns("left", "right", "height[left]", "height[right]", "height[left] < height[right]", 
				"height[left]>=leftMax", "height[right]>=rightMax",
				"leftMax", "rightMax", "water");	
		debugColumns();
		while(left<right) {
			debugRow(left, right, height[left], height[right], height[left] +"<"+ height[right]+"="+(height[left] < height[right]), 
					(height[left] < height[right]) ? height[left]+">="+leftMax+"="+(height[left]>=leftMax) : "-",
					!(height[left] < height[right]) ? height[right]+">="+rightMax+"="+(height[right]>=rightMax) : "-", 		
					leftMax, rightMax, water);
			if(height[left] < height[right]) {
				if(height[left]>=leftMax) {
					leftMax = height[left];
				}else {
					water+= leftMax - height[left];
				}
				++left;
			}else {
				if(height[right]>=rightMax) {
					rightMax = height[right];
				}else {
					water+= rightMax - height[right];
				}
				--right;
			}
		}
		return water;
	}
}
