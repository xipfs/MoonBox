package net.xipfs.moonbox.quant.strategies;

import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.rules.OverIndicatorRule;
import org.ta4j.core.rules.UnderIndicatorRule;

public class DefaultStrategy {

    public Strategy buildStrategy(BarSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("Series cannot be null");
        }
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        EMAIndicator shortEma = new EMAIndicator(closePrice, 15);
        EMAIndicator longEma = new EMAIndicator(closePrice, 30);
        Rule entryRule = new OverIndicatorRule(shortEma, longEma);
        Rule exitRule = new UnderIndicatorRule(shortEma, longEma);

        return new BaseStrategy(entryRule, exitRule);
    }
}
