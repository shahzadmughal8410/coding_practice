/**
 * 
 */
package sm.coding.ds.array.leetcode._238;

/**
 * @author shahzadmughal8410
 *
 */
public class ProductOfArrayExceptSelf_238 {

	
	/**
Medium

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
Example:
Input:  [1,2,3,4]
Output: [24,12,8,6]


Note: Please solve it without division and in O(n).
Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

Solution:
{              1,         a[0],    a[0]*a[1],    a[0]*a[1]*a[2],  }
{ a[1]*a[2]*a[3],    a[2]*a[3],         a[3],                 1,  }

Submission
https://leetcode.com/submissions/detail/195694793/
You are here! 
Your runtime beats 100.00 % of java submissions.
	 */
    public int[] productExceptSelf_BruteForce(int[] nums) {
		int[] productsBefore = new int[nums.length];
		int[] productsAfter = new int[nums.length];
		int[] result = new int[nums.length];
		// calculating products left --> right
		int product = 1;
		for(int i=0; i<nums.length;i++) {
			productsBefore[i] = product; 
			product*= nums[i];
		}
		
		// calculating products left <-- right
		product = 1;
		for(int i=nums.length-1; i>=0;i--) {
			productsAfter[i] = product; 
			product *= nums[i];
		}
		
		// creating final result by multiplying products from right and left at same index location
		for(int i=nums.length-1; i>=0;i--) {
			result[i] = productsBefore[i] * productsAfter[i];
		}
		
		return result;
    }

	/**
Optimization of brute force to do it without using extra space

Submission
https://leetcode.com/submissions/detail/195695203/
You are here! 
Your runtime beats 100.00 % of java submissions.

	 * @param nums
	 * @return
	 */
    public int[] productExceptSelf(int[] nums) {
    		int[] result = new int[nums.length];
    		
    		//products from left to right
    		int product = 1;
    		for(int i=0; i<nums.length;i++) {
    			result[i] = product; 
    			product*= nums[i];
    		}
    		
    		// products from right to left, also multiple of the index 
    		product = 1;
    		for(int i=nums.length-1; i>=0;i--) {
    			result[i] *= product; 
    			product *= nums[i];
    		}
    		return result;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
