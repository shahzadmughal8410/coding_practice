/**
 * 
 */
package sm.coding.string.leetcode._125;

/**
 * @author shahzadmughal8410
 *
 */
public class ValidPalindrome_125_Str_2P {

	/**
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.

 https://leetcode.com/problems/valid-palindrome/description/
 
 T=O(n)
 S=O(1)
 
Submission
https://leetcode.com/submissions/detail/169098464/
You are here!
Your runtime beats 98.26 % of java submissions. 
 
	 * @param args
	 */
	public static boolean isPalindrome(String s) {
		if(null==s)
			return false;
		if(s.length()==0)
			return true;
		int start = 0;
		int end = s.length()-1;
		
		while(start<end) {
			if(!isLetterOrDigit(s.charAt(start))) {// we can use Character.isLetterOrDigit if its not ASCII or extended ASCII
				++start;
			}else if(!isLetterOrDigit(s.charAt(end))) {
				--end; 
			}else if(equalsIgnoreCase(s.charAt(start), s.charAt(end))) { // we can use Character.toLowerCase(ch) if its not ASCII or extended ASCII
				++start;
				--end;
			}else {
				return false;
			}
		}		
		return true;
	}
	// helper function, Character.isLetterOrDigit can be used instead
	public static boolean isLetterOrDigit(char c) {
		if((c>='a' && c<='z') ||
			(c>='A' && c<='Z') ||
			(c>='0' && c<='9')
				) {
			return true;
		}
		
		return false;
	}
	// helper function, Character.toLowerCase(c1)==Character.toLowerCase(c2) can be used instead
	public static boolean equalsIgnoreCase(char c1, char c2) {
		int i1 = c1;
		int i2 = c2;
		if(c1>='A' && c1<'a') {
			i1 = c1+32;
		}
		if(c2>='A' && c2<'a') {
			i2 = c2+32;
		}
		
		return i1==i2;
	}
	
	
	public static void main(String[] args) {
//		String s = "civic";
		String s = "A man, a plan, a canal: Panama";
//		String s = "race a car";
		System.out.println("["+s+"] isPalindrome="+isPalindrome(s));

	}

}
