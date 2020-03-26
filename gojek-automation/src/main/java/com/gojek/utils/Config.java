package com.gojek.utils;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author abhishekkumar65 
 * This class will serve the purpose of reading test data from properties file.
 *
 */
public class Config {

	// This method reads data from top to bottom order from the properties file.
	public static List<String> getApiUrlsAsList(String fileName) throws IOException {
		List<String> apiUrls = new ArrayList<String>();
		List<String> allUrls = Files.readAllLines(Paths.get(".\\templates\\" + fileName));
		assertTrue(allUrls.size() != 0 && !allUrls.isEmpty(), "No test data present in the file - " + fileName);
		for (int fileLine = 0; fileLine < allUrls.size(); fileLine++) {
			apiUrls.add(allUrls.get(fileLine));
		}
		return apiUrls;
	}
}
