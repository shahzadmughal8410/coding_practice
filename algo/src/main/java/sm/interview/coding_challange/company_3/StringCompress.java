/**
 * 
 */
package sm.interview.coding_challange.company_3;

/**
 * @author shahzadmughal8410
 *
 */
public class StringCompress {

	// Given an array, [a,a,b,c,j,j,k,a,a], write a method that compacts
	// consecutive identical values into a string like the following "a2bcj2ka2".

	public static void main(String[] args) {
		System.out.println("Hello Java");

		char[] arr = new char[] { 'a', 'a', 'b', 'c', 'j', 'j', 'k', 'a', 'a' };
		String compressed = compress(arr);

		System.out.println("compressed=" + compressed);

	}

	static String compress(char[] arr) {
		// null check and length check
		//
		int count = 1;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < arr.length; i++) {

			if (arr[i - 1] == arr[i]) {
				++count;
			} else {
				sb.append(arr[i - 1]);
				if (count > 1) {
					sb.append(count);
					count = 1;
				}
			}
		}

		// for last character
		sb.append(arr[arr.length - 1]);
		if (count > 1) {
			sb.append(count);
			count = 1;
		}
		return sb.toString();
	}

}
