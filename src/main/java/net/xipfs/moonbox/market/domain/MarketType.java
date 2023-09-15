package net.xipfs.moonbox.market.domain;

/**
 * SPOT：币币
 * MARGIN：币币杠杆
 * SWAP：永续合约
 * FUTURES：交割合约
 * OPTION：期权
 * ANY： 全部
 */
public enum MarketType {
    SPOT,
    FUTURES,
    SWAP,
    OPTION,
}
