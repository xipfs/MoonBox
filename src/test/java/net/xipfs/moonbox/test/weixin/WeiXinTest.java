package net.xipfs.moonbox.test.weixin;

import lombok.extern.slf4j.Slf4j;
import net.xipfs.moonbox.service.WeiXinService;
import net.xipfs.moonbox.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/19/16:47
 */

@Slf4j
public class WeiXinTest extends BaseTest {
    @Autowired
    private WeiXinService weiXinService;

    @Test
    public void sendMsg(){
        weiXinService.sendFundingMsg("test");
    }
}
