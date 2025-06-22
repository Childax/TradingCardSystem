package com.TradingCardSystem;

import java.util.ArrayList;

public class Card {
    private String name;
    private String rarity;
    private String variant;
    private double value;
    private int count;
    ArrayList<String> rarityList;
    ArrayList<String> variantList;

    public Card(String name, String rarity, String variant, float value) {
        this.name = name;
        this.rarity = rarity;
        this.variant = variant;
        this.value = value;
        this.count = 0;
        this.rarityList = new ArrayList<>();
        this.variantList = new ArrayList<>();
    }

    public Card() {
        this.name = null;
        this.rarity = null;
        this.variant = null;
        this.value = 0;
        this.count = 0;
        this.rarityList = new ArrayList<>();
        this.variantList = new ArrayList<>();
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
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
