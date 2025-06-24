package com.TradingCardSystem;

import java.util.ArrayList;

public class Deck {
    private String deckName;
    private ArrayList<Card> deckCards;

    public Deck(String name) {
        this.deckName = name;
        this.deckCards = new ArrayList<>();
    }

    public String getName() {
        return this.deckName;
    }

    public void setName(String name) {
        this.deckName = name;
    }

    public ArrayList<Card> getCards() {
        return deckCards;
    }

    public int getCardCount() {
        return deckCards.size();
    }

    public boolean isFull() {
        return deckCards.size() >= 10;
    }

    public void addCard(Card card) {
        deckCards.add(card);
    }

    public void removeCard(Card card) {
        deckCards.remove(card);
    }

    public Card getCardWithName(String name) {
        for (Card card : deckCards) {
            if (card.getName().equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;
    }


}


