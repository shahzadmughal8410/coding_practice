package sm.coding.ds.llstq.leetcode.linked_list._146;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheUsingLinkedHashMap {

	Map<Integer, Integer> cache;

	public LRUCacheUsingLinkedHashMap(int capacity) {
		cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {

			private static final long serialVersionUID = 6722942663955651876L;

			@Override
			public boolean removeEldestEntry(Map.Entry<Integer, Integer> entry) {
				return this.size() > capacity;
			}
		};
	}

	public void put(int key, int value) {
		cache.put(key, value);
	}

	public int get(int key) {
		return cache.getOrDefault(key, -1);
	}

	public static void main(String[] args) {
		LRUCacheUsingLinkedHashMap cache = new LRUCacheUsingLinkedHashMap(2);
		
		cache.put(1, 1);
		cache.put(2, 2);
		
		System.out.println(cache.get(1));       // returns 1
		
		cache.put(3, 3);    						// evicts key 2
		
		System.out.println(cache.get(2));       // returns -1 (not found)
		
		cache.put(4, 4);    						// evicts key 1
		System.out.println(cache.get(1));       // returns -1 (not found)
		System.out.println(cache.get(3));       // returns 3
		System.out.println(cache.get(4));       // returns 4
	}

}
