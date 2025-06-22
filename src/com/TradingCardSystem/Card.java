package com.TradingCardSystem;

import java.util.ArrayList;

public class Card {
    private String name;
    private Rarity rarity;
    private Variant variant;
    private double value;
    private int count;

    public enum Rarity {
        COMMON, UNCOMMON, RARE, LEGENDARY
    }
    public enum Variant {
        NONE, NORMAL, EXTENDED_ART, FULL_ART, ALT_ART,
    }


    public Card(String name, Rarity rarity, Variant variant, float value) {
        this.name = name;
        this.rarity = rarity;
        this.variant = variant;
        this.value = value;
        this.count = 0;
    }

    public Card() {
        this.name = null;
        this.rarity = null;
        this.variant = null;
        this.value = 0;
        this.count = 0;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount() {
        this.count++;
    }

    public void decrementCount() {
        this.count--;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Card card = (Card) obj;
        return this.name.equals(card.name);
    }
}
