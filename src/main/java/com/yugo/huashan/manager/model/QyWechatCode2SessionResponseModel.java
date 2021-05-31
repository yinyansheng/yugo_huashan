package com.yugo.huashan.manager.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author dxl
 */
@Data
public class QyWechatCode2SessionResponseModel {

    private Integer errcode;

    private String errmsg;

    private String corpid;

    private String userid;

    @JSONField(name = "session_key")
    private String sessionKey;
}
