package com.yugo.huashan.manager.model;

import lombok.Data;

import java.util.List;

/**
 * 企业微信发送消息基础参数
 */
@Data
public class WeChatMessageModel {
    /**
     * 成员ID列表, 最多支持1000个
     */
    private List<String> userIds;

    /**
     * 部门ID列表, 最多支持100个
     */
    private List<String> departmentIds;

    /**
     * 标签ID列表, 最多支持100个
     */
    private List<String> tagIds;

    /**
     * 工号列表，最多支持1000个，UserID列表和工号列表，两个必传一个
     */
    private List<String> jobNumbers;

    private String appId;

    private String appSecret;

    private String content;
}
