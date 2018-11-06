package sm.coding.algo.practice.recursion.codingbat.recursion2;

public class GroupNoAdj {

	public boolean groupNoAdj(int start, int[] nums, int target) {
		if(target ==0) {
			return true;
		}
		if(start>=nums.length) {
			return false;
		}
		
		int ch = nums[start];
		if(groupNoAdj(start+2, nums, target-ch)) {
			return true;
		}
		return groupNoAdj(start+1, nums, target);
		
	}
}
