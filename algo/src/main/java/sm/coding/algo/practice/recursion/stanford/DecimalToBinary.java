package sm.coding.algo.practice.recursion.stanford;


public class DecimalToBinary {

	public static void main(String[] args) {
//		System.out.print("pr=");
//		printBin(23);
//		System.out.println();
//		System.out.println("it="+Integer.toBinaryString(23));
//		System.out.println("st="+toBin(23));
		System.out.println();
		System.out.println("sb="+toBinStringBuilder(23));
//		System.out.println("toBinStringBuilder(23));
		
		
	}
	
	public static void printBin(int n ) {
		if(n>0) {
			int remaining = n/2;
			int reminder = n%2; // current binary number
			printBin(remaining);
			System.out.print(reminder);
			
		}
	}

	public static String toBinStringBuilder(int n) {
		return toBinStringBuilder(n, new StringBuilder());
	}
		public static String toBinStringBuilder(int n, StringBuilder sb) {
		if(n>0) {
			int remaining = n/2;
			int reminder = n%2; // current binary number
			sb.append(reminder);
			toBinStringBuilder(remaining, sb);
			
		}
		return sb.toString();
	}

	
	public static String toBin(int n) {
		return toBinHelper(n,"");
	}

	private static String toBinHelper(int n , String sofar) {
		if(n==0) {
			return sofar;
		}else {
			int remaining = n/2;
			int reminder = n%2; // current binary number
			return toBinHelper(remaining, reminder+sofar);
		}
	}

}
