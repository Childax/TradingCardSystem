package com.TradingCardSystem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The BinderView class handles all user interaction related to binders.
 * It is responsible for displaying binder information and prompting for user input.
 */
public class BinderView {
    private Scanner sc;

    /**
     * Constructs a BinderView with the specified Scanner for user input.
     *
     * @param sc the Scanner to read user input
     */
    public BinderView(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Displays the main binder menu.
     *
     * @param binder the binder whose name will be shown in the menu
     */
    public void displayBinderMenu(Binder binder) {
        System.out.printf("Binder Selected: [%s]\n", binder.getName());
        System.out.println("[1] Add a Card to Binder");
        System.out.println("[2] Remove a Card from Binder");
        System.out.println("[3] Trade Card");
        System.out.println("[4] View Binder Cards");
        System.out.println("[5] Delete Binder");
        System.out.println("[0] Back");
        System.out.print("> ");
    }

    /**
     * Displays all cards in the binder in a formatted list.
     *
     * @param binder the binder whose cards will be displayed
     */
    public void displayCardsFromBinder(Binder binder) {
        if (binder.getCards().size() <= 0) {
            System.out.println("No cards found in the binder.");
            return;
        }

        ArrayList<Card> cards = binder.getCardsSortedWithDuplicates();
        System.out.println("========== [" + binder.getName() + "] ==========");
        System.out.println("Total Cards: " + cards.size());
        System.out.println();
        System.out.println("Card List:");

        int index = 1;
        for (Card card : cards) {
            System.out.printf("%d. %s [%s, Variant: %s, $%.2f]%n",
                    index++,
                    card.getName(),
                    card.getRarity(),
                    card.getVariant() != null ? card.getVariant() : "None",
                    card.getValue()
            );
        }

        System.out.println("===================================");
    }

    /**
     * Prompts the user to enter a menu choice.
     *
     * @return the user's menu choice as an integer
     */
    public int promptBinderMenuChoice() {
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }

    /**
     * Prompts the user for confirmation before deleting a binder.
     *
     * @param name the name of the binder to confirm deletion for
     * @return the user's response
     */
    public String promptBinderDeletionConfirmation(String name) {
        System.out.printf("Are you sure you want to delete binder \"%s\"? (y/n): ", name);
        return sc.nextLine();
    }

    /**
     * Displays a message indicating the binder was not deleted.
     */
    public void displayBinderNotDeleted() {
        System.out.println("Binder was not deleted.");
    }

    /**
     * Displays a message indicating a return to the main menu.
     */
    public void displayReturnMessage() {
        System.out.println("Returning to main menu...");
    }

    /**
     * Prompts the user to input the name of a card to add to the binder.
     *
     * @return the entered card name
     */
    public String promptAddCard() {
        System.out.print("Enter the card you want to add (0 - Cancel): ");
        return sc.nextLine();
    }

    /**
     * Displays a confirmation message that a card was added to a binder.
     *
     * @param cardName   the name of the card added
     * @param binderName the name of the binder it was added to
     */
    public void displayAddCardConfirmation(String cardName, String binderName) {
        System.out.printf("%s was added to %s\n", cardName, binderName);
    }

    /**
     * Prompts the user to input the name of a card to remove from the binder.
     *
     * @return the entered card name
     */
    public String promptRemoveCard() {
        System.out.print("Enter the card you want to remove (0 - Cancel): ");
        return sc.nextLine();
    }

    /**
     * Displays a confirmation message that a card was removed from the binder.
     *
     * @param cardName the name of the removed card
     */
    public void displayRemoveCardConfirmation(String cardName) {
        System.out.printf("%s was returned to collection\n", cardName);
    }

    /**
     * Displays a message indicating the card was not found in the collection or binder.
     */
    public void displayCardNotFound() {
        System.out.println("Card was not in your collection.");
    }

    /**
     * Displays a message indicating that the binder has reached its maximum capacity.
     */
    public void displayBinderFull() {
        System.out.println("Maximum card count reached");
    }

    /**
     * Prompts the user to start a card trade.
     *
     * @return the user's response
     */
    public String promptTradeStart() {
        System.out.print("Would you like to trade one of your cards? (y/n): ");
        return sc.nextLine();
    }

    /**
     * Prompts the user for the name of the card they want to trade away.
     *
     * @return the entered card name
     */
    public String promptTradeCardName() {
        System.out.print("Enter the card name to be traded (0 - Cancel): ");
        return sc.nextLine();
    }

    /**
     * Prompts the user for the name of the new card they want in the trade.
     *
     * @return the entered card name
     */
    public String promptTradeNewCard() {
        System.out.print("Enter the new card name (0 - Cancel): ");
        return sc.nextLine();
    }

    /**
     * Prompts the user to enter the full details of a card for trade and creates it via CardController.
     *
     * @param cardController the CardController used to create the card
     * @return the new card object created
     */
    public Card promptTradeCardDetails(CardController cardController) {
        System.out.println("New Card Details ");
        String name = promptTradeNewCard();
        if (name.equals("0")) {
            System.out.println("Returning to menu...");
            return null;
        }
        return cardController.makeCardFromName(name);
    }

    /**
     * Prompts the user to confirm a card trade, showing the value difference.
     *
     * @param oldCard the value of the card to be removed
     * @param newCard the value of the card to be added
     * @return 'y' if trade confirmed, 'n' if rejected
     */
    public char promptTradeConfirmation(double oldCard, double newCard) {
        if (oldCard - newCard > 1.0 || oldCard - newCard < -1.0) {
            System.out.println("[!] You are trading a card worth more or less than $1 compared to the new card.");
        } else {
            System.out.println("[✔] Old card and New card to be replaced are of similar value");
        }
        System.out.print("Would you like to accept the trade? (y/n): ");
        return sc.nextLine().charAt(0);
    }

    /**
     * Displays a message indicating that the trade was cancelled.
     */
    public void displayTradeCancellation() {
        System.out.println("Trade Card has been cancelled.");
    }

    /**
     * Displays detailed information about a completed card trade.
     *
     * @param oldCard the card that was removed
     * @param newCard the card that was added
     */
    public void displayTradeInformation(Card oldCard, Card newCard) {
        System.out.println("\nTrade Information:");
        System.out.println("=================================");

        System.out.println("Removed Card:");
        System.out.println("-Name   : " + oldCard.getName());
        System.out.println("-Value  : $" + oldCard.getValue());
        System.out.println("-Rarity : " + oldCard.getRarity());
        System.out.println("-Variant: " + oldCard.getVariant());

        System.out.println("---------------------------------");

        System.out.println("Added Card:");
        System.out.println("-Name   : " + newCard.getName());
        System.out.println("-Value  : $" + newCard.getValue());
        System.out.println("-Rarity : " + newCard.getRarity());
        System.out.println("-Variant: " + newCard.getVariant());

        System.out.println("=================================");
    }
}
