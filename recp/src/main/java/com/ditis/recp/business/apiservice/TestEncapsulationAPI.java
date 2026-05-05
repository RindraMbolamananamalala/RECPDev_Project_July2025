package com.ditis.recp.business.apiservice;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TestEncapsulationAPI {
	
	public String searchPatternByName(String patternName) {
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
		MediaType mediaType = MediaType.parse("text/plain");
		Request request = new Request.Builder()
				  .url("http://localhost:8090/search_patterns?typeOfPatternResearch=patternResearchByName&inputPatternInformation=" + patternName)
				  .method("GET", null)
				  .build();
		try {
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
