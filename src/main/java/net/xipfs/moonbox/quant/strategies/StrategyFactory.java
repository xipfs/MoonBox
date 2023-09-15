package net.xipfs.moonbox.quant.strategies;

import org.ta4j.core.BarSeries;
import org.ta4j.core.Strategy;

public class StrategyFactory {

    public Strategy buildStrategy(BarSeries series, String strategyName) {
        if ("EMA".equalsIgnoreCase(strategyName)) {
            return new StrongEMAStrategy().buildEMAStrategy(series);
        } else {
            return new DefaultStrategy().buildStrategy(series);
        }
    }
}
