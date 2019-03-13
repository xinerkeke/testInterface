package com.test.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

public class JSONParser {
	JSONObject internalJSON;

	public String getCity(JSONObject jo) {
		// 先获取反馈中的result这个一个内部JSON对象
		JSONObject internalJSON = jo.getJSONObject("data");
		// 再根据键名查找键值
		return internalJSON.getString("city");
	}

	public String getPhone(JSONObject jo) {
		// 先获取反馈中的result这个一个内部JSON对象
		JSONObject internalJSON = jo.getJSONObject("data");
		// 再根据键名查找键值
		return internalJSON.getString("phone");
	}

	public boolean isResponseCorrect(JSONObject jo, String checkpoint, String passValue) {
		ReadContext context = JsonPath.parse(jo);
		Object result = context.read("$.data."+ checkpoint );
		String resultString = result.toString();
		if (resultString.equals(passValue)) {
			return true;
		} else {
			return false;
		}
	}
}
