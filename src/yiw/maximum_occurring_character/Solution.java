package yiw.maximum_occurring_character;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	public static void main(String... args) {
		String s = "baababc2c";
		System.out.println(maximumOccurringCharacter(s));
	}

	public static char maximumOccurringCharacter(String text) {
		int ASCII_SIZE = 128;
		int[] charCounts = new int[ASCII_SIZE];

		for (char c : text.toCharArray()) {
			int index = (int) c - (int) '0';
			charCounts[index]++;
		}

		List<Character> results = new ArrayList<>();
		int maxCount = -1;

		for (int i = 0; i < charCounts.length; i++) {
			int count = charCounts[i];
			char c = (char) (i + (int) '0');

			if (count > maxCount) {
				maxCount = count;
				results.clear();
				results.add(c);
			} else if (count == maxCount) {
				results.add(c);
			}
		}

		int minIndex = text.length();
		for (char c : results) {
			int index = text.indexOf(c);
			if (index < minIndex) {
				minIndex = index;
			}
		}

		return text.charAt(minIndex);
	}

}
