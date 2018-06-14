package org.playground.problems;

public class RotateMatrix {

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 },
				{ 21, 22, 23, 24, 25 } };

		System.out.println("Before Rotate");
		printMatrix(matrix);
		rotate(matrix, matrix.length);
		System.out.println("After Rotate");
		printMatrix(matrix);
	}

	static void rotate(int[][] matrix, int n) {
		for (int layer = 0; layer < n / 2; layer++) {
			int first = layer;
			int last = n - layer - 1;
			for (int i = first; i < last; i++) {
				int offset = i - first;

				// top
				int top = matrix[first][i];

				// left -> top
				matrix[first][i] = matrix[last - offset][first];

				// bottom -> left
				matrix[last - offset][first] = matrix[last][last - offset];

				// right -> bottom
				matrix[last][last - offset] = matrix[i][last];

				// top -> right
				matrix[i][last] = top;
			}
		}
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
}