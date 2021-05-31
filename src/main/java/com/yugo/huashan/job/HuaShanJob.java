package com.yugo.huashan.job;

import com.yugo.huashan.manager.WeChatManager;
import com.yugo.huashan.service.HuaShanService;
import lombok.extern.slf4j.Slf4j;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class HuaShanJob {

    @Autowired
    private HuaShanService huaShanService;

    @Autowired
    private WeChatManager weChatManager;

    @Scheduled(cron = "0 0/2 * * * ?")
    public void twoMinutesExecute() {
        log.info("每2分钟执行一次：" + getDate());

        if (huaShanService.judge()) {
            weChatManager.send(Sets.newSet("5008574"), "huashan true!");
        }
    }

    private String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

}
