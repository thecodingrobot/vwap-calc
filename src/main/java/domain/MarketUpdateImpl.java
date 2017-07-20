package domain;

public class MarketUpdateImpl implements MarketUpdate {

    final private TwoWayPrice twoWayPrice;
    final private Market market;

    public MarketUpdateImpl(TwoWayPrice twoWayPrice, Market market) {
        this.twoWayPrice = twoWayPrice;
        this.market = market;
    }

    @Override
    public Market getMarket() {
        return this.market;
    }

    @Override
    public TwoWayPrice getTwoWayPrice() {
        return this.twoWayPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarketUpdateImpl that = (MarketUpdateImpl) o;

        if (!twoWayPrice.equals(that.twoWayPrice)) return false;
        return market == that.market;
    }

    @Override
    public int hashCode() {
        int result = twoWayPrice.hashCode();
        result = 31 * result + market.hashCode();
        return result;
    }
}
