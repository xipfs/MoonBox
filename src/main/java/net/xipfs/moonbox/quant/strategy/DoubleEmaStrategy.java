package net.xipfs.moonbox.quant.strategy;

import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.rules.*;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/21/15:34
 */

public class DoubleEmaStrategy{
    public static Strategy build(BarSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("Series cannot be null");
        }
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        EMAIndicator shortEma = new EMAIndicator(closePrice, 15);
        EMAIndicator longEma = new EMAIndicator(closePrice, 30);
        Rule entryRule = new CrossedUpIndicatorRule(shortEma,longEma);
        Rule exitRule = new CrossedDownIndicatorRule(shortEma,longEma);
        return new BaseStrategy(entryRule, exitRule);
    }
}
