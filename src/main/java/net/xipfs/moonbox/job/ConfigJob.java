package net.xipfs.moonbox.job;

import net.xipfs.moonbox.cache.MarketCache;
import net.xipfs.moonbox.config.MoonBoxConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/19/17:07
 */

public class ConfigJob {

    @Autowired
    private MoonBoxConfig config;

    @Scheduled(initialDelay = 500, fixedRate=1000*60*60)
    public void loadConfig() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(config.getSecretPath()));
            MarketCache.secretMap.put("WxPusherToken",props.getProperty("WxPusherToken"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
