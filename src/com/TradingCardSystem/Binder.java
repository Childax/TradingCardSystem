package com.TradingCardSystem;

import java.util.ArrayList;

public class Binder {
    private String name;
    private ArrayList<Card> binderCards;

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

    public void displayBinder() {

    }

    public void addCard(Card card) {
        this.binderCards.add(card);
    }

    public void removeCard(Card card) {
        this.binderCards.remove(card);
    }

    public void tradeCard(Card card) {

    }



}
