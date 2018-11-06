/**
 * 
 */
package sm.coding.algo.practice.recursion.ctci._08_13.recommended;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author smughal
 *
 */
public class StackOfBoxes_8_14_Recommended {



	
	/**
	 * 
8.13 Stack of Boxes: You have a stack of n boxes, with widths W1, heights h1 and depths d1 . The boxes
cannot be rotated and can only be stacked on top of one another if each box in the stack is strictly
larger than the box above it in width, height, and depth. Implement a method to compute the
height of the tallest possible stack. The height of a stack is the sum of the heights of each box.
Hints Pg 674: #155, #194, #214, #260, #322, #368, #378
Solution Pg 378
	
	 */
	
	public static int createStack(ArrayList<Box> boxes) {
		Collections.sort(boxes, new BoxComparator());
		return createStack(boxes, null, 0);
	}
	
	public static int createStack(ArrayList<Box> boxes, Box bottom, int offset) {
		if (offset >= boxes.size()) {
			return 0;
		}
		
		/* height with this bottom */
		Box newBottom = boxes.get(offset);
		int heightWithBottom = 0;
		boolean canPlaceAbove  = canPlaceAbove(bottom, newBottom); 
		if (bottom == null || canPlaceAbove) {
			heightWithBottom = createStack(boxes, newBottom, offset + 1);
			heightWithBottom += newBottom.height;
		}
		
		/* without this bottom */
		int heightWithoutBottom = createStack(boxes, bottom, offset + 1);
		
		return Math.max(heightWithBottom, heightWithoutBottom);
	}
		
	
	public static void main(String[] args) {
//		Box[] boxList = { new Box(6, 4, 4), new Box(8, 6, 2), new Box(5, 3, 3), new Box(7, 8, 3), new Box(4, 2, 2), new Box(9, 7, 3)};
//		ArrayList<Box> boxes = new ArrayList<Box>();
//		for (Box b : boxList) {
//			boxes.add(b);
//		}

		ArrayList<Box> boxes = new ArrayList<>();
		Box a = new Box(2,4,2);
		Box c = new Box(1,1,1);
		Box d = new Box(3,3,3);
		boxes.add(a);
		boxes.add(c);
		boxes.add(d);

		int height = SolutionDebug.createStack(boxes);
		System.out.println(height);
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
	

	
	public static boolean canPlaceAbove(Box bottom, Box next) {
		if(null==bottom) {
			return true;
		}
		if(bottom.height>next.height && bottom.width>next.width && bottom.depth> next.depth) {
			return true;
		}
		return false;
	}
	
	public static int createStack(ArrayList<Box> boxes) {
		debug(boxes);
		Collections.sort(boxes, new BoxComparator());
		debug(boxes);
//		int[] stackMap = new int[boxes.size()];
		return createStack(boxes, null, 0);
	}
	
	public static int createStack(ArrayList<Box> boxes, Box bottom, int offset) {
		debug("bottom="+bottom+", offset="+offset);
		if (offset >= boxes.size()) {
			debug("return 0");
			return 0;
		}
		
		/* height with this bottom */
		Box newBottom = boxes.get(offset);
		int heightWithBottom = 0;
		boolean canPlaceAbove  = canPlaceAbove(bottom, newBottom); 
		debug("bottom="+bottom+", newBottom="+newBottom+", canPlaceAbove="+canPlaceAbove+", offset="+offset);
		if (bottom == null || canPlaceAbove) {
			String actual = incrementIndent();
			heightWithBottom = createStack(boxes, newBottom, offset + 1);
			setIndent(actual);
			debug("heightWithBottom="+heightWithBottom+", newBottom.height="+newBottom.height);
			heightWithBottom += newBottom.height;
			debug("heightWithBottom="+heightWithBottom);
		}
		
		/* without this bottom */
		String actual = incrementIndent();
		int heightWithoutBottom = createStack(boxes, bottom, offset + 1);
		setIndent(actual);
		debug("heightWithBottom="+heightWithBottom+", heightWithoutBottom="+heightWithoutBottom);
		
		return Math.max(heightWithBottom, heightWithoutBottom);
	}
}
