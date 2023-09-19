package net.xipfs.moonbox.api;

import net.xipfs.moonbox.common.ResponseBean;
import net.xipfs.moonbox.market.domain.MarketType;
import net.xipfs.moonbox.market.domain.Symbol;
import net.xipfs.moonbox.service.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/19/9:12
 */

@RestController
@RequestMapping("/fish")
public class FishController {
    @Autowired
    private FishService fishService;
    @GetMapping("/peek")
    public ResponseBean<String> ai(@RequestParam String base){
        Symbol symbol = new Symbol();
        symbol.setBase(base.toUpperCase());
        symbol.setPair(base.toUpperCase()+"USDT");
        symbol.setQuote("USDT");
        symbol.setMarketType(MarketType.SWAP);
        return ResponseBean.success(fishService.peek(symbol));
    }
}
