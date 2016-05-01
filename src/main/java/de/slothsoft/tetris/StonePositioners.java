package de.slothsoft.tetris;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

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
	 *            - the package to be searched (e.g. "de.slothsoft.tetris")
	 * @return a list of classes
	 */

	static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
		List<Class<?>> classes = new ArrayList<>();
		String path = packageName.replace('.', '/');

		try {
			URL resource = StonePositioners.class.getResource('/' + path);
			if (resource == null) return classes;
			URI uri = resource.toURI();

			Path[] pathToFolders;
			if (uri.getScheme().equals("jar")) {
				// we are in the application JAR
				FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object> emptyMap());
				pathToFolders = new Path[] { fileSystem.getPath('/' + path) };
			} else {
				// we are in the IDE
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				List<URL> resources = Collections.list(classLoader.getResources(path));
				pathToFolders = new Path[resources.size()];
				for (int i = 0; i < pathToFolders.length; i++) {
					pathToFolders[i] = Paths.get(resources.get(i).toURI());
				}
			}
			for (Path pathToFolder : pathToFolders)
				classes.addAll(findClasses(pathToFolder, packageName));

		} catch (URISyntaxException e) {
			throw new RuntimeException("This really should not have happend o_O", e);
		}
		return classes;
	}

	private static List<Class<?>> findClasses(Path directory, String packageName)
			throws ClassNotFoundException, IOException {
		List<Class<?>> classes = new ArrayList<>();
		try (Stream<Path> walker = Files.walk(directory, Integer.MAX_VALUE)) {
			for (Iterator<Path> it = walker.iterator(); it.hasNext();) {
				Path file = it.next();
				if (!Files.isDirectory(file)) {
					String fileName = file.getFileName().toString();
					if (fileName.endsWith(".class")) {
						classes.add(Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6)));
					}
				}
			}
		}
		return classes;
	}

	private StonePositioners() {
		// hide me
	}
}
