package com.TradingCardSystem;

import java.util.Scanner;

public class TradingCardView {
    private Scanner sc = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("Trading Card Inventory System");
        System.out.println("[1] Add a Card");
        System.out.println("[2] Create a new Binder");
        System.out.println("[3] Create a new Deck");
        System.out.println("[0] Exit");
        System.out.print("> ");
    }

    public int getMenuChoice() {
        int choice = sc.nextInt();
        sc.nextLine(); // Clear newline
        return choice;
    }

    public void showInvalidChoice(int choice) {
        System.out.println(choice + " is not a valid option.");
    }

    public void showExitMessage() {
        System.out.println("See you next time.");
    }
}
