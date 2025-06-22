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

    public ArrayList<Card> getCollection() {
        return cards;
    }
}
