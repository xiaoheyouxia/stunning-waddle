package com.zkn.learnspringboot.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** 
 * 封装了一些采用HttpClient发送HTTP请求的方法
 * 本工具所采用的是最新的HttpComponents-Client-4.5.3
 */
public class HttpClientUtil {
	private Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	private static final int timeOut = 10 * 1000;
	private static PoolingHttpClientConnectionManager connectionManager;

	private static String CHARSET = "UTF-8";

	public static final String CONTENT = "content";
	public static final String STATUSCODE = "statusCode";

	private static HttpClientUtil httpUtil = new HttpClientUtil();

	private HttpClientUtil() {
	}

	public static HttpClientUtil getInstance() {
		return httpUtil;
	}

	private void init() {
		if (connectionManager == null) {
			connectionManager = new PoolingHttpClientConnectionManager();
			connectionManager.setMaxTotal(300);// 整个连接池最大连接数
			connectionManager.setDefaultMaxPerRoute(60);// 每路由最大连接数，默认值是10
		}
	}

	private static void config(HttpRequestBase httpRequestBase) {
		// 配置请求的超时设置
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(timeOut)
				.setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
		httpRequestBase.setConfig(requestConfig);
	}

	/**
	 * 通过连接池获取HttpClient
	 * 
	 * @return HttpClient对象
	 */
	private CloseableHttpClient getHttpClient() {
		init();
		return HttpClients.custom().setConnectionManager(connectionManager).build();
	}

	/**
	 * 发送POST请求
	 * 
	 * @param url URL
	 * @param params 请求参数
	 * @return 返回map集合，响应体内容key为content
	 * @throws UnsupportedEncodingException
	 */
	public Map<String, String> httpPOST(String url, Map<String, String> params) throws Exception {
		logger.info("Http Client Post请求地址: " + url);
		logger.info("Http Client Post请求参数: " + params);
		HttpPost httpPost = new HttpPost(url);
		config(httpPost);

		if (params != null) {
			ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
		}
		return getResult(httpPost);
	}
	
	/**
	 * 发送POST请求【JSON参数】
	 * 
	 * @param url URL
	 * @param params 请求参数
	 * @return 返回map集合，响应体内容key为content
	 * @throws UnsupportedEncodingException
	 */
	public Map<String, String> httpPostJson(String url, JSONObject jsonParam) throws Exception {
		logger.info("Http Client Post Json请求地址: " + url);
		logger.info("Http Client Post Json请求参数: " + jsonParam);
		HttpPost httpPost = new HttpPost(url);
		config(httpPost);
		
		if (jsonParam != null) {
			//解决中文乱码问题
			StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
		}
		return getResult(httpPost);
	}
	
	/**
	 * 发送GET请求
	 * 
	 * @param url URL
	 * @param params 请求参数
	 * @return 返回map集合，响应体内容key为content
	 * @throws UnsupportedEncodingException
	 */
	public Map<String, String> httpGET(String url, Map<String, String> params) throws Exception {
		logger.info("Http Client Get请求地址: " + url);
		logger.info("Http Client Get请求参数: " + params);
		URIBuilder ub = new URIBuilder(url);
		
		if (params != null) {
			ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
			ub.setParameters(pairs);
		}
		HttpGet httpGet = new HttpGet(ub.build());
		config(httpGet);
		return getResult(httpGet);
	}
	
	/**
	 * 设置请求参数
	 * 
	 * @param params 请求参数
	 * @return ArrayList<NameValuePair>
	 */
	private ArrayList<NameValuePair> covertParams2NVPS(Map<String, String> params) {
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> param : params.entrySet()) {
			pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
		}

		return pairs;
	}

	/**
	 * 处理HTTP请求
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, String> getResult(HttpRequestBase request) throws Exception {
		CloseableHttpClient httpClient = getHttpClient();
		CloseableHttpResponse response = null;
		Map<String, String> result = new HashMap<String, String>();
		try {
			response = httpClient.execute(request);
			String statusCode = response.getStatusLine().getStatusCode() + "";
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String content = EntityUtils.toString(entity, CHARSET);
				logger.info("Http Client请求返回结果【content】====" + content);
				result.put(CONTENT, content);
				result.put(STATUSCODE, statusCode);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}