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

    public boolean addCard(Card card) {
        String name = card.getName();
        if (!this.hasCardWithName(name)) {
            card.setCount(1);
            collectionCards.add(card);
            collectionCardCount++;
            return true;
        } else {
            this.getCardWithName(name).incrementCount();
            collectionCardCount++;
            return true;
        }
    }

    public boolean removeCard(Card card) {
        String name = card.getName();
        if (this.hasCardWithName(name)) {
            collectionCards.remove(card);
            this.collectionCardCount--;
            return true;
        }
        return false;
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

    public void displayCards() {
        for (Card card : this.collectionCards) {
            System.out.printf("[%d pcs] %s (%s) : %.2f\n", card.getCount() ,card.getName(), card.getRarity(), card.getValue());
        }
    }

    public boolean hasCardWithName(String name) {
        for (Card card : this.collectionCards) {
            if (card.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Card getCardWithName(String name) {
        for (Card card : this.collectionCards) {
            if (card.getName().equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;
    }
}
