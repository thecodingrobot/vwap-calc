import domain.*;
import org.junit.Assert;
import org.junit.Test;

public class TestCalculator {
    @Test
    public void testInitialUpdate() {
        TwoWayPrice price = new SimpleTwoWayPrice.Builder()
                .withInstrument(Instrument.INSTRUMENT0)
                .withOfferPrice(3).withOfferAmount(1)
                .withBidPrice(2).withBidAmount(1)
                .build();

        Calculator calc = new CalculatorImpl();
        TwoWayPrice updated = calc.applyMarketUpdate(new MarketUpdateImpl(price, Market.MARKET0));

        Assert.assertEquals(price.getBidPrice(), updated.getBidPrice(), 0.1);
        Assert.assertEquals(price.getOfferPrice(), updated.getOfferPrice(), 0.1);
    }

    @Test
    public void testTwoUpdates() {
        TwoWayPrice twoWayPrice1 = new SimpleTwoWayPrice.Builder()
                .withInstrument(Instrument.INSTRUMENT0)
                .withOfferPrice(3).withOfferAmount(1)
                .withBidPrice(2).withBidAmount(1)
                .build();
        TwoWayPrice twoWayPrice2 = new SimpleTwoWayPrice.Builder()
                .withInstrument(Instrument.INSTRUMENT0)
                .withOfferPrice(2).withOfferAmount(1)
                .withBidPrice(1).withBidAmount(1)
                .build();

        Calculator calc = new CalculatorImpl();
        calc.applyMarketUpdate(new MarketUpdateImpl(twoWayPrice1, Market.MARKET0));
        TwoWayPrice updated2 = calc.applyMarketUpdate(new MarketUpdateImpl(twoWayPrice2, Market.MARKET0));

        Assert.assertEquals((2 + 1) / 2.0d, updated2.getBidPrice(), 0.1);
        Assert.assertEquals((3 + 2) / 2.0d, updated2.getOfferPrice(), 0.1);
    }

    @Test
    public void testThreeUpdates() {
        TwoWayPrice twoWayPrice1 = new SimpleTwoWayPrice.Builder()
                .withInstrument(Instrument.INSTRUMENT0)
                .withOfferPrice(3).withOfferAmount(1)
                .withBidPrice(2).withBidAmount(1)
                .build();
        TwoWayPrice twoWayPrice2 = new SimpleTwoWayPrice.Builder()
                .withInstrument(Instrument.INSTRUMENT0)
                .withOfferPrice(2).withOfferAmount(1)
                .withBidPrice(1).withBidAmount(1)
                .build();
        TwoWayPrice twoWayPrice3 = new SimpleTwoWayPrice.Builder()
                .withInstrument(Instrument.INSTRUMENT0)
                .withOfferPrice(4).withOfferAmount(1)
                .withBidPrice(3).withBidAmount(10)
                .build();

        Calculator calc = new CalculatorImpl();
        calc.applyMarketUpdate(new MarketUpdateImpl(twoWayPrice1, Market.MARKET0));
        calc.applyMarketUpdate(new MarketUpdateImpl(twoWayPrice2, Market.MARKET0));
        TwoWayPrice updated3 = calc.applyMarketUpdate(new MarketUpdateImpl(twoWayPrice3, Market.MARKET0));

        Assert.assertEquals((3 * 10.0d + 2 + 1) / 12.0d, updated3.getBidPrice(), 0.1);
        Assert.assertEquals((4 + 3 + 2) / 3.0d, updated3.getOfferPrice(), 0.1);
    }

    @Test
    public void testUpdatingDifferentInstruments() {

        TwoWayPrice twoWayPrice1 = new SimpleTwoWayPrice.Builder()
                .withInstrument(Instrument.INSTRUMENT0)
                .withOfferPrice(3).withOfferAmount(1)
                .withBidPrice(2).withBidAmount(1)
                .build();
        TwoWayPrice twoWayPrice2 = new SimpleTwoWayPrice.Builder()
                .withInstrument(Instrument.INSTRUMENT1)
                .withOfferPrice(2).withOfferAmount(1)
                .withBidPrice(1).withBidAmount(1)
                .build();

        Calculator calc = new CalculatorImpl();
        TwoWayPrice updated1 = calc.applyMarketUpdate(new MarketUpdateImpl(twoWayPrice1, Market.MARKET0));
        TwoWayPrice updated2 = calc.applyMarketUpdate(new MarketUpdateImpl(twoWayPrice2, Market.MARKET0));

        Assert.assertEquals(twoWayPrice1.getBidPrice(), updated1.getBidPrice(), 0.1);
        Assert.assertEquals(twoWayPrice1.getOfferPrice(), updated1.getOfferPrice(), 0.1);
        Assert.assertEquals(twoWayPrice2.getBidPrice(), updated2.getBidPrice(), 0.1);
        Assert.assertEquals(twoWayPrice2.getOfferPrice(), updated2.getOfferPrice(), 0.1);
    }
}
