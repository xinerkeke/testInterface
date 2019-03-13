package testInterface;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class testDemo {
	public static void main(String[] args) throws Exception {
		/**
		 * postman进行接口用例测试
		 * 1.请求链接/地址：http://120.78.128.25:8080/futureloan/mvc/api/member/register
		 * 2.请求参数:mobilephone=13123896589,pwd=123456,regname=tom
		 * 3.请求方式：get,post
		 * 4.调用发包工具发送请求---发送数据包，发送请求
		 * 5.响应结果---提取结果数据
		 * 6.断言---判断响应结果与预期是否相符，如果符合测试通过，反之用例未通过
		 */
		
	//	get();
	//	post();
		
	}

	/**
	 *接口测试用例
	 *post请求
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private static void post() throws UnsupportedEncodingException, IOException, ClientProtocolException {
		//1.请求地址
		String url = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
		
//		 3.请求方式：post---封装一个post请求
		 HttpPost post = new HttpPost(url);
		//2.post参数
		 List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		 parameters.add(new BasicNameValuePair("mobilephone", "13123896589"));
		 parameters.add(new BasicNameValuePair("pwd", "123456"));
		 parameters.add(new BasicNameValuePair("regname", "tom"));
		 post.setEntity(new UrlEncodedFormEntity(parameters));
//		 4.调用发包工具发送请求---发送数据包，发送请求
		//需要一个客户端
		 HttpClient httpClient = HttpClients.createDefault();
		//执行请求得到响应
		 HttpResponse response = httpClient.execute(post);
//		 5.响应结果---提取结果数据
		 String result = EntityUtils.toString(response.getEntity());
//		 6.断言---判断响应结果与预期是否相符，如果符合测试通过，反之用例未通过
		 System.out.println(result);
	}

	/**
	 * 
	 * 接口测试用例
	 * get请求
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private static void get() throws IOException, ClientProtocolException {
		//1.请求地址
		String url = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
		//2.get参数
		url += "?mobilephone=13123896589&pwd=123456&regname=tom";
//		 3.请求方式：get---封装一个get请求
		HttpGet get = new HttpGet(url);
//		 4.调用发包工具发送请求---发送数据包，发送请求
		//需要一个客户端
		HttpClient httpClient = HttpClients.createDefault();
		//执行请求得到响应
		HttpResponse response = httpClient.execute(get);
//		 5.响应结果---提取结果数据
		String result = EntityUtils.toString(response.getEntity());
//		 6.断言---判断响应结果与预期是否相符，如果符合测试通过，反之用例未通过
		System.out.println(result);
	}

}


