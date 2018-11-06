/**
 * 
 */
package sm.interview.coding_challange.company_1.q1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author shahzad
 *
 */
public class Qustion1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println("Working");
//		Distination d1 = new Distination();
//		d1.distance = 10.11;
//
//		Distination d2 = new Distination();
//		d2.distance = 15.11;
//
//		Distination d3 = new Distination();
//		d3.distance = 4.6;
//
//		Set<Distination> set = new TreeSet<>();
//		set.add(d2);
//		set.add(d1);
//		set.add(d3);
//		
//		for(Distination d:set) {
//			System.out.println(d.distance);
//		}
		
		
		List<List<Integer>> allLocations = new ArrayList<>();
		
		List<Integer> l1 = new ArrayList<>();
		l1.add(1);
		l1.add(2);
		allLocations.add(l1);

		List<Integer> l2 = new ArrayList<>();
		l2.add(3);
		l2.add(4);
		allLocations.add(l2);

		List<Integer> l3 = new ArrayList<>();
		l3.add(1);
		l3.add(-1);
		allLocations.add(l3);

		List<Integer> l4 = new ArrayList<>();
		l4.add(3);
		l4.add(4);
		allLocations.add(l4);

		List<List<Integer>> result = ClosestXdestinations(3, allLocations, 2);
		
		result.forEach(r->System.out.println(r));
		

	}
	

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    static List<List<Integer>> ClosestXdestinations(int numDestinations, 
                                             List<List<Integer>> allLocations,
                                             int numDeliveries)
	{
    		List<List<Integer>> result = new ArrayList<>();
    		
    		if(numDeliveries>numDestinations) {
    			return result;
    		}
    		
    		if(null==allLocations || allLocations.size()==0) {
    			return result;
    		}
    		Set<Distination> nearestDistinations = new TreeSet<>();
    		
        for(List<Integer> dis: allLocations) {
        		Distination d = new Distination();
        		d.location = dis;
        		int x = d.location.get(0);
        		int y = d.location.get(1);
        		d.distance = Math.sqrt( Math.pow(x,2) + Math.pow(y, 2) );
        		nearestDistinations.add(d);
        		
        }
        
        for(Distination dist: nearestDistinations) {
        		result.add(dist.location);
        		if(result.size()==numDeliveries) {
        			break;
        		}
        }
        return result;
    }
	

}

class Distination implements Comparable<Distination>{
    
    List<Integer> location;
    double distance;
	
    @Override
	public int compareTo(Distination o) {
        if (this.distance < o.distance) return -1;
        if (this.distance > o.distance) return 1;
        return 0;
    }
    
}
