package com.test.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ResultPostClient {
	int responseCode;
	HttpClient httpClient;
	HttpResponse response;
	JSONObject responseBody;
	
	/**
	 * 获取post响应
	 * @param url
	 * @param parameters
	 * @param headers
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void sendPost(String url, List<NameValuePair> parameters, HashMap<String, String> headers) throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(url);
		post.setEntity(new UrlEncodedFormEntity(parameters));
		//设置头部信息
        Set<String> set = headers.keySet();
        for(Iterator<String> iterator = set.iterator(); iterator.hasNext();){
            String key = iterator.next();
            String value = headers.get(key);
            post.addHeader(key, value);
        }

		httpClient = HttpClients.createDefault();
		response = httpClient.execute(post);
	}

	/**
	 * 
	 * @param response
	 * @return
	 */
	public int getCodeInNumber() {
		int responseCode = response.getStatusLine().getStatusCode();
		System.out.println("This is the response code :" + responseCode);
		return responseCode;
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
	
	
	// 以JSON格式获取到反馈的主体
	public JSONObject getBodyInJSON() throws IOException {
		HttpEntity entity = response.getEntity();
		String entityToString = EntityUtils.toString(entity, "utf-8");
		responseBody = JSON.parseObject(entityToString);

		System.out.println("This is the response body:" + responseBody);
		return responseBody;
	}
}
