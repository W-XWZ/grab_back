package com.yafco.grab.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;


/**
 * http工具集合
 * @author kevin_gm
 *
 */
public class HttpUtil {


	 private static Log log = LogFactory.getLog(HttpUtil.class); 
	 
	/** 
	 * 
     * 调用 API 
     *  
     * @param parameters 
     * @return 
     */  
    public static String doPost(String apiURL,String parameters) {  
        String body = null;  
        HttpClient httpClient = new DefaultHttpClient();  
        HttpPost method = new HttpPost(apiURL);  
  
        if (method != null & parameters != null  && !"".equals(parameters.trim())) {  
            try {  
  
                // 建立一个NameValuePair数组，用于存储欲传送的参数  
                method.addHeader("Content-type","application/json; charset=utf-8");  
                method.setHeader("Accept", "application/json");  
                method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));  
  
                HttpResponse response = httpClient.execute(method);  
                  
                int statusCode = response.getStatusLine().getStatusCode();  
                
                if (statusCode != HttpStatus.SC_OK) {  
                	log.error("Method failed:" + response.getStatusLine());
                }  
  
                // Read the response body  
                body = EntityUtils.toString(response.getEntity());  
  
            } catch (IOException e) {  
                log.error("执行HTTP POST请求" + apiURL + "时，发生异常！", e); 
            } finally {  
            }  
  
        }  
        return body;  
    } 
    
    /**
     * get请求
     * @param url
     * @return
     * @throws Exception
     */
    public static String doGet(String url) {  
        BufferedReader in = null;  
  
        String content = null;  
        try {  
            // 定义HttpClient  
            HttpClient client = new DefaultHttpClient();  
            // 实例化HTTP方法  
            HttpGet request = new HttpGet();  
            request.setURI(new URI(url));  
            HttpResponse response = client.execute(request);  
  
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"utf-8"));  
            StringBuffer sb = new StringBuffer("");  
            String line = "";  
            String NL = System.getProperty("line.separator");  
            while ((line = in.readLine()) != null) {  
                sb.append(line + NL);  
            }  
            in.close();  
            content = sb.toString();  
        } catch (IOException e) {  
            log.error("执行HTTP Get请求" + url + "时，发生异常！", e); 
        }finally {  
            if (in != null) {  
                try {  
                    in.close();// 最后要关闭BufferedReader  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
            return content;  
        }  
    }  
   
	/**
	 * 
	 * 回写response
	 * @param response
	 * @param json
	 * @throws IOException
	 */
	public static final void flushHttpResponse(HttpServletResponse response, String contentType, String content) 
			throws IOException {
		response.setContentType(contentType);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		StringBuilder builder = new StringBuilder();
		builder.append(content);
		out.print(builder.toString());
		out.close();
	}
	
	/**
	 * 
	 * 回写response
	 * @param response
	 * @param json
	 * @throws IOException
	 */
	public static final void flushHttpResponseWithJson(HttpServletResponse response, String json) 
			throws IOException {
		flushHttpResponse(response, "application/json;charset=utf-8", json);
	}
	
	public static void main(String[] args) throws IOException{
		String filePath = "G:\\document\\html\\grab_backend\\WebContent\\newData\\getSheetList.txt";
		BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
	    if(!reader.ready())
	    {
	        System.out.println("文件流暂时无法读取");
	    }
	    String line = null;
	    String res = "";
	    while((line = reader.readLine())!=null)
	    {
	    	res += line;
	    }
	    reader.close();
		JSONObject result1 = JSON.parseObject(res);
		JSONArray resultArray = result1.getJSONObject("result").getJSONObject("page").getJSONArray("list");
		
	}
}
