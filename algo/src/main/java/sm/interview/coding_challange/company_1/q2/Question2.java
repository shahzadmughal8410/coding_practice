/**
 * 
 */
package sm.interview.coding_challange.company_1.q2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * @author shahzad
 *
 */
public class Question2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Working");
		List<List<Integer>> foregroundAppList = new ArrayList<>();
		
		List<Integer> l1 = new ArrayList<>();
		l1.add(1);
		l1.add(2);
		foregroundAppList.add(l1);

		List<Integer> l2 = new ArrayList<>();
		l2.add(2);
		l2.add(4);
		foregroundAppList.add(l2);

		List<Integer> l3 = new ArrayList<>();
		l3.add(3);
		l3.add(3);
		foregroundAppList.add(l3);

		List<List<Integer>> backgroundAppList = new ArrayList<>();
		List<Integer> l4 = new ArrayList<>();
		l4.add(1);
		l4.add(2);
		backgroundAppList.add(l4);

		
		List<List<Integer>> result = optimalUtilization(7, foregroundAppList, backgroundAppList);
		result.forEach(r->System.out.println(r));

	}

	static List<List<Integer>> optimalUtilization(int deviceCapacity, List<List<Integer>> foregroundAppList,
			List<List<Integer>> backgroundAppList) {
		// WRITE YOUR CODE HERE
		List<List<Integer>> result = new ArrayList<>();
		List<AppPair> eligibleAppPairs = new ArrayList<>();
		for(List<Integer> fgApp: foregroundAppList) {
			for(List<Integer> bgApp: backgroundAppList) {
				if(fgApp.get(1)+bgApp.get(1) <= deviceCapacity) {
					AppPair pair = new AppPair();
					pair.fgApp = fgApp.get(0);
					pair.bgApp = bgApp.get(0);
					pair.totlaMemory = fgApp.get(1)+bgApp.get(1);
					eligibleAppPairs.add(pair);					
				}
			}
		}
		
		Collections.sort(eligibleAppPairs);
		AppPair eligibleappPair = eligibleAppPairs.size()>0 ? eligibleAppPairs.get(0) : null;
		if(null!= eligibleappPair) {
			List<Integer> appPair = new ArrayList<>();
			appPair.add(eligibleappPair.fgApp);
			appPair.add(eligibleappPair.bgApp);
			result.add(appPair);
			for(int i =1; i<eligibleAppPairs.size(); i++) {
				AppPair currentPair = eligibleAppPairs.get(i);
				if(currentPair.totlaMemory == eligibleappPair.totlaMemory) {
					appPair = new ArrayList<>();
					appPair.add(currentPair.fgApp);
					appPair.add(currentPair.bgApp);
					result.add(appPair);
				}else {
					break;
				}
			}
		}				
		return result;
	}

}

class AppPair implements Comparable<AppPair>{
	
	int bgApp;
	int fgApp;
	int totlaMemory;
	@Override
	public int compareTo(AppPair o) {
		//descending order
        if (this.totlaMemory < o.totlaMemory) return 1;
        if (this.totlaMemory> o.totlaMemory) return -1;
        return 0;
    }
	
}