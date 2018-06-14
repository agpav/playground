package org.playground.problems;

import java.util.Scanner;

public class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Tree tree = new Tree();
		for (int t = 0; t < n; t++) {
			int value = sc.nextInt();
			int parent = sc.nextInt();
			tree.add(value, parent);
		}
		sc.close();
	}
}

class Tree {
	Node root;

	void add(int key, int parent) {
		Node n = new Node(key);
		if (parent == -1) {
			root = n;
			root.parent = null;
		} else {

		}
	}
}

class Node {
	int key;
	Node left;
	Node right;
	Node parent;

	public Node(int key) {
		this.key = key;
	}

	public void addLeft(Node node) {
		this.left = node;
	}

	public void addRight(Node node) {
		this.right = node;
	}
}