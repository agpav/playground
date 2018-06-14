package org.playground.problems;

public class Gcd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(gcd(12, 8));
	}

	static int gcd(int x, int y) {

		while (x != y) {
			if (x > y) {
				x = x - y;
			}
			if (y > x) {
				y = y - x;
			}
		}

		return x;
	}

}
