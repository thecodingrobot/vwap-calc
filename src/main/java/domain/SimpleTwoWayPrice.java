package domain;

public class SimpleTwoWayPrice implements TwoWayPrice {

    private Instrument instrument;
    private State state;
    double bidPrice;
    double offerPrice;
    private double bidAmount;
    private double offerAmount;

    SimpleTwoWayPrice(Instrument instrument, State state, double bidPrice, double offerPrice, double bidAmount, double offerAmount) {
        this.instrument = instrument;
        this.state = state;
        this.bidPrice = bidPrice;
        this.offerPrice = offerPrice;
        this.bidAmount = bidAmount;
        this.offerAmount = offerAmount;
    }

    @Override
    public Instrument getInstrument() {
        return this.instrument;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public double getBidPrice() {
        return this.bidPrice;
    }

    @Override
    public double getOfferAmount() {
        return this.offerAmount;
    }

    @Override
    public double getOfferPrice() {
        return this.offerPrice;
    }

    @Override
    public double getBidAmount() {
        return this.bidAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleTwoWayPrice that = (SimpleTwoWayPrice) o;

        if (Double.compare(that.bidPrice, bidPrice) != 0) return false;
        if (Double.compare(that.offerPrice, offerPrice) != 0) return false;
        if (Double.compare(that.bidAmount, bidAmount) != 0) return false;
        if (Double.compare(that.offerAmount, offerAmount) != 0) return false;
        return instrument == that.instrument && state == that.state;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = instrument != null ? instrument.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        temp = Double.doubleToLongBits(bidPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(offerPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(bidAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(offerAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public static class Builder {
        private Instrument instrument;
        private State state;
        private double bidPrice;
        private double offerPrice;
        private double bidAmount;
        private double offerAmount;
        public Builder withInstrument(Instrument instrument) {
            this.instrument = instrument;
            return this;
        }
        public Builder withState(State state) {
            this.state = state;
            return this;
        }
        public Builder withBidPrice(double bidPrice) {
            this.bidPrice = bidPrice;
            return this;
        }
        public Builder withOfferPrice(double offerPrice) {
            this.offerPrice = offerPrice;
            return this;
        }
        public Builder withBidAmount(double bidAmount) {
            this.bidAmount = bidAmount;
            return this;
        }
        public Builder withOfferAmount(double offerAmount) {
            this.offerAmount = offerAmount;
            return this;
        }

        public Builder withInitialState(TwoWayPrice twoWayPrice) {
            this.state = twoWayPrice.getState();
            this.instrument = twoWayPrice.getInstrument();
            this.bidPrice = twoWayPrice.getBidPrice();
            this.bidAmount = twoWayPrice.getBidAmount();
            this.offerPrice = twoWayPrice.getOfferPrice();
            this.offerAmount = twoWayPrice.getOfferAmount();
            return this;
        }

        public TwoWayPrice build() {
            // TODO check that all fields are set
            return new SimpleTwoWayPrice(instrument, state, bidPrice, offerPrice, bidAmount, offerAmount);
        }
    }
}
