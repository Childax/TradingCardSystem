package com.TradingCardSystem;

import java.util.Scanner;

public class TradingCardApp {
    public void run() {
        Collector collector = new Collector();

        int choice = 0;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Trading Card Inventory System");
            System.out.println("[1] Add a Card");
            System.out.println("[2] Create a new Binder");
            System.out.println("[3] Create a new Deck");
            System.out.println("[0] Exit");
            System.out.print("> ");
            choice = sc.nextInt();

            if (choice < 0 || choice > 3) {
                System.out.println(choice + " is not a valid option.");
            }
        } while (choice < 0 || choice > 3);

        switch (choice) {
            case 1:
                Card card = new Card();

                if (card != null) {
                    collector.getCollection().addCard(card);
                }
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
}
