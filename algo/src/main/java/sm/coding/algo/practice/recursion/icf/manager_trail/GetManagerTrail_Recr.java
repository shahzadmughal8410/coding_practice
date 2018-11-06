/**
 * 
 */
package sm.coding.algo.practice.recursion.icf.manager_trail;

import java.util.regex.Pattern;

/**
 * @author shahzadmughal8410
 *
 */
public class GetManagerTrail_Recr {

	/**
	 * static String getManagerTrail(Employee e);
Employee jack = new Employee("Jack", 123, null);
Employee jill = new Employee("Jill", 256, jack);
Employee jim = new Employee("Jim", 3, jill);
Employee jane = new Employee("Jane", 256, jim);

//It should return manager org
System.out.println(getManagerTrial(jane));
Jane -> Jim -> Jill -> Jack

	 */
	public static String getManagerTrial(Employee e) {
		if(e==null) {
			return "";
		}		
		String seperator = e.getManager()!=null ? " -> " :"";		
		return e.getName()+seperator +getManagerTrial(e.getManager());
	}
	
	public static String getManagerTrial_Iterative(Employee e) {
		String result = "";		
		while(null!=e) {
			String seperator = e.getManager()!=null ? " -> " : "";
			result += e.getName() +seperator;
			e = e.getManager();
		}		
		return result;		
	}
	
	public static void main(String[] args) {
		Employee jack = new Employee("Jack", 123, null);
		Employee jill = new Employee("Jill", 256, jack);
		Employee jim = new Employee("Jim", 3, jill);
		Employee jane = new Employee("Jane", 256, jim);

		//It should return manager org
		System.out.println("Recursive = "+SolutionDebug.getManagerTrial(jane));
		System.out.println("Iterative = "+getManagerTrial_Iterative(jane));

	}
}


class Employee {
	int id;
	String name;
	Employee manager;
	public Employee(String name, int id, Employee manager) {
		this.id = id;
		this.name = name;
		this.manager = manager;
	}
	public Employee getManager() {
		return this.manager;
	}
	public String getName() {
		return this.name;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}
	
	
}


class SolutionDebug {

	static StringBuilder format = new StringBuilder();
	static String [] columns = new String[] {};
	public static void tableColumns(String ... cols) {
		for(int i = 0; i<cols.length;i++) {
			String c = cols[i];
			if(c.indexOf(":")!=-1) {
				String[] arr = c.split(Pattern.quote(":"));
				format.append("|%-").append(arr[1]).append("s ");
				cols[i] = arr[0];
			}else {
				format.append("|%-").append(c.length()).append("s ");
			}
			
		}
		format.append("|");
//		debugRow(cols);
		columns = cols;
	}
	
	public static void debugRow(Object ... cols) {
		debug(String.format(format.toString(), cols));
	}

	public static void debug(Object msg) {
		System.out.println("DEBUG: "+msg);
	}

	public static void debugColumns() {
		debugRow(columns);
	}
	
	public static void reset() {
		format = new StringBuilder();
		columns = new String[] {};
	}

	public static String indent = "|---";
	public static void debugRecr(Object msg) {
		System.out.println("DEBUG "+indent+">"+msg);
	}
	public static String incrementIndent() {
		String indentActual = indent;
		indent = indent+"|---";
		return indentActual;
	}
	public static void setIndent(String newIndent) {
		indent = newIndent;
	}
	
	public static String getManagerTrial(Employee e) {
		debugRecr(e);
		incrementIndent();
		if(e==null) {
			return "";
		}		
		String seperator = e.getManager()!=null ? " -> " :"";		
		return e.getName()+seperator +getManagerTrial(e.getManager());
	}
}