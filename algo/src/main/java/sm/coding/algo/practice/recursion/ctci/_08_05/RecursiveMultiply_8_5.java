/**
 * 
 */
package sm.coding.algo.practice.recursion.ctci._08_05;

/**
 * @author smughal
 *
 */
public class RecursiveMultiply_8_5 {

	/**
	 * 
8.5 Recursive Multiply: Write a recursive function to multiply two positive integers without using the
* operator. You can use addition, subtraction, and bit shifting, but you should minimize the number
of those operations.
Hints: # 166, #203, #227, #234, #246, #280

https://en.wikipedia.org/wiki/Ancient_Egyptian_multiplication
https://en.wikipedia.org/wiki/Division_by_two
 
	 */
	
	public static int multiply(int a, int b) {
		int smaller = a<b? a :b;
		int bigger = a>b? a :b;
		return multiplyHelper(smaller, bigger);
	}
	
	public static int multiplyHelper(int smaller, int bigger) {
		if(smaller==0) {
			return 0;
		}else if(smaller==1) {
			return bigger;
		}
		
		int half = multiplyHelper(smaller>>1, bigger);
		if(smaller%2==0) {
			return half+half;
		}else {
			return half+half+bigger;
		}
	}

	public static int multiply_BruteForce(int a, int b) {
		if(a==0 || b==0) return 0;
		if(a==1) {
			return b;
		}
		return b+multiply_BruteForce(a-1, b);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("2*3="+multiply_BruteForce(2, 3));
		System.out.println("10*5="+multiply_BruteForce(10, 5));
		System.out.println("25*4="+multiply_BruteForce(4, 25));
		System.out.println("0*25="+multiply_BruteForce(0, 25));
		System.out.println("10*25="+multiply_BruteForce(10, 0));

		System.out.println();
	
		System.out.println("2*3="+multiply(2, 3));
		System.out.println("10*5="+multiply(10, 5));
		System.out.println("25*4="+multiply(4, 25));
		System.out.println("0*25="+multiply(0, 25));
		System.out.println("10*25="+multiply(10, 0));

		System.out.println("19*27="+SolutionDebug.multiply(190, 287));
		
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
	
	public static int multiply(int a, int b) {
		int smaller = a<b? a :b;
		int bigger = a>b? a :b;
		debug("smaller="+smaller+", bigger="+bigger);
		return multiplyHelper(smaller, bigger);
	}
	
	public static int multiplyHelper(int smaller, int bigger) {
		debug("smaller="+smaller+", bigger="+bigger);
		if(smaller==0) {
			return 0;
		}else if(smaller==1) {
			return bigger;
		}
		String actual = incrementIndent();
		int half = multiplyHelper(smaller>>1, bigger);
		debug("half="+half);
		setIndent(actual);
		if(smaller%2==0) {
			debug("even");
			return half+half;
		}else {
			debug("odd");
			return half+half+bigger;
		}
	}
	
}