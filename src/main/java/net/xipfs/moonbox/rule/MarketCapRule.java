package net.xipfs.moonbox.rule;

import com.alibaba.fastjson2.JSONObject;
import net.xipfs.moonbox.common.Constants;
import net.xipfs.moonbox.market.domain.Symbol;
import net.xipfs.moonbox.util.CoinMarketCapUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/23/16:16
 */

public class MarketCapRule implements IRule{
    @Override
    public List<Symbol> filter(List<Symbol> symbolList) {
        List<Symbol> data = new ArrayList<>();
        for(Symbol symbol: symbolList){
            String resp = CoinMarketCapUtil.getPrice(symbol.getBase());
            if(resp != null){
                JSONObject jsonObject = JSONObject.parseObject(resp);
                Double marketCap = jsonObject.getJSONObject("data").getJSONObject(symbol.getBase()).getJSONObject("quote").getJSONObject("USD")
                        .getDouble("market_cap");
                symbol.setMarketCap(marketCap);
                double capInterestRadio = marketCap / symbol.getSumOpenInterestValue();
                symbol.setCapInterestRadio(capInterestRadio);
                if(capInterestRadio > Constants.MIN_CAP_INTEREST_RADIO){
                    data.add(symbol);
                }
            }
        }
        return data;
    }
}
