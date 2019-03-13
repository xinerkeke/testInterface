package com.test.api;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.test.client.JSONParser;
import com.test.client.ResultPostClient;

import test.com.utils.ExcelProcess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class TestPost extends TestApi {
	Object[][] excelData;
	ResultPostClient client;
	HashMap<String, String> hashHead;
	String url;
	JSONObject responseBody;
    int responseCode;


	@Test
	public void testPostRequest() throws ClientProtocolException, IOException {
		// 从第二行开始遍历表单，跳过表头
		for (int i = 1; i < excelData.length; i++) {
			// 从特定位置读取测试数据
			String address = excelData[i][3].toString();
			url = host + address;
			String checkPoint = excelData[i][4].toString();
			String checkVal = excelData[i][5].toString();

			// 用NameValuePair存储所有请求参数
			List<NameValuePair> keys = new ArrayList<NameValuePair>();
			for (int j = 7; j < excelData[i].length; j = j + 2) {
				// 因为每种请求参数个数不确定，在这里进行非空判断
				if (excelData[i][j] == null) {
					break;
				}
				NameValuePair pair = new BasicNameValuePair(excelData[i][j].toString(), excelData[i][j + 1].toString());
				keys.add(pair);
			}

			// 发送请求
			client.sendPost(url, keys, hashHead);
			
			responseBody = client.getBodyInJSON();
			responseCode = client.getCodeInNumber();
			
			JSONParser jParser = new JSONParser();
			boolean result = jParser.isResponseCorrect(responseBody, checkPoint, checkVal);
		
			//断言判断结果
			Assert.assertTrue(result);
			Assert.assertEquals(responseCode, 200);
		}
	}

	@BeforeClass
	public void setup() throws IOException {
		// 读取用例excel
		excelData = ExcelProcess.processExcel(excelPath, 1);

		// 实力和client
		client = new ResultPostClient();

		// 设置请求头部
		hashHead = new HashMap<String, String>();
		hashHead.put("Content-Type", "application/x-www-form-urlencoded");
	}

}
