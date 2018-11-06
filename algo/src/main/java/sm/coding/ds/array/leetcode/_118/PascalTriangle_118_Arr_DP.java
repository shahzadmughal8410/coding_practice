/**
 * 
 */
package sm.coding.ds.array.leetcode._118;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shahzadmughal8410
 *
 */
public class PascalTriangle_118_Arr_DP {

	/**
Given numRows, generate the first numRows of Pascal's triangle.
For example, given numRows = 5,
Return
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

Links
https://leetcode.com/problems/pascals-triangle/description/
https://leetcode.com/problems/pascals-triangle/discuss/38141/My-concise-solution-in-Java
https://www.geeksforgeeks.org/pascal-triangle/ 

Similar questions
https://leetcode.com/problems/pascals-triangle-ii/description/ 

	 * @param args
	 */
	public static List<List<Integer>> pascalTriangle(int rows){
		List<List<Integer>> triangle = new ArrayList<>();
		
		if(rows<=0) {
			return triangle;
		}
		
		List<Integer> firstRow = new ArrayList<>();
		firstRow.add(1);
		triangle.add(firstRow);

		for(int i=1; i<rows;i++) {
			List<Integer> row = new ArrayList<>();
			row.add(1);//first element should always be 1
			List<Integer> previous = triangle.get(i-1);
			for(int j = 1; j<i; j++) {
				// add the top and topLeft diagonal value
				int value = previous.get(j-1) + previous.get(j);
				row.add(value);
			}
			row.add(1);//last element should always be 1
			triangle.add(row);
		}
		return triangle;
	} 
	
	public static void main(String[] args) {
		int rows = 0;
		System.out.printf("Pascal's triangle with rows %d %n", rows);
		List<List<Integer>> triangle = pascalTriangle(rows);
		triangle.forEach(row-> System.out.println(row));
		System.out.println();
		
		rows = 1;
		System.out.printf("Pascal's triangle with rows %d %n", rows);
		triangle = pascalTriangle(rows);
		triangle.forEach(row-> System.out.println(row));
		System.out.println();
		
		rows = 3;
		System.out.printf("Pascal's triangle with rows %d %n", rows);
		triangle = pascalTriangle(rows);
		triangle.forEach(row-> System.out.println(row));
		System.out.println();

		rows = 5;
		System.out.printf("Pascal's triangle with rows %d %n", rows);
		triangle = pascalTriangle(rows);
		triangle.forEach(row-> System.out.println(row));
		System.out.println();

		rows = 10;
		System.out.printf("Pascal's triangle with rows %d %n", rows);
		triangle = pascalTriangle(rows);
		triangle.forEach(row-> System.out.println(row));
		System.out.println();

	}

}
