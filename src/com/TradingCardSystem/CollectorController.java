package com.TradingCardSystem;

public class CollectorController {
    private Collector collector;
    private CollectorView collectorView;

    public CollectorController(Collector collector, CollectorView collectorView) {
        this.collector = collector;
        this.collectorView = collectorView;
    }

    public boolean addCardToCollection(Collector collector) {
        Card card = collectorView.promptAddCard(collector);
        if (!collector.hasCardWithName(card.getName())) {
            if (collectorView.promptAddCardChoice().equalsIgnoreCase("Y")) {
                collectorView.displayAddCardConfirmation(card);
                collector.addCard(card);
                return true;
            } else {
                collectorView.displayAddCardDenial();
                return false;
            }
        } else {
            if (collectorView.promptDuplicateCard().equalsIgnoreCase("Y")) {
                collector.getCardWithName(card.getName()).incrementCount();
                collectorView.displayAddCardConfirmation(card);
                return true;
            } else {
                collectorView.displayAddCardDenial();
                return false;
            }
        }
    }

    public boolean removeCardFromCollection(Collector collector) {
        collectorView.displayCollection(collector);
        String name = collectorView.promptRemoveCard();
        Card card = collector.getCardWithName(name);

        if (card == null) {
            collectorView.displayCardNotFound();
            return false;
        }

        if (collectorView.promptRemoveCardChoice(name).equalsIgnoreCase("Y")) {
            card.decrementCount();

            if (card.getCount() <= 0 && !collector.isCardInBindersOrDecks(name)) {
                collector.removeCardObject(name);
            }
            collectorView.displayRemoveCardConfirmation(name);

            return true;
        } else {
            collectorView.displayRemoveCardDenial(name);
            return false;
        }
    }

    public boolean createBinder(Collector collector) {
        String name = collectorView.promptBinderName();
        collector.addBinder(name);
        collectorView.displayBinderCreation(name);
        return true;
    }

    public Binder returnBinderChoice() {
        collectorView.displayBinders(collector);
        String name = collectorView.promptBinderOption();

        return collector.getBinderByName(name);
    }

    public boolean createDeck(Collector collector) {
        String name = collectorView.promptDeckName();

        if(collector.hasDeckWithName(name)) {
            System.out.print("Name already exists.");
            return false;
        }
        collector.addDeck(name);
        return true;
    }

    public boolean removeDeck(Collector collector) {
        String name = collectorView.promptDeckName();
        collector.removeDeck(name);
        return true;
    }
}
