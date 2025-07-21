package com.TradingCardSystem;

/**
 * The {@code DeckController} class handles user interactions and logic for managing a specific deck.
 * It supports adding/removing cards, viewing deck contents, and deleting the deck.
 */
public class DeckController {
    private Collector collector;
    private DeckView deckView;
    private Deck deck;

    /**
     * Constructs a new {@code DeckController} with the given collector, deck view, and deck.
     *
     * @param collector the {@code Collector} who owns the deck
     * @param deckView the {@code DeckView} used for user interactions
     * @param deck the {@code Deck} being managed
     */
    public DeckController(Collector collector, DeckView deckView, Deck deck) {
        this.collector = collector;
        this.deckView = deckView;
        this.deck = deck;
    }

    /**
     * Manages deck operations through a menu-driven loop until the user chooses to exit.
     */
    public void manageDeck() {
        while (true) {
            deckView.displayDeckMenu(deck);
            int choice = deckView.promptDeckMenuChoice();

            switch (choice) {
                case 1 -> handleAddCardToDeck();
                case 2 -> handleRemoveCardFromDeck();
                case 3 -> {
                    deckView.displayCardsFromDeck(deck);

                    String name = deckView.promptCardName();
                    if (name.equals("0")) {
                        System.out.println("Returning to deck menu...");
                        break;
                    }
                    Card card = deck.getCardWithName(name);

                    if (card != null) {
                        card.displayCardBox();
                    } else {
                        deckView.displayCardNotFound();
                    }
                }
                case 4 -> {
                    if (handleDeleteDeck()) return;
                }
                case 0 -> {
                    deckView.displayReturnMessage();
                    return;
                }
                default -> {}
            }
        }
    }

    /**
     * Handles the logic for adding a card from the collection to the deck.
     *
     * @return {@code true} if the card was successfully added, {@code false} otherwise
     */
    public boolean handleAddCardToDeck() {
        collector.displayCards();

        String cardName = deckView.promptAddCardToDeck();
        if (cardName.equals("0")) {
            System.out.println("Returning to deck menu...");
            return false;
        }
        Card card = collector.getCardWithName(cardName);

        if (card == null || card.getCount() <= 0) {
            deckView.displayCardNotFound();
            return false;
        }
        if (deck.isFull()) {
            deckView.displayDeckFull();
            return false;
        }
        if (deck.getCards().contains(card)) {
            deckView.displayCardExists();
            return false;
        }
        if (!collector.getCards().contains(card)) {
            deckView.displayCardNotFound();
            return false;
        }

        deck.addCard(card);
        card.decrementCount();
        deckView.displayAddCardConfirmation(cardName, deck.getName());
        return true;
    }

    /**
     * Handles the logic for removing a card from the deck and returning it to the collection.
     *
     * @return {@code true} if the card was successfully removed, {@code false} otherwise
     */
    public boolean handleRemoveCardFromDeck() {
        deckView.displayCardsFromDeck(deck);
        String name = deckView.promptRemoveCardFromDeck();
        if (name.equals("0")) {
            System.out.println("Returning to deck menu...");
            return false;
        }
        Card card = deck.getCardWithName(name);

        if (card == null) {
            System.out.println("Invalid card name.");
            return false;
        }

        deck.removeCard(card);
        collector.addCard(card);
        deckView.displayRemoveCardConfirmation(name);
        return true;
    }

    /**
     * Handles the deletion of the current deck, returning all its cards to the collection.
     *
     * @return {@code true} if the deck was deleted, {@code false} otherwise
     */
    public boolean handleDeleteDeck() {
        String name = deck.getName();
        if (deckView.promptDeckDeletionConfirmation(name).equalsIgnoreCase("Y")) {
            collector.deleteDeck(name);
            return true;
        } else {
            deckView.displayDeckNotDeleted();
            return false;
        }
    }
}
