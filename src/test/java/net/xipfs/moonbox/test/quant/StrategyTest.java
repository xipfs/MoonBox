package net.xipfs.moonbox.test.quant;

import net.xipfs.moonbox.common.Constants;
import net.xipfs.moonbox.market.domain.Symbol;
import net.xipfs.moonbox.quant.common.Candlestick;
import net.xipfs.moonbox.quant.common.StrategyType;
import net.xipfs.moonbox.quant.strategy.StrategyBuilder;
import net.xipfs.moonbox.service.MarketService;
import net.xipfs.moonbox.test.BaseTest;
import net.xipfs.moonbox.util.Ta4jUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ta4j.core.*;
import org.ta4j.core.num.DecimalNum;

import java.time.ZonedDateTime;
import java.util.List;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Stack;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/21/15:54
 */

public class StrategyTest extends BaseTest {

    @Autowired
    private MarketService marketService;

    @Test
    public void testDoubleEmaStrategy(){
        Symbol symbol = new Symbol();
        symbol.setPair("TRBUSDT");
        List<Candlestick> candlestickList = marketService.queryKlines(symbol, Constants.INTERVAL.INTERVAL_1h);
        BarSeries barSeries = Ta4jUtil.convertToBarSeries(candlestickList);
        Strategy strategy = StrategyBuilder.build(StrategyType.DOUBLE_EMA,barSeries);
        BarSeriesManager seriesManager = new BarSeriesManager(barSeries);
        TradingRecord tradingRecord = seriesManager.run(strategy);
        int barSize = barSeries.getBarCount();
        int index = barSize - 1;
        Bar latestBar = barSeries.getBar(index);
        ZonedDateTime beginTime = latestBar.getBeginTime();
        String timeStr = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(beginTime);
        StringBuilder sb = new StringBuilder();
        String preSpace = "  ";
        String lineSplitter = "\n";
        if(strategy == null){
            return;
        }
        if (strategy.shouldEnter(index)) {
            if (tradingRecord.enter(index, latestBar.getClosePrice(), DecimalNum.valueOf(10))) {
                Trade entry = tradingRecord.getLastEntry();
                sb.append("Double EMA: ")
                        .append(preSpace).append("时间: ").append(timeStr).append(lineSplitter)
                        .append(preSpace).append("买入信号").append(lineSplitter)
                        .append(preSpace).append("当前价格:").append(entry.getNetPrice().doubleValue()).append(lineSplitter);
            }
        } else if (strategy.shouldExit(index)) {
            if (tradingRecord.exit(index, latestBar.getClosePrice(), DecimalNum.valueOf(10))) {
                Trade exit = tradingRecord.getLastEntry();
                sb.append("Double EMA: ")
                        .append(preSpace).append("时间: ").append(timeStr).append(lineSplitter)
                        .append(preSpace).append("卖出信号").append(lineSplitter)
                        .append(preSpace).append("当前价格:").append(exit.getNetPrice().doubleValue()).append(lineSplitter);
            }
        }
    }
}
