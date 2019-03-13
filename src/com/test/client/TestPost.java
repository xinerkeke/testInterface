package com.test.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

public class TestPost {
	ResultPostClient client;
	int responseCode;
	HashMap<String, String> responseHeads;
	JSONObject responseBody;
	JSONParser jParser;
	String phone;
		
	
	@Test
	public void testPost() throws ClientProtocolException, IOException {
		client = new ResultPostClient();
		/*注册*/
		/*
		String url = "https://www.apiopen.top/createUser";
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("key","00d91e8e0cca2b76f515926a36db68f5"));
		parameters.add(new BasicNameValuePair("phone","18256890986"));
		parameters.add(new BasicNameValuePair("passwd","123456"));
		*/
		/*登录*/
		String url = "https://www.apiopen.top/login";
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("key","00d91e8e0cca2b76f515926a36db68f5"));
		parameters.add(new BasicNameValuePair("phone","13594347818"));
		parameters.add(new BasicNameValuePair("passwd","123456"));
		
		//用哈希图准备请求头部信息
		HashMap<String, String> hashHead = new HashMap<String, String>();
		hashHead.put("Content-Type", "application/x-www-form-urlencoded");
		
		//发送请求---获取post响应
		client.sendPost(url, parameters, hashHead);
		
		//获取响应码
		responseCode = client.getCodeInNumber();
		
		//响应头
		responseHeads = client.getHeaderInHash();
		
		//响应体
		responseBody = client.getBodyInJSON();
		
		jParser = new JSONParser();
		
		phone = jParser.getPhone(responseBody);
		
		//断言
		Assert.assertEquals(phone, "13594347818");
		Assert.assertEquals(responseCode, 200);
	}

}
