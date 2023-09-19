package net.xipfs.moonbox.service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import net.xipfs.moonbox.cache.MarketCache;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/19/11:31
 */

@Slf4j
@Service
public class WeiXinService {
    public void sendFundingMsg(String content){
        JSONObject param = JSONUtil.createObj();
        param.set("appToken", MarketCache.secretMap.get("WxPusherToken"));
        param.set("content",content);
        param.set("contentType",1);
        param.set("topicIds",new Integer[]{22573});
        param.set("verifyPay",false);
        HttpUtil.post("https://wxpusher.zjiecode.com/api/send/message", param.toString());
    }
}
