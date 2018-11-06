/**
 * 
 */
package sm.coding.ds.array.leetcode._605;

/**
 * @author shahzadmughal8410
 *
 */
public class CanPlaceFlowers_605_Arr {

	/**
Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.
Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: True


Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: False


Note:
The input array won't violate no-adjacent-flowers rule.
The input array size is in the range of [1, 20000].
n is a non-negative integer which won't exceed the input array size. 
	 * @param args
	 */
	public static boolean canPlaceFlowers(int[] flowerBed, int n) {
		int count = 0;		
		for(int i =0; i<flowerBed.length && count<n; i++) {
			if(flowerBed[i]==0) {
				int next = (i==flowerBed.length-1) ? 0 : flowerBed[i+1];
				int previous = (i==0) ? 0 : flowerBed[i-1];
				if(next==0 && previous==0) {
					flowerBed[i] = 1;
					count++;
				}
			}
		}
		return count==n;
	}
	
	
	public static void main(String[] args) {
		int[] flowerBed = new int[] {1,0,0,0,1};
		int n = 1;
		System.out.printf("Can place [%d] flowers on flower bed [%s] %n", n, canPlaceFlowers(flowerBed, n));

		flowerBed = new int[] {1,0,0,0,1};
		n = 2;
		System.out.printf("Can place [%d] flowers on flower bed [%s] %n", n, canPlaceFlowers(flowerBed, n));

	}

}
