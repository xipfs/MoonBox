package net.xipfs.moonbox.rule;

import net.xipfs.moonbox.common.Constants;
import net.xipfs.moonbox.market.domain.Symbol;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/19/18:35
 */

public class ContractRule implements IRule{
    @Override
    public List<Symbol> filter(List<Symbol> symbolList) {
        List<Symbol> data = new ArrayList<>();
        for(Symbol symbol: symbolList){
            if(symbol.getFundingRate() > Constants.MAX_FUNDING_RATE || symbol.getFundingRate() < Constants.MIN_FUNDING_RATE){
                data.add(symbol);
            }
        }
        return data;
    }
}
