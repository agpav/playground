package org.playground.problems;

public class FindMissingElementInSeries {

	public static void main(String[] args) {
		int[] arr = { 1, 3, 4 };
		System.out.println(solve(arr));
	}

	public static int solve(int[] arr) {
		int n = arr.length + 1;
		int naturalSum = (n * (n + 1)) / 2;

		int arrSum = 0;
		for (int val : arr) {
			arrSum += val;
		}

		return naturalSum - arrSum;
	}
}