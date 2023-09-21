package net.xipfs.moonbox.quant.strategy;

import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.rules.CrossedDownIndicatorRule;
import org.ta4j.core.rules.CrossedUpIndicatorRule;
import org.ta4j.core.rules.IsRisingRule;
import org.ta4j.core.rules.OverIndicatorRule;

public class StrongEMAStrategy {

    public Strategy buildEMAStrategy(BarSeries series) {
        ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);
        EMAIndicator shortEMA = new EMAIndicator(closePriceIndicator, 15);
        EMAIndicator longEMA = new EMAIndicator(closePriceIndicator, 30);
        MACDIndicator macdIndicator = new MACDIndicator(closePriceIndicator, 12, 26);

        Rule buyingRule = new CrossedUpIndicatorRule(shortEMA, longEMA)  // 向上交叉
                .and(new IsRisingRule(macdIndicator, 2))        // two kline
                .and(new OverIndicatorRule(macdIndicator, 0)); // 水上
        Rule sellingRule = new CrossedDownIndicatorRule(shortEMA, longEMA);
        return new BaseStrategy(buyingRule, sellingRule);
    }
}
