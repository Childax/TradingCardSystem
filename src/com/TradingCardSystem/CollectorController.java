package com.TradingCardSystem;

/**
 * The {@code CollectorController} class manages interactions between the {@code Collector}
 * model and the {@code CollectorView}. It handles high-level operations such as adding/removing
 * cards and creating binders or decks.
 */
public class CollectorController {
    private Collector collector;
    private CollectorView collectorView;

    /**
     * Constructs a {@code CollectorController} with the given collector and view.
     *
     * @param collector the {@code Collector} model to manipulate
     * @param collectorView the view used to display prompts and results to the user
     */
    public CollectorController(Collector collector, CollectorView collectorView) {
        this.collector = collector;
        this.collectorView = collectorView;
    }

    /**
     * Prompts the user to add a card to the collector's collection.
     * If the card already exists, it handles incrementing the count based on user confirmation.
     *
     * @param collector the collector whose collection is being modified
     * @return true if the card was added or incremented; false otherwise
     */
    public boolean addCardToCollection(Collector collector, String cardName, Rarity rarity, Variant variant, double value) {

        collector.addCard(new Card(cardName, rarity, variant, value));
        return true;

//        Card card = collectorView.promptAddCard(collector);
//        if (card == null) {
//            return false;
//        }
//
//        if (!collector.hasCardWithName(card.getName())) {
//            if (collectorView.promptAddCardChoice().equalsIgnoreCase("Y")) {
//                collectorView.displayAddCardConfirmation(card);
//                collector.addCard(card);
//                return true;
//            } else {
//                collectorView.displayAddCardDenial();
//                return false;
//            }
//        } else {
//            if (collectorView.promptDuplicateCard().equalsIgnoreCase("Y")) {
//                collector.getCardWithName(card.getName()).incrementCount();
//                collectorView.displayAddCardConfirmation(card);
//                return true;
//            } else {
//                collectorView.displayAddCardDenial();
//                return false;
//            }
//        }
    }

    /**
     * Prompts the user to remove a card from the collector's collection.
     * Will also remove the card object if its count is zero and it's not in a binder or deck.
     *
     * @param collector the collector whose collection is being modified
     * @return true if the card was removed; false if not found or canceled
     */
    public boolean removeCardFromCollection(Collector collector) {
        collectorView.displayCollection(collector);
        String name = collectorView.promptRemoveCard();
        if (name.equals("0")) {
            System.out.println("Returning to main menu...");
            return false;
        }

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

    /**
     * Creates a new binder after checking for name duplication.
     *
     * @param collector the collector to add the binder to
     * @return true if the binder was created; false if it failed
     */
    public boolean createBinder(Collector collector) {
        String name = collectorView.promptBinderName();
        if (name.equals("0")) {
            System.out.println("Returning to main menu...");
            return false;
        }

        if (collector.hasBinderWithName(name)) {
            System.out.println("Binder already exists");
            return false;
        }
        collector.addBinder(name);
        collectorView.displayBinderCreation(name);
        return true;
    }

    /**
     * Prompts the user to choose an existing binder.
     *
     * @return the selected {@code Binder} object, or {@code null} if not found
     */
    public Binder returnBinderChoice() {
        while (true) {
            collectorView.displayBinders(collector);
            String input = collectorView.promptBinderName();

            if (input.equals("0")) {
                System.out.println("Returning to main menu...");
                return null;
            }

            Binder binder = collector.getBinderByName(input);
            if (binder != null) {
                return binder;
            }

            System.out.println("Binder not found. Please try again.");
        }
    }

    /**
     * Creates a new deck after checking for name duplication.
     *
     * @param collector the collector to add the deck to
     * @return true if the deck was created; false if name exists
     */
    public boolean createDeck(Collector collector) {
        String name = collectorView.promptDeckName();
        if (name.equals("0")) {
            System.out.println("Returning to main menu...");
            return false;
        }

        if (collector.hasDeckWithName(name)) {
            System.out.println("Deck already exists.");
            return false;
        }
        collector.addDeck(name);
        collectorView.displayDeckCreation(name);
        return true;
    }

    /**
     * Prompts the user to choose an existing deck.
     *
     * @return the selected {@code Deck} object, or {@code null} if not found
     */
    public Deck returnDeckChoice() {
        while (true) {
            collectorView.displayDecks(collector);
            String input = collectorView.promptDeckName();

            if (input.equals("0")) {
                System.out.println("Returning to main menu...");
                return null;
            }

            Deck deck = collector.getDeckByName(input);
            if (deck != null) {
                return deck;
            }

            System.out.println("Deck not found. Please try again.");
        }
    }
}
