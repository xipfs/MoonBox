package net.xipfs.moonbox.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.xipfs.moonbox.cache.MarketCache;
import net.xipfs.moonbox.market.domain.MarketType;
import net.xipfs.moonbox.market.domain.Symbol;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/19/9:14
 */


@Slf4j
@Service
public class FishService {
    public String peek(Symbol symbol){
        String str = "";
        if(MarketCache.symbolMap.containsKey(symbol.getBase())){
            String url = "https://www.binance.com/fapi/v1/premiumIndex?symbol="+symbol.getPair();
            String result = HttpUtil.get(url);
            JSONObject jsonObject = JSONObject.parseObject(result);
            Double indexPrice = jsonObject.getDouble("indexPrice");
            Double markPrice = jsonObject.getDouble("markPrice");
            Double lastFundingRate = jsonObject.getDouble("lastFundingRate");
            str += (String.format("%.4f",indexPrice)+" "+String.format("%.4f",markPrice)+" "+String.format("%.4f",lastFundingRate*100)+"%");
        }else{
            String url = "https://www.binance.com/fapi/v1/ticker/price?symbol="+symbol.getPair();
            String result = HttpUtil.get(url);
            JSONObject jsonObject = JSONObject.parseObject(result);
            Double price = jsonObject.getDouble("price");
            str += String.format("%.4f",price);
        }
        return str;
    }
}
