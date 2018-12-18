/**
 * 
 */
package sm.coding.ds.array.leetcode._240;

/**
 * @author shahzadmughal8410
 *
 */
public class SearchA2DMatrix_II_240 {

	/**
Medium
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Example:
Consider the following matrix:
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]


Given target = 5, return true.
Given target = 20, return false.

Submission
https://leetcode.com/submissions/detail/195709190/
You are here! 
Your runtime beats 96.48 % of java submissions.

	 */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(null== matrix || matrix.length==0 || matrix[0].length==0) {
        		return false;
        }
        // start from bottom left
        int row = matrix.length-1;
        int col = 0;
        
        while(row>=0 && col<matrix[0].length) {
        		if(matrix[row][col] > target) {
        			row--;
        		} else if(matrix[row][col] < target) {
        			col++;
        		}else { // value found
        			return true;
        		}
        }
        
        return false;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
