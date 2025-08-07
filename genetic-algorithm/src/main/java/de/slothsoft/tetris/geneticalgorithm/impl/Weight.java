package de.slothsoft.tetris.geneticalgorithm.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Weight {

	private static final String FILE_NAME = "/genetic-algorithm-weight.json";
	
	public double heightDifferences;
	public double holeCount;
	public double futureLinesVanished;
	public double pointsForX;
	public double pointsForY;
	public double pointsForFlatObjects;

	public String toJsonString() {
		try (Writer writer = new StringWriter();) {
			saveToWriter(writer);
			return writer.toString();
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public Weight copy() {
		try( Reader reader = new StringReader(toJsonString());) {
			return loadFromReader(reader);
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public void save() {
		File targetFile = new File("src/main/resources" + FILE_NAME);
		try (Writer writer = new FileWriter(targetFile)) {
			saveToWriter(writer);
        } catch (IOException e) {
			throw new RuntimeException("Could not save to file " + targetFile, e);
        }
	}
	
	private void saveToWriter(Writer writer) {
		try {
			writer.append(createGson().toJson(this));
		} catch (IOException e) {
			throw new RuntimeException("Could not write JSON!", e);
		}
	}

	private static Gson createGson() {
		return new GsonBuilder().setPrettyPrinting().create();
	}

	public static Weight load() {
		try (InputStream input = Weight.class.getResourceAsStream(FILE_NAME); 
				InputStreamReader reader = new InputStreamReader(input, "UTF-8");) {
			return loadFromReader(reader);
		} catch (Exception e) {
			throw new RuntimeException("Could not load " + FILE_NAME, e);
		}
	}

	private static Weight loadFromReader(Reader reader) {
		Weight result = createGson().fromJson(reader, Weight.class);
		return result == null ? new Weight() : result;
	}
}
