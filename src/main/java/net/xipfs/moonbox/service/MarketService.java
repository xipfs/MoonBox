package net.xipfs.moonbox.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.xipfs.moonbox.cache.MarketCache;
import net.xipfs.moonbox.config.MoonBoxConfig;
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
@Data
public class MarketService {
    @Autowired
    private MoonBoxConfig moonBoxConfig;
    /**
     * 查询所有交易对
     */
    public void queryAllSymbols(){
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
        FileUtil.writeLines(symbolList, moonBoxConfig.getDataPath()+"symbols.txt", CharsetUtil.UTF_8,false);
    }

    /**
     *  获取资金费率
     */
    public void queryFundingRate(){
        MarketCache.fundingRateMap.clear();
        MarketCache.minFundingRateList.clear();
        MarketCache.maxFundingRateList.clear();
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
        MarketCache.minFundingRateList.addAll(symbolList.subList(0,10));
        MarketCache.maxFundingRateList.addAll(CollUtil.reverse(symbolList).subList(0,10));
        FileUtil.writeLines(symbolList, moonBoxConfig.getDataPath()+"fundingRate.txt", CharsetUtil.UTF_8,false);
    }

    public boolean queryFundingRate(Symbol symbol){
        if(MarketCache.symbolMap.containsKey(symbol.getBase())){
            String url = "https://www.binance.com/fapi/v1/premiumIndex?symbol="+symbol.getPair();
            String result = HttpUtil.get(url);
            JSONObject jsonObject = JSONObject.parseObject(result);
            symbol.setFundingRate(jsonObject.getDouble("lastFundingRate"));
            return true;
        }else{
            return false;
        }

    }

    /**
     * 获取持仓信息
     *
     * @param symbol 交易对
     */
    public void queryOpenInterest(Symbol symbol){
        MarketCache.sendList.clear();
        String url = "https://www.binance.com/futures/data/openInterestHist?symbol="+symbol.getPair()+"&period=5m&limit=1";
        String result = HttpUtil.get(url);
        JSONArray array = JSONArray.parseArray(result);
        array.forEach(object -> {
            JSONObject jsonObject = (JSONObject) object;
            Double sumOpenInterest= jsonObject.getDouble("sumOpenInterest");
            Double sumOpenInterestValue = jsonObject.getDouble("sumOpenInterestValue");
            symbol.setSumOpenInterest(sumOpenInterest == null? 0.0 : sumOpenInterest);
            symbol.setSumOpenInterestValue(sumOpenInterestValue == null? 0.0: sumOpenInterestValue);
        });
    }
}
