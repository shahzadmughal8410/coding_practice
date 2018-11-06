/**
 * 
 */
package sm.coding.algo.practice.recursion.stanford;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smughal
 *
 */
public class Sublist_PowerSet_Recr_BckTrk {

	static int counter = 0 ;
	
	/**
	 * T=O(2^n)
	 * S=O(n)
	 * 
	 * @param input
	 * @param choosen
	 */
	public static void sublist(List<String> input, List<String> choosen) {
		if(input.size()==0) {
			System.out.println(choosen);
			++counter;
		}else {
			String ch = input.remove(0); // choose
			
			// explore
			choosen.add(ch);
			sublist(input, choosen);
			
			choosen.remove(choosen.size()-1);
			sublist(input, choosen);
			
			//backtrack
			input.add(0, ch);
		}
	}

	
	public static void sublistWithReturn(List<String> input, List<String> choosen, List<List<String>> result) {
		if(input.size()==0) {
			System.out.println(choosen);
			result.add(new ArrayList<>(choosen));
			++counter;
		}else {
			String ch = input.remove(0); // choose
			
			// explore
			choosen.add(ch);
			sublistWithReturn(input, choosen, result);
			
			choosen.remove(choosen.size()-1);
			sublistWithReturn(input, choosen, result);
			
			//backtrack
			input.add(0, ch);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<List<String>> result = new ArrayList<>();
		List<String> input = new ArrayList<>();
		input.add("Jack");
		input.add("Jill");
		input.add("Jane");
		input.add("John");
		
		sublistWithReturn(input, new ArrayList<String>(), result);
		System.out.println(counter);
		System.out.println(result.size()+", "+result);
	}

}
