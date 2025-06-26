package com.TradingCardSystem;

public class DeckController {
    private Collector collector;
    private DeckView deckView;
    private Deck deck;

    public DeckController(Collector collector, DeckView deckView, Deck deck) {
        this.collector = collector;
        this.deckView = deckView;
        this.deck = deck;
    }

    public void manageDeck() {
        while (true) {
            deckView.displayDeckMenu(deck);
            int choice = deckView.promptDeckMenuChoice();

            switch (choice) {
                case 1:
                    handleAddCardToDeck();
                    break;
                case 2:
                    handleRemoveCardFromDeck();
                    break;
                case 3:
                    deckView.displayCardsFromDeck(deck);
                    break;
                case 4:
                    if (handleDeleteDeck()) {
                        return;
                    } else {
                        break;
                    }
                case 0:
                    deckView.displayReturnMessage();
                    return;
                default:

            }
        }
    }

    public boolean handleAddCardToDeck(){
        collector.displayCards();

        String cardName = deckView.promptAddCardToDeck();

        Card card = collector.getCardWithName(cardName);
        if (card == null || card.getCount() <= 0){
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

    public boolean handleRemoveCardFromDeck() {
        deckView.displayCardsFromDeck(deck);
        String name = deckView.promptRemoveCardFromDeck();
        Card card = deck.getCardWithName(name);

        if (card == null){
            System.out.println("Invalid card name.");
            return false;
        }
        
        deck.removeCard(card);
        collector.addCard(card);
        deckView.displayRemoveCardConfirmation(name);
        return true;
    }

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
