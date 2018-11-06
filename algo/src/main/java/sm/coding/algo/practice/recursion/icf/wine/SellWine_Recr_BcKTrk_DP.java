/**
 * 
 */
package sm.coding.algo.practice.recursion.icf.wine;

/**
 * @author shahzadmughal8410
 *
 */
public class SellWine_Recr_BcKTrk_DP {

	/**
	 * 


Imagine you have a collection of N wines placed next to each other on a shelf. For simplicity, let's number the wines from left to right as they are standing on the shelf with integers from 1 to N, respectively. The price of the ith wine is pi. (prices of different wines can be different).

Because the wines get better every year, supposing today is the year 1, on year y the price of the ith wine will be y*pi, i.e. y-times the value that current year.

You want to sell all the wines you have, but you want to sell exactly one wine per year, starting on this year. One more constraint - on each year you are allowed to sell only either the leftmost or the rightmost wine on the shelf and you are not allowed to reorder the wines on the shelf (i.e. they must stay in the same order as they are in the beginning).

You want to find out, what is the maximum profit you can get, if you sell the wines in optimal order
 
	 * @param args
	 */
//	public static int sellWines(int[] wines, int start, int end, int year, int[] profitSofar) {
//		int max = 0;
//		if(start==end) {
//			return wines[start]*year;
//		}
//		
//		
//		
//	}
	
	// Shanu bhai's code, brute force, actual solution is DP
    public static int maxProfit(int[] a, int l, int h, int y) {
        int max = 0;
        if (l == h)
            return a[l] * y;
        max = Math.max(a[l] * y + maxProfit(a, l + 1, h, y + 1),
                       a[h] * y + maxProfit(a, l, h - 1, y + 1));
        return max;
    }
	
	
	public static void main(String[] args) {
		int [] wines = new int[] {2,4};
		int maxProfit = maxProfit(wines, 0, wines.length-1, 1); 
		
		System.out.println("maxProfit="+maxProfit);
	}

}


