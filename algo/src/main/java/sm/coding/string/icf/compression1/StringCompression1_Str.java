/**
 * 
 */
package sm.coding.string.icf.compression1;

/**
 * @author smughal
 *
 */
public class StringCompression1_Str {

	/**
	 * 
Input="abbcccd"
Output="a2b3cd"
 
	 * Improved solution to avoid redundant/duplicate code
	 * 
	 * @param args
	 */
	public static String compress_improved(String input) {
		if(null==input || input.length()==0) {
			return input;
		}		
		
		StringBuffer sb = new StringBuffer();
		int i=0;
		while(i<input.length()) {
			int count = 1;
			char current = input.charAt(i);
			
			while(i+1<input.length() && input.charAt(i) == input.charAt(i+1)) {
				++i;
				++count;
			}

			if(count>1) {
				sb.append(count);
			}				
			sb.append(current);
			++i;
		}
		return sb.toString();
	}
	
	/**
	 * ICF class solution
	 * @param input
	 * @return
	 */
	public static String compress(String input) {
		if(null==input || input.length()==0) {
			return input;
		}
		
		StringBuffer sb = new StringBuffer();
		int count = 1;
		char current = input.charAt(0);
		
		for(int i=1; i<input.length();i++) {
			char next = input.charAt(i);
			if(current==next) {
				++count;
			}
			else{
				if(count>1) {
					sb.append(count);
					count = 1;
				}				
				sb.append(current);
			}
			current = next;
		}
		if(count>1) {
			sb.append(count);
		}
		sb.append(current);
		
		return sb.toString();
	}
	public static void main(String[] args) {
		String input = "abbcccd";
		String compress = compress_improved(input);
		System.out.println(String.format("input=%s, compressed=%s", input, compress));

	}

}
