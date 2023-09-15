package net.xipfs.moonbox.api;

import net.xipfs.moonbox.cache.MarketCache;
import net.xipfs.moonbox.common.ResponseBean;
import net.xipfs.moonbox.market.domain.Symbol;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 获取市场信息
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/15/10:54
 */

@RestController
@RequestMapping("/market")
public class MarketController {

    @GetMapping("/fundingRate/max")
    public ResponseBean<List<Symbol>> obtainMaxFundingRate(){
        return ResponseBean.success(MarketCache.maxFundingRateList);
    }
    @GetMapping("/fundingRate/min")
    public ResponseBean<List<Symbol>> obtainMinFundingRate(){
        return ResponseBean.success(MarketCache.minFundingRateList);
    }
}
