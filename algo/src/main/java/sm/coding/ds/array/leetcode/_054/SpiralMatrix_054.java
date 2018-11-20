/**
 * 
 */
package sm.coding.ds.array.leetcode._054;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smughal
 *
 */
public class SpiralMatrix_054 {

	/**
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].

 https://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/
 https://www.youtube.com/watch?v=TmweBVEL0I0
 
Submission
https://leetcode.com/submissions/detail/190660242/
You are here! 
Your runtime beats 100.00 % of java submissions.

Count logic
Submission
https://leetcode.com/submissions/detail/190660558/
 
	 * @param args
	 */
	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		
		if(null==matrix ||matrix.length==0) {
			return result;
		}
		
		int rowStart = 0;
		int colStart = 0;
		int rowEnd= matrix.length-1;
		int colEnd = matrix[0].length-1;
		int i;
		// count logic also works
		int count = 0;
		
		while(rowStart<=rowEnd && colStart<=colEnd) { 
//		while(count< (matrix.length * matrix[0].length)) {
			for(i=colStart;i<=colEnd;i++) {
				result.add(matrix[rowStart][i]);
				++count;
			} rowStart++;
			
			for(i=rowStart;i<=rowEnd;i++) {
				result.add(matrix[i][colEnd]);
				++count;
			} colEnd--;
			
			if(rowStart<=rowEnd) {
				for(i=colEnd;i>=colStart;i--) {
					result.add(matrix[rowEnd][i]);
					++count;
				} rowEnd--;
			}
			
			if(colStart<=colEnd) {
				for(i=rowEnd;i>=rowStart;i--) {
					result.add(matrix[i][colStart]);
					++count;
				}colStart++;
			} 
		}
		return result;
	}
	
	public static void main(String[] args) {
		int matrix1[][] = { 
						{ 1,  2,  3,  4,  5,  6},
						{14, 15, 16, 17, 18, 7},
						{13, 12, 11, 10,  9, 8}
	      			};
		int matrix2[][] = { 
				{ 1,   2,   3, 4},
				{12,  13,  14, 5},
				{11,  16,  15, 6},
				{10,   9,   8, 7}
  			};
		int matrix3[][] = { 
				{1,  2,  3},
				{8,  9,  4},
				{7,  6,  5},
  			};

		List<Integer> result = null; 
		result = spiralOrder(matrix1);
		System.out.println(result);

		result = spiralOrder(matrix2);
		System.out.println(result);

		result = spiralOrder(matrix3);
		System.out.println(result);
	}

}
