package com.TradingCardSystem;

import java.util.ArrayList;

public class Binder {
    private ArrayList<Card> binderCards;
    private int binderCardCount;

    public Binder() {
        this.binderCards = new ArrayList<>();
        this.binderCardCount = 0;
    }

    public ArrayList<Card> getCards() {
        return binderCards;
    }

    public Card getCard(int index) {
        return binderCards.get(index);
    }

    public int getCardCount() {
        return binderCardCount;
    }

    public void setCardCount(int count) {
        this.binderCardCount = count;
    }

    public void displayBinder() {

    }

    public void addCard(Card card) {
        this.binderCards.add(card);
        binderCardCount++;
    }

    public void removeCard(Card card) {
        if (binderCards.remove(card)) {
            binderCardCount--;
        }
    }

    public void tradeCard(Card card) {

    }



}
