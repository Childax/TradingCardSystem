package com.TradingCardSystem;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deckCards;
    private int deckCardCount;

    public Deck() {
        this.deckCards = new ArrayList<>();
        this.deckCardCount = 0;
    }

    public ArrayList<Card> getCards() {
        return deckCards;
    }

    public int getCardCount() {
        return deckCardCount;
    }

    public void addCard(Card card) {
        deckCards.add(card);
        deckCardCount++;
    }

    public void removeCard(Card card) {
        if (deckCards.remove(card)) {
            deckCardCount--;
        }
    }
}


