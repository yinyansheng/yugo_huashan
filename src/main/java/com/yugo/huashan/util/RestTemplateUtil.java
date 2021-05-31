package com.yugo.huashan.util;

import com.yugo.huashan.enums.RestTemplateEnum;
import org.springframework.http.*;

/**
 * @author yinyansheng
 */
public class RestTemplateUtil {

    private static String exchangeJson(String reqUrl, HttpMethod httpMethod, String reqJsonStrParam) {
        //设置 Header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //设置参数
        HttpEntity<String> requestEntity = new HttpEntity<>(reqJsonStrParam, httpHeaders);
        //执行请求
        ResponseEntity<String> resp = RestTemplateEnum.SINGLE_INSTANCE.getRestTemplate()
                .exchange(reqUrl, httpMethod, requestEntity, String.class);
        //返回请求返回值
        return resp.getBody();
    }

    private static String exchangeJson(String reqUrl, HttpHeaders httpHeaders, HttpMethod httpMethod, String reqJsonStrParam) {
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //设置参数
        HttpEntity<String> requestEntity = new HttpEntity<>(reqJsonStrParam, httpHeaders);
        //执行请求
        ResponseEntity<String> resp = RestTemplateEnum.SINGLE_INSTANCE.getRestTemplate()
                .exchange(reqUrl, httpMethod, requestEntity, String.class);
        //返回请求返回值
        return resp.getBody();
    }

    public static String postJson(String reqUrl, String reqJsonStrParam) {
        return exchangeJson(reqUrl, HttpMethod.POST, reqJsonStrParam);
    }

    public static String postJson(String reqUrl, HttpHeaders httpHeaders, String reqJsonStrParam) {
        return exchangeJson(reqUrl, httpHeaders, HttpMethod.POST, reqJsonStrParam);
    }

    public static String getJson(String reqUrl) {
        return exchangeJson(reqUrl, HttpMethod.GET, null);
    }

    public static String getJson(String reqUrl,HttpHeaders httpHeaders,String reqJsonStrParam) {
        return exchangeJson(reqUrl, httpHeaders,HttpMethod.GET, reqJsonStrParam);
    }

    public static String postForm(String reqUrl, String reqJsonStrParam) {
        return exchangeForm(reqUrl, HttpMethod.POST, reqJsonStrParam);
    }

    public static String getForm(String reqUrl) {
        return exchangeForm(reqUrl, HttpMethod.GET, null);
    }

    public static String exchangeForm(String reqUrl, HttpMethod httpMethod, String reqFormPara) {
        //设置 Header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //设置参数
        HttpEntity<String> requestEntity = new HttpEntity<>(reqFormPara, httpHeaders);
        //执行请求
        ResponseEntity<String> resp = RestTemplateEnum.SINGLE_INSTANCE.getRestTemplate()
                .exchange(reqUrl, httpMethod, requestEntity, String.class);
        //返回请求返回值
        return resp.getBody();
    }
}
