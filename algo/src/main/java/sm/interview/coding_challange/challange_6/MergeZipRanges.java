package sm.interview.coding_challange.challange_6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class MergeZipRanges {

	/**
BACKGROUND
Sometimes items cannot be shipped to certain zip codes, and the rules for these restrictions are stored as a series of ranges of 5 digit codes. For example if the ranges are:

[94133,94133] [94200,94299] [94600,94699]

Then the item can be shipped to zip code 94199, 94300, and 65532, but cannot be shipped to 94133, 94650, 94230, 94600, or 94299.

Any item might be restricted based on multiple sets of these ranges obtained from multiple sources.

PROBLEM
Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds), provide an algorithm that produces the minimum number of ranges required to represent the same restrictions as the input.

NOTES
- The ranges above are just examples, your implementation should work for any set of arbitrary ranges
- Ranges may be provided in arbitrary order
- Ranges may or may not overlap
- Your solution will be evaluated on the correctness and the approach taken, and adherence to coding standards and best practices

EXAMPLES:
If the input = [94133,94133] [94200,94299] [94600,94699]
Then the output should be = [94133,94133] [94200,94299] [94600,94699]

If the input = [94133,94133] [94200,94299] [94226,94399] 
Then the output should be = [94133,94133] [94200,94399]

Evaluation Guidelines:
Your work will be evaluated against the following criteria:
- Successful implementation
- Efficiency of the implementation
- Design choices and overall code organization
- Code quality and best practices
	 */
	
	public static List<Range> mergeZipCodeRanges(List<Range> ranges){
		if(null==ranges || ranges.size()<1) {
			return ranges;
		}
		List<Range> result = new LinkedList<>();
		// sort ranges by starting value
		Collections.sort(ranges, (a,b) -> Integer.compare(a.start, b.start));
		
		Range previous = ranges.get(0);
		
		for(int i=1; i<ranges.size();i++) {
			Range current = ranges.get(i);
			if(previous.end>=current.start) { // overlapping range update the end value
				previous.end = Math.max(previous.end, current.end);
			}else {// not overlapping range
				result.add(previous);
				previous = current;
			}
		}
		result.add(previous);
		return result;
	}
	
	public static void main(String[] args) {
		List<Range> intervals = new ArrayList<>(); 
		intervals.add(new Range(94133,94133));
		intervals.add(new Range(94200,94299));
		intervals.add(new Range(94600,94699));
		System.out.println("Rages total="+intervals.size());
		System.out.println("Rages="+intervals);
		List<Range> result = mergeZipCodeRanges(intervals);
		System.out.println("Merged Rages Total="+result.size());
		System.out.println("Merged Rages="+result);

		intervals = new ArrayList<>(); 
		intervals.add(new Range(94133,94133));
		intervals.add(new Range(94200,94299));
		intervals.add(new Range(94226,94399));
		
		System.out.println("Rages total="+intervals.size());
		System.out.println("Rages="+intervals);
		result = mergeZipCodeRanges(intervals);
		System.out.println("Merged Rages Total="+result.size());
		System.out.println("Merged Rages="+result);
	}
}


class Range {
	int start;
	int end;
	
	public Range(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		return "[" + start + "," + end + "]";
	}
}