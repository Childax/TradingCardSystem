package com.TradingCardSystem;

import java.util.ArrayList;
import java.util.Comparator;

public class Binder {
    private String name;
    private ArrayList<Card> binderCards;

    public Binder() {
        this.binderCards = new ArrayList<>();
        this.name = null;
    }

    public Binder(String name) {
        this.binderCards = new ArrayList<>();
        this.name = name;
    }

    public ArrayList<Card> getCards() {
        return binderCards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardCount() {
        return this.binderCards.size();
    }

    public void addCard(Card card) {
        this.binderCards.add(card);
    }

    public void removeCard(Card card) {
        this.binderCards.remove(card);
    }

    public boolean isFull() {
        return binderCards.size() >= 20;
    }

    public void tradeCard(Card card) {

    }

    public ArrayList<Card> getCardsSortedWithDuplicates() {
        ArrayList<Card> expanded = new ArrayList<>();
        for (Card card : binderCards) {
            for (int i = 0; i < card.getCount(); i++) {
                expanded.add(card);
            }
        }

        expanded.sort(Comparator.comparing(Card::getName, String.CASE_INSENSITIVE_ORDER));
        return expanded;
    }

    public Card getCardWithName(String name) {
        for (Card card : binderCards) {
            if (card.getName().equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;
    }
}
