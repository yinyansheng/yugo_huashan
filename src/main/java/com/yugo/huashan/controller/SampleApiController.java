package com.yugo.huashan.controller;

import com.yugo.huashan.util.HttpClientUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleApiController {

    @RequestMapping(value = "/hello")
    public String hello(String name) {
        return "hello:" + name;
    }

    public static void main(String[] args) {
        String s = HttpClientUtil.get("http://www.baidu.com", null);
        System.out.println(s);
    }

}
