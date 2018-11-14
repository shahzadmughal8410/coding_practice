/**
 * 
 */
package sm.coding.string.icf.arrayencode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author smughal
 *
 */
public class EncodeDecodeStringArrayToString_Str_Array_271 {

	/**
String Encode/Decode

input:
[“hello” , “world”, "!", ""] 

input:
[“1hello” , “world”, “!”, ’””, “hi” , “”] 


Encode -> helloworld!””
Decode -> back to list 
	 * 
	 * @param args
	 */

	/**
	 * 
 Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}

Machine 2 (receiver) has the function:

vector<string> decode(string s) {
  //... your code
  return strs;
}

So Machine 1 does:

string encoded_string = encode(strs);

and Machine 2 does:

vector<string> strs2 = decode(encoded_string);

strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:

    The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
    Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
    Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.



Submission
https://leetcode.com/submissions/detail/189481655/
You are here!
Your runtime beats 87.86 % of java submissions.
	 * @param strs
	 * @return
	 */
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String s : strs) {
            sb.append(s.length()).append('/').append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> ret = new ArrayList<String>();
        int i = 0;
        while(i < s.length()) {
            int slash = s.indexOf('/', i);
            int size = Integer.valueOf(s.substring(i, slash));
            ret.add(s.substring(slash + 1, slash + size + 1));
            i = slash + size + 1;
        }
        return ret;
    }
	
	
	
	public static String nullEscape = "@#@$^"; //use any pattern that can not be part of string
	public static String emptyEscape = "@#@$"; //use any pattern that can not be part of string
	
	/**
	 * Encode the string in following format
	 * <array length>|<length of each word>,-<all the words as one string>
	 * 
	 * It will use nullExcape pttern when null elements comes in string array
	 * It will use emptyExcape pattern when string is empty string i.e. ""
	 * 
	 * Time complexity is O(n)
	 * Space complexity is O(n) i.e. space of returned string, O(1) if returned string space is not considered
	 * 
	 * @param arr
	 * @return
	 */
	public static String encode_Hard(String[] arr) {
		
		if(null==arr) {
			return null;
		}
		if(arr.length==0) {
			return "";
		}
		
		/*
		 *  Patern is 
		 *  array length|length of each string|all strings as concantenated string
		 *  for array  [“hello” , “world”, "!", ""] the encoded string would be
		 *  4|5,5,1,0,-helloworld!<spaceEscape>
		 */
		StringBuilder sb = new StringBuilder();
		StringBuilder pattern = new StringBuilder();
		pattern.append(arr.length);
		pattern.append("|");
		for(String s:arr) {
			if(s==null) {
				s = nullEscape;
			}else if(s.length()==0) {
				s = emptyEscape;
			}
			pattern.append(s.length());
			pattern.append(",");
			sb.append(s);
		}
		pattern.append("-");
		pattern.append(sb.toString());
		
		return pattern.toString();
	}
	
	/**
	 * Decode the string from following format
	 * <array length>|<length of each word>,-<all the words as one string>
	 * 
	 * It will replace null with nullExcape pattern
	 * It will replace empty string (i.e. "") with emptyExcape pattern
	 * 
	 * Time complexity is O(n)
	 * Space complexity is O(m) i.e. space of returned array where m is the returned array, O(1) if returned array space is not considered
	 * 
	 * @param arr
	 * @return
	 */
	public static String[] decode_Hard(String encoded) {
		
		if(null==encoded) {
			return null;
		}
		if(encoded.length()==0) {
			return new String[] {};
		}
		
		int i = 0; // index of encoded string 
		boolean totalProcessed = false;
		int[] lengthArray = null;
		String[] resultArr = null;
		boolean lengthProcessed = false;
		int lengthIndex = 0;
		int wordsIndex=0;
		StringBuilder sofar = new StringBuilder(); // running string content
		
		// parse the length of string array
		while(i<encoded.length() && !totalProcessed) {
			char c = encoded.charAt(i);
			if(c=='|') {
				int totalWords = Integer.parseInt(sofar.toString());
				lengthArray = new int[totalWords];
				resultArr = new String[totalWords];
				totalProcessed = true;
				sofar.setLength(0);
			}else {
				sofar.append(c);// case where total words are > 9
			}
			++i;// i has to be incremented when we processed the '|' sign that is why we are using the boolean flag rather than break
		}
		// invalid encoding format no '|' found
		if(totalProcessed==false) {
			return null;
		}

		// parse the length of each string in array
		while(i<encoded.length() && !lengthProcessed) {
			char c = encoded.charAt(i);
			if(c=='-') {
				lengthProcessed=true;
				sofar.setLength(0);
			} else if(c==',') {
				lengthArray[lengthIndex++] = Integer.parseInt(sofar.toString());
				sofar.setLength(0);
			}else {
				sofar.append(c);// word length is > 9
			}
			++i;
		}
		// invalid encoding format no '-' found
		if(!lengthProcessed) {
			return null;
		}

		// parse each string in array
		while(i<encoded.length()) {
			char c = encoded.charAt(i);
			sofar.append(c);
			if(sofar.length()==lengthArray[wordsIndex]) {
				resultArr[wordsIndex] = sofar.toString();
				if(resultArr[wordsIndex].equals(nullEscape)) {
					resultArr[wordsIndex] = null;
				}else if(resultArr[wordsIndex].equals(emptyEscape)) {
					resultArr[wordsIndex] = "";
				}
				sofar.setLength(0);
				++wordsIndex;
			}
			++i;
		}
		return resultArr;
	}
	
	public static void main(String[] args) {
		
		test_basic();
		test_basic_word_length_2_digit();
		test_null();
		test_empty();
		test_null_scape();
		test_empty_scape();
		test_space_string();
		test_empty_null_start();
		test_empty_null_end();
		test_null_empty_start();
		test_null_empty_end();
		test_null_empty_middle();
		test_invalid_decode_1();
		test_invalid_decode_2();
		test_invalid_decode_3();
		test_invalid_decode_4();
	}
	
	public static void test_basic() {
		String[] arr = new String[] {"hello" , "world", "!"};
		System.out.println("original="+Arrays.asList(arr));
		String encoded = encode_Hard(arr);
		
		String[] result = decode_Hard(encoded);
		System.out.println("decoded ="+Arrays.asList(result));
		System.out.println("encoded="+encoded);
		
		if(Arrays.asList(arr).toString().equals(Arrays.asList(result).toString())) {
			System.out.println("test_basic PASSED");
		}else {
			System.err.println("test_basic FAILED");
		}
	}

	public static void test_basic_word_length_2_digit() {
		String[] arr = new String[] {"hello" , "world", "!", "internationalization", "one" ,"two", "three", "4", "5", "6", "7", "8", "9", "10"};
		System.out.println("original="+Arrays.asList(arr));
		String encoded = encode_Hard(arr);
		
		String[] result = decode_Hard(encoded);
		System.out.println("decoded ="+Arrays.asList(result));
		System.out.println("encoded="+encoded);
		
		if(Arrays.asList(arr).toString().equals(Arrays.asList(result).toString())) {
			System.out.println("test_basic PASSED");
		}else {
			System.err.println("test_basic FAILED");
		}
	}

	public static void test_null() {
		String encoded = encode_Hard(null);
		
		String[] result = decode_Hard(null);
		System.out.println("encoded="+encoded);
		System.out.println("decoded ="+result);
		
		if(encoded==null && result==null) {
			System.out.println("test_null PASSED");
		}else {
			System.err.println("test_null FAILED");
		}
	}

	public static void test_empty() {
		String[] arr = new String[] {};
		String encoded = encode_Hard(arr);
		
		String[] result = decode_Hard("");
		System.out.println("encoded="+encoded);
		System.out.println("decoded ="+Arrays.asList(result));

		if(Arrays.asList(arr).toString().equals(Arrays.asList(result).toString())) {
			System.out.println("test_empty PASSED");
		}else {
			System.err.println("test_empty FAILED");
		}
	}

	public static void test_null_scape() {
		String[] arr = new String[] {"one",null,"two"};
		System.out.println("original="+Arrays.asList(arr));
		String encoded = encode_Hard(arr);
		
		String[] result = decode_Hard(encoded);
		System.out.println("decoded ="+Arrays.asList(result));
		System.out.println("encoded="+encoded);
	
		if(Arrays.asList(arr).toString().equals(Arrays.asList(result).toString())) {
			System.out.println("test_null_scape PASSED");
		}else {
			System.err.println("test_null_scape FAILED");
		}
	}

	public static void test_empty_scape() {
		String[] arr = new String[] {"empty one","","empty two"};
		System.out.println("original="+Arrays.asList(arr));
		String encoded = encode_Hard(arr);
		
		String[] result = decode_Hard(encoded);
		System.out.println("decoded ="+Arrays.asList(result));
		System.out.println("encoded="+encoded);

		if(Arrays.asList(arr).toString().equals(Arrays.asList(result).toString())) {
			System.out.println("test_empty_scape PASSED");
		}else {
			System.err.println("test_empty_scape FAILED");
		}
}

	public static void test_space_string() {
		String[] arr = new String[] {"space one","   ","space two"};
		System.out.println("original="+Arrays.asList(arr));
		String encoded = encode_Hard(arr);
		
		String[] result = decode_Hard(encoded);
		System.out.println("decoded ="+Arrays.asList(result));
		System.out.println("encoded="+encoded);

		if(Arrays.asList(arr).toString().equals(Arrays.asList(result).toString())) {
			System.out.println("test_space_string PASSED");
		}else {
			System.err.println("test_space_string FAILED");
		}
}

	public static void test_empty_null_start() {
		String[] arr = new String[] {"",null,"data"};
		System.out.println("original="+Arrays.asList(arr));
		String encoded = encode_Hard(arr);
		
		String[] result = decode_Hard(encoded);
		System.out.println("decoded ="+Arrays.asList(result));
		System.out.println("encoded="+encoded);

		if(Arrays.asList(arr).toString().equals(Arrays.asList(result).toString())) {
			System.out.println("test_empty_null_start PASSED");
		}else {
			System.err.println("test_empty_null_start FAILED");
		}
	}

	public static void test_empty_null_end() {
		String[] arr = new String[] {"data","",null};
		System.out.println("original="+Arrays.asList(arr));
		String encoded = encode_Hard(arr);
		
		String[] result = decode_Hard(encoded);
		System.out.println("decoded ="+Arrays.asList(result));
		System.out.println("encoded="+encoded);

		if(Arrays.asList(arr).toString().equals(Arrays.asList(result).toString())) {
			System.out.println("test_empty_null_end PASSED");
		}else {
			System.err.println("test_empty_null_end FAILED");
		}
	}

	public static void test_null_empty_start() {
		String[] arr = new String[] {null,"","data"};
		System.out.println("original="+Arrays.asList(arr));
		String encoded = encode_Hard(arr);
		
		String[] result = decode_Hard(encoded);
		System.out.println("decoded ="+Arrays.asList(result));
		System.out.println("encoded="+encoded);

		if(Arrays.asList(arr).toString().equals(Arrays.asList(result).toString())) {
			System.out.println("test_null_empty_start PASSED");
		}else {
			System.err.println("test_null_empty_start FAILED");
		}
	}

	public static void test_null_empty_end() {
		String[] arr = new String[] {"data",null,""};
		System.out.println("original="+Arrays.asList(arr));
		String encoded = encode_Hard(arr);
		
		String[] result = decode_Hard(encoded);
		System.out.println("decoded ="+Arrays.asList(result));
		System.out.println("encoded="+encoded);

		if(Arrays.asList(arr).toString().equals(Arrays.asList(result).toString())) {
			System.out.println("test_null_empty_end PASSED");
		}else {
			System.err.println("test_null_empty_end FAILED");
		}
	}

	public static void test_null_empty_middle() {
		String[] arr = new String[] {"data",null,"more data","","even more data"};
		System.out.println("original="+Arrays.asList(arr));
		String encoded = encode_Hard(arr);
		
		String[] result = decode_Hard(encoded);
		System.out.println("decoded ="+Arrays.asList(result));
		System.out.println("encoded="+encoded);

		if(Arrays.asList(arr).toString().equals(Arrays.asList(result).toString())) {
			System.out.println("test_null_empty_middle PASSED");
		}else {
			System.err.println("test_null_empty_middle FAILED");
		}
	}

	public static void test_invalid_decode_1() {
		String encoded = "not an encoded string";
		String[] result = decode_Hard(encoded);
		System.out.println("decoded ="+result);
		System.out.println("encoded="+encoded);

		if(result==null) {
			System.out.println("test_invalid_decode_1 PASSED");
		}else {
			System.err.println("test_invalid_decode_1 FAILED");
		}
	}

	public static void test_invalid_decode_2() {
		String encoded = "pipe sign | not an encoded string";
		try {
			String[] result = decode_Hard(encoded);
			System.err.println("test_invalid_decode_2 FAILED");
		}catch(Exception e){
			System.out.println("test_invalid_decode_2 PASSED");
		}
	}

	public static void test_invalid_decode_3() {
		String encoded = "hyphen sign - not an encoded string";
		String[] result = decode_Hard(encoded);
		System.out.println("decoded ="+result);
		System.out.println("encoded="+encoded);

		if(result==null) {
			System.out.println("test_invalid_decode_3 PASSED");
		}else {
			System.err.println("test_invalid_decode_3 FAILED");
		}
	}

	public static void test_invalid_decode_4() {
		String encoded = "comma , not an encoded string";
		String[] result = decode_Hard(encoded);
		System.out.println("decoded ="+result);
		System.out.println("encoded="+encoded);

		if(result==null) {
			System.out.println("test_invalid_decode_4 PASSED");
		}else {
			System.err.println("test_invalid_decode_4 FAILED");
		}
	}

}
