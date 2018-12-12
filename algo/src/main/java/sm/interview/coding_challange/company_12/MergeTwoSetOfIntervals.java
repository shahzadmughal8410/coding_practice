package sm.interview.coding_challange.company_12;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoSetOfIntervals {
    public static void main(String args[] ) throws Exception {
    	
    	/*
For example:
1st set: [0, 2], [5, 10], [16, 20]
2nd set: [1, 5], [10, 14], [20, 23]

OR Result: [0, 14], [16, 23]
    	 */
        List<Interval> l1 = new ArrayList<>(); //[0, 2], [5, 10], [16, 20]
        l1.add(new Interval(0,2));
        l1.add(new Interval(5,10));
        l1.add(new Interval(16,20));
        
        List<Interval> l2 = new ArrayList<>(); //[1, 5], [10, 15], [20, 23]
        l2.add(new Interval(1,5));
        l2.add(new Interval(10,15));
        l2.add(new Interval(20,23));


        List<Interval> result = doOr(l1,l2);
        result.forEach(r->System.out.print("["+r.start+","+r.end+"], ")); //[0, 14], [16, 23]
        System.out.println();

        l1 = new ArrayList<>(); //[0, 2], [5, 10], [16, 20]
        l1.add(new Interval(16,20));
        
        l2 = new ArrayList<>(); //[1, 5], [10, 14], [20, 23]
        l2.add(new Interval(1,5));

        result = doOr(l1,l2);
        result.forEach(r->System.out.print("["+r.start+","+r.end+"], ")); //[0, 14], [16, 23]
        System.out.println();
    }
    
    public static List<Interval> doOr(List<Interval> l1, List<Interval> l2){
        
        if(null== l1 || l1.size()==0){
            return l2;
        }
        if(null== l2 || l2.size()==0){
            return l1;
        }
        
        List<Interval> result = new ArrayList<>();
        
        int i = 0;
        int j = 0;
        Interval previous = l1.get(0).start<=l2.get(0).start ? l1.get(i++) : l2.get(j++);
        while(i<l1.size() && j<l2.size()){
            Interval a = l1.get(i);
            Interval b = l2.get(j);
            
            Interval current = null;
            
            if(a.start <= b.start){
                current = a;
                ++i;
            }else{
                current = b;
                ++j;
            }

            if(previous.end>=current.start || (current.start-previous.end <=1)){
                previous.end = Math.max(previous.end,current.end); 
            }else{
                result.add(previous);
                previous = current;
            }
        }
        
        while(i<l1.size()){
            Interval current = null;
            current = l1.get(i++);
            if(previous.end>=current.start){
                previous.end = Math.max(previous.end,current.end);
            }else{
                result.add(previous);
                previous = current;
            }
        }
        
        while(j<l2.size()){
            Interval current = null;
            current = l2.get(j++);
            if(previous.end>=current.start){
                previous.end = Math.max(previous.end,current.end);
            }else{
                result.add(previous);
                previous = current;
            }
        }
        
        result.add(previous);
        
        return result;
    }
    
    
}

class Interval {
    int start;
    int end;
    
    public Interval(int start, int end){
        this.start = start;
        this.end = end;
    }
    
}