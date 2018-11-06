/**
 * 
 */
package sm.coding.string.icf.string_sort;

/**
 * @author shahzadmughal8410
 *
 */
public class StringSort_Bucket_Count_Sort {

	/**
Given a String, return it in sorted form. 
Input is extended ascii characters.
Example "Apple" -> "Aelpp"

	 */
	public static String sortString(String s) {
		int[] frequency = new int[26];
		for(int i =0; i<s.length(); i++) {
			int ch = s.charAt(i);
			int index = ch - 'a';
			frequency[index] = frequency[index]+1;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<frequency.length;i++) {			
			for(int j =0; j<frequency[i]; j++) {
				// to convert ascii int to character down cast int to char
				sb.append((char) (i+'a') );
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String input = "apple";
		String output = sortString(input);
		System.out.printf("%s ---> %s %n", input, output);
		
		input = "classic";
		output = sortString(input);
		System.out.printf("%s ---> %s %n", input, output);

		input = "ascii";
		output = sortString(input);
		System.out.printf("%s ---> %s %n", input, output);

		input = "somelongstringishere";
		output = sortString(input);
		System.out.printf("%s ---> %s %n", input, output);

	}
}
