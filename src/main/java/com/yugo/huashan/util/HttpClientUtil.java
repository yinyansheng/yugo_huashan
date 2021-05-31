package com.yugo.huashan.util;


import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtil {

    /**
     * 编码格式。发送编码格式统一用UTF-8
     */
    private static final String ENCODING = "UTF-8";

    public static String get(String url, Map<String, String> header, Map<String, String> paramsMap) {
        String body = "";
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //装填参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (paramsMap != null) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        try {
            //转换为键值对
            String str = EntityUtils.toString(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
            //创建Get请求
            HttpGet httpGet = new HttpGet(url + "?" + str);
            if (header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }
            //执行Get请求，
            CloseableHttpResponse response = client.execute(httpGet);
            //获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            //释放链接
            response.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return body;
    }


    public static String get(String url, Map<String, String> paramsMap) {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json;charset=utf-8");
        return get(url, header, paramsMap);
    }


    public static String post(String url, Map<String, String> header, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (header != null) {
                for (Map.Entry<String, String> param : header.entrySet()) {
                    method.addHeader(param.getKey(), param.getValue());
                }
            }
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity, ENCODING);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return responseText;
    }

    public static String postJson(String url, Map<String, String> headerMap, String body) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (headerMap != null) {
                for (Map.Entry<String, String> param : headerMap.entrySet()) {
                    method.addHeader(param.getKey(), param.getValue());
                }
            }
            method.setEntity(new StringEntity(body, ENCODING));
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity, ENCODING);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }

    public static String postJson(String url, String body) {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json;charset=utf-8");
        return postJson(url, header, body);
    }

    public static String putJson(String url, Map<String, String> headerMap, String body) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPut method = new HttpPut(url);
            if (headerMap != null) {
                for (Map.Entry<String, String> param : headerMap.entrySet()) {
                    method.addHeader(param.getKey(), param.getValue());
                }
            }
            method.setEntity(new StringEntity(body, ENCODING));
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity, ENCODING);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return responseText;
    }
}
