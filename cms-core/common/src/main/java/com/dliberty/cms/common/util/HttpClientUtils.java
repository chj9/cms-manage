package com.dliberty.cms.common.util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * httpClient 公用
 * @author GuoJingtao 2016年7月19日
 *
 */
public class HttpClientUtils {

	/**
	 * get请求
	 * @param url
	 * @return
	 */
	public static String responseGet(String url) {
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		String responseContent = null; // 响应内容
		try {
			HttpResponse res = client.execute(get);
			HttpEntity entity = res.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch(Exception e) {
			//logger.warn("httpClient get请求异常",e.getMessage());
		}
		return responseContent;
	}
	
	/**
	 * post请求
	 * @param url
	 * @return
	 */
	public static String responsePost(String url,String text){
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		String responseContent = null; // 响应内容
		try {
			post.setEntity(new StringEntity(text, "UTF-8"));
			HttpResponse res = client.execute(post);
			HttpEntity entity = res.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch(Exception e) {
			//logger.warn("httpClient post请求异常",e.getMessage());
		}
		return responseContent;
	}
	
	
}
