package org.playground.problems;

import java.util.Scanner;

public class Swap {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String text = in.nextLine();
		String swapped = swap(text);
		System.out.println(swapped);
	}

	private static String swap(String text) {
		String[] arr = text.split(" ");
		
		int n = arr.length;
		for (int i = 0, j = n - 1; i < n / 2; i++, j--) {
			String temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}

		return toString(arr);
	}

	private static String toString(String[] arr) {
		String newText = "";
		for (String s : arr) {
			newText += s + " ";
		}
		return newText.trim();
	}
}
