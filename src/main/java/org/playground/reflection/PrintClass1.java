package org.playground.reflection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class PrintClass1 {

	// public static void main(String[] args) throws Exception {
	// Field f = ClassLoader.class.getDeclaredField("classes");
	// f.setAccessible(true);
	// ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	// Vector<Class<?>> classes = (Vector<Class<?>>) f.get(classLoader);
	//
	// for (Class<?> cls : classes) {
	// java.net.URL location = cls.getResource('/' + cls.getName().replace('.', '/')
	// + ".class");
	// System.out.println("<p>" + location + "<p/>");
	// }
	// }

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		for (Class<?> cls : getClasses("java.util")) {
			System.out.println(cls);
		}

		// print();
	}

	private static void print() {
		Package[] pkgs = Package.getPackages();
		for (Package p : pkgs) {
			// System.out.println(p.getName());
			if (!p.getName().equals("java.util")) {
				continue;
			}

			//

		}
	}

	/**
	 * Scans all classes accessible from the context class loader which belong to
	 * the given package and subpackages.
	 *
	 * @param packageName
	 *            The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader().getParent();// Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);

		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}

		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}

		return classes.toArray(new Class[classes.size()]);
	}

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory
	 *            The base directory
	 * @param packageName
	 *            The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(
						Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}
}
