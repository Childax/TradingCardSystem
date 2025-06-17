package com.TradingCardSystem;

import java.util.ArrayList;

public class Collection {
    private ArrayList<Card> collectionCards;
    private ArrayList<Binder> binders;
    private ArrayList<Deck> decks;
    private int collectionCardCount;

    public Collection() {
        this.collectionCards = new ArrayList<>();
        this.binders = new ArrayList<>();
        this.decks = new ArrayList<>();
        this.collectionCardCount = 0;
    }
    public ArrayList<Card> getCards() {
        return collectionCards;
    }

    public Card getCard(int index) {
        return collectionCards.get(index);
    }

    public int getCardCount() {
        return collectionCardCount;
    }

    public void setCardCount(int count) {
        this.collectionCardCount = count;
    }

    public void addCard(Card card) {
        collectionCards.add(card);
        collectionCardCount++;
    }

    public void removeCard(Card card) {
        if (collectionCards.remove(card)) {
            collectionCardCount--;
        }
    }

    public ArrayList<Binder> getBinders() {
        return binders;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void addBinder(Binder binder) {
        this.binders.add(binder);
    }

    public void deleteBinder(Binder binder) {
        this.binders.remove(binder);
    }

    public void addDeck(Deck deck) {
        this.decks.add(deck);
    }

    public void removeDeck(Deck deck) {
        this.decks.remove(deck);
    }
}
