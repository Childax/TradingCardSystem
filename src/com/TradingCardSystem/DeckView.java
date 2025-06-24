package com.TradingCardSystem;

import java.util.Scanner;

public class DeckView {
    private Scanner sc = new Scanner(System.in);

    public String promptAddCardToDeck(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the card you want to add to the deck");
        return sc.nextLine().trim();
    }

    public String promptRemoveCardFromDeck(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the card you want to remove from the deck");
        return sc.nextLine().trim();
    }
}
