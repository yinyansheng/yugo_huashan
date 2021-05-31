package com.yugo.huashan.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yugo.huashan.dto.Group;
import com.yugo.huashan.util.HttpClientUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class HuaShanService {

    public boolean judge() {
        String url = "https://mzfwh.huashan.org.cn/common/api";

        Map<String, String> header = new HashMap<>();
        header.put("Host", "mzfwh.huashan.org.cn");
        header.put("Accept-Encoding", "gzip, deflate, br");
        header.put("Cookie", "JSESSIONID=69938119CB00F1AA8AB24BBE77140AD4; waf_cookie=f97f6ee6-a3e7-46d878547e26b9557bdeba6c27e336c4d304");
        header.put("Connection", "keep-alive");
        header.put("Accept", "application/json, text/plain, */*");
        header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 14_5_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/8.0.5(0x18000528) NetType/WIFI Language/zh_CN");
        header.put("Referer", "https://mzfwh.huashan.org.cn/doctor/detail?id=17433&hospitalid=17");
        header.put("Accept-Language", "zh-cn");
        header.put("X-Requested-With", "XMLHttpRequest");

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("cmd", "DoctorDetailApp");
        queryMap.put("sr", "1");
        queryMap.put("doctorid", "17433");
//        queryMap.put("doctorid", "33807");
        queryMap.put("platform", "m");

        String json = HttpClientUtil.get(url, header, queryMap);

        System.out.println(json);

        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray cliniclist = jsonObject.getJSONArray("cliniclist");

        List<Group> groupList = new ArrayList<>();
        cliniclist.forEach(r -> {
            Group group = JSON.parseObject(JSON.toJSONString(r), Group.class);
            groupList.add(group);
        });

        if (CollectionUtils.isEmpty(groupList)) {
            return false;
        }

        for (Group group : groupList) {
            if (group.getList().stream().anyMatch(r -> "2".equals(r.getStatus()))) {
                return true;
            }
        }

        return false;
    }

}
