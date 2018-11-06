/**
 * 
 */
package sm.interview.coding_challange.company_4.q1;

/**
 * @author shahzadmughal8410
 *
 */
public class StringArraysMerge {

	/**
Given 2 strings merge them to form the merge string.
For merging use alternate characters from string first and second.
Example:
a = abc
b = def
merge = adbecf

	 */    
	public static String mergeStrings(String a, String b) {
		if (null == a || a.length() == 0) {
			return b;
		}

		if (null == b || b.length() == 0) {
			return a;
		}

		int i = 0;
		int j = 0;
		StringBuilder sb = new StringBuilder();
		while (i < a.length() && j < b.length()) {
			int length = sb.length();
			if (length % 2 == 0) {
				sb.append(a.charAt(i++));
			} else {
				sb.append(b.charAt(j++));
			}
		}

		while (i < a.length()) {
			sb.append(a.charAt(i++));
		}

		while (j < b.length()) {
			sb.append(b.charAt(j++));
		}
		return sb.toString();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a = "ace";
		String b = "bdf";
		String merged = mergeStrings(a, b);
		System.out.println("a="+a);
		System.out.println("b="+b);
		System.out.println("merged="+merged);

		a = "acexxx";
		b = "bdf";
		merged = mergeStrings(a, b);
		System.out.println("a="+a);
		System.out.println("b="+b);
		System.out.println("merged="+merged);

		a = "ace";
		b = "bdfzzz";
		merged = mergeStrings(a, b);
		System.out.println("a="+a);
		System.out.println("b="+b);
		System.out.println("merged="+merged);

	}

}
