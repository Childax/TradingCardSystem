package com.TradingCardSystem;

import java.util.ArrayList;

public class Collector {

    private String username;
    private ArrayList<Card> cards;
    private ArrayList<Binder> binders;
    private ArrayList<Deck> decks;

    public Collector() {
        this.username = "fn";
        this.cards = new ArrayList<>();
        this.binders = new ArrayList<>();
        this.decks = new ArrayList<>();
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Binder> getBinders() {
        return binders;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    // Add card to collection (merge if name matches)
    public void addCard(Card newCard) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(newCard.getName())) {
                card.incrementCount();
                return;
            }
        }
        cards.add(newCard);
    }

    // Remove card by name
    public boolean removeCard(String name) {
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.getName().equalsIgnoreCase(name)) {
                card.decrementCount();
                if (card.getCount() <= 0) {
                    cards.remove(i);
                }
                return true;
            }
        }
        return false; // card not found
    }

    // Display all cards
    public void displayCards() {
        if (cards.isEmpty()) {
            System.out.println("Your collection is empty.");
            return;
        }

        System.out.println("Your Collection:");
        for (Card card : cards) {
            System.out.println("- " + card.getName() + " (x" + card.getCount() + ")");
        }
    }

    // Check if card with given name exists
    public boolean hasCardWithName(String name) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Get card by name
    public Card getCardWithName(String name) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;
    }


}
