package org.playground.reflection;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.regex.Pattern;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

public class PrintClass {

	public static void main(String[] args) throws Exception {
		// spring();
		// guava();
		// java();
		// test();
		// classTest("org.playground.reflection");

		print(getClassesForPackage("java.util"));
	}

	/**
	 * Scans all classloaders for the current thread for loaded jars, and then scans
	 * each jar for the package name in question, listing all classes directly under
	 * the package name in question. Assumes directory structure in jar file and
	 * class package naming follow java conventions (i.e. com.example.test.MyTest
	 * would be in /com/example/test/MyTest.class)
	 */
	static Collection<Class<?>> getClassesForPackage(String packageName) throws Exception {
		String packagePath = packageName.replace(".", "/");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Set<URL> jarUrls = new HashSet<URL>();

		while (classLoader != null) {
			if (classLoader instanceof URLClassLoader)
				for (URL url : ((URLClassLoader) classLoader).getURLs())
					if (url.getFile().endsWith(".jar")) // may want better way to detect jar files
						jarUrls.add(url);

			classLoader = classLoader.getParent();
		}

		Set<Class<?>> classes = new HashSet<Class<?>>();

		for (URL url : jarUrls) {
			JarInputStream stream = new JarInputStream(url.openStream()); // may want better way to open url connections
			JarEntry entry = stream.getNextJarEntry();

			while (entry != null) {
				String name = entry.getName();
				int i = name.lastIndexOf("/");

				if (i > 0 && name.endsWith(".class") && name.substring(0, i).equals(packagePath))
					classes.add(Class.forName(name.substring(0, name.length() - 6).replace("/", ".")));

				entry = stream.getNextJarEntry();
			}

			stream.close();
		}

		return classes;
	}

	static void classTest(String packageName) throws Exception {
		Class<?> cls = ArrayList.class;
		System.out.println(cls.getName());
		// spring(packageName);
		guava(packageName);
	}

	static void test() {
		System.out.println("class loader for HashMap: " + java.util.HashMap.class.getClassLoader());
	}

	static void java(String packageName) throws Exception {
		ClassLoader bootstrapLoader = ClassLoader.getPlatformClassLoader().getParent();
		Package pkg = bootstrapLoader.getDefinedPackage(packageName);
		System.out.println(pkg.getName());
	}

	static void spring(String packageName) throws ClassNotFoundException {

		// create scanner and disable default filters (that is the 'false' argument)
		final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
				false);
		// add include filters which matches all the classes (or use your own)
		provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));

		// get matching classes defined in the package
		final Set<BeanDefinition> classes = provider.findCandidateComponents(packageName);

		// this is how you can load the class type from BeanDefinition instance
		for (BeanDefinition bean : classes) {
			Class<?> cls = Class.forName(bean.getBeanClassName());
			System.out.println(cls.getName());
		}

	}

	static void guava(String packageName) {
		// Reflections reflections = new
		// Reflections(ClasspathHelper.forPackage("java.util"), new SubTypesScanner());
		// new Reflections("java.util");

		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());

		Reflections reflections = new Reflections(new ConfigurationBuilder()
				.setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
				.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
				.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageName))));

		Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);

		print(allClasses);
	}

	static void print(Collection<Class<?>> allClasses) {
		for (Class<?> cls : allClasses) {
			System.out.println(cls.getName());
		}
	}
}