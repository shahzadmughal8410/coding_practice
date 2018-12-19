/**
 * 
 */
package sm.interview.coding_challange.zero_game;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author shahzadmughal8410
 *
 */
public class PlayGameToFindZero {

	/**

	 */
	public static boolean playGame(int[] nums, int index){
	    if(null== nums || nums.length==0 | index<0 || index>nums.length-1){
	      return false;
	    }
	    Deque<Integer> q = new LinkedList<>();
	    Set<Integer> visited = new HashSet<>();
	    
	    q.offer(index);
	    visited.add(index);
	    
	    while (!q.isEmpty()){
//	      System.out.println("Loop, q.size="+q.size()+", q="+q);
	      int size = q.size();// 1, 1, 1
	      for(int i=0; i<size;i++){
	        int current = q.poll(); // 4
	          if(nums[current]==0){
	            return true;
	          }else {
	            int valueAsIndex = nums[current];
	            int left = current-valueAsIndex;
	            int right = current+valueAsIndex;
	            
//	            System.out.println("valueAsIndex="+valueAsIndex+", left="+left+", right="+right);
	            
	            if(left >=0 && !visited.contains(left)){
//	              System.out.println("Adding left="+left);
	              q.offer(left);
	              visited.add(left);
	            }
	            if(right<=nums.length-1 && !visited.contains(right)){
//	              System.out.println("Adding right="+right);
	              q.offer(right);
	              visited.add(right);
	            }

	          }
	      } // end for
	    } // while end
	    
	    return false;    
	  }
	
	/**
	 * @param args
	 */
	  public static void main(String[] args) {
		    int[] nums = new int[]{1, 2, 1, 7, 0, 3, 2, 5, 2};
		    int startIndex = 8;
		    boolean zeroFound = playGame(nums, startIndex);
		    System.out.println("Test 1: startIndex="+startIndex+", zeroFound="+zeroFound);

		    //    int[] nums = new int[]{1, 2, 1, 7, 0, 3, 2, 5, 2};
		    startIndex = 0;
		    zeroFound = playGame(nums, startIndex);
		    System.out.println("Test 2: startIndex="+startIndex+", zeroFound="+zeroFound);

		    startIndex = 0;
		    zeroFound = playGame(null, startIndex);
		    System.out.println("Test 3: startIndex="+startIndex+", zeroFound="+zeroFound);

		    startIndex = -1;
		    zeroFound = playGame(nums, startIndex);
		    System.out.println("Test 4: startIndex="+startIndex+", zeroFound="+zeroFound);

		    nums = new int[]{0, 1, 2, 1, 7, 0, 3, 2, 5, 2};
		    startIndex = 0;
		    zeroFound = playGame(nums, startIndex);
		    System.out.println("Test 5: startIndex="+startIndex+", zeroFound="+zeroFound);

		    startIndex = 10;
		    zeroFound = playGame(nums, startIndex);
		    System.out.println("Test 6: startIndex="+startIndex+", zeroFound="+zeroFound);

		  }
		  

}
