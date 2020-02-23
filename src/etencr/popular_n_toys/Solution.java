package etencr.popular_n_toys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Solution {

	public static void main(String... args) {
		int numToys = 6;
		int topToys = 2;
		List<String> toys = Arrays.asList("elmo", "elsa", "legos", "drone");
		int numQuotes = 6;
		List<String> quotes = Arrays.asList("Elmo is the hottest toy of the season!",
				"Elmo wil be on every kid's wishlist!", "The new Elmo dolls are super high quality.",
				"Expect the Elsa dolls to be very popular this year",
				"Elsa and Elmo are the toys I'll be buying for my kids.",
				"For parents of older kids, look into buying them a drone.",
				"Warcraft is slowly rising in popularity ahead of the holiday season.");
		System.out.println(popularNToys(numToys, topToys, toys, numQuotes, quotes));
	}

	/*
	 * Q=Quote count
	 * L=Largest Quote Length
	 * T=Toy count
	 * 
	 * TimeComplexity=O(Q.T.L + T.log(T))
	 * */
	public static ArrayList<String> popularNToys(int numToys, int topToys, List<String> toys, int numQuotes,
			List<String> quotes) {
		if (numToys == 0 || topToys == 0)
			return new ArrayList<>();

		Map<String, Integer> freqMap = new HashMap<>();
		quotes.stream().forEach(quote -> {
			toys.stream().forEach(toy -> {
				if (Pattern.compile(Pattern.quote(toy), Pattern.CASE_INSENSITIVE).matcher(quote).find()) {
					freqMap.put(toy, freqMap.getOrDefault(toy, 0) + 1);
				}
			});
		});

		ArrayList<String> orderedWordList = new ArrayList<>(freqMap.keySet());
		Collections.sort(orderedWordList, (w1, w2) -> freqMap.get(w1).equals(freqMap.get(w2)) ? w1.compareTo(w2)
				: freqMap.get(w2).compareTo(freqMap.get(w1)));

		return orderedWordList.size() > topToys ? new ArrayList<String>(orderedWordList.subList(0, topToys))
				: orderedWordList;
	}
}
