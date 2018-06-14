package org.playground.problems;

import java.util.HashSet;
import java.util.Set;

public class FindSumPair {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 4, 4 };
		int sum = 8;
		System.out.println(findOrdered(arr, sum));

		int[] arr1 = { 1, 3, 2, 9 };
		sum = 8;
		System.out.println(findUnordered(arr1, sum));
	}

	public static boolean findOrdered(int[] arr, int target) {
		int low = 0;
		int high = arr.length - 1;
		while (low < high) {
			int sum = arr[low] + arr[high];
			if (sum == target) {
				return true;
			} else if (sum < target) {
				low++;
			} else if (sum > target) {
				high++;
			}
		}
		return false;
	}

	public static boolean findUnordered(int[] arr, int target) {
		Set<Integer> compliment = new HashSet<>();
		for (int val : arr) {
			int comp = target - val;
			if (compliment.contains(comp)) {
				return true;
			}
			compliment.add(comp);
		}
		return false;
	}
}