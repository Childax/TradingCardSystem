package com.TradingCardSystem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code DeckView} class handles all user interface operations
 * related to displaying and interacting with a {@link Deck}.
 * It presents menus, prompts for input, and displays deck details.
 */
public class DeckView {
    private Scanner sc;

    /**
     * Constructs a {@code DeckView} with a {@code Scanner} for input handling.
     *
     * @param sc the {@code Scanner} object used for user input
     */
    public DeckView(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Displays the main menu for the selected deck.
     *
     * @param deck the deck whose menu is being shown
     */
    public void displayDeckMenu(Deck deck) {
        System.out.printf("Deck Selected: [%s]\n", deck.getName());
        System.out.println("[1] Add a Card to Deck");
        System.out.println("[2] Remove a Card from Deck");
        System.out.println("[3] View Deck Cards");
        System.out.println("[4] Delete Deck");
        System.out.println("[0] Back");
        System.out.print("> ");
    }

    /**
     * Prompts the user to enter the name of a card to add to the deck.
     *
     * @return the card name as input by the user
     */
    public String promptAddCardToDeck() {
        System.out.print("Enter the card you want to add (0 - Cancel): ");
        return sc.nextLine().trim();
    }

    /**
     * Displays a confirmation message after adding a card to the deck.
     *
     * @param cardName the name of the card added
     * @param deckName the name of the deck
     */
    public void displayAddCardConfirmation(String cardName, String deckName) {
        System.out.printf("%s was added to %s\n", cardName, deckName);
    }

    /**
     * Prompts the user to enter the name of a card to remove from the deck.
     *
     * @return the card name as input by the user
     */
    public String promptRemoveCardFromDeck() {
        System.out.print("Enter the card you want to remove (0 - Cancel): ");
        return sc.nextLine().trim();
    }

    /**
     * Displays a message indicating that a card was not found.
     */
    public void displayCardNotFound() {
        System.out.println("Card was not in your collection.");
    }

    /**
     * Displays a message indicating the deck has reached its card limit.
     */
    public void displayDeckFull() {
        System.out.println("Maximum card count reached.");
    }

    /**
     * Displays a message indicating that a card is already in the deck.
     */
    public void displayCardExists() {
        System.out.println("Card is already in your deck.");
    }

    /**
     * Displays the list of cards from the deck and shows full details of a selected card.
     *
     * @param deck the deck whose cards are displayed
     */
    public void displayCardsFromDeck(Deck deck) {
        ArrayList<Card> cards = deck.getCards();
        System.out.println("========== [" + deck.getName() + "] ==========");
        System.out.println("Card List:");

        int index = 1;
        for (Card card : cards) {
            System.out.printf("%d. %s%n", index++, card.getName());
        }

        System.out.println("===================================");

    }

    /**
     * Prompts the user to enter a card name.
     *
     * @return the card name as input by the user
     */
    public String promptCardName() {
        System.out.print("Enter Card Name (0 - Cancel): ");
        return sc.nextLine();
    }

    /**
     * Prompts the user for a menu choice.
     *
     * @return the user's selected menu option
     */
    public int promptDeckMenuChoice() {
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline
        return choice;
    }

    /**
     * Displays a message indicating the user is returning to the main menu.
     */
    public void displayReturnMessage() {
        System.out.println("Returning to main menu...");
    }

    /**
     * Displays a confirmation message after removing a card from the deck.
     *
     * @param cardName the name of the card removed
     */
    public void displayRemoveCardConfirmation(String cardName) {
        System.out.printf("%s was returned to collection\n", cardName);
    }

    /**
     * Prompts the user to confirm deck deletion.
     *
     * @param name the name of the deck
     * @return the user's confirmation input
     */
    public String promptDeckDeletionConfirmation(String name) {
        System.out.printf("Are you sure you want to delete deck \"%s\"? (y/n): ", name);
        return sc.nextLine();
    }

    /**
     * Displays a message indicating that the deck was not deleted.
     */
    public void displayDeckNotDeleted() {
        System.out.println("Deck was not deleted.");
    }
}
