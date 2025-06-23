package com.TradingCardSystem;

public class CollectorController {
    private Collector collector;
    private CollectorView collectorView;

    public CollectorController(Collector collector, CollectorView collectorView) {
        this.collector = collector;
        this.collectorView = collectorView;
    }

    public boolean addCardToCollection(Collector collector) {
        Card card = collectorView.promptAddCard();
        if (collectorView.promptAddCardConfirmation(card).equalsIgnoreCase("Y")) {
            System.out.println("Added " + card.getName() + " to your collection.");
            collector.addCard(card);
            return true;
        } else {
            System.out.println("Card was not added to the collection.");
            return false;
        }
    }


}
