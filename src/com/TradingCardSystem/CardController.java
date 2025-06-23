package com.TradingCardSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class CardController {

    private CardView view;

    public CardController(CardView view) {
        this.view = view;
    }

    public Card makeCardFromName(String name) {
        Card newCard = new Card();

        newCard.setName(name);
        view.displayRarities();
        Rarity rarity = view.promptRarity();

        Variant variant = Variant.NONE;
        if (rarity == Rarity.RARE || rarity == Rarity.LEGENDARY) {
            variant = view.promptVariantIfRareOrLegendary();
        }

        double value = view.promptCardValue();
        int count = 1;

        newCard.setName(name);
        newCard.setRarity(rarity);
        newCard.setValue(value);
        newCard.setVariant(variant);
        newCard.setCount(1);

        return newCard;
    }








}
