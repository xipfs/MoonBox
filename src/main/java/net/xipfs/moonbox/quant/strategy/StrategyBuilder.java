package net.xipfs.moonbox.quant.strategy;

import cn.hutool.core.util.StrUtil;
import net.xipfs.moonbox.quant.common.StrategyType;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Strategy;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/21/15:59
 */

public class StrategyBuilder {
    public static Strategy build(String type, BarSeries series){
        if(StrUtil.equals(StrategyType.DOUBLE_EMA,type)){
            return DoubleEmaStrategy.build(series);
        }
        return null;
    }
}
