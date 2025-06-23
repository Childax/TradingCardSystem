package com.TradingCardSystem;

import java.util.ArrayList;

public class Card {
    private String name;
    private Rarity rarity;
    private Variant variant;
    private double value;
    private int count;

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
        this.adjustValueByVariant();
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

    public void displayCardHidden() {
        System.out.printf("- %s (x%d): $%.2f each\n", this.getName(), this.getCount(), this.getValue());
    }

    public void displayCardDetails() {
        System.out.printf("Name: %s | Rarity: %s | Variant: %s | Value: %.2f | Count: %d\n",
                this.getName(), this.getRarity(), this.getVariant(), this.getValue(), this.getCount());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Card card = (Card) obj;
        return this.name.equals(card.name);
    }

    public void adjustValueByVariant() {
        switch (this.variant) {
            case EXTENDED_ART:
                this.setValue(this.getValue() * 1.5);
                break;
            case FULL_ART:
                this.setValue(this.getValue() * 2);
                break;
            case ALT_ART:
                this.setValue(this.getValue() * 3);
                break;
            default:
                break;
        }
    }
}
