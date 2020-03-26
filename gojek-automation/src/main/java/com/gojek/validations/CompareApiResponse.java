package com.gojek.validations;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gojek.base.BaseService;
import com.gojek.utils.Config;

/**
 * @author abhishekkumar65 This class aims to have functions for api response
 *         validations.
 *
 */

public class CompareApiResponse extends BaseService {
	private static final Logger log = LogManager.getLogger(CompareApiResponse.class);
	

	//This methods checks equality of two api responses in parallel, where as the api urls are picked from two file in sequential order of occurrence.
	
	public void compareApis(String file1Name, String file2Name) throws IOException {
		
		log.info("Getting api urls from propetrties file as a list");
		List<String> file1Urls = Config.getApiUrlsAsList(file1Name);
		List<String> file2Urls = Config.getApiUrlsAsList(file2Name);
		
		log.info("Validate both the files have equal number of api urls present to compare");
		assertTrue(file1Urls.size() == file2Urls.size(),
				"The number of api urls present in file1 is not equal to the number of api urls present in file2.");
		for (int i = 0; i < file1Urls.size(); i++) {
			
			log.info("Getting  response of api from file1 and api from file2");
			String responseOfApi1 = get(file1Urls.get(i)).getBody().asString();
			String responseOfApi2 = get(file2Urls.get(i)).getBody().asString();
			
			log.info("Checking equality of response for api1 and api2");
			if (responseOfApi1.equals(responseOfApi2)) {
				log.info("For apis at line no - "+ (i+1) +" -> "+ file1Urls.get(i) + "    equals     " + file2Urls.get(i));
			} else {
				log.info("For apis at line no - "+ (i+1) +" -> "+ file1Urls.get(i) + "    not equals   " + file2Urls.get(i));
			}
		}
	}
}
