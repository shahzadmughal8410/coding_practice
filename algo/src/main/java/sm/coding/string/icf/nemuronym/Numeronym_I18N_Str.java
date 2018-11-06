/**
 * 
 */
package sm.coding.string.icf.nemuronym;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author smughal
 *
 */
public class Numeronym_I18N_Str {

	/**
i18n (where 18 stands for the number of letters between the first i and the last n in the word “internationalization,”) Wiki it.
Generate all such possible i18n strings for any given string. for eg. "careercup"=>"c7p","ca6p","c6up","car5p","ca5up","care4p","car4up","caree3p","care3up"..till the count is 0 which means its the complete string again.

https://www.careercup.com/question?id=5733696185303040
https://classroom.google.com/c/ODc3NTQ5NDkzNVpa/a/MTA1OTcyNDQ4NTVa/details
 
	 * @param args
	 */
	public static List<String> numeronym(String word){
		List<String> result = new ArrayList<>();
		
		if(null==word || word.length()<=2)
			return result;
		
		int length = word.length();
		int range = length-2;
		
		for(int i=range ; i>1 ; i--) {
			for(int j=1 ; j+i<length ; j++) {
				String s =  word.substring(0, j) + i + word.substring(j+i, length);
				result.add(s);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
//		List<String> result = numeronym("nailed");
//		List<String> result = SolutionDebug.numeronym("nailed");
		List<String> result = SolutionDebug.numeronym("internationalization");
//		List<String> result = SolutionDebug.numeronym("batch");
//		List<String> result = SolutionDebug.numeronym("careercup");
//		List<String> result = numeronym("aaa");
		result.forEach(r->System.out.println(r));
		System.out.println("Total combinations="+result.size());
	}

}

class SolutionDebug {

	static StringBuilder format = new StringBuilder();

	public static void tableColumns(String ... cols) {
		for(int i = 0; i<cols.length;i++) {
			String c = cols[i];
			if(c.indexOf(":")!=-1) {
				String[] arr = c.split(Pattern.quote(":"));
				format.append("|%-").append(arr[1]).append("s ");
				cols[i] = arr[0];
			}else {
				format.append("|%-").append(c.length()).append("s ");
			}
			
		}
		format.append("|");
		debugRow(cols);
	}
	
	public static void debugRow(Object ... cols) {
		debug(String.format(format.toString(), cols));
	}

	public static void debug(Object msg) {
		System.out.println("DEBUG: "+msg);
	}

	public static List<String> numeronym(String word){
		List<String> result = new ArrayList<>();
		
		if(null==word || word.length()<=2)
			return result;
		
		int length = word.length();
		int range = length-2;
		tableColumns("i:3", "j:4", "s:6", "result:35", "length", "range");
		for(int i=range ; i>1 ; i--) {
			for(int j=1 ; j+i<length ; j++) {
				String s =  word.substring(0, j) + i + word.substring(j+i, length);
				debugRow(i, j, s, result, length, range);
				result.add(s);
			}
		}
		return result;
	}

}
