package com.yugo.huashan.manager;

import com.alibaba.fastjson.JSON;
import com.yugo.huashan.manager.model.WeChatMessageModel;
import com.yugo.huashan.util.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Set;

@Slf4j
@Component
public class WeChatManager {

    @Value("${sso.root_path:}")
    private String ssoApiDomain;

    @Value("${sso.api_app_id:}")
    private String apiAppId;

    @Value("${sso.api_app_secret:}")
    private String apiAppSecret;

    private static final String WECHAT_SEND_PATH = "/wechat/sendText";

    public String send(Set<String> jobNumberSet, String content) {
        if (CollectionUtils.isEmpty(jobNumberSet)) {
            log.warn("[WeChatManager]工号列表为空");
        }

        if (StringUtils.isEmpty(content)) {
            log.warn("[WeChatManager]发送内容为空");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("appId", apiAppId);
        headers.add("secret", apiAppSecret);

        WeChatMessageModel weChatMessageModel = new WeChatMessageModel();
        weChatMessageModel.setContent(content);
        weChatMessageModel.setJobNumbers(new ArrayList<>(jobNumberSet));

        String response = RestTemplateUtil.postJson(ssoApiDomain.concat(WECHAT_SEND_PATH), headers, JSON.toJSONString(weChatMessageModel));
        return response;
    }

}
