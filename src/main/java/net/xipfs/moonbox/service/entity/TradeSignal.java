package net.xipfs.moonbox.service.entity;

import lombok.Data;

@Data
public class TradeSignal {
    private int id;
    private String symbol;
    private String tradeType;
    private long time;
}
