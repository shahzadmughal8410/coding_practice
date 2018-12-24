/**
 * 
 */
package sm.coding.string.icf.base62;

/**
 * @author shahzadmughal8410
 *
 */
public class Base62EncoderDecoder {

	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static final int BASE = ALPHABET.length();
	
	public static String encode(int num) {
		if(num==0) {
			return "a";
		}
		int c = num;
		StringBuilder sb = new StringBuilder();
		while (c > 0) {
			sb.append(ALPHABET.charAt(c % BASE));
			c /= BASE;
		}
		return sb.reverse().toString();
	}
	
	public static int decode(String shortURL) 
	{ 
	    int id = 0; // initialize result 
	  
	    // A simple base conversion logic 
	    for (int i=0; i < shortURL.length(); i++) 
	    { 
	        if ('a' <= shortURL.charAt(i) && shortURL.charAt(i) <= 'z') 
	          id = id*BASE + shortURL.charAt(i) - 'a' ; 
	        if ('A' <= shortURL.charAt(i) && shortURL.charAt(i) <= 'Z') 
	          id = id*BASE + shortURL.charAt(i) - 'A' + 26; 
	        if ('0' <= shortURL.charAt(i) && shortURL.charAt(i) <= '9') 
	          id = id*BASE + shortURL.charAt(i)- '0' + 52; 
	    } 
	    return id; 
	} 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int num = 12345;
		Base62EncoderDecoder codec = new Base62EncoderDecoder();
		String encoded = codec.encode(num);
		int decoded = codec.decode(encoded);
		
		System.out.println(num);
		System.out.println(encoded);
		System.out.println(decoded);

	}

}
