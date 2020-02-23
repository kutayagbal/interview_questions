package ekcsb.demo;

import java.util.Arrays;
import java.util.Random;

public class Solution {

	public static void main(String[] args) {
		int[] A = new int[100];

		for (int i = 0; i < A.length; i++) {
			A[i] = new Random().nextInt(200) - 100; // -100 <-> 100
		}

		System.out.println("RESULT: " + solution(A));

		Arrays.sort(A);

		for (int a : A) {
			System.out.print(a + ", ");
		}
	}

	public static int solution(int[] A) {
		int[] bits = new int[31250]; // 1 to 1M count

		boolean isAllNegative = true;
		for (int a : A) {
			if (a <= 0) {
				continue;
			} else {
				isAllNegative = false;
			}

			int fullIndex = a / Integer.SIZE;
			int offset = a % Integer.SIZE;

			int mask = 0;
			if (offset == 0) {
				mask = 1 << 31;
				fullIndex--;
			} else {
				mask = (1 << (offset - 1)); // starts with 1
			}

			bits[fullIndex] = (bits[fullIndex] | mask);
		}

		if (isAllNegative) {
			return 1;
		}

		for (int i = 0; i < bits.length; i++) {
			int intBit = bits[i];

			if ((intBit & -1) == -1) {// no zeros
				continue;
			}

			int zeroIndex = 0;
			boolean zeroFound = false;
			while (!zeroFound) {
				if ((intBit & 1) == 0) { // check last bit
					zeroFound = true;
				} else {
					intBit = (intBit >> 1);
					zeroIndex++;
				}
			}

			if (zeroFound) {
				return i * Integer.SIZE + zeroIndex + 1;
			}
		}

		return (bits.length - 1) * Integer.SIZE;
	}

}
