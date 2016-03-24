package de.slothsoft.tetris;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import de.slothsoft.tetris.contrib.ExampleStonePositioner;

/**
 * Util class for getting and managing {@link StonePositioner}s (hence the name)
 * 
 * @since 1.0.0
 */

public final class StonePositioners {

	/**
	 * Returns all instances of {@link StonePositioner}s in the
	 * <code>contrib</code> package.
	 * 
	 * @return a list of stone positioners
	 */

	public static List<StonePositioner> getStonePositioners() {
		return getStonePositioners(ExampleStonePositioner.class.getPackage());
	}

	/**
	 * Returns all instances of {@link StonePositioner}s in a specified package.
	 * 
	 * @param searchedPackage
	 *            - the package to be searched
	 * @return a list of stone positioners
	 */

	static List<StonePositioner> getStonePositioners(Package searchedPackage) {
		try {
			List<StonePositioner> stonePositioner = new ArrayList<>();
			List<Class<?>> classes = getClasses(searchedPackage.getName());
			for (Class<?> clazz : classes) {
				if (!Modifier.isAbstract(clazz.getModifiers()) && StonePositioner.class.isAssignableFrom(clazz)) {
					stonePositioner.add((StonePositioner) clazz.newInstance());
				}
			}
			return stonePositioner;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns all implementations of {@link StonePositioner}s in a specified
	 * package.
	 * 
	 * @param packageName
	 *            - the package to be searched
	 * @return a list of classes
	 */

	static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packageName.replace('.', '/');

		Enumeration<URL> resources = classLoader.getResources(path);
		List<Class<?>> classes = new ArrayList<>();
		while (resources.hasMoreElements()) {
			classes.addAll(findClasses(new File(resources.nextElement().getFile()), packageName));
		}
		return classes;
	}

	private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<>();
		if (!directory.exists())
			return classes;
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(
						Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

	private StonePositioners() {
		// hide me
	}
}
