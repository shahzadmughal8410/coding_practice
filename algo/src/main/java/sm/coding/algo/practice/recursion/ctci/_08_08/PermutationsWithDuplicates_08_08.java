/**
 * 
 */
package sm.coding.algo.practice.recursion.ctci._08_08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author smughal
 *
 */
public class PermutationsWithDuplicates_08_08 {

	/**
Permutations with Dups: Write a method to compute all permutations of a string whose characters
are not necessarily unique. The list of permutations should not have duplicates.
Hints: # 161, #190, #222, #255
	 * @param args
	 */
	public static List<String> permuteNoDups(String input){
		List<String> result = new ArrayList<>();
		Map<Character, Integer> map = buildFrequencyMap(input);
		permuteNoDupsHelper(map, "", result, input.length());
		return result;
	}
	public static void permuteNoDupsHelper(Map<Character, Integer> map, String sofar, List<String> result, int remaining) {
		if(remaining==0) {
			result.add(sofar);
			return;
		}
		
		for(char c:map.keySet()) {
			int count = map.get(c);
			if(count>0) {
				map.put(c, count-1);
				permuteNoDupsHelper(map, sofar+c, result, remaining-1);
				map.put(c, count);
			}
		}
	}
	
	public static Map<Character, Integer> buildFrequencyMap(String input){
		Map<Character, Integer> map = new HashMap<>();		
		for(char c:input.toCharArray()) {
			int count = map.containsKey(c) ? map.get(c) : 0;
			++count;
			map.put(c, count);
		}		
		return map;
	}
	
	
	public static void main(String[] args) {
//		List<String> result = permuteNoDups("aaabbbccc");
		List<String> result = SolutionDebug.permuteNoDups("aba");
		System.out.println("Unique permutations="+result.size());
		result.forEach(s->System.out.println(s));

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
	
	public static List<String> permuteNoDups(String input){
		debug("Input="+input);
		List<String> result = new ArrayList<>();
		Map<Character, Integer> map = buildFrequencyMap(input);
		debug("Map="+map);
		permuteNoDupsHelper(map, "", result, input.length());
		return result;
	}
	public static void permuteNoDupsHelper(Map<Character, Integer> map, String sofar, List<String> result, int remaining) {
		debug(String.format("sofar=\"%s\", temaining=%d, map=%s", sofar, remaining, map));
		if(remaining==0) {
			result.add(sofar);
			debug("added in result="+sofar);
			return;
		}
		
		for(char c:map.keySet()) {
			int count = map.get(c);
			if(count>0) {
				map.put(c, count-1);
				String actual = incrementIndent();
				permuteNoDupsHelper(map, sofar+c, result, remaining-1);
				setIndent(actual);
				map.put(c, count);
				debug("backtrack: character="+c+", count="+count);
			}else {
				debug("skipping character="+c+", count="+count);
			}
		}
	}
	
	public static Map<Character, Integer> buildFrequencyMap(String input){
		Map<Character, Integer> map = new HashMap<>();		
		for(char c:input.toCharArray()) {
			int count = map.containsKey(c) ? map.get(c) : 0;
			++count;
			map.put(c, count);
		}		
		return map;
	}
}
