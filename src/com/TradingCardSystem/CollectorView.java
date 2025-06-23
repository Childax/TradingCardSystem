package com.TradingCardSystem;

import java.util.Scanner;

public class CollectorView {
    private CardController cardController;
    private CardView cardView;
    private Scanner sc = new Scanner(System.in);

    public CollectorView(CardController cardController, CardView cardView) {
        this.cardController = cardController;
        this.cardView = cardView;
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
        System.out.print("Enter the name of the card to view details: ");
        String name = sc.next();

        Card card = collector.getCardWithName(name);
        if (card != null) {
            System.out.println("----- Card Details -----");
            System.out.println("Name: " + card.getName());
            System.out.println("Rarity: " + card.getRarity());
            System.out.println("Variant: " + card.getVariant());
            System.out.println("Value: " + card.getValue());
            System.out.println("Count: " + card.getCount());
        } else {
            System.out.println("Card not found in your collection.");
        }
    }
}

