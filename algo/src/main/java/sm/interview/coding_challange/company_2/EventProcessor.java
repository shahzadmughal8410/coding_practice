/**
 * 
 */
package sm.interview.coding_challange.company_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class EventProcessor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = null;
		int readedLines = 0;
		try {
			sc = new Scanner(System.in);
			String event = sc.nextLine();
			Integer totalLines = processIntegerEvent(event, 1);
			if(totalLines==null) {
				// error for first line encountered
				return;
			}
			String line = null;
			while(readedLines < totalLines) {
				line = sc.nextLine();
				++readedLines;
				processEvents(line, readedLines+1);
			}
		} catch(Exception e) {
			System.out.println("FAILURE => WRONG INPUT (LINE " + (readedLines+1) + ")");
		} finally {
			if(null!=sc) {
				sc.close();
			}
		}
	}
	
	public static void processEvents(String events, int lineNumber) {
		String[] eventArr = events.split(Pattern.quote(" "));
		int lastEvent = Integer.MIN_VALUE;
		int totalEvents = eventArr.length;
		int sum = 0;
		for(int i =0; i<eventArr.length; i++) {
			String event = eventArr[i];
			Integer eventValue =processIntegerEvent(event, lineNumber);
			if(eventValue==null) {
				// error parsing event
				return;
			}
			if(eventValue>lastEvent) {
				lastEvent = eventValue;
			}
			sum+=eventValue;
		}
		if(totalEvents!=lastEvent) {
			System.out.println("FAILURE => RECEIVED: "+totalEvents+", EXPECTED: "+lastEvent);
			
		}else if(validSum(sum, lastEvent, lineNumber)) {
			System.out.println("SUCCESS => RECEIVED: "+totalEvents);
		}
	}
	
	public static Integer processIntegerEvent(String event, int lineNumber){
		Integer eventValue = null;
		try {
			eventValue = Integer.parseInt(event);
		} catch (NumberFormatException e) {
			System.out.println("FAILURE => WRONG INPUT (LINE " + lineNumber + ")");
		}
		return eventValue;
	}
	
	public static boolean validSum(int totalSum, int n, int lineNumber) {
		int calculatedSum = (n *(n+1)) / 2;
		if(totalSum!=calculatedSum) {
			System.out.println("FAILURE => RECEIVED: "+n+", EXPECTED: "+n);
			return false;
		}
		return true;
	}
	

}
