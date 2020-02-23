package muksbmqk.maximum_difference_in_an_array;

import java.util.Arrays;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		Integer[] array = { 1, 2, 6, 4 };

		System.out.println(maxDifference(Arrays.asList(array)));
	}

	public static int maxDifference(List<Integer> arr) {
		int min = Integer.MAX_VALUE;
		int maxDif = Integer.MIN_VALUE;

		for (Integer i : arr) {
			int dif = i - min;
			if (dif > maxDif) {
				maxDif = dif;
			}

			if (i < min) {
				min = i;
			}
		}
		
		return maxDif;
	}

}
