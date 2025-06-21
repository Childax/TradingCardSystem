package com.TradingCardSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class CardController {


    public static Card makeCard() {
        String cardName, rarity, variant;
        double value;
        Card newCard = new Card();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Card Name: ");
        cardName = sc.next();
        System.out.print("Select Card Rarity");
        rarity = sc.next();
        if (rarity.equalsIgnoreCase("Legendary")) {
            System.out.print("Select Card Variant");
            variant = sc.next();
        } else {
            variant = null;
        }
        System.out.print("Input Card Value: ");
        value = sc.nextDouble();

        newCard.setName(cardName);
        newCard.setRarity(rarity);
        newCard.setVariant(variant);
        newCard.setValue(value);

        return newCard;
    }
}
