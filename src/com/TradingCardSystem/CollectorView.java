package com.TradingCardSystem;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * The {@code CollectorView} class handles user interaction for the {@code Collector}.
 * It displays collections, binders, and decks, and prompts the user for input
 * related to card management.
 */
public class CollectorView {
    private CardController cardController;
    private CardView cardView;
    private Scanner sc;

    /**
     * Constructs a new {@code CollectorView} instance.
     *
     * @param cardController the controller used to manage card logic
     * @param cardView the view used for card-specific prompts
     * @param sc the {@code Scanner} object for reading user input
     */
    public CollectorView(CardController cardController, CardView cardView, Scanner sc) {
        this.cardController = cardController;
        this.cardView = cardView;
        this.sc = sc;
    }

    /**
     * Prompts the user to add a card to the collection.
     * If the card already exists, it returns the existing one.
     *
     * @param collector the {@code Collector} instance
     * @return the new or existing {@code Card}
     */
    public Card promptAddCard(Collector collector) {
        String name = cardView.promptCardName();
        if (name.equals("0")) {
            return null;
        }

        if (collector.hasCardWithName(name)) {
            return collector.getCardWithName(name);
        }
        return cardController.makeCardFromName(name);
    }

    /**
     * Prompts the user whether to add a card to the collection.
     *
     * @return the user's choice (Y/N)
     */
    public String promptAddCardChoice() {
        System.out.print("Do you want to add this card to your collection? (y/n): ");
        return sc.next();
    }

    /**
     * Displays confirmation that a card was added.
     *
     * @param card the added {@code Card}
     */
    public void displayAddCardConfirmation(Card card) {
        System.out.println("Added " + card.getName() + " to your collection.");
    }

    /**
     * Displays a message indicating the card was not added.
     */
    public void displayAddCardDenial() {
        System.out.println("Card was not added to the collection.");
    }

    /**
     * Prompts the user whether to increment the count of an existing card.
     *
     * @return the user's choice (Y/N)
     */
    public String promptDuplicateCard() {
        System.out.println("Card is already in your collection");
        System.out.print("Increment card count? (y/n): ");
        return sc.next();
    }

    /**
     * Prompts the user to select a card to remove.
     *
     * @return the name of the card
     */
    public String promptRemoveCard() {
        System.out.print("Select card to remove (0 - Cancel): ");
        return sc.next();
    }

    /**
     * Prompts for confirmation before removing a card.
     *
     * @param name the name of the card to remove
     * @return the user's choice (Y/N)
     */
    public String promptRemoveCardChoice(String name) {
        System.out.printf("Are you sure you want to remove %s from the collection? (y/n): ", name);
        return sc.next();
    }

    /**
     * Displays confirmation that a card was removed.
     *
     * @param name the name of the removed card
     */
    public void displayRemoveCardConfirmation(String name) {
        System.out.println("Removed " + name + " from the collection");
    }

    /**
     * Displays a message indicating that a card was not removed.
     *
     * @param name the card's name
     */
    public void displayRemoveCardDenial(String name) {
        System.out.println(name + " was not removed from the collection");
    }

    /**
     * Displays a message indicating that a card was not found.
     */
    public void displayCardNotFound() {
        System.out.println("Card not found.");
    }

    /**
     * Displays the full collection of cards from the given collector.
     *
     * @param collector the {@code Collector} whose collection is shown
     */
    public void displayCollection(Collector collector) {
        collector.displayCards();
    }

    /**
     * Prompts the user to view a card's detailed information using a boxed format.
     *
     * @param collector the {@code Collector} whose cards are displayed
     */
    public void showCardDetails(Collector collector) {
        this.displayCollection(collector);
        System.out.print("Enter the name of the card to view details (0 - Cancel): ");
        String name = sc.nextLine();
        if (name.equals("0")) {
            System.out.println("Returning to main menu...");
            return;
        }

        Card card = collector.getCardWithName(name);
        if (card != null) {
            card.displayCardBox();
        } else {
            System.out.println("Card not found in your collection.");
        }
    }

    /**
     * Prompts the user to input a binder name.
     *
     * @return the name of the binder
     */
    public String promptBinderName() {
        System.out.print("Enter Binder Name (0 - Cancel): ");
        return sc.nextLine();
    }

    /**
     * Displays confirmation that a binder was created.
     *
     * @param name the binder's name
     */
    public void displayBinderCreation(String name) {
        System.out.printf("Binder \"%s\" created.\n", name);
    }

    /**
     * Displays all binders currently owned by the collector.
     *
     * @param collector the {@code Collector} whose binders are shown
     */
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

    /**
     * Displays all decks currently owned by the collector.
     *
     * @param collector the {@code Collector} whose decks are shown
     */
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

    /**
     * Prompts the user to input a deck name.
     *
     * @return the name of the deck
     */
    public String promptDeckName(){
        System.out.print("Enter Deck Name (0 - Cancel): ");
        return sc.nextLine();
    }

    /**
     * Displays confirmation that a deck was created.
     *
     * @param name the deck's name
     */
    public void displayDeckCreation(String name) {
        System.out.printf("Deck \"%s\" created.\n", name);
    }
}
