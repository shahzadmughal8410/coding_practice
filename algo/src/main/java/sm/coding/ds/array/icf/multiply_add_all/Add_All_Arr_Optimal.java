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
public class Add_All_Arr_Optimal {

	/**
Create a data structure that will provide following operations in constant time
add(value) // add value at the end of list
get(index) // get value at index
addToAll(number) // add number for all numbers in array

	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ListWithAdd_All list = new ListWithAdd_All();
		System.out.println("Adding 2 in list");
		list.add(2); 
		printList(list);
		
		list.addToAll(2);
		System.out.println("Added 2 for all elements");
		printList(list);

		System.out.println("Adding 3 in list");
		list.add(3);
		printList(list);
		
		list.addToAll(3);
		System.out.println("Added 3 for all elements");
		printList(list);

		System.out.println("Adding 4 in list");
		list.add(4);
		printList(list);
		list.addToAll(4);
		System.out.println("Added 4 for all elements");
		printList(list);

		System.out.println("Adding 5 in list");
		list.add(5);
		printList(list);
		list.addToAll(-3);
		System.out.println("Added -3 for all elements");
		printList(list);

		System.out.println("Adding 6 in list");
		list.add(6);
		printList(list);

	}

	public static void printList(ListWithAdd_All list) {
		System.out.print("List --> [");
		String prefix = "";
		for(int i =0;i<list.values.size();i++) {
			System.out.print(prefix+list.get(i));
			prefix =",";
		}
		System.out.println("]");
	}

}


class ListWithAdd_All {
	List<Integer> values = new ArrayList<>();
	
	int addFactor = 0;
	
	public void add(int value) {
		values.add(value - addFactor);
	}
	
	public int get(int index) {
		return values.get(index)+addFactor;
	} 
	
	public void addToAll(int number) {
		addFactor+=number;
	}
	
}
