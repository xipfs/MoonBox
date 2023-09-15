package net.xipfs.moonbox.util;

import net.xipfs.moonbox.quant.core.Candlestick;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.num.DecimalNum;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class QuantUtil {
    public static BaseBar convertToBaseBar(Candlestick candlestick) {
        ZonedDateTime closeTime = getZonedDateTime(candlestick.getCloseTime());
        Duration candleDuration = Duration.ofMillis(candlestick.getCloseTime()
                - candlestick.getOpenTime());
        DecimalNum openPrice = DecimalNum.valueOf(candlestick.getOpen());
        DecimalNum closePrice = DecimalNum.valueOf(candlestick.getClose());
        DecimalNum highPrice = DecimalNum.valueOf(candlestick.getHigh());
        DecimalNum lowPrice = DecimalNum.valueOf(candlestick.getLow());
        DecimalNum volume = DecimalNum.valueOf(candlestick.getVolume());

        return BaseBar.builder(DecimalNum::valueOf, Number.class).timePeriod(candleDuration).endTime(closeTime)
                .openPrice(openPrice).highPrice(highPrice).lowPrice(lowPrice).closePrice(closePrice).volume(volume)
                .build();
    }

    public static BarSeries convertToBarSeries(
            List<Candlestick> candlesticks) {
        BarSeries series = new BaseBarSeries();
        for (Candlestick candlestick : candlesticks) {
            series.addBar(convertToBaseBar(candlestick));
        }
        return series;
    }

    public static ZonedDateTime getZonedDateTime(Long timestamp) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp),
                ZoneId.systemDefault());
    }

    public static boolean isSameBaseBar(Candlestick candlestick, BaseBar baseBar) {
        return baseBar.getEndTime().equals(
                getZonedDateTime(candlestick.getCloseTime()));
    }
}
