package com.gojek.validations;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.testng.asserts.SoftAssert;

import com.gojek.base.BaseService;
import com.gojek.utils.Config;

/**
 * @author abhishekkumar65 This class aims to have functions for api response
 *         validations.
 *
 */

public class CompareApiResponse extends BaseService {

	SoftAssert softAssert = new SoftAssert();
	//This methods checks equality of two api responses in parallel, where as the api urls are picked from two file in sequential order of occurrence.
	public void compareApis(String file1Name, String file2Name) throws IOException {
		List<String> file1Urls = Config.getApiUrlsAsList(file1Name);
		List<String> file2Urls = Config.getApiUrlsAsList(file2Name);
		assertTrue(file1Urls.size() == file2Urls.size(),
				"The number of api urls present in file1 is not equal to the number of api urls present in file2.");
		for (int i = 0; i < file1Urls.size(); i++) {
			String responseOfApi1 = get(file1Urls.get(i)).getBody().asString();
			String responseOfApi2 = get(file2Urls.get(i)).getBody().asString();
			softAssert.assertEquals(!responseOfApi1.isEmpty() && !responseOfApi2.isEmpty() == true ? true:false, "The response of api is empty");
			if (responseOfApi1.equals(responseOfApi2)) {
				System.out.println(file1Urls.get(i) + "    equals     " + file2Urls.get(i));
			} else {
				System.out.println(file1Urls.get(i) + "    not equals   " + file2Urls.get(i));
			}
		}
		softAssert.assertAll();
	}
}
