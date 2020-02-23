package ekcsb.same_length_nails;

public class Solution {

	public static void main(String[] args) {
		int Y = 2;
		int[] A = { 1, 1, 3, 3, 3, 4, 5, 5, 5, 5 };
		System.out.println(solution(A, Y));
	}

	public static int solution(int[] A, int Y) {
		int maxCount = -1;
		int currentSizeNailCount = 0;
		int hammerableNailCountForSize = 0;
		int prevNailLength = -1;
		int len = A.length;

		int currNailLength = -1;
		for (int i = len - 1; i >= 0; i--) {
			currNailLength = A[i];

			if (prevNailLength != currNailLength) {
				int totalAvailableCountForSize = currentSizeNailCount
						+ Math.min(Y, (hammerableNailCountForSize - currentSizeNailCount));

				maxCount = Math.max(totalAvailableCountForSize, maxCount);

				currentSizeNailCount = 1;
				prevNailLength = currNailLength;
			} else {
				currentSizeNailCount++;
			}

			hammerableNailCountForSize++;
		}

		int cnt = currentSizeNailCount + Math.min(Y, (hammerableNailCountForSize - currentSizeNailCount));
		maxCount = Math.max(cnt, maxCount);

		return maxCount;
	}
}
