package net.xipfs.moonbox.util;


import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;
import net.xipfs.moonbox.quant.PrivateConfig;
import org.ta4j.core.num.Num;


public class BinanceUtil {
    private static Num LAST_BAR_CLOSE_PRICE;
    private static SpotClient client = null;

    public static SpotClient getSpotClient() {
        if (client == null) {
            client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        }
        return client;
    }

    public static void main(String[] args) throws Exception {
    }
}