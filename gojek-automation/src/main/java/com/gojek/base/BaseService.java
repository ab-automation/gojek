package com.gojek.base;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * @author abhishekkumar65
 *This class consists of request methods.
 */
public class BaseService {

	private Response response;

	
	public Response get(String path) {
		this.response = RestAssured.get(path);
		return this.response;
	}
}
