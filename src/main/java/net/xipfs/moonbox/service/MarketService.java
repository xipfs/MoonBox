package net.xipfs.moonbox.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.xipfs.moonbox.cache.MarketCache;
import net.xipfs.moonbox.config.CryptoConfig;
import net.xipfs.moonbox.market.domain.MarketType;
import net.xipfs.moonbox.market.domain.Symbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 交易市场服务
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/13/14:20
 */

@Slf4j
@Service
public class MarketService {
    @Autowired
    private CryptoConfig cryptoConfig;
    /**
     * 查询所有交易对
     */
    public void queryAllSymbols(){
        log.info("start get market symbols ...");
        MarketCache.symbolMap.clear();
        List<String> symbolList = new ArrayList<>();
        String url = "https://api.binance.com/api/v3/exchangeInfo";
        String result = HttpUtil.get(url);
        JSONArray array = JSONObject.parseObject(result).getJSONArray("symbols");
        array.forEach(object -> {
            Symbol symbol = new Symbol();
            JSONObject jsonObject = (JSONObject) object;
            symbol.setMarketType(MarketType.SPOT);
            String quoteAsset = jsonObject.getString("quoteAsset");
            if(StrUtil.equals("USDT",quoteAsset)){
                symbol.setPair(jsonObject.getString("symbol"));
                symbol.setBase(jsonObject.getString("baseAsset"));
                symbol.setQuote(quoteAsset);
                MarketCache.symbolMap.put(symbol.getBase(),symbol.getPair());
                symbolList.add(symbol.getPair());
            }
        });
        FileUtil.writeLines(symbolList,cryptoConfig.getDataPath()+"symbols.txt", CharsetUtil.UTF_8,false);
        log.info("get market symbols end ...");
    }

    public void queryFundingRate(){
        MarketCache.fundingRateMap.clear();
        MarketCache.minFundingRateList.clear();
        MarketCache.maxFundingRateList.clear();
        log.info("start get funding rate ...");
        List<Symbol> symbolList = new ArrayList<>();
        String url = "https://www.binance.com/fapi/v1/premiumIndex";
        String result = HttpUtil.get(url);
        JSONArray array = JSONArray.parseArray(result);
        array.forEach(object -> {
            Symbol symbol = new Symbol();
            JSONObject jsonObject = (JSONObject) object;
            symbol.setMarketType(MarketType.SWAP);
            String str= jsonObject.getString("symbol");
            int length = str.length();
            String quote = str.substring(length - 4);
            if(StrUtil.equals("USDT",quote)){
                String base = str.substring(0, length - 4);
                symbol.setPair(str);
                symbol.setBase(base);
                symbol.setQuote(quote);
                symbol.setFundingRate(jsonObject.getDouble("lastFundingRate"));
                symbolList.add(symbol);
            }
        });
        Collections.sort(symbolList);
        MarketCache.minFundingRateList.addAll(symbolList.subList(0,9));
        MarketCache.maxFundingRateList.addAll(symbolList.subList(symbolList.size()-10,symbolList.size()-1));
        FileUtil.writeLines(symbolList,cryptoConfig.getDataPath()+"fundingRate.txt", CharsetUtil.UTF_8,false);
        log.info("get funding rate end...");
    }
}
