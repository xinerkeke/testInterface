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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class RestfulClient {
	HttpGet get;
	HttpClient httpClient;
	HttpResponse response;
	int responseCode;
	HashMap<String, String> responseHeads;
	JSONObject responseBody;

	/**
	 * 通过httpClient获取请求的反馈
	 * 
	 * @param url
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public void getResponse(String url) throws IOException, ClientProtocolException {
		// 创建一个httpGet请求实例
		get = new HttpGet(url);
		// 创建http客户端请求
		httpClient = HttpClients.createDefault();
		// 执行发送请求并接受响应
		response = httpClient.execute(get);
	}

	// 以JSON格式获取到反馈的主体
	public JSONObject getBodyInJSON() throws IOException {
		HttpEntity entity = response.getEntity();
		String entityToString = EntityUtils.toString(entity, "utf-8");
		responseBody = JSON.parseObject(entityToString);

		System.out.println("This is the response body:" + responseBody);
		return responseBody;
	}

	/**
	 * //以哈希图的方式获取到反馈头部
	 * 
	 * @return
	 */
	public HashMap<String, String> getHeaderInHash() {
		Header[] headers = response.getAllHeaders();
		HashMap<String, String> responseHeads = new HashMap<String, String>();
		for (Header header : headers) {
			responseHeads.put(header.getName(), header.getValue());
		}
		System.out.println("This is the response header in hash " + responseHeads);
		return responseHeads;
	}

	/**
	 * 获取返回状态码
	 * 
	 * @return
	 */
	public int getCodeInNumber() {
		responseCode = response.getStatusLine().getStatusCode();
		System.out.println("This is the resposne code:" + responseCode);
		return responseCode;
	}

}
