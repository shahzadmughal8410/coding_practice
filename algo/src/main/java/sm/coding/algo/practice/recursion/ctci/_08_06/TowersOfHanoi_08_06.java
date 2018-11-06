/**
 * 
 */
package sm.coding.algo.practice.recursion.ctci._08_06;

/**
 * @author smughal
 *
 */
public class TowersOfHanoi_08_06 {

	/**
Towers of Hanoi: In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of
different sizes which can slide onto any tower. The puzzle starts with disks sorted in ascending order
of size from top to bottom (Le., each disk sits on top of an even larger one). You have the following
constraints:
(1) Only one disk can be moved at a time.
(2) A disk is slid off the top of one tower onto another tower.
(3) A disk cannot be placed on top of a smaller disk.
Write a program to move the disks from the first tower to the last using Stacks. 
	 * @param args
	 */
	
	public static void move(int n, char from_rod, char to_rod, char aux_rod) {
		if(n==1) {
			System.out.println("Move disk 1 from rod " +  from_rod + " to rod " + to_rod);
			return;
		}
		
		move(n-1, from_rod, aux_rod, to_rod);
		System.out.println("Move disk " + n + " from rod " +  from_rod + " to rod " + to_rod);
		move(n-1, aux_rod, to_rod , from_rod);
	}
	
	public static void main(String[] args) {
//		move(3, 'A', 'B', 'C');
		SolutionDebug.move(3, 'A', 'B', 'C');

	}

}

class SolutionDebug { 
	
	public static String indent = "|---";
	public static void debug(Object msg) {
		System.out.println("DEBUG "+indent+">"+msg);
	}
	public static String incrementIndent() {
		String indentActual = indent;
		indent = indent+"|---";
		return indentActual;
	}
	public static void setIndent(String newIndent) {
		indent = newIndent;
	}
	
	public static void move(int n, char from_rod, char to_rod, char aux_rod) {
		debug(String.format("disks=%d, from_rod=%c, to_rod=%c, aux_rod=%c", n, from_rod, to_rod, aux_rod));
		if(n==1) {
			debug("Move disk 1 from rod " +  from_rod + " to rod " + to_rod);
			return;
		}
		String actual = incrementIndent();
		move(n-1, from_rod, aux_rod, to_rod);
		debug("Move disk " + n + " from rod " +  from_rod + " to rod " + to_rod);
		move(n-1, aux_rod, to_rod , from_rod);
		setIndent(actual);
	}
}
