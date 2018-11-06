/**
 * 
 */
package sm.coding.algo.practice.recursion.ctci._08_13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author smughal
 *
 */
public class StackOfBoxes_8_13 {



	
	/**
	 * 
8.13 Stack of Boxes: You have a stack of n boxes, with widths W1, heights h1 and depths d1 . The boxes
cannot be rotated and can only be stacked on top of one another if each box in the stack is strictly
larger than the box above it in width, height, and depth. Implement a method to compute the
height of the tallest possible stack. The height of a stack is the sum of the heights of each box.
Hints Pg 674: #155, #194, #214, #260, #322, #368, #378
Solution Pg 378
	
	 */
	
	public static int stackBoxes(List<Box> boxes) {
		Collections.sort(boxes, new BoxComparator());
		int maxHeight = 0;
		for(int i =0; i< boxes.size(); i++) {
			int height  = stackBoxesHelper(boxes, i) ;
			maxHeight = Math.max(maxHeight, height);
		}
		return maxHeight;
	}

	public static int stackBoxesHelper(List<Box> boxes, int bottomIndex) {
		Box bottom = boxes.get(bottomIndex);
		int maxHeight = 0;
		for(int i = bottomIndex+1; i<boxes.size(); i++) {
			Box nextBox = boxes.get(i);
			if(canPlaceAbove(bottom, nextBox)) {
				int height = stackBoxesHelper(boxes, i);
				maxHeight = Math.max(maxHeight, height);
			}
		}
		maxHeight+=bottom.height;
		return maxHeight;
	}
	
	public static boolean canPlaceAbove(Box bottom, Box next) {
		if(null==bottom) {
			return true;
		}
		if(bottom.height>next.height && bottom.width>next.width && bottom.depth> next.depth) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Box> boxes = new ArrayList<>();
		Box a = new Box(2,2,2);
		Box b = new Box(4,4,4);
		Box c = new Box(1,1,1);
		Box d = new Box(3,3,2);
		boxes.add(a);
		boxes.add(b);
		boxes.add(c);
		boxes.add(d);
		
//		System.out.println("Height="+stackBoxes(boxes));
		System.out.println("Height="+SolutionDebug.stackBoxes(boxes));
	}

}

class Box {
	int width, height, depth;
	public Box(int width, int height, int depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	@Override
	public String toString() {
		return "(w=" + width + ", h=" + height + ", d=" + depth + ")";
	}
}

class BoxComparator implements Comparator<Box> {

	@Override
	public int compare(Box x, Box y) {
		return y.height-x.height;
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
	
	public static int stackBoxes(List<Box> boxes) {
		debug("Unsorted list="+boxes);
		Collections.sort(boxes, new BoxComparator());
		debug("  Sorted list="+boxes);
		int maxHeight = 0;
		for(int i =0; i< boxes.size(); i++) {
			int height  = stackBoxesHelper(boxes, i) ;
			maxHeight = Math.max(maxHeight, height);
			debug("stackBoxes: height="+height+", maxHeight="+maxHeight);
		}
		return maxHeight;
	}

	public static int stackBoxesHelper(List<Box> boxes, int bottomIndex) {
		debug("bottomIndex="+bottomIndex+", boxes="+boxes);
		Box bottom = boxes.get(bottomIndex);
		int maxHeight = 0;
		for(int i = bottomIndex+1; i<boxes.size(); i++) {
			Box nextBox = boxes.get(i);
			boolean canPlaceAbove = canPlaceAbove(bottom, nextBox);
			debug("canPlaceAbove="+canPlaceAbove+", bottom="+bottom+", nextBox="+nextBox);
			if(canPlaceAbove) {
				String actual = incrementIndent();
				int height = stackBoxesHelper(boxes, i);
				maxHeight = Math.max(maxHeight, height);
				setIndent(actual);
				debug("stackBoxesHelper: height="+height+", maxHeight="+maxHeight);
			}
		}
		debug("maxHeight="+maxHeight+", bottom.height="+bottom.height);
		maxHeight+=bottom.height;
		debug("maxHeight="+maxHeight);
		return maxHeight;
	}
	
	public static boolean canPlaceAbove(Box bottom, Box next) {
		if(null==bottom) {
			return true;
		}
		if(bottom.height>next.height && bottom.width>next.width && bottom.depth> next.depth) {
			return true;
		}
		return false;
	}
	
}
