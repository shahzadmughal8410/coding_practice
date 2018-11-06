/**
 * 
 */
package sm.coding.ds.array.icf.multiply_add_all;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shahzadmughal8410
 *
 */
public class Multiply_All_Arr {

	/**
Create a data structure that will provide following operations in constant time
add(value) // add value at the end of list
get(index) // get value at index
multiplyToAll(number) // multiply all numbers in array with number

	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ListWithMultiplyAll list = new ListWithMultiplyAll();
		System.out.println("Adding 2 in list");
		list.add(2); 
		printList(list);
		
		list.multiplyToAll(2);
		System.out.println("Multiplying 2 with all elements");
		printList(list);

		System.out.println("Adding 3 in list");
		list.add(3);
		printList(list);
		
		list.multiplyToAll(3);
		System.out.println("Multiplying 3 with all elements");
		printList(list);

		System.out.println("Adding 4 in list");
		list.add(4);
		printList(list);
		list.multiplyToAll(4);
		System.out.println("Multiplying 4 with all elements");
		printList(list);

		System.out.println("Adding 5 in list");
		list.add(5);
		printList(list);
		list.multiplyToAll(-3);
		System.out.println("Multiplying -3 with all elements");
		printList(list);

		list.add(6);
		printList(list);
		list.multiplyToAll(-1);
		System.out.println("Multiplying -1 with all elements");
		printList(list);

	}

	public static void printList(ListWithMultiplyAll list) {
		System.out.print("List --> [");
		String prefix = "";
		for(int i =0;i<list.values.size();i++) {
			System.out.print(prefix+list.get(i));
			prefix =",";
		}
		System.out.println("]");
	}
}


class ListWithMultiplyAll {
	List<Integer> values = new ArrayList<>();
	Map<Integer,Integer> multiplyMap = new HashMap<>();
	
	int multiplyFactor = 1;
	
	public void add(int value) {
		multiplyMap.put(values.size(), multiplyFactor);
		values.add(value);
	}
	
	public int get(int index) {
		return values.get(index) * (multiplyFactor/multiplyMap.get(index)) ;
	} 
	
	public void multiplyToAll(int number) {
		multiplyFactor*=number;
	}
	
}
