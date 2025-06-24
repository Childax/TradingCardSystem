package com.TradingCardSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class BinderView {
    private Binder binder;
    private CardView cardView;
    private Scanner sc;

    public BinderView(Binder binder, CardView cardView, Scanner sc) {
        this.binder = binder;
        this.cardView = cardView;
        this.sc = sc;
    }

    public void displayBinderMenu() {
        System.out.println("[1] Add a Card to Binder");
        System.out.println("[2] Remove a Card from Binder");
        System.out.println("[3] Trade Card");
        System.out.println("[4] View Binder Cards");
        System.out.println("[5] Delete Binder");
        System.out.println("[0] Back");
        System.out.print("> ");
    }

    public void displayCardsFromBinder() {
        ArrayList<Card> cards = binder.getCardsSortedWithDuplicates();
        System.out.println("========== [" + binder.getName() + "] ==========");
        System.out.println("Total Cards: " + cards.size());
        System.out.println();
        System.out.println("Card List:");

        int index = 1;
        for (Card card : cards) {
            System.out.printf("%d. %s [%s, Variant: %s, $%.2f]%n",
                    index++,
                    card.getName(),
                    card.getRarity(),
                    card.getVariant() != null ? card.getVariant() : "None",
                    card.getValue()
            );
        }

        System.out.println("===================================");
    }

    public int promptBinderMenuChoice() {
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }

    public String promptBinderDeletionConfirmation(String name) {
        System.out.printf("Are you sure you want to delete binder \"%s\"? (y/n): ", name);
        return sc.nextLine();
    }

    public void displayBinderNotDeleted() {
        System.out.println("Binder was not deleted.");
    }

    public void displayReturnMessage() {
        System.out.println("Returning to main menu...");
    }

    public String promptAddCard() {
        System.out.print("Enter the card you want to transfer: ");
        return sc.nextLine();
    }

    public void displayAddCardConfirmation(String cardName, String binderName) {
        System.out.printf("%s was added to %s\n", cardName, binderName);
    }

    public String promptRemoveCard() {
        System.out.print("Enter the card you want to remove: ");
        return sc.nextLine();
    }

    public void displayRemoveCardConfirmation(String cardName) {
        System.out.printf("%s was returned to collection\n", cardName);
    }

    public void displayCardNotFound() {
        System.out.println("Card was not in your collection.");
    }
}
