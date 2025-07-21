package com.TradingCardSystem;

import java.util.Scanner;

/**
 * The {@code CardView} class handles all user input and output related to creating cards.
 * It prompts the user for card attributes such as name, value, rarity, and variant.
 */
public class CardView {
    private Scanner sc;

    /**
     * Constructs a {@code CardView} with the specified {@code Scanner} for input.
     *
     * @param sc the Scanner used to receive user input
     */
    public CardView(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Prompts the user to enter a card name.
     *
     * @return the name entered by the user
     */
    public String promptCardName() {
        System.out.print("Enter Card Name (0 - Cancel): ");
        return sc.nextLine();
    }

    /**
     * Prompts the user to enter a card value.
     *
     * @return the value entered by the user as a double
     */
    public double promptCardValue() {
        System.out.print("Enter Card Value (0 - Cancel): ");
        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }

    /**
     * Prompts the user to select a card rarity from the {@code Rarity} enum.
     * Retries until a valid value is entered.
     *
     * @return the selected {@code Rarity}
     */
    public Rarity promptRarity() {
        while (true) {
            displayRarities();
            System.out.println("0 - Cancel");
            System.out.print("Select Card Rarity: ");
            String input = sc.next().trim();

            if (input.equals("0")) {
                return null;
            }

            try {
                return Rarity.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid rarity. Please try again.");
            }
        }
    }

    /**
     * Prompts the user to select a card variant from the {@code Variant} enum.
     * Intended to be used only when the card rarity is {@code RARE} or {@code LEGENDARY}.
     * Retries until a valid value is entered.
     *
     * @return the selected {@code Variant}
     */
    public Variant promptVariantIfRareOrLegendary() {
        while (true) {
            displayVariants();
            System.out.println("0 - Cancel");
            System.out.print("Select Card Variant: ");
            String input = sc.next().trim();

            if (input.equals("0")) {
                return null;
            }

            try {
                return Variant.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid variant. Please try again.");
            }
        }
    }

    /**
     * Displays all possible card rarities from the {@code Rarity} enum.
     */
    public void displayRarities() {
        for (Rarity r : Rarity.values()) {
            System.out.println("- " + r);
        }
    }

    /**
     * Displays all possible card variants from the {@code Variant} enum.
     */
    public void displayVariants() {
        for (Variant v : Variant.values()) {
            System.out.println("- " + v);
        }
    }
}
