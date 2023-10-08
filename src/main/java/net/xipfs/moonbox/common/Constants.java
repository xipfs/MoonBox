package net.xipfs.moonbox.common;

/**
 * 常量
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/13/14:24
 */

public class Constants {

    // risk Alarm
    public static long MIN_OPEN_INTEREST_VALUE = 400000000;
    public static long OPEN_INTEREST_VALUE = 100000000;
    public static double MIN_FUNDING_RATE  = -0.01;
    public static double MAX_FUNDING_RATE = 0.01;

    public static double MIN_CAP_INTEREST_RADIO = 1;


    public interface INTERVAL {
        String INTERVAL_1m = "1m";
        String INTERVAL_3m = "3m";
        String INTERVAL_5m = "5m";
        String INTERVAL_15m = "15m";
        String INTERVAL_30m = "30m";
        String INTERVAL_1h = "1h";
        String INTERVAL_4M = "4m";
        String INTERVAL_1d = "1d";
        String INTERVAL_3d = "3d";
        String INTERVAL_1w = "1w";
        String INTERVAL_1M = "1M";
    }
}
