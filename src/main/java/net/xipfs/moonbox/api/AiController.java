package net.xipfs.moonbox.api;

import net.xipfs.moonbox.common.ResponseBean;
import net.xipfs.moonbox.market.domain.MarketType;
import net.xipfs.moonbox.market.domain.Symbol;
import net.xipfs.moonbox.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * AI分析
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/18/10:40
 */

@RestController
@RequestMapping("/ai")
public class AiController {
    @Autowired
    private MarketService marketService;
    @GetMapping("/analysis")
    public ResponseBean<String> ai(@RequestParam String base){
        Symbol symbol = new Symbol();
        symbol.setBase(base.toUpperCase());
        symbol.setPair(base.toUpperCase()+"USDT");
        symbol.setQuote("USDT");
        symbol.setMarketType(MarketType.SWAP);
        if(marketService.queryFundingRate(symbol)){
            marketService.queryOpenInterest(symbol);
            return ResponseBean.success(symbol.toString());
        }else{
            return ResponseBean.failed("未查询到改币种信息");
        }
    }
}
