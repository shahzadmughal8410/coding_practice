package sm.coding.algo.practice.recursion.stanford;


public class StringPermute {

	private static void permutation(String input, String soFar, int indent) {
		indent(indent);
		if (input.length() == 0) {
			System.out.println(String.format("permutation('%s', '%s')", input, soFar));
			System.out.println(soFar);
		}
		else {
			for(int i=0; i<input.length(); i++) {
				char ch = input.charAt(i);
				System.out.println(String.format("permutation('%s', '%s', '%s')", input, soFar, ch));
				soFar += ch;
				
				permutation(input.substring(0,i)+input.substring(i,input.length()-1), soFar, indent+1);
				
				soFar = soFar.substring(0, soFar.length()-1);
				System.out.println();
			}
		}
	}
	
	public static void indent(int spaces) {
		for(int i =0;i<spaces;i++) {
			System.out.print("\t");
		}
	}
	
	public static void main(String[] args) {
		permutation("abc", "", 0);
	}

}
