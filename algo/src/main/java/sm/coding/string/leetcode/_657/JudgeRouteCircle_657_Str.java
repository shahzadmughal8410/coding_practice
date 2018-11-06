/**
 * 
 */
package sm.coding.string.leetcode._657;

/**
 * @author shahzadmughal8410
 *
 */
public class JudgeRouteCircle_657_Str {

	/**
 Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.

The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.

Example 1:

Input: "UD"
Output: true

Example 2:

Input: "LL"
Output: false
 
	 * @param args
	 */
	public static boolean judgeCircle(String moves) {
		if(null==moves || moves.length()==0) {
			return true;
		}
		
		int x = 0;
		int y = 0;
		
		for(int i =0; i<moves.length();i++) {
			char currentMove = moves.charAt(i);
			switch(currentMove) {
				case 'L':
					x--;
					break;
				case 'R':
					x++;
					break;
				case 'U':
					y--;
					break;
				case 'D':
					y++;
					break;
			}
		}
		
		return x==0 && y==0;
	}	
	
	public static void main(String[] args) {
//		String moves = "UD";
//		String moves = "LR";
		String moves = "LLRRUUDDLRDU";
		System.out.printf("Moves [%s], makes circle=%s.%n", moves, judgeCircle(moves));
	}

}
