package net.xipfs.moonbox.util;


import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;
import net.xipfs.moonbox.cache.MarketCache;
import net.xipfs.moonbox.quant.PrivateConfig;
import org.ta4j.core.num.Num;

import java.util.LinkedHashMap;
import java.util.Map;


public class BinanceUtil {
    private static SpotClient client = null;

    public static SpotClient getSpotClient() {
        if (client == null) {
            client = new SpotClientImpl(MarketCache.secretMap.get("BN_API_KEY"),MarketCache.secretMap.get("BN_SECRET_KEY"));
        }
        return client;
    }

    public static String klines(String pair, String interval){
        Map<String, Object> parameters = new LinkedHashMap<>();
        SpotClient client =getSpotClient();
        parameters.put("symbol", pair);
        parameters.put("interval", interval);
        return client.createMarket().klines(parameters);
    }
}