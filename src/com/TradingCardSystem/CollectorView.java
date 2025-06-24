package com.TradingCardSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class CollectorView {
    private CardController cardController;
    private CardView cardView;
    private Scanner sc;

    public CollectorView(CardController cardController, CardView cardView, Scanner sc) {
        this.cardController = cardController;
        this.cardView = cardView;
        this.sc = sc;
    }

    public Card promptAddCard(Collector collector) {
        String name = cardView.promptCardName();
        if (collector.hasCardWithName(name)) {
            return collector.getCardWithName(name);
        }
        return cardController.makeCardFromName(name);
    }

    public String promptAddCardChoice() {
        System.out.print("Do you want to add this card to your collection? (y/n): ");
        return sc.next();
    }

    public void displayAddCardConfirmation(Card card) {
        System.out.println("Added " + card.getName() + " to your collection.");
    }

    public void displayAddCardDenial() {
        System.out.println("Card was not added to the collection.");
    }

    public String promptDuplicateCard() {
        System.out.println("You already have this card in your collection");
        System.out.print("Increment card count? (y/n): ");
        return sc.next();
    }

    public String promptRemoveCard() {
        System.out.print("Select card to remove (by name): ");
        return sc.next();
    }

    public String promptRemoveCardChoice(String name) {
        System.out.printf("Are you sure you want to remove %s from the collection? (y/n): ", name);
        return sc.next();
    }

    public void displayRemoveCardConfirmation(String name) {
        System.out.println("Removed " + name + " from the collection");
    }

    public void displayRemoveCardDenial(String name) {
        System.out.println(name + " was not removed from the collection");
    }

    public void displayCardNotFound() {
        System.out.println("Card not found.");
    }

    public void displayCollection(Collector collector) {
        collector.displayCards();
    }

    public void showCardDetails(Collector collector) {
        this.displayCollection(collector);
        System.out.print("Enter the name of the card to view details: ");
        String name = sc.nextLine();

        Card card = collector.getCardWithName(name);
        if (card != null) {
            card.displayCardBox();
        } else {
            System.out.println("Card not found in your collection.");
        }
    }

    public String promptBinderName() {
        System.out.print("Enter Binder Name: ");
        return sc.nextLine();
    }

    public void displayBinderCreation(String name) {
        System.out.printf("Binder \"%s\" created.\n", name);
    }

    public void displayBinders(Collector collector) {
        ArrayList<Binder> binders = collector.getBinders();

        if (binders.isEmpty()) {
            System.out.println("You have no binders.");
            return;
        }

        System.out.println("========= [ Your Binders ] =========");

        int index = 1;
        for (Binder binder : binders) {
            System.out.printf("[%d] %s - %d cards\n", index++, binder.getName(), binder.getCardCount());
        }

        System.out.println("===================================");
    }

    public void displayDecks(Collector collector) {
        ArrayList<Deck> decks = collector.getDecks();

        if (decks.isEmpty()) {
            System.out.println("You have no decks.");
            return;
        }

        System.out.println("========= [ Your Decks ] =========");

        int index = 1;
        for (Deck deck : decks) {
            System.out.printf("[%d] %s - %d cards\n", index++, deck.getName(), deck.getCardCount());
        }

        System.out.println("===================================");
    }

    public String promptDeckName(){
        System.out.print("Enter Deck Name: ");
        return sc.nextLine();
    }
}

