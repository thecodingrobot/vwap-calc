import domain.MarketUpdate;
import domain.TwoWayPrice;

public interface Calculator {
    TwoWayPrice applyMarketUpdate(final MarketUpdate twoWayMarketPrice);
}