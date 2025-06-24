package com.TradingCardSystem;

import java.util.Scanner;

public class DeckController {
    private Collector collector;
    private DeckView view;

    public DeckController(Collector collector, DeckView deckView) {
        this.collector = collector;
        this.view = view;
    }

    public DeckController() {
        this.collector = new Collector();
        this.view = new DeckView();
    }

    public boolean addCardToDeck(Deck deck){
        System.out.println("Here is your collecton:");
        collector.displayCards();

        String cardName = view.promptAddCardToDeck();

        Card card = collector.getCardWithName(cardName);
        if (card == null){
            System.out.println("Invalid card name");
            return false;
        }
        if (deck.getCardCount() >= 10){
            return false;
        }
        if (deck.getCards().contains(card)){
            return false;
        }
        if (!collector.getCards().contains(card)){
            return false;
        }

        deck.addCard(card);
        collector.removeCardObject(cardName);
        System.out.println("Card added successfully");
        return true;
    }

    public boolean removeCardFromDeck(Deck deck){
        displayCardsInDeck(deck);

        String cardName = view.promptRemoveCardFromDeck();

        Card card = null;
        for(Card c : deck.getCards()){
            if(c.getName().equalsIgnoreCase(cardName)){
                card = c;
            }
        }
        if(card == null){
            System.out.println("Invalid card name");
            return false;
        }
        
        deck.removeCard(card);
        collector.addCard(card);
        System.out.println("Card removed successfully!");
        return true;
    }

    public void displayCardsInDeck(Deck deck){
        System.out.println("Here are the available cards in the deck:");
        int i = 0;
        for(Card card : deck.getCards()){
            System.out.printf("Card %d\nCard Name: %s\nRarity: %s\nVariant: %s\nValue: %.2f\n",
                    i+1,
                    card.getName(),
                    card.getRarity(),
                    card.getVariant(),
                    card.getValue());
            i++;
        }
    }
}
