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

    public Card(Card card) {
        this.name = card.getName();
        this.rarity = card.getRarity();
        this.variant = card.getVariant();
        this.value = card.getValue();
        this.count = 1;
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

    public void displayCardInline() {
        System.out.printf("Name: %s | Rarity: %s | Variant: %s | Value: %.2f | Count: %d\n",
                this.getName(), this.getRarity(), this.getVariant(), this.getValue(), this.getCount());
    }

    public void displayCardBox() {
        String name = "Name: " + this.getName();
        String rarity = "Rarity: " + this.getRarity();
        String variant = "Variant: " + (this.getVariant() != null ? this.getVariant() : "None");
        String value = String.format("Value: $%.2f", this.getValue());
        String count = "Count: " + this.getCount();

        int contentWidth = Math.max(
                Math.max(name.length(), rarity.length()),
                Math.max(Math.max(variant.length(), value.length()), count.length())
        );
        int width = contentWidth + 4; // 2 for borders, 2 for spacing

        String border = "+" + "-".repeat(width - 2) + "+";

        String title = "CARD DETAILS";
        int padding = (width - 2 - title.length()) / 2;
        String titleLine = "|" + " ".repeat(padding) + title + " ".repeat(width - 2 - padding - title.length()) + "|";

        // Print box
        System.out.println(border);
        System.out.println(titleLine);
        System.out.println(border);
        System.out.printf("| %-" + contentWidth + "s |\n", name);
        System.out.printf("| %-" + contentWidth + "s |\n", rarity);
        System.out.printf("| %-" + contentWidth + "s |\n", variant);
        System.out.printf("| %-" + contentWidth + "s |\n", value);
        System.out.printf("| %-" + contentWidth + "s |\n", count);
        System.out.println(border);
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
