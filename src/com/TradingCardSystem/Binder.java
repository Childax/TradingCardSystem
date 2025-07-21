package com.TradingCardSystem;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents a binder that holds trading cards.
 * A binder has a name and can hold up to 20 cards.
 */
public class Binder {
    private String name;
    private ArrayList<Card> binderCards;

    /**
     * Constructs a Binder with the specified name.
     *
     * @param name the name of the binder
     */
    public Binder(String name) {
        this.binderCards = new ArrayList<>();
        this.name = name;
    }

    /**
     * Returns the list of cards in the binder.
     *
     * @return a list of cards
     */
    public ArrayList<Card> getCards() {
        return binderCards;
    }

    /**
     * Returns the name of the binder.
     *
     * @return the name of the binder
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new name for the binder.
     *
     * @param name the new name of the binder
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the number of cards in the binder.
     *
     * @return the number of cards
     */
    public int getCardCount() {
        return this.binderCards.size();
    }

    /**
     * Adds a card to the binder.
     *
     * @param card the card to add
     */
    public void addCard(Card card) {
        this.binderCards.add(card);
    }

    /**
     * Removes a card from the binder.
     *
     * @param card the card to remove
     */
    public void removeCard(Card card) {
        this.binderCards.remove(card);
    }

    /**
     * Checks whether the binder has reached its capacity of 20 cards.
     *
     * @return true if the binder is full, false otherwise
     */
    public boolean isFull() {
        return binderCards.size() >= 20;
    }

    /**
     * Trades an old card in the binder with a new card.
     *
     * @param newCard the new card to add
     * @param oldCard the old card to remove
     */
    public void tradeCard(Card newCard, Card oldCard) {
        addCard(newCard);
        removeCard(oldCard);
    }

    /**
     * Returns a list of all cards in the binder, including duplicates,
     * sorted alphabetically by card name (case-insensitive).
     *
     * @return a sorted list of cards including duplicates
     */
    public ArrayList<Card> getCardsSortedWithDuplicates() {
        ArrayList<Card> expanded = new ArrayList<>();
        for (Card card : binderCards) {
            for (int i = 0; i < card.getCount(); i++) {
                expanded.add(card);
            }
        }

        expanded.sort(Comparator.comparing(Card::getName, String.CASE_INSENSITIVE_ORDER));
        return expanded;
    }

    /**
     * Searches for and returns a card in the binder by name.
     *
     * @param name the name of the card to search for
     * @return the card if found, null otherwise
     */
    public Card getCardWithName(String name) {
        for (Card card : binderCards) {
            if (card.getName().equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;
    }
}
