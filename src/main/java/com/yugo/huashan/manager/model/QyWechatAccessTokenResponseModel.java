package com.yugo.huashan.manager.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author dxl
 */
@Data
public class QyWechatAccessTokenResponseModel {

    private Integer errcode;

    private String errmsg;

    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "expires_in")
    private Integer expiresIn;
}
