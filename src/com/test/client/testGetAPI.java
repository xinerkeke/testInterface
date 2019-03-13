package com.test.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

public class testGetAPI {

	@Test
	public void testInterface() throws ClientProtocolException, IOException {
		// 接口测试用例
		//1.发送请求
		// 接口地址
		String url = "https://suggest.taobao.com/sug?code=utf-8&q=毛衣&callback=cb";
		// 创建一个httpGet请求实例
		HttpGet get = new HttpGet(url);
		// 创建http客户端请求
		HttpClient httpClient = HttpClients.createDefault();
		// 执行发送请求并接受响应
		HttpResponse response = httpClient.execute(get);
		
		//2.响应结果
		int responseCode = response.getStatusLine().getStatusCode();
		Header[] responseHeader = response.getAllHeaders();
		HttpEntity responseBody = response.getEntity();
		String responseBodyString = EntityUtils.toString(responseBody, "utf-8");
		
		//用哈希图将反馈头信息以键值对形式保存
		HashMap<String, String> hashMap = new HashMap<String, String>();
		for(Header header: responseHeader) {
			hashMap.put(header.getName(), header.getValue());
		}
		
		System.out.println("This is the resposne code:" + responseCode);
		System.out.println("This is the response body:" + responseBodyString);
		System.out.println("This is the response header in hash " + hashMap);
	}

}
