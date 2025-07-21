package com.TradingCardSystem;

import java.util.Scanner;

/**
 * Handles all display and input interactions for the main menu
 * of the Trading Card Inventory System.
 */
public class TradingCardView {
    private Scanner sc;

    /**
     * Constructs a TradingCardView with the provided Scanner.
     *
     * @param sc the Scanner used for user input
     */
    public TradingCardView(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Displays the main menu options based on the collector's current data.
     *
     * @param collector the current Collector object containing cards, binders, and decks
     */
    public void displayMenu(Collector collector) {
        System.out.println("Trading Card Inventory System");
        System.out.println("[1] Add a Card");
        System.out.println("[2] Create a new Binder");
        System.out.println("[3] Create a new Deck");

        if (!collector.getCards().isEmpty()) {
            System.out.println("[4] View collection");
            System.out.println("[5] View card details");
            System.out.println("[6] Remove a card");
        }

        if (!collector.getBinders().isEmpty()) {
            System.out.println("[7] Manage Binders");
        }

        if (!collector.getDecks().isEmpty()) {
            System.out.println("[8] Manage Decks");
        }
        System.out.println("[0] Exit");
        System.out.print("> ");
    }

    /**
     * Prompts the user to select a menu option.
     *
     * @return the chosen menu option as an integer
     */
    public int getMenuChoice() {
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }

    /**
     * Displays an error message for an invalid menu option.
     *
     * @param choice the invalid option selected by the user
     */
    public void showInvalidChoice(int choice) {
        System.out.println(choice + " is not a valid option.");
    }

    /**
     * Displays an exit message when the user quits the program.
     */
    public void showExitMessage() {
        System.out.println("See you next time.");
    }
}
