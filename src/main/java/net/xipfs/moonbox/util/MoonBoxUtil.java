package net.xipfs.moonbox.util;

import net.xipfs.moonbox.cache.MarketCache;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/19/17:22
 */

public class MoonBoxUtil {
    public static void loadSecretCache(String secretPath){
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(secretPath));
            MarketCache.secretMap.put("WxPusherToken",props.getProperty("WxPusherToken"));
            MarketCache.secretMap.put("CoinMarketCap_API_KEY",props.getProperty("CoinMarketCap_API_KEY"));
            MarketCache.secretMap.put("BN_API_KEY",props.getProperty("BN_API_KEY"));
            MarketCache.secretMap.put("BN_SECRET_KEY",props.getProperty("BN_SECRET_KEY"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
