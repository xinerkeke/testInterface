package com.test.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

public class testGet {

	RestfulClient restfulClient;
	int responseCode;
	HashMap<String, String> responseHeads;
	JSONObject responseBody;
	JSONParser jParser;
	String city;

	@Test
	public void f() throws ClientProtocolException, IOException {
		String url = "https://www.apiopen.top/weatherApi?city=成都";
		restfulClient = new RestfulClient();
		restfulClient.getResponse(url);
		//返回码
		responseCode = restfulClient.getCodeInNumber();
		//返回头
		responseHeads = restfulClient.getHeaderInHash();
		//返回体
		responseBody = restfulClient.getBodyInJSON();
		
		jParser = new JSONParser();
		city = jParser.getCity(responseBody);
		
		//断言反馈中城市是否正确
        Assert.assertEquals(city, "成都");
        //断言反馈中的状态码是否正确
        Assert.assertEquals(responseCode, 200);

	}
}
