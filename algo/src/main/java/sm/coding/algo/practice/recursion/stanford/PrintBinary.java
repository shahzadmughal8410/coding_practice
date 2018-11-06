package sm.coding.algo.practice.recursion.stanford;

import java.util.ArrayList;
import java.util.List;

//import util.Utils;

/**
 * 
 * T=O(2^n)
 * S=O(n)
 *
 */
public class PrintBinary {

	public static void main(String[] args) {
//		int num = 4;
//		printBinary(num);
//		System.out.println(printBinaryList(num));
		
		roll(2);
		
	}

	public static List<String> printBinaryList(int num) {
		List<String> result = new ArrayList<>();
		printBinaryList(num, "", result);
		return result;
	}
	
	private static void printBinaryList(int num, String soFar, List<String> list) {
		if (num == 0) {
			list.add(soFar);
			return;
		}
		else {
			printBinaryList(num-1, soFar+"0", list);
			printBinaryList(num-1, soFar+"1", list);
		}
	}
	
	public static void printBinary(int num) {
		printBinary(num, "");
	}
	
	private static void printBinary(int num, String soFar) {
		if (num == 0) {
			System.out.println(soFar);
			return;
		}
		else {
			printBinary(num-1, soFar+"0");
			printBinary(num-1, soFar+"1");
		}
	}
	
	public static void roll(int dice) {
		roll(dice, new ArrayList<>(), 0);
	}

	public static void roll(int dice, List<Integer> choosen, int indent) {
//		Utils.indent(indent);
		System.out.println("roll('"+dice+"', '"+choosen+"')");
		if(dice==0) {
			System.out.println(choosen);
		}else {
			for(int i =1;i<=6;i++) {
				choosen.add(i);
				roll(dice-1, choosen, indent+1);
				choosen.remove(choosen.size()-1);
			}
		}
	}

}
