package org.playground.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Permutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String text = in.next();
		List<String> list = new ArrayList<>();
		permutation("", text, list);
		for (String s : list) {
			System.out.println(s);
		}
	}

	private static void permutation(String prefix, String str, List<String> list) {
		int n = str.length();
		if (n == 0) {
			list.add(prefix);
		} else {
			for (int i = 0; i < n; i++) {
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), list);
			}
		}
	}
}
