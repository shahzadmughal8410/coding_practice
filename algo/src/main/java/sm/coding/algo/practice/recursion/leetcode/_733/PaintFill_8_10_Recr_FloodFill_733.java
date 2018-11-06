/**
 * 
 */
package sm.coding.algo.practice.recursion.leetcode._733;

/**
 * @author smughal
 *
 */
public class PaintFill_8_10_Recr_FloodFill_733 {

	/**
	 *
8.10 Paint Fill: Implement the "paint nil" function that one might see on many image editing programs.
That is, given a screen (represented by a two-dimensional array of colors), a point, and a new color,
nil in the surrounding area until the color changes from the original color.
Hints: #364, #382

T=O (r+c)
S=O (Max(r,c))
Where r=row, c=column

Submission
https://leetcode.com/submissions/detail/174524300/
You are here! 
Your runtime beats 31.41 % of java submissions. 
	 * @param args
	 */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    		int oldColer = image[sr][sc];
    		paintFill(0, 0, oldColer, newColor, image);
    		return image;
    }
    	public static void paintFill(int r, int c, int old, int _new, int[][] image) {
    		if(image[r][c] == old) {
    			image[r][c] = _new;
    			if(r+1<image.length) paintFill(r+1, c, old, _new, image);
    			if(c+1<image[0].length) paintFill(r, c+1, old, _new, image);
    			if(r-1>=0) paintFill(r-1, c, old, _new, image);
    			if(c-1>=0)paintFill(r, c-1, old, _new, image);
    		}
	}
	
	
	public static void main(String[] args) {
		int[][] canvas = new int[][] {
			new int[] {0,1,2,1},
			new int[] {1,1,2,2},
			new int[] {3,1,1,1},
			new int[] {4,0,1,2}
						};

		printCanvas(canvas);
		paintFill(0, 1, 1, 4, canvas);
		System.out.println("1 at (0,1) -> filled with 4");
		printCanvas(canvas);
		
	}
	
	public static void printCanvas(int[][] canvas) {
		for(int i =0;i<canvas.length;i++) {
			for(int j=0; j<canvas[i].length;j++) {
				System.out.print(canvas[i][j]+" ");
			}
			System.out.println();
		}
	}

}
