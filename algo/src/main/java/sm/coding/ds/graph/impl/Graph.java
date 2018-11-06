/**
 * 
 */
package sm.coding.ds.graph.impl;

import java.util.List;

/**
 * @author shahzadmughal8410
 *
 */
public interface Graph {

    void addEdge(int v1, int v2);

    List<Integer> getAdjacentVertices(int v);
}
