/**
 * 
 */
package sm.coding.algo.binsrch.bug;

/**
 * @author shahzadmughal8410
 *
 */
public class BinarySearchMidPointBug {

	/**
https://research.googleblog.com/2006/06/extra-extra-read-all-about-it-nearly.html 
	 * @param args
	 */
	public static int midPoint_Bug(int low, int high) {
		int mid = (low+high)/2;
		if(mid<0) {
			System.out.println("ERROR: Invalid midPoint="+mid+", low="+low+", high="+high);
		}
		return mid;
	}
	
	public static int midPoint(int low, int high) {
		return low + ((high-low)/2);
	}
	
	
	public static void main(String[] args) {
		int low = 0;
		int high = 10;
		System.out.printf("   With bug low=%d, high=%d, midPoint=%d %n", low, high, midPoint_Bug(low, high));
		System.out.printf("Without bug low=%d, high=%d, midPoint=%d %n", low, high, midPoint(low, high));

		low = Integer.MAX_VALUE/2;
		high = Integer.MAX_VALUE;
		System.out.printf("   With bug low=%d, high=%d, midPoint=%d %n", low, high, midPoint_Bug(low, high));
		System.out.printf("Without bug low=%d, high=%d, midPoint=%d %n", low, high, midPoint(low, high));

		low = 1;
		high = Integer.MAX_VALUE;
		System.out.printf("   With bug low=%d, high=%d, midPoint=%d %n", low, high, midPoint_Bug(low, high));
		System.out.printf("Without bug low=%d, high=%d, midPoint=%d %n", low, high, midPoint(low, high));

	}

}
