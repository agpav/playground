package org.playground;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	
	static int inversions = 0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int d = in.nextInt();
		for (int k = 0; k < d; k++) {
			int n = in.nextInt();
			int[] arr = Arrays.stream(new int[n]).map(i -> i = in.nextInt()).toArray();
			int swaps = mergeSort(arr);
			System.out.println(inversions);
		}
	}

	private static int mergeSort(int[] arr) {
		int swaps = 0;
		swaps += mergeSort(arr, 0, arr.length - 1);
		return swaps;
	}

	private static int mergeSort(int[] arr, int leftStart, int rightEnd) {
		int swaps = 0;
		if (leftStart >= rightEnd) {
			return 0;
		}
		int middle = (leftStart + rightEnd) / 2;
		mergeSort(arr, leftStart, middle);
		mergeSort(arr, middle + 1, rightEnd);
		swaps += merge(arr, leftStart, rightEnd);
		return swaps;
	}

	private static int merge(int[] arr, int leftStart, int rightEnd) {
		int leftEnd = (leftStart + rightEnd) / 2;
		int rightStart = leftEnd + 1;
		int size = rightEnd - leftStart + 1;
		int[] temp = new int[arr.length];
		int swaps = 0;

		int left = leftStart;
		int right = rightStart;
		int index = leftStart;

		while (left <= leftEnd && right <= rightEnd) {
			if (arr[left] <= arr[right]) {
				temp[index] = arr[left];
				left++;
			} else {
				temp[index] = arr[right];
				right++;
				inversions++;
			}
			index++;
		}

		System.arraycopy(arr, left, temp, index, leftEnd - left + 1);
		System.arraycopy(arr, right, temp, index, rightEnd - right + 1);
		System.arraycopy(temp, leftStart, arr, leftStart, size);

		System.out.println(Arrays.toString(arr));
		System.out.println(inversions);
		return swaps;
	}
}