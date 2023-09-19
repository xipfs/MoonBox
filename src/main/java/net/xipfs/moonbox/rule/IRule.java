package net.xipfs.moonbox.rule;

import net.xipfs.moonbox.market.domain.Symbol;

import java.util.List;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/19/17:45
 */


public interface IRule {
    List<Symbol> filter(List<Symbol> symbolList);
}
