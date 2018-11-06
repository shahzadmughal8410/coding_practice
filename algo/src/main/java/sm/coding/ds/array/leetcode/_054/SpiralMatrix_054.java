/**
 * 
 */
package sm.coding.ds.array.leetcode._054;

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
	 * @param args
	 */
	public static void printSpiral(int[][] matrix) {
		int rowStart = 0;
		int colStart = 0;
		int rowEnd= matrix.length-1;
		int colEnd = matrix[0].length-1;
		int i;
		// count logic also works
//		int count = 0;
		
		while(rowStart<=rowEnd && colStart<=colEnd) {
//		while(count< (matrix.length * matrix[0].length)) {
			for(i=colStart;i<=colEnd;i++) {
				System.out.print(matrix[rowStart][i]+" ");
//				++count;
			} rowStart++;
			
			for(i=rowStart;i<=rowEnd;i++) {
				System.out.print(matrix[i][colEnd]+" ");
//				++count;
			} colEnd--;
			
			if(rowStart<=rowEnd) {
				for(i=colEnd;i>=colStart;i--) {
					System.out.print(matrix[rowEnd][i]+" ");
//					++count;
				} rowEnd--;
			}
			
			if(colStart<=colEnd) {
				for(i=rowEnd;i>=rowStart;i--) {
					System.out.print(matrix[i][colStart]+" ");
//					++count;
				}colStart++;
			} 
		}
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

		printSpiral(matrix1);
		System.out.println();
		printSpiral(matrix2);
		System.out.println();
		printSpiral(matrix3);
	}

}
