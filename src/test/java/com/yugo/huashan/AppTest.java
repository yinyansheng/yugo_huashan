package com.yugo.huashan;

import static org.junit.Assert.assertTrue;

import com.yugo.huashan.manager.WeChatManager;
import com.yugo.huashan.service.HuaShanService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback(false)
@Slf4j
public class AppTest {

    @Autowired
    private HuaShanService huaShanService;

    @Autowired
    private WeChatManager weChatManager;

    @Test
    public void Test() {
        //System.out.println(huaShanService.judge());
//        weChatManager.send(Sets.newSet("5008574"), "huashan true!");
    }
}
