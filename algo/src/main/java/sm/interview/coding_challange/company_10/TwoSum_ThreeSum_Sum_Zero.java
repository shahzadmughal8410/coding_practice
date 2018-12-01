/**
 * 
 */
package sm.interview.coding_challange.company_10;

import java.util.HashSet;
import java.util.Set;

/**
 * @author shahzadmughal8410
 *
 */
public class TwoSum_ThreeSum_Sum_Zero {

	
	/*
	 * Given an array of integers, how would you determine if it contains a pair of numbers that add up to 0?
	Examples: [3, -2, 1, 7, -3, 5, 9] -> true
	          [3, -2, -1, 7, 3, 5, 9] -> false
	          [3, -2, 1, 7, -4, 5, 9] -> false
	 
	 T = O (n2)
	 S = O(1)

	 T = O (n)
	 S = O(n)

	HashSet 
	0-3 = -3 
	0-2 = 2 

	-----------

	 T = O (n2), 
	 S = O(n)


	[3, -2, -1, 7, 3, 5, 9]

	[1,5,3]

	0-3 = -3

	 */
	  
	  public static boolean findTriplets(int[] nums){
	      if(null==nums || nums.length<3){
	        return false;
	      }
	    
	    for(int i=0;i<nums.length;i++){
	        int sum = 0-nums[i];
	        if(findPair(nums, sum, i)){
	          return true;
	        }
	      }
	    
	    return false;

	  }
	  
	  public static boolean findPair(int[] nums, int sum, int choosen){
	    Set<Integer> set = new HashSet<>();
	    
	    for(int i=0;i<nums.length;i++){
	      if(i==choosen){
	        continue;
	      }
	      if(set.contains(nums[i])){
	        return true;
	      }else{
	        set.add(sum-nums[i]);
	      }
	    }
	    
	    return false;
	  }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    int[] nums = new int[]{3, -2, -1, 7, 3, 5, 9};
	    boolean zeroSum = findTriplets(nums);
	    System.out.println("zeroSum="+zeroSum);

	    nums = new int[]{1, 5, 3};
	    zeroSum = findTriplets(nums);
	    System.out.println("zeroSum="+zeroSum);

	}

}


