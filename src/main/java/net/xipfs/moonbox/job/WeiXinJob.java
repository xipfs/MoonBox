package net.xipfs.moonbox.job;

import lombok.extern.slf4j.Slf4j;
import net.xipfs.moonbox.cache.MarketCache;
import net.xipfs.moonbox.market.domain.Symbol;
import net.xipfs.moonbox.rule.ContractRule;
import net.xipfs.moonbox.rule.IRule;
import net.xipfs.moonbox.service.WeiXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/19/19:38
 */

@Component
@Async
@Slf4j
public class WeiXinJob {
    @Autowired
    private WeiXinService weiXinService;
    @Scheduled(cron = "0 2 0,8,12,16,20 * * ?")
    public void riskAlarm(){
        StringBuffer sb = new StringBuffer("异常币种: ");
        IRule rule = new ContractRule();
        List<Symbol> symbolList = rule.filter(MarketCache.minFundingRateList);
        List<Symbol> symbolList2 = rule.filter(MarketCache.maxFundingRateList);
        symbolList.addAll(symbolList2);
        if(symbolList.isEmpty()){
            weiXinService.sendFundingMsg("未发现异常币种");
        }else{
            for(Symbol symbol: symbolList){
                sb.append(symbol.getBase()).append(" ");
            }
            weiXinService.sendFundingMsg(sb.toString());
        }
    }
}
