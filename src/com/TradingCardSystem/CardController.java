package com.TradingCardSystem;

import java.util.Scanner;

/**
 * The CardController class is responsible for handling card creation logic and interaction
 * with the CardView. It mediates user input to construct and configure Card objects.
 */
public class CardController {

    private CardView view;

    /**
     * Constructs a CardController with the specified CardView for user input.
     *
     * @param view the view used to prompt the user for card details
     */
    public CardController(CardView view) {
        this.view = view;
    }

    /**
     * Creates a new Card object using the provided card name and user-inputted details
     * such as rarity, variant (if applicable), value, and count.
     *
     * <p>If the card's rarity is {@code RARE} or {@code LEGENDARY},
     * the user will be prompted to select a variant (e.g., Full Art, Extended Art).</p>
     *
     * @param name the name of the card to create
     * @return the constructed Card object
     */
    public Card makeCardFromName(String name) {
        Card newCard = new Card();

        newCard.setName(name);
        Rarity rarity = view.promptRarity();
        if (rarity == null) {
            System.out.println("Returning to menu...");
            return null;
        }


        Variant variant = Variant.NONE;
        if (rarity == Rarity.RARE || rarity == Rarity.LEGENDARY) {
            variant = view.promptVariantIfRareOrLegendary();
            if (variant == null) {
                System.out.println("Returning to menu...");
                return null;
            }
        }

        double value = view.promptCardValue();
        if (value == 0) {
            System.out.println("Returning to menu...");
            return null;
        }

        newCard.setName(name);
        newCard.setRarity(rarity);
        newCard.setValue(value);
        newCard.setVariant(variant);
        newCard.setCount(1);

        return newCard;
    }
}
