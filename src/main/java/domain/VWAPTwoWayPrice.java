package domain;

public class VWAPTwoWayPrice extends SimpleTwoWayPrice {

    private double bidDotProduct;
    private double bidWeight;
    private double offerDotProduct;
    private double offerWeight;

    public VWAPTwoWayPrice(TwoWayPrice n) {
        super(n.getInstrument(), n.getState(), n.getBidPrice(), n.getOfferPrice(), n.getBidAmount(), n.getOfferAmount());

        bidDotProduct = n.getBidPrice() * n.getBidAmount();
        bidWeight = n.getBidAmount();

        offerDotProduct = n.getOfferPrice() * n.getOfferAmount();
        offerWeight = n.getOfferAmount();
    }

    public VWAPTwoWayPrice update(TwoWayPrice n) {
        if (!getInstrument().equals(n.getInstrument())) {
            throw new IllegalArgumentException("Cannot calculate VWAP for two different instruments");
        }

        bidDotProduct += n.getBidPrice() * n.getBidAmount();
        bidWeight += n.getBidAmount();

        offerDotProduct += n.getOfferPrice() * n.getOfferAmount();
        offerWeight += n.getOfferAmount();

        this.bidPrice = bidDotProduct / bidWeight;
        this.offerPrice = offerDotProduct / offerWeight;

        return this;
    }
}
