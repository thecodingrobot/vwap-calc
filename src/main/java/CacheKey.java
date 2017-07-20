import domain.Instrument;
import domain.Market;

class CacheKey {
    private final Market market;
    private final Instrument instrument;

    private CacheKey(Market market, Instrument instrument) {
        this.market = market;
        this.instrument = instrument;
    }

    public static CacheKey build(Market market, Instrument instrument) {
        return new CacheKey(market, instrument);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CacheKey cacheKey = (CacheKey) o;

        if (market != cacheKey.market) return false;
        return instrument == cacheKey.instrument;
    }

    @Override
    public int hashCode() {
        int result = market.hashCode();
        result = 31 * result + instrument.hashCode();
        return result;
    }
}
