/**
 * 
 */
package sm.coding.string.leetcode._535;

import java.util.HashMap;
import java.util.Map;

import sm.coding.string.icf.base62.Base62EncoderDecoder;

/**
 * @author shahzadmughal8410
 *
 */
public class EncodeAndDecodeTinyURL_535 {
	
/**
https://leetcode.com/submissions/detail/196805706/
You are here! 
Your runtime beats 47.63 % of java submissions.
 */

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String host = "http://www.test.com/" ;
		TinyUrlMapper mapper = new TinyUrlMapper();
		for(int i=0;i<(63*2);i++) {
			String originalUrl = host+i;
			String shortUrl = mapper.encode(originalUrl);
			String longUrl = mapper.decode(shortUrl);
			System.out.println("originalUrl= "+originalUrl);
			System.out.println("longUrl    = "+longUrl);
			System.out.println("shortUrl   = "+shortUrl);
		}

	}

}

class TinyUrlMapper {
	
	Map<String, String> map = new HashMap<>();
	Base62EncoderDecoder codec = new Base62EncoderDecoder();
	int counter = 0;
	String host = "http://tinyurl.my/";
	
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
       String tinyUrl = codec.encode(counter++);
       map.put(tinyUrl, longUrl);
       return host+tinyUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
       return map.get(shortUrl.replace(host, "")); 
    }
}

