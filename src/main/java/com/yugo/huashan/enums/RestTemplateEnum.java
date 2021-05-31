package com.yugo.huashan.enums;

import com.yugo.huashan.common.HttpLoggingInterceptor;
import lombok.Getter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yinyansheng
 */


public enum RestTemplateEnum {
    SINGLE_INSTANCE;

    @Getter
    private RestTemplate restTemplate;

    RestTemplateEnum() {
        restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(10_000))
                .setReadTimeout(Duration.ofMillis(10_000))
                .build();

        // 设置日志拦截
        ClientHttpRequestInterceptor ri = new HttpLoggingInterceptor();
        List<ClientHttpRequestInterceptor> ris = new ArrayList<>();
        ris.add(ri);
        restTemplate.setInterceptors(ris);
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    }
}