import domain.*;

import java.util.HashMap;
import java.util.Map;

class CalculatorImpl implements Calculator {
    private final Map<CacheKey, VWAPTwoWayPrice> priceTracker = new HashMap<>();

    @Override
    public TwoWayPrice applyMarketUpdate(MarketUpdate twoWayMarketPrice) {

        Market market = twoWayMarketPrice.getMarket();
        Instrument instrument = twoWayMarketPrice.getTwoWayPrice().getInstrument();
        CacheKey key = CacheKey.build(market, instrument);

        return priceTracker.merge(key,
                new VWAPTwoWayPrice(twoWayMarketPrice.getTwoWayPrice()),
                (oldPrice, newPrice) -> oldPrice.update(newPrice));
    }
}
