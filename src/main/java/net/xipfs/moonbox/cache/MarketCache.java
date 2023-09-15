package net.xipfs.moonbox.cache;

import net.xipfs.moonbox.market.domain.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketCache {
    public static Map<String, String> symbolMap = new HashMap<>();
    public static Map<String, Symbol> fundingRateMap = new HashMap<>();
    public static List<String> monitorSymbolList = new ArrayList<>();


    // TOP 10 SHORT
    public static List<Symbol> minFundingRateList = new ArrayList<>(10);

    // TOP 10 LONG
    public static List<Symbol> maxFundingRateList = new ArrayList<>(10);
}
