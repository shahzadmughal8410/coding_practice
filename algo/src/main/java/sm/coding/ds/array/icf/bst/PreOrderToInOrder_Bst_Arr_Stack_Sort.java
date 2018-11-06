/**
 * 
 */
package sm.coding.ds.array.icf.bst;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class PreOrderToInOrder_Bst_Arr_Stack_Sort {

	/**
	 * 
Given a pre-order array of BST, print in-order sequence

Given   8, 3, 1, 6, 4, 7, 10, 14, 13
Return 1, 3, 4, 6, 7, 8, 10 ,13, 14
 
http://www.cs.armstrong.edu/liang/animation/web/BST.html
 
	 * @param args
	 */
	public static void printPreOrderToInOrder(int[] arr) {
		Deque<Integer> stack = new ArrayDeque<>();
		
		for(int i = 0; i<arr.length;i++) {
			// push in to stack if stack is empty or current element is less then the 
			// stack top element
			if(stack.isEmpty() || stack.peek()>arr[i]) {
				stack.push(arr[i]);
			}else {
				// if current element greater than the stock top
				// pop and print till the above condition satisfies or stack is empty 
				while(!stack.isEmpty() && stack.peek()<arr[i]) {
					System.out.print(stack.pop()+" ");
				}
				// now current element is less than the stack top
				// or stack is empty
				stack.push(arr[i]);
			}
		}
		// if stack is not empty means left over elements are all in 
		// decreasing order, i.e. stack top is minimum and bottom is maximum
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		int[] preOrder = new int[] {8, 3, 1, 6, 4, 7, 10, 14, 13};
		System.out.print("PreOrder = ");
		Arrays.stream(preOrder).boxed().collect(Collectors.toList()).forEach(i->System.out.print(i+" "));
		System.out.println();
		System.out.print(" InOrder = ");
		printPreOrderToInOrder(preOrder);

		preOrder = new int[] {4, 3, 1, 2, 5, 9, 7, 6, 8 };
		System.out.print("PreOrder = ");
		Arrays.stream(preOrder).boxed().collect(Collectors.toList()).forEach(i->System.out.print(i+" "));
		System.out.println();
		System.out.print(" InOrder = ");
		printPreOrderToInOrder(preOrder);

		preOrder = new int[] {1, 0, 2, 3, 4, 5, 6, 7, 8, 9};
		System.out.print("PreOrder = ");
		Arrays.stream(preOrder).boxed().collect(Collectors.toList()).forEach(i->System.out.print(i+" "));
		System.out.println();
		System.out.print(" InOrder = ");
		printPreOrderToInOrder(preOrder);

	}

}

class SolutionBruteforce{
	/**
	 * (n log n) 
	 * @param arr
	 * @return
	 */
	public static int[] preOrderToInOrder_BF(int[] arr) {
		sort_BF(arr, 0, arr.length-1);
		return arr;
	}
	

	public static void merge_BF(int[] arr, int l, int m, int r) {
		int s1 = m-l+1;
		int s2 = r-m;
		
		int left[] = new int[s1];
		int right[] = new int[s2];
		
		for(int i=0; i<s1;i++) {
			left[i] = arr[l+i];
		}
		for(int i=0; i<s2;i++) {
			right[i] = arr[m+1+i];
		}
		
		int i =0;
		int j = 0;
		int k = l;
		while(i<s1 && j<s2) {
			if(left[i]<=right[j]) {
				arr[k++] = left[i++]; 
			}else {
				arr[k++] = right[j++];
			}
		}
		
		while(i<s1) {
			arr[k++] = left[i++];
		}
		while(j<s2) {
			arr[k++] = right[j++];
		}
	}
	
	public static void sort_BF(int[]arr , int l, int r) {
		if(l<r) {
			int m = (l+r)/2;
			
			sort_BF(arr, l,m);
			sort_BF(arr, m+1,r);
			
			merge_BF(arr, l, m , r);
		}
	}
	
}
