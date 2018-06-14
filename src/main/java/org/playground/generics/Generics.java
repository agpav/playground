package org.playground.generics;

import java.util.ArrayList;
import java.util.List;

public class Generics {

	public static void main(String[] args) {

	}

	public void foo() {
		List list = new ArrayList();
		list.add("hello");
		String s = (String) list.get(0);
	}
}
