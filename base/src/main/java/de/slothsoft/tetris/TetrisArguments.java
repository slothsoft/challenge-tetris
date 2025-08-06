package de.slothsoft.tetris;

import java.util.Arrays;
import java.util.function.Function;

/**
 * A util class for program arguments
 * 
 * @since 1.0.0
 */

public final class TetrisArguments {

	private static final String PREFIX = "-";
	private static final String SEPARATOR = "=";
	private static final int FIRST_COLUMN_LENGTH = 25;

	private static final String HELP = "help";
	private static final String STONE_POSITIONER = "stonePositioner";
	private static final String SHOW_SETTINGS = "showSettings";
	private static final String TIME_PER_STONE = "timePerStone";

	public static void check(String[] args) {
		if (Arrays.asList(args).contains(PREFIX + HELP)) {
			printHelp();
			System.exit(0);
		}
	}

	public static void printHelp() {
		printLine(HELP, "shows this help");
		printLine(STONE_POSITIONER, "presets the stone positioner to a specified class, e.g. -" + STONE_POSITIONER
				+ "=ExampleStonePositioner");
		printLine(SHOW_SETTINGS, "flag describing if the settings should be shown on the bottom left, e.g. -"
				+ SHOW_SETTINGS + "=false");
		printLine(TIME_PER_STONE, "time used per stone, e.g. -" + TIME_PER_STONE + "=100");
	}

	private static void printLine(String property, String description) {
		System.out.print(property);
		for (int i = property.length(); i < FIRST_COLUMN_LENGTH; i++) {
			System.out.print(' ');
		}
		System.out.println(description);
	}

	public static StonePositioner getStonePositioner(String[] args, StonePositioner defaultValue) {
		return getValue(args, STONE_POSITIONER, value -> createStonePositioner(value, defaultValue), defaultValue);
	}

	private static StonePositioner createStonePositioner(String value, StonePositioner defaultValue) {
		try {
			if (value.isEmpty()) return defaultValue;
			return (StonePositioner) Class.forName("de.slothsoft.tetris.contrib." + value).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isShowSettings(String[] args, boolean defaultValue) {
		return getBooleanValue(args, SHOW_SETTINGS, defaultValue);
	}

	public static int getTimePerStone(String[] args, int defaultValue) {
		return getIntegerValue(args, TIME_PER_STONE, defaultValue);
	}

	private static int getIntegerValue(String[] args, String property, int defaultValue) {
		return getValue(args, property, s -> Integer.valueOf(s), Integer.valueOf(defaultValue)).intValue();
	}

	private static boolean getBooleanValue(String[] args, String property, boolean defaultValue) {
		String entirePropertyWithoutSeparator = PREFIX + property;
		for (String arg : args) {
			if (arg.equals(entirePropertyWithoutSeparator)) {
				return true;
			}
		}
		return getValue(args, property, s -> Boolean.parseBoolean(s), Boolean.valueOf(defaultValue)).booleanValue();
	}

	private static <V> V getValue(String[] args, String property, Function<String, V> converter, V defaultValue) {
		String entireProperty = PREFIX + property + SEPARATOR;
		for (String arg : args) {
			if (arg.startsWith(entireProperty)) {
				String value = arg.substring(entireProperty.length()).trim();
				if (!value.isEmpty()) return converter.apply(value);
			}
		}
		return defaultValue;
	}

	private TetrisArguments() {
		// if this was work, I'd make a stupid comment
	}
}
