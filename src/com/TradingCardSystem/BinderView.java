package com.TradingCardSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class BinderView {
    private Scanner sc;

    public BinderView(Scanner sc) {
        this.sc = sc;
    }

    public void displayBinderMenu(Binder binder) {
        System.out.printf("Binder Selected: [%s]\n", binder.getName());
        System.out.println("[1] Add a Card to Binder");
        System.out.println("[2] Remove a Card from Binder");
        System.out.println("[3] Trade Card");
        System.out.println("[4] View Binder Cards");
        System.out.println("[5] Delete Binder");
        System.out.println("[0] Back");
        System.out.print("> ");
    }

    public void displayCardsFromBinder(Binder binder) {
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
        System.out.print("Enter the card you want to add: ");
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

    public void displayBinderFull() {
        System.out.println("Maximum card count reached");
    }

    public String promptTradeStart(){
        System.out.print("Would you like to trade one of your cards? (y/n): ");
        return sc.nextLine();
    }

    public String promptTradeCardName(){
        System.out.print("Enter the card name to be traded: ");
        return sc.nextLine();
    }

    public String promptTradeNewCard(){
        System.out.print("Enter the new card name: ");
        return sc.nextLine();
    }

    public Card promptTradeCardDetails(CardController cardController) {
        System.out.print("Enter the details of the card to be traded: ");

        String name = promptTradeNewCard();
        return cardController.makeCardFromName(name);
    }

    public char promptTradeConfirmation(double oldCard, double newCard){
        if(oldCard - newCard > 1.0 || oldCard - newCard < -1.0) {
            System.out.println("[!] You are trading a card worth more or less than $1 compared to the new card.");
        } else {
            System.out.println("[✔] Old card and New card to be replaced are of similar value");
        }
        System.out.print("Would you like to accept the trade? (y/n): ");
        return sc.nextLine().charAt(0);
    }

    public void displayTradeCancellation(){
        System.out.println("Trade Card has been cancelled.");
    }

    public void displayTradeInformation(Card oldCard, Card newCard) {
        System.out.println("\nTrade Information:");
        System.out.println("=================================");

        System.out.println("Removed Card:");
        System.out.println("-Name   : " + oldCard.getName());
        System.out.println("-Value  : $" + oldCard.getValue());
        System.out.println("-Rarity : " + oldCard.getRarity());
        System.out.println("-Variant: " + oldCard.getVariant());

        System.out.println("---------------------------------");

        System.out.println("Added Card:");
        System.out.println("-Name   : " + newCard.getName());
        System.out.println("-Value  : $" + newCard.getValue());
        System.out.println("-Rarity : " + newCard.getRarity());
        System.out.println("-Variant: " + newCard.getVariant());

        System.out.println("=================================");
    }
}
