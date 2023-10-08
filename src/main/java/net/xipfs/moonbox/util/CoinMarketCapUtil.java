package net.xipfs.moonbox.util;

import net.xipfs.moonbox.cache.MarketCache;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/23/14:36
 */

public class CoinMarketCapUtil {

    public static String getPrice(String symbol){
        String uri = "http://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
        paratmers.add(new BasicNameValuePair("symbol","DODO"));
        paratmers.add(new BasicNameValuePair("convert","USD"));
        try {
            return makeAPICall(uri, paratmers);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        return null;
    }
    public static String makeAPICall(String uri, List<NameValuePair> parameters)
            throws Exception {
        String API_KEY= MarketCache.secretMap.get("CoinMarketCap_API_KEY");
        String response_content = "";
        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());
        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", API_KEY);
        CloseableHttpResponse response = client.execute(request);
        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        return response_content;
    }
    public static void main(String[] args) throws Exception {
        System.out.println(getPrice("DODO"));
    }
}
