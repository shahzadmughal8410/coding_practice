package sm.coding.ds.array.icf.recr;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ArrayPermute {

	/**

	  Template - ICF TIPS
	  
	  printPerm(Set s, Set perm){
	  	if(s.isEmpty()){
	  		print(perm)
	  	}
	  	
	  	for(elem e:s){
	  		s.remove(e);
	  		perm.append(e);
	  		printPerm(s, perm);
	  		s.add(e);			// backtracking
	  		perm.removeLast(e); 	// backtracking
	  	}
	  }
	  
	  
	 * @param arr
	 */
	
	static void printPerm(int[] arr) {		
		printPermHelper(arr, 0) ;
	}
	
	static void printPermHelper(int[] arr, int i) {
		if(i==arr.length) {
			System.out.println(Arrays.stream(arr).boxed().collect(Collectors.toList()));
			return;
		}
		
		for(int j = i; j< arr.length; j++) {
			swap(arr, i, j) ;
			printPermHelper(arr, i+1);
			swap(arr, i, j);
		}
	}
	
	static void swap(int arr[], int i , int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main(String[] args) {
		printPerm(new int[] {1,2,3});
	}
}
