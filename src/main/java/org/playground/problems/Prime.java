package org.playground.problems;

import java.math.BigInteger;
import java.util.Scanner;

public class Prime {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int sum = in.nextInt();
		boolean prime = checkPrime(sum);
		System.out.println(prime ? "YES" : "NO");
	}

	private static boolean checkPrime(int sum) {
		boolean flag = false;
		for (int i = 2; i <= sum / 2; ++i) {
			if (isPrime(i) && isPrime(sum - i)) {
				flag = true;
			}
		}
		return flag;
	}

	private static boolean isPrime(int num) {
		for (int i = 2; i <= num / 2; ++i) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	private static boolean isPrime2(int num) {
		BigInteger bi = new BigInteger(num + "");
		return bi.isProbablePrime(100);
	}
}
