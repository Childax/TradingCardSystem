package com.TradingCardSystem;

import java.util.Scanner;

public class CollectorView {
    private CardController cardController;
    private Scanner sc = new Scanner(System.in);

    public CollectorView(CardController cardController) {
        this.cardController = cardController;
    }

    public void promptAddCard(Collector collector) {
        Card card = cardController.makeCard();
        collector.addCard(card);
        System.out.println("Added " + card.getName() + " to your collection.");
    }

    public void promptRemoveCard(Collector collector) {
        sc = new Scanner(System.in);
        collector.displayCards();

        System.out.print("Select card to remove (by name): ");
        String name = sc.next();

        if (collector.hasCardWithName(name)) {
            Card card = collector.getCardWithName(name);
            card.decrementCount();
            System.out.println("Removed one copy of " + name + " from your collection.");
        } else {
            System.out.println("Card does not exist.");
        }

    }

    public void displayCollection(Collector collector) {
        collector.displayCards();
    }

    public void showCardDetails(Collector collector) {
        sc = new Scanner(System.in);

        System.out.print("Enter the name of the card to view details: ");
        String name = sc.nextLine();

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

