package muksbmqk.maximum_occuring_character;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

	public static void main(String[] args) {
		System.out.println(maximumOccuringCharacter("helloworld"));
	}

	public static char maximumOccuringCharacter(String text) {
		Set<Character> maxOccurers = new HashSet<>();
		Map<Character, Integer> freq = new HashMap<>();

		if (text == null || text.length() == 0) {
			return ' ';
		}

		for (char c : text.toCharArray()) {
			Integer f = freq.get(c);

			if (f == null) {
				freq.put(c, 1);
			} else {
				freq.put(c, f + 1);
			}
		}

		int max = -1;
		for (Map.Entry<Character, Integer> e : freq.entrySet()) {
			if (e.getValue() > max) {
				max = e.getValue();
				maxOccurers.clear();
				maxOccurers.add(e.getKey());
			} else if (e.getValue() == max) {
				maxOccurers.add(e.getKey());
			}
		}

		int minIndex = Integer.MAX_VALUE;
		char first = ' ';
		for (Character c : maxOccurers) {
			int index = text.indexOf(c);
			if (index < minIndex) {
				first = c;
				minIndex = index;
			}
		}

		return first;
	}

}
