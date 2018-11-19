package sm.coding.string.leetcode._046;

import java.util.ArrayList;
import java.util.List;

public class String_Array_Permutations_046 {

	private static List<String> permutationString(String input) {
		List<String> result = new ArrayList<>();
		permutation(new StringBuilder(input), new StringBuilder(), result);
		return result;
	}

	private static void permutation(StringBuilder input, StringBuilder soFar, List<String> result) {
		if (input.length() == 0) {
			result.add(soFar.toString());
		}
		else {
			for(int i=0; i<input.length(); i++) {
				//choose
				char ch = input.charAt(i);
				soFar.append(ch);
				input.deleteCharAt(i);
				
				// explore
				permutation(input, soFar, result);
				
				//un-choose
				soFar = soFar.deleteCharAt(soFar.length()-1);
				input.insert(i, ch);
			}
		}
	}
	
	public static void main(String[] args) {
		List<String> result = permutationString("abc");
		result.forEach(r->System.out.println(r) );
		
	}

}
