package com.gojek.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import com.gojek.validations.CompareApiResponse;

public class CompareResponse {

	private static final String file1 = "apiUrls1.properties";
	private static final String file2 = "apiUrls2.properties";

	@Test
	public void testCompareApiResponse() throws IOException { 
		CompareApiResponse compareApiResponse = new CompareApiResponse();
		compareApiResponse.compareApis(file1, file2);
	}
}
	