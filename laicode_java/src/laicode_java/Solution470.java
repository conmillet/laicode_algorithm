package laicode_java;

import java.util.*;
//
//Palindrome Pairs
//Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
//
//Example 1:
//Given words = ["bat", "tab", "cat"]
//Return [[0, 1], [1, 0]]
//The palindromes are ["battab", "tabbat"]
//
//Example 2:
//Given words = ["abcd", "dcba", "lls", "s", "sssll"]
//Return [[0, 1], [1, 0], [3, 2], [2, 4]]
//The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
public class Solution470 {
	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(words==null || words.length==0) {
			return res;
		}
		//build the map save the key-val pairs : String - idx
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0; i<words.length; i++) {
			map.put(words[i], i);
		}
		
		//special cases: "" can be combine with any palindrome string
		if(map.containsKey("")) {
			int blankIdx = map.get("");
			for(int i=0; i<words.length; i++) {
				if(isPalindrome(words[i])) {
					if(i==blankIdx) {
						continue;
					}
					res.add(Arrays.asList(blankIdx, i));
					res.add(Arrays.asList(i, blankIdx));
				}
			}
		}
		
		//find all string and reverse string pairs
		for(int i=0; i<words.length; i++) {
			String cur = reverseStr(words[i]);
			if(map.containsKey(cur)) {
				int found = map.get(cur);
				if(found == i) continue;
				res.add(Arrays.asList(i, found));
			}
		}
		
		//find the pair s1, s2 that
		//case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1)
		//case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverses(s2) => (s1, s2)
		for(int i=0; i<words.length; i++) {
			String cur = words[i];
			for(int cut=1; cut<cur.length(); cut++) {
				if(isPalindrome(cur.substring(0, cut))) {
					String curr = reverseStr(cur.substring(cut));
					if(map.containsKey(curr)) {
						int found = map.get(curr);
						if(found == i) {
							continue;
						}
						res.add(Arrays.asList(found, i));
					}
				}
				if(isPalindrome(cur.substring(cut))) {
					String curr = reverseStr(cur.substring(0, cut));
					if(map.containsKey(curr)) {
						int found = map.get(curr);
						if(found == i) {
							continue;
						}
						res.add(Arrays.asList(i, found));
					}
				}
			}
		}
		
		return res;
	}
	
	private String reverseStr(String str) {
		StringBuilder sb = new StringBuilder(str);
		return sb.reverse().toString();
	}
	
	private boolean isPalindrome(String str) {
		int i=0;
		int j=str.length()-1;
		while(i<=j) {
			if(str.charAt(i) != str.charAt(j)) {
				return false;
			}
			i++;	j--;
		}
		return true;
	}
	public static void main(String[] args) {


	}
}
