/**
 * 
 */
package sm.coding.ds.array.icf.pattern_camel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shahzadmughal8410
 *
 */
public class MatchingPatternInCamelCaseNotationDictonary {

	
	/**
Given a dictionary of words where each word follows CamelCase notation, print all words in the dictionary that match with a given pattern consisting of uppercase characters only.

CamelCase is the practice of writing compound words or phrases such that each word or abbreviation begins with a capital letter. Common examples include: “PowerPoint” and “WikiPedia”, “GeeksForGeeks”, “CodeBlocks”, etc.

Examples:


Input: 
dict[] = ["Hi", "Hello", "HelloWorld",  "HiTech", "HiGeek", 
"HiTechWorld", "HiTechCity", "HiTechLab"]

For pattern "HT",
Output: ["HiTech", "HiTechWorld", "HiTechCity", "HiTechLab"]

For pattern "H",
Output: ["Hi", "Hello", "HelloWorld",  "HiTech", "HiGeek", 
    "HiTechWorld", "HiTechCity", "HiTechLab"]

For pattern "HTC",
Output: ["HiTechCity"]


Input: 
dict[] = ["WelcomeGeek","WelcomeToGeeksForGeeks", "GeeksForGeeks"]

For pattern "WTG",
Output: ["WelcomeToGeeksForGeeks"]

For pattern "GFG",
Output: [GeeksForGeeks]

For pattern "GG",
Output: No match found

	 */
	public static List<String> findAllWords(List<String> dict, String pattern) { 
		TrieNode root = buildTrie(dict);
		List<String> matches = search(pattern, root);
		return matches;
	}
	
	public static TrieNode buildTrie(List<String> dict) {
		TrieNode root = new TrieNode();
		for(String word:dict) {
			TrieNode current = root;
			
			// for every word starting from root build the trie
			for(int i =0; i<word.length(); i++) {
				char c = word.charAt(i);
				// build trie for capital letters only
				if(Character.isUpperCase(c)) {
					TrieNode child = current.childs.get(c);
					if(null==child) {
						child = new TrieNode();
						current.childs.put(c, child);
					}
					current = child;
				}
			}
			current.end = true;
			current.words.add(word);// holds the word Itself, to return for search
		}
		return root;
	}
	
	public static List<String> search(String pattern, TrieNode root) {
		List<String> matches = new ArrayList<>();
		TrieNode current = root;
		// first search for full pattern/prefix
		for(int i=0; i<pattern.length();i++) {
			char c = pattern.charAt(i);
			TrieNode child = current.childs.get(c);
			if(null==child) { // if pattern doesn't match completely return;
				return matches;
			}
			current = child;
		}
		// pattern match completely, add all the words for this level
		if(current.end) {
			matches.addAll(current.words);
		}
		
		// pattern matched completely, find all the words under this root(current)
		searchAll(current, matches);
		return matches;
	}

	// recursively search for words in trie, till we exhaust the trie, i.e. its the same as auto complete for prefix 
	public static void searchAll(TrieNode root, List<String> matches) {
		if(root.childs.size()==0) {
			return;
		}
		TrieNode current = root;
		for(TrieNode child: current.childs.values()) {
			if(child.end) {
				matches.addAll(child.words);
			}
			// search for all childs recursively
			searchAll(child, matches);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> dict = Arrays.asList("Hi", "Hello", 
                "HelloWorld", "HiTech", "HiGeek", 
               "HiTechWorld", "HiTechCity", 
                 "HiTechLab");
		String pattern = "HT";
		List<String> matches = findAllWords(dict, pattern);
		
		System.out.println("dict="+dict);
		System.out.println("pattern="+pattern);
		System.out.println("Matches="+matches);

		
		pattern = "H";
		matches = findAllWords(dict, pattern);
		
		System.out.println("pattern="+pattern);
		System.out.println("Matches="+matches);

		pattern = "HTC";
		matches = findAllWords(dict, pattern);
		
		System.out.println("pattern="+pattern);
		System.out.println("Matches="+matches);

		dict = Arrays.asList("WelcomeGeek","WelcomeToGeeksForGeeks", "GeeksForGeeks");
		pattern = "WTG";
		matches = findAllWords(dict, pattern);
		
		System.out.println("dict="+dict);
		System.out.println("pattern="+pattern);
		System.out.println("Matches="+matches);

		pattern = "W";
		matches = findAllWords(dict, pattern);
		
		System.out.println("pattern="+pattern);
		System.out.println("Matches="+matches);

		pattern = "GFG";
		matches = findAllWords(dict, pattern);
		
		System.out.println("pattern="+pattern);
		System.out.println("Matches="+matches);
	}

}

class TrieNode {
	boolean end;
	Map<Character, TrieNode> childs;
	List<String> words;
	
	public TrieNode() {
		childs = new HashMap<>();
		words = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "TrieNode [end=" + end + ", childs=" + childs.size() + ", words=" + words + "]";
	}
}
