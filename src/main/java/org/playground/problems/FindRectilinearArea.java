package org.playground.problems;

public class FindRectilinearArea {

	public static void main(String[] args) {
		System.out.println(solve(-4, 1, 2, 6, 0, -1, 4, 3));
	}

	static int solve(int K, int L, int M, int N, int P, int Q, int R, int S) {
		int r1 = getArea(K, L, M, N);
		int r2 = getArea(P, Q, R, S);
		int r3 = getArea(P, L, M, S);
		return r1 + r2 - r3;
	}

	static int getArea(int x1, int y1, int x2, int y2) {
		int length = x2 - x1;
		int height = y2 - y1;
		return length * height;
	}
}