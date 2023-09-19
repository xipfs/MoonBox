package net.xipfs.moonbox.job;

import lombok.extern.slf4j.Slf4j;
import net.xipfs.moonbox.cache.MarketCache;
import net.xipfs.moonbox.market.domain.Symbol;
import net.xipfs.moonbox.service.MarketService;
import net.xipfs.moonbox.service.WeiXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Async
@Slf4j
public class MarketJob {
    @Autowired
    private MarketService marketService;
    @Scheduled(initialDelay = 5000, fixedRate=1000*60*60*12)
    public void obtainSymbols() {
        marketService.queryAllSymbols();
    }

    // cron = "0 2 0,2,4,6,8,10,12,14,16,18,20,22 * * ?"
    @Scheduled(initialDelay = 5000, fixedRate=1000*15)
    public void obtainFundingRate(){
        marketService.queryFundingRate();
    }

    @Scheduled(initialDelay = 5000, fixedRate=1000*5)
    public void obtainOpenInterest(){
        for(Symbol symbol: MarketCache.maxFundingRateList){
            marketService.queryOpenInterest(symbol);
        }
        for(Symbol symbol: MarketCache.minFundingRateList){
            marketService.queryOpenInterest(symbol);
        }
    }

}
