package net.xipfs.moonbox.test.market;

import lombok.extern.slf4j.Slf4j;
import net.xipfs.moonbox.service.MarketService;
import net.xipfs.moonbox.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/12/16:16
 */

@Slf4j
public class MarketTest extends BaseTest {
    @Autowired
    private MarketService marketService;
    @Test
    public void testFetchSpotMarkets(){

    }

    @Test
    public void testFundingRate(){
        marketService.queryFundingRate();
    }
}
