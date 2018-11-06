/**
 * 
 */
package sm.coding.algo.practice.recursion.ctci._08_07;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smughal
 *
 */
public class PermutationWithoutDups_8_7 {

	/**
8.7 Permutations without Dups: Write a method to compute all permutations of a string of unique
characters.
Hints: #150, #185, #200, #267, #278, #309, #335, #356
Solution Pg: 367

	 * @param input
	 * @param soFar
	 * @param indent
	 */
	public static List<String> permute(String word){
		List<String> result = new ArrayList<>();
		permuteHelper(word, result, "");
		return result;
	}
	
	public static void permuteHelper(String word, List<String> result, String soFar) {
		if(word.length()==0) {
			result.add(soFar);
		}
		
		for(int i =0 ; i<word.length(); i++) {
			char ch = word.charAt(i);
			soFar+=ch;
			permuteHelper(word.substring(0, i)+word.substring(i+1), result, soFar);
			soFar = soFar.substring(0, soFar.length()-1);
			
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> result = permute("abcd");
		System.out.println("Total permutations="+result.size());
		result.forEach(w->System.out.println(w));

	}

}
