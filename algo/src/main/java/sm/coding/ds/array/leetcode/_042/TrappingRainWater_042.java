/**
 * 
 */
package sm.coding.ds.array.leetcode._042;

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

	 * @param args
	 */
	public static int findWater(int[] height) {
		if(null==height || height.length==0) {
			return 0;
		}
		int left = 0;
		int right = height.length-1;
		int leftMax = 0;
		int rightMax = 0;
		int water = 0;
		
		while(left<right) {
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
	
	
	public static void main(String[] args) {
		int[] height = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
//		int[] height = new int[] {2, 0, 2};
//		int[] height = new int[] {3, 0, 0, 2, 0, 4};
		
		System.out.println("Water="+findWater(height));

	}
	
}
