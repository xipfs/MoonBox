package net.xipfs.moonbox.market.domain;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;

@Data
public class Symbol implements Comparable<Symbol>{
    private MarketType marketType;
    private String pair;
    private String base;
    private String quote;
    private Double fundingRate;

    @Override
    public int compareTo(@NotNull Symbol o) {
        return Double.compare(fundingRate,o.fundingRate);
    }

    @Override
    public String toString(){
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        return pair+" "+nf.format(fundingRate);
    }
}
