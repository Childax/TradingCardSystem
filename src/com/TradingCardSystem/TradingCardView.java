package com.TradingCardSystem;

import java.util.Scanner;

public class TradingCardView {
    private Scanner sc;

    public TradingCardView(Scanner sc) {
        this.sc = sc;
    }

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

    public int getMenuChoice() {
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }

    public void showInvalidChoice(int choice) {
        System.out.println(choice + " is not a valid option.");
    }

    public void showExitMessage() {
        System.out.println("See you next time.");
    }
}
