/**
 * 
 */
package sm.coding.ds.graph.leetcode._909;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class SnakesAndLadders_909_BFS {

	
    public static int snakesAndLadders(int[][] board) {
        int N = board.length;

        // visited node with its minimum distance
        Map<Integer, Integer> visitedDist = new HashMap<>();
        visitedDist.put(1, 0);

        Deque<Integer> queue = new LinkedList<>();
        queue.add(1);

        while (!queue.isEmpty()) {
            int s = queue.remove();
            if (s == N*N) return visitedDist.get(s);

            for (int s2 = s+1; s2 <= Math.min(s+6, N*N); ++s2) {
                int rc[] = get(s2, N);
                int r = rc[0];
                int c = rc[1];                
                int s2Final = board[r][c] == -1 ? s2 : board[r][c];
                if (!visitedDist.containsKey(s2Final)) {
                    visitedDist.put(s2Final, visitedDist.get(s) + 1); // minimum distance to reach this square
                    queue.add(s2Final);
                }
            }
        }
        return -1;
    }

    public static int[] get(int s, int N) {
        // Given a square num s, return board coordinates (r, c) 
        int quot = (s-1) / N;
        int rem = (s-1) % N;
        int row = N - 1 - quot;
        int col = row % 2 != N % 2 ? rem : N - 1 - rem;
        return new int[] {row, col};
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] board = new int[][] {
						{-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1},
						{-1,35,-1,-1,13,-1},
						{-1,-1,-1,-1,-1,-1},
						{-1,15,-1,-1,-1,-1}};
						
		System.out.println("Minimum moves ="+SolutionDebug.snakesAndLadders(board));
		System.out.println("Minimum moves ="+snakesAndLadders(board));

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

	public static String grid(int grid[][]) {
		return grid(grid, null, null, "-", "-");
	}

	public static String grid(int grid[][], List rowHeader, List colHeader, String rowHeading, String colHeading) {
		int padding = 5;
		StringBuilder output = new StringBuilder("\n");
		// output index of columns
		output.append("index");
		output.append( String.format(" | %1$-"+Math.max(padding, rowHeading.length()+colHeading.length())+"s ", "-") );
		int underline = 0;
		for(int i =0; i<grid[0].length; i++) {
			output.append( String.format(" | %1$-"+padding+"s ", i) );
		}
		output.append("|\n");
		underline = output.length();
		
		if(null!=colHeader) {
			output.append("-");
			output.append( String.format("     | %1$-"+Math.max(padding, rowHeading.length()+colHeading.length())+"s ", rowHeading+colHeading) );
			for(int i =0; i<grid[0].length; i++) {
				output.append( String.format(" | %1$-"+padding+"s ", colHeader.get(i)) );
			}
		}

		output.append("|\n");
		IntStream.range(0, underline).forEach(i->output.append("-"));
		output.append("\n");
		
		for(int r=0; r<grid.length; r++) {
			// output index of rows
			output.append( r+ String.format("     | %1$-"+Math.max(padding, rowHeading.length()+colHeading.length())+"s ", (rowHeader!=null ? rowHeader.get(r) : "" ) )  );
			
			// grid data
			for(int c=0; c<grid[r].length; c++) {
				output.append( String.format(" | %1$-"+padding+"s ", grid[r][c]) );
			}
			output.append("|\n");
		}
		return output.toString();
	}
	
	public static List asList(Object [] list, int padding, Object value) {
		List l = new ArrayList<>();
		for(int i=0; i<padding; i++) {
			l.add(value);
		}
		l.addAll(Arrays.asList(list));
		return l;
	}

    public static int snakesAndLadders(int[][] board) {
    		reset();
    		debug("Board\n"+grid(board));
        int N = board.length;
        debug("N="+N);

        Map<Integer, Integer> dist = new HashMap<>();
        dist.put(1, 0);

        Deque<Integer> queue = new LinkedList<>();
        queue.add(1);

        tableColumns("s:3", "s2:3", "r:3", "c:3", "s2Final", "dist.containsKey(s2Final)", "key,val", "count");
        debugColumns();
        int count = 0;
        while (!queue.isEmpty()) {
            int s = queue.remove();
            if (s == N*N) {
            		debug("Destination reached s="+s);
            		return dist.get(s);
            }

            for (int s2 = s+1; s2 <= Math.min(s+6, N*N); ++s2) {
                int rc[] = get(s2, N);
                int r = rc[0];
                int c = rc[1];                
                int s2Final = board[r][c] == -1 ? s2 : board[r][c];
                debugRow(s, s2, r, c, s2Final, dist.containsKey(s2Final), !dist.containsKey(s2Final) ? s2Final+","+(dist.get(s) + 1) : "-", count);
                if (!dist.containsKey(s2Final)) {
                    dist.put(s2Final, dist.get(s) + 1);
                    queue.add(s2Final);
                }
            }
        }
        debug("Unable to reach distination.");
        return -1;
    }

    public static int[] get(int s, int N) {
        // Given a square num s, return board coordinates (r, c) 
        int quot = (s-1) / N;
        int rem = (s-1) % N;
        int row = N - 1 - quot;
        int col = row % 2 != N % 2 ? rem : N - 1 - rem;
        return new int[] {row, col};
    }
}

