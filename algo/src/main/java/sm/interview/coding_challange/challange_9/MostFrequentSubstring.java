package sm.interview.coding_challange.challange_9;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostFrequentSubstring {

    public static int getMaxOccurrences(String s, int minLength, int maxLength, int maxUnique) {
    // Write your code here
    	
    		// frequency map of sub strings
		Map<String, Integer> fmap = new HashMap<>();
        int maxFrequency = 0;
		// generate all sub string of a string, with pruning
        for (int i = 0; i < s.length(); i++) {
        		// start generating string of length at least minLength
            for (int j = i+minLength; j <= s.length(); j++) {
            		// this is tricky to understand and was causing most of test failures
            		// if there is sub string X with repeating K times, then there must be a string X-1>=K times so only consider minLength 
            		if( j-i == minLength) {
	            		String sub = s.substring(i, j);
	            		// do unique characters validation
	            		boolean valid = isValid(sub, maxUnique);
	            		if(valid) {
	                		fmap.put(sub, fmap.getOrDefault(sub, 0)+1);
	                		// update maxFrequency as we calculating it
	                		maxFrequency = Math.max(maxFrequency, fmap.getOrDefault(sub, 0));
	            		}
            		}
            }
        }
        return maxFrequency;
    }
	
    // to check if string has at most K unique characters
	public static boolean isValid(String s, int unique) {
		Set<Character> uniqueChars = new HashSet<>();
		for(int i=0; i<s.length();i++) {
			uniqueChars.add(s.charAt(i));	
			// no need to continue if validations, i.e. fail fast
			if(uniqueChars.size()>unique) {
				return false;
			}
		}		
		return uniqueChars.size()<=unique;
	}
	
	public static void main(String[] args) {
		String s = "abcde";
		int min = 2;
		int max = 4;
		int unique = 26;
		int freq = getMaxOccurrences(s, min, max, unique);
		System.out.println("freq="+freq);
		
		
		/*
SELECT ID, IF 
(P_ID IS NULL,'ROOT',
IF((SELECT COUNT(*) FROM TREE WHERE P_ID=T.ID)>0,'INNER','LEAF')) 
FROM TREE AS T ORDER BY ID;
		 */

	}
	
}
