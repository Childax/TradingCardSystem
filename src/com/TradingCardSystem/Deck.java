package com.TradingCardSystem;

import java.util.ArrayList;

/**
 * The {@code Deck} class represents a collection of cards grouped under a deck name.
 * It supports operations such as adding, removing, and retrieving cards, as well as
 * checking if the deck is full.
 */
public class Deck {
    private String deckName;
    private ArrayList<Card> deckCards;
    private boolean isSellable;

    /**
     * Constructs a {@code Deck} with the given name.
     *
     * @param name the name of the deck
     */
    public Deck(String name) {
        this.deckName = name;
        this.deckCards = new ArrayList<>();
        this.isSellable = false;
    }

    /**
     * Returns the name of the deck.
     *
     * @return the deck name
     */
    public String getName() {
        return this.deckName;
    }

    /**
     * Sets the name of the deck.
     *
     * @param name the new name of the deck
     */
    public void setName(String name) {
        this.deckName = name;
    }

    /**
     * Returns the list of cards in the deck.
     *
     * @return an {@code ArrayList} of {@code Card} objects
     */
    public ArrayList<Card> getCards() {
        return deckCards;
    }

    /**
     * Returns the number of cards in the deck.
     *
     * @return the size of the deck
     */
    public int getCardCount() {
        return deckCards.size();
    }

    /**
     * Checks if the deck has reached the maximum capacity of 10 cards.
     *
     * @return {@code true} if the deck is full, {@code false} otherwise
     */
    public boolean isFull() {
        return deckCards.size() >= 10;
    }

    /**
     * Adds a card to the deck.
     *
     * @param card the {@code Card} to add
     */
    public void addCard(Card card) {
        deckCards.add(card);
    }

    /**
     * Removes a card from the deck.
     *
     * @param card the {@code Card} to remove
     */
    public void removeCard(Card card) {
        deckCards.remove(card);
    }

    /**
     * Returns boolean value if deck is sellable
     *
     * @return true or false
     */
    public boolean getSellability() { return isSellable; }

    /**
     * Allows user to set if deck is sellable.
     *
     * @param isSellable checks if deck is sellable
     */
    public void setSellablility(boolean isSellable) { this.isSellable = isSellable; }

    /**
     * Retrieves a card from the deck by its name (case-insensitive).
     *
     * @param name the name of the card
     * @return the matching {@code Card}, or {@code null} if not found
     */
    public Card getCardWithName(String name) {
        for (Card card : deckCards) {
            if (card.getName().equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;
    }

    /**
     *  Gets the total value of the deck.
     *
     * @return total value
     */
    public double getDeckPrice() {
        double total = 0;
        for (Card c : getCards()) {
            total += c.getValue();
        }
        return total;
    }
}
