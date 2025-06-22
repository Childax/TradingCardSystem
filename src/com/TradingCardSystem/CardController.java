package com.TradingCardSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class CardController {

    public static Card makeCard() {
        String cardName;
        double value;
        Card newCard = new Card();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Card Name: ");
        cardName = sc.next();
        System.out.println("Select Card Rarity (COMMON, UNCOMMON, RARE, LEGENDARY): ");
        Rarity rarity;
        try {
            rarity = Rarity.valueOf(sc.next().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid rarity. Defaulting to COMMON.");
            rarity = Rarity.COMMON;
        }

        Variant variant = Variant.NONE;
        if (rarity == Rarity.LEGENDARY) {
            System.out.println("Select Card Variant (NORMAL, EXTENDED_ART, FULL_ART, ALT_ART): ");
            try {
                variant = Variant.valueOf(sc.next().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid variant. Defaulting to NORMAL.");
                variant = Variant.NORMAL;
            }
        }

        System.out.print("Input Card Value: ");
        value = sc.nextDouble();

        newCard.setName(cardName);
        newCard.setRarity(rarity);
        newCard.setVariant(variant);
        newCard.setValue(value);

        return newCard;
    }

    private static void adjustVariantValue(Card card, Variant variant) {
        switch (variant) {
            case EXTENDED_ART:
                card.setValue(card.getValue() * 1.5);
                break;
            case FULL_ART:
                card.setValue(card.getValue() * 2);
                break;
            case ALT_ART:
                card.setValue(card.getValue() * 3);
                break;
            default:
                break;
        }
    }


}
