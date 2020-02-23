package etencr.primes_smaller_than_n;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

	public static void main(String[] args) {
		int num = 182933;
		System.out.println(Arrays.stream(findPrimes(num)).mapToObj(String::valueOf).collect(Collectors.joining(",")));
	}

	static int[] findPrimes(int num) {
		List<Integer> primeList = new ArrayList<Integer>();

		if (num > 2) {
			primeList.add(2);
		}

		for (int i = 3; i < num; i = i + 2) {
			boolean isPrime = true;
			for (int prime : primeList) {
				if (i % prime == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime) {
				primeList.add(i);
			}
		}

		return primeList.stream().mapToInt(i -> i).toArray();
	}

}
