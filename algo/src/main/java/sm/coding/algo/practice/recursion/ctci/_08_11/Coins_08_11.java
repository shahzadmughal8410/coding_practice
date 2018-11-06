/**
 * 
 */
package sm.coding.algo.practice.recursion.ctci._08_11;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smughal
 *
 */
public class Coins_08_11 {

	/**
Coins: Given an innnite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and
pennies (1 cent), write code to calculate the number of ways of representing n cents.
Hints: #300, #324, #343, #380, #394
	 * @param args
	 */
	public static List<List<Integer>> representCoins(int targetAmount, List<Integer> coins){
		List<List<Integer>> result = new ArrayList<>();
		representCoinsHelper(targetAmount, new ArrayList<>(), coins, result, 0);
		return result;
	}
	
	public static void representCoinsHelper(int n, List<Integer> sofar, List<Integer> coins, List<List<Integer>> result, int index) {
		
		if(n==0) {
			result.add(new ArrayList<>(sofar));
			return;
		}
		
		for(int i=index; i<coins.size(); i++) {
			int choosen = coins.get(i);
			if(choosen<=n) {
				sofar.add(choosen);
				representCoinsHelper(n-choosen, sofar, coins, result, i);
				sofar.remove(sofar.size()-1);
			}
		}
		
	}
	
	public static void main(String[] args) {
		List<Integer> coins = new ArrayList<>();
		coins.add(25);
		coins.add(10);
		coins.add(5);
		coins.add(1);
		
		List<List<Integer>> result = representCoins(100, coins);
		result.forEach(r->System.out.println(r));
		System.out.println("Total ways="+result.size());
	}

}
