package com.yugo.huashan.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

/**
 * @author yinyansheng
 */
@Slf4j
public class HttpLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        Instant start = Instant.now();
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response, start);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) {
        if (log.isInfoEnabled()) {
            log.info("[HttpLoggingInterceptor]logRequest,URI:{},Method:{},Headers:{},Request body:{}"
                    , request.getURI(), request.getMethod(), request.getHeaders(), new String(body, StandardCharsets.UTF_8));
        }
    }

    private void logResponse(ClientHttpResponse response, Instant instant) throws IOException {
        if (log.isInfoEnabled()) {
            log.info("[HttpLoggingInterceptor]logResponse,Status code:{},Status text:{},Headers:{},Response body:{},耗时:{}ms"
                    , response.getStatusCode(), response.getStatusText(), response.getHeaders(), StreamUtils.copyToString(response.getBody(), Charset.defaultCharset())
                    , Duration.between(instant, Instant.now()).toMillis());
        }
    }
}