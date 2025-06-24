package com.TradingCardSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class DeckView {
    private Scanner sc;

    public DeckView(Scanner sc) {
        this.sc = sc;
    }

    public void displayDeckMenu(Deck deck) {
        System.out.printf("Deck Selected: [%s]\n", deck.getName());
        System.out.println("[1] Add a Card to Deck");
        System.out.println("[2] Remove a Card from Deck");
        System.out.println("[3] View Deck Cards");
        System.out.println("[4] Delete Deck");
        System.out.println("[0] Back");
        System.out.print("> ");
    }

    public String promptAddCardToDeck(){
        System.out.print("Enter the card you want to add: ");
        return sc.nextLine().trim();
    }

    public void displayAddCardConfirmation(String cardName, String deckName) {
        System.out.printf("%s was added to %s\n", cardName, deckName);
    }

    public String promptRemoveCardFromDeck(){
        System.out.print("Enter the card you want to remove: ");
        return sc.nextLine().trim();
    }

    public void displayCardNotFound() {
        System.out.println("Card was not in your collection.");
    }

    public void displayDeckFull() {
        System.out.println("Maximum card count reached.");
    }

    public void displayCardExists() {
        System.out.println("Card is already in your deck.");
    }

    public void displayCardsFromDeck(Deck deck) {
        ArrayList<Card> cards = deck.getCards();
        System.out.println("========== [" + deck.getName() + "] ==========");
        System.out.println("Card List:");

        int index = 1;
        for (Card card : cards) {
            System.out.printf("%d. %s%n", index++, card.getName());
        }

        System.out.println("===================================");
        String name = promptCardName();
        Card card = deck.getCardWithName(name);

        if (card != null) {
            card.displayCardBox();
        } else {
            displayCardNotFound();
        }
    }

    public String promptCardName() {
        System.out.print("Enter Card Name: ");
        return sc.nextLine();
    }

    public int promptDeckMenuChoice() {
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }

    public void displayReturnMessage() {
        System.out.println("Returning to main menu...");
    }

    public void displayRemoveCardConfirmation(String cardName) {
        System.out.printf("%s was returned to collection\n", cardName);
    }

    public String promptBinderDeletionConfirmation(String name) {
        System.out.printf("Are you sure you want to delete deck \"%s\"? (y/n): ", name);
        return sc.nextLine();
    }

    public void displayDeckNotDeleted() {
        System.out.println("Deck was not deleted.");
    }
}
