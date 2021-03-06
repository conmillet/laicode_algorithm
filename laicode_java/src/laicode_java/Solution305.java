package laicode_java;

import java.util.*;

//Reverse Alternate Nodes
//Given a linked list, reverse alternate nodes and append at the end.
//
//Examples:
//Input List:    1 -> 2 -> 3 -> 4 -> 5 -> 6
//Output List: 1 -> 3 -> 5 -> 6 -> 4 -> 2
//Input List:    1 -> 2 -> 3 -> 4 -> 5
//Output List: 1 -> 3 -> 5 -> 4 -> 2
public class Solution305 {
/*
 * KAltReverse(ListNode head, int k)
 * 1) Reverse first k nodes
 * 2) In the modified list head points to the kth node. So change next
 * of head to (k+1)th node
 * 3) Move the current pointer to skip next k nodes
 * 4) Call the kAltReverse() recursively for rest of the n-2k nodes
 * 5) Return new head of the list
 */
	static class ListNode {
		int data;
		ListNode next;
		ListNode(int d) {
			data = d;
			next = null;
		}
	}
	/*
	 * Reverse alternate k nodes and
	 * returns the pointer to the new head node
	 */
	ListNode kAltReverse(ListNode node, int k) {
		ListNode prev=null, curr=node, next=null;
		int count=0;
		//1. reverse first k nodes of the linked list
		while(curr != null && count < k) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
			count++;
		}
		
		//2. Now head points to the kth node. 
		// So change next of head to (k+1) node
		if (node!=null) {
			node.next = curr;
		}
		
		//3. We do not want to reverse next k nodes. 
		// So move the current pointer to skip next k nodes
		count = 0;
		while (count<k-1 && curr!=null) {
			curr = curr.next;
			count++;
		}
		
		//4. Recursively call for the list starting from curr->next
		// And make rest of the list as next of first node
		if (curr!=null) {
			curr.next = kAltReverse(curr.next, k);
		}
		
		//5. prev is new head of the input list
		return prev;
	}
	
	ListNode kReverse(ListNode node, int k) {
		ListNode prev=null, curr=node, next=null;
		int count=0;
		
		while(curr != null && count < k) {
			next = curr.next;
			curr.next = prev;
			curr = next;
			prev = curr;
			count++;
		}

		if(node != null) {
			node.next = curr;
		}
		
		count=0;
		while (count < k-1 && curr != null) {
			curr = curr.next;
			count++;
		}
		
		if(curr != null) {
			curr.next = kReverse(curr.next, k);
		}
		
		return prev;
		
	}
	int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		List<Integer> result = new ArrayList<>();
		if(m<=0 || n<=0)	return result;
		int count=0;
		int[] roots = new int[m*n];
		Arrays.fill(roots, -1);
		for(int[] p: positions) {
			int root = n*p[0] + p[1];
			roots[root] = root;
			count++;
			for(int[] dir:dirs) {
				int x = p[0]+dir[0];
				int y = p[1]+dir[1];
				int nb =n*x+y;
				if(x<0 || x>=m || y<0 || y>=n || roots[nb]==-1) {
					continue;
				}
				int rootNb = findIsland(roots, nb);
				if(root != rootNb) {
					roots[root] = rootNb;
					root = rootNb;
					count--;
				}
			}
			result.add(count);
		}
		return result;
	}
	
	public int findIsland(int[] roots, int id) {
        while(id != roots[id]) id = roots[id];
        return id;
    } 
	
	public static void main(String[] args) {


	}
}
