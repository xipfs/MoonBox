package net.xipfs.moonbox.service.dao;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import net.xipfs.moonbox.service.entity.TradeSignal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TradeSignalDao {
    static {
        String sql = "CREATE TABLE IF NOT EXISTS T_TRADE_SIGNAL  " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " SYMBOL         CHAR(16)       NOT NULL, " +
                " TRADE_TYPE     CHAR(8)        NOT NULL, " +
                " TRADE_TIME     INTEGER        NOT NULL) ";
        try {
            Db.use().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSignal(TradeSignal signal) {
        try {
            Db.use().insert(
                    Entity.create("TRADE_SIGNAL")
                            .set("SYMBOL", signal.getSymbol())
                            .set("TRADE_TYPE", signal.getTradeType())
                            .set("TRADE_TIME", signal.getTime())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TradeSignal> queryAll() {
        try {
            List<TradeSignal> tradeSignalList = new ArrayList<>();
            List<Entity> dataList = Db.use().findAll("TRADE_SIGNAL");
            for (Entity entity : dataList) {
                TradeSignal tradeSignal = new TradeSignal();
                tradeSignal.setId(entity.getInt("ID"));
                tradeSignal.setSymbol(entity.getStr("SYMBOL"));
                tradeSignal.setTradeType(entity.getStr("TRADE_TYPE"));
                tradeSignal.setTime(entity.getInt("TRADE_TIME"));
                tradeSignalList.add(tradeSignal);
            }
            return tradeSignalList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
