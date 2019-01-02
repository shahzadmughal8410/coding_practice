/**
 * 
 */
package sm.coding.ds.llstq.leetcode.linked_list._146.lru.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * Least Recently Used (LRU) cache
 * 
 * https://leetcode.com/submissions/detail/157256941/
 * https://www.programcreek.com/2013/03/leetcode-lru-cache-java/

Clarifying questions:
Input, output
API signature
Time complexity - O(1)



Submission
https://leetcode.com/submissions/detail/198581233/
You are here! 
Your runtime beats 14.96 % of java submissions.
 * @author shahzadmughal8410
 *
 */
public class LRUCache_146 {
    int capacity;
    Map<Integer, Node> map = new HashMap<Integer, Node>();
    Node head=null;
    Node tail=null;
 
    public LRUCache_146(int capacity) {
        this.capacity = capacity;
    }
 
    /**
     * To return value from LRU
     * 1- if key exists, move this to top of list (remove,makeHead) and retunr value
     * 2- key is not there return -1
     * 
     * @param key
     * @return
     */
    public int get(int key) {
        if(map.containsKey(key)){
            Node n = map.get(key);
            remove(n);
            makeHead(n);
            return n.value;
        }
 
        return -1;
    }
 
    /**
     * To remove the node from double linked list
     * 1- handle previous pinter of the node
     * 2- handle next pointer of node
     * 
     * @param n
     */
    private void remove(Node n){    		
        if(n.previous!=null){ // not being removed is not head 
            n.previous.next = n.next;
        }else{ // means node being removed is head
            head = n.next;
        }
 
        if(n.next!=null){// not being removed is not tail
            n.next.previous = n.previous;
        }else{// means node being removed is tail
            tail = n.previous;
        }
 
    }
 
    /**
     * To make head
     * 1- Make input node pointers as head node
     * 2- if head not null then make input as head previous
     * 3- update head pointer to input node
     * 4- if end is null point it to head; 
     * 
     * @param n
     */   
    private void makeHead(Node n){
        n.next = head;
        n.previous = null; // as we call make head for existing nodes, so need to set previous as null
 
        if(head!=null) {
            head.previous = n;
        }
 
        head = n;
 
        if(tail ==null) {
            tail = head;
        }
    }
 
    /**
     * TO insert in LRU
     * 1- if key is already there bring it to top of list
     * 2- when key is not there, ensure capacity and create new node and bring it to top of list
     * 3- if we have the capacity just makeHead.
     * 4- put into the ma to reflect new value in both cases
     * 
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node old = map.get(key);
            old.value = value;
            remove(old);
            makeHead(old);
        }else{
            Node created = new Node(key, value);
            if(map.size()>=capacity){
                map.remove(tail.key);
                remove(tail);
            }    
            makeHead(created);
            map.put(key, created);
        }
    }
}
/**
 * Doubly Linked list.
 * 
 * @author smughal
 *
 */
class Node{
	public Integer key;
	public Integer value;
	public Node next;
	public Node previous;
	
	public Node(Integer key, Integer value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Node [key=" + key + ", value=" + value + "]";
	}
}
