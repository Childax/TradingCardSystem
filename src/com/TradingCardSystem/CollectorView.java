package com.TradingCardSystem;

import java.util.Scanner;

public class CollectorView {
    private CardController cardController;

    public CollectorView(CardController cardController) {
        this.cardController = cardController;
    }

    public void promptAddCard(Collector collector) {
        Card card = cardController.makeCard();
        collector.addCard(card);
        System.out.println("Added " + card.getName() + " to your collection.");
    }

    public void promptRemoveCard(Collector collector) {
        Scanner sc = new Scanner(System.in);
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
}

