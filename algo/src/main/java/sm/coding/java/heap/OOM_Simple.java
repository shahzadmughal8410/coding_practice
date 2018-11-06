/**
 * 
 */
package sm.coding.java.heap;

/**
 * @author smughal
 *
 */
public class OOM_Simple {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		System.out.println("Max JVM memory: " + Runtime.getRuntime().maxMemory());
		long[][] ary = new long[Integer.MAX_VALUE][Integer.MAX_VALUE];
	}

}
