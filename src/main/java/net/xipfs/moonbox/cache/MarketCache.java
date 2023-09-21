package net.xipfs.moonbox.cache;

import net.xipfs.moonbox.market.domain.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketCache {
    /** 秘密文件 */
    public static Map<String,String> secretMap = new HashMap<>();
    public static Map<String, String> symbolMap = new HashMap<>();
    public static Map<String, Symbol> fundingRateMap = new HashMap<>();
    // TOP 10 SHORT
    public static List<Symbol> minFundingRateList = new ArrayList<>();

    // TOP 10 LONG
    public static List<Symbol> maxFundingRateList = new ArrayList<>();

    public static List<String> sendList = new ArrayList<>();

}
