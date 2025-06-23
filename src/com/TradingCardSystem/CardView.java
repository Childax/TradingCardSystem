package com.TradingCardSystem;

import java.util.Scanner;

public class CardView {
    private Scanner sc = new Scanner(System.in);

    public String promptCardName() {
        System.out.print("Enter Card Name: ");
        return sc.nextLine();
    }

    public double promptCardValue() {
        System.out.print("Enter Card Value: ");
        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }

    public Rarity promptRarity() {
        while (true) {
            displayRarities();
            System.out.print("Select Card Rarity: ");
            try {
                return Rarity.valueOf(sc.next().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid rarity. Please try again.");
            }
        }
    }

    public Variant promptVariantIfRareOrLegendary() {
        while (true) {
            displayVariants();
            System.out.print("Select Card Variant: ");
            try {
                return Variant.valueOf(sc.next().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid variant. Please try again.");
            }
        }
    }

    public void displayRarities() {
        for (Rarity r : Rarity.values()) {
            System.out.println("- " + r);
        }
    }

    public void displayVariants() {
        for (Variant v : Variant.values()) {
            System.out.println("- " + v);
        }
    }

    public void displayCard(Card card) {
        System.out.printf("Name: %s | Rarity: %s | Variant: %s | Value: %.2f | Count: %d\n",
                card.getName(), card.getRarity(), card.getVariant(), card.getValue(), card.getCount());
    }
}
