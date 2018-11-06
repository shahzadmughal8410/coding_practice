/**
 * 
 */
package sm.coding.ds.array.leetcode._119;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shahzadmughal8410
 *
 */
public class PascalsTriangleII_119_Arr {

	/**
Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
Note that the row index starts from 0.

In Pascal's triangle, each number is the sum of the two numbers directly above it.
Example:
Input: 3
Output: [1,3,3,1]


Follow up:
Could you optimize your algorithm to use only O(k) extra space?
	 * 
	 * @param args
	 */
    public static List<Integer> getRow(int rowIndex) {
	    	List<Integer> firstRow = new ArrayList<>();
	    	firstRow.add(1);
	    	
	    	List<Integer> previous = firstRow;
	    	
	    	for(int i = 1; i<= rowIndex ; i++) {
	    		List<Integer> next = new ArrayList<>();
	    		next.add(1); //first value in a row
	    		for(int j=1; j<i ;j++) {
	    			next.add( previous.get(j-1) + previous.get(j) );
	    		}
	    		next.add(1);// last value in a row 
	    		previous = next;
	    	}
	    	return previous;
    }
	
	public static void main(String[] args) {
		int k = 0;
		List<Integer> kthRow = getRow(k);
		System.out.printf("%d row of pascal's triangle is %s %n", k ,kthRow);

		k = 2;
		kthRow = getRow(k);
		System.out.printf("%d row of pascal's triangle is %s %n", k ,kthRow);

		k = 3;
		kthRow = getRow(k);
		System.out.printf("%d row of pascal's triangle is %s %n", k ,kthRow);

		k = 5;
		kthRow = getRow(k);
		System.out.printf("%d row of pascal's triangle is %s %n", k ,kthRow);
	}

}
