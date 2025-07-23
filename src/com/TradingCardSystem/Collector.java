package com.TradingCardSystem;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * The {@code Collector} class represents a user who collects trading cards,
 * organizes them into binders and decks, and manages their personal collection.
 */
public class Collector {

    private String username;
    private ArrayList<Card> cards;
    private ArrayList<Binder> binders;
    private ArrayList<Deck> decks;

    /**
     * Constructs a {@code Collector} with a default name ("fn") and initializes empty
     * lists for cards, binders, and decks.
     */
    public Collector() {
        this.username = "fn";
        this.cards = new ArrayList<>();
        this.binders = new ArrayList<>();
        this.decks = new ArrayList<>();
    }

    /**
     * Returns the collector's username.
     *
     * @return the username
     */
    public String getName() {
        return username;
    }

    /**
     * Sets the collector's username.
     *
     * @param name the new username
     */
    public void setName(String name) {
        this.username = name;
    }

    /**
     * Returns the list of cards in the collector's collection.
     *
     * @return the list of cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Returns the list of binders owned by the collector.
     *
     * @return the list of binders
     */
    public ArrayList<Binder> getBinders() {
        return binders;
    }

    /**
     * Returns the list of decks owned by the collector.
     *
     * @return the list of decks
     */
    public ArrayList<Deck> getDecks() {
        return decks;
    }

    /**
     * Adds a card to the collection. If a card with the same name already exists,
     * its count is incremented instead of adding a new entry.
     *
     * @param newCard the card to add
     */
    public void addCard(Card newCard) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(newCard.getName())) {
                card.incrementCount();
                return;
            }
        }
        cards.add(newCard);
        newCard.incrementCount();
    }

    /**
     * Removes one instance of a card by name from the collection.
     *
     * @param name the name of the card to remove
     * @return true if the card was found and removed; false otherwise
     */
    public boolean removeCardObject(String name) {
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.getName().equalsIgnoreCase(name)) {
                card.decrementCount();
                if (card.getCount() <= 0) {
                    cards.remove(i);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Displays all cards in the collection sorted alphabetically.
     */
    public void displayCards() {
        if (cards.isEmpty()) {
            System.out.println("Your collection is empty.");
            return;
        }

        cards.sort(Comparator.comparing(Card::getName, String.CASE_INSENSITIVE_ORDER));

        System.out.println("Your Collection:");
        for (Card card : cards) {
            if(card.getCount() > 0) {
                card.displayCardHidden();
            }
        }
    }

    /**
     * Checks if a card with the specified name exists in the collection.
     *
     * @param name the name of the card to check
     * @return true if the card exists; false otherwise
     */
    public boolean hasCardWithName(String name) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a reference to the card with the given name, if it exists.
     *
     * @param name the name of the card
     * @return the matching {@code Card} object, or null if not found
     */
    public Card getCardWithName(String name) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;
    }

    /**
     * Checks if a card is stored in any binder or deck.
     *
     * @param name the name of the card
     * @return true if the card is present in a binder or deck
     */
    public boolean isCardInBindersOrDecks(String name) {
        for (Binder binder : binders) {
            for (Card c : binder.getCards()) {
                if (c.getName().equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }

        for (Deck deck : decks) {
            for (Card c : deck.getCards()) {
                if (c.getName().equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Adds a new binder to the collector with the specified name.
     *
     * @param name the name of the binder
     */
    public void addBinder(String name) {
        binders.add(new Binder(name));
    }

    /**
     * Adds a new binder to the collector with the specified binder object
     *
     * @param binder the binder to be added
     */
    public void addBinder(Binder binder) {
        binders.add(binder);
    }

    /**
     * Deletes a binder by name. Returns cards in the binder back to the collection.
     *
     * @param name the name of the binder
     * @return true if the binder was successfully deleted; false if not found
     */
    public boolean deleteBinder(String name) {
        Binder binderToRemove = getBinderByName(name);

        if(binderToRemove == null){
            System.out.println("Binder with name \"" + name + "\" not found.");
            return false;
        }

        for(Card card : binderToRemove.getCards()){
            this.addCard(card);
        }

        binders.remove(binderToRemove);

        System.out.println("Binder \"" + name + "\" has been removed and cards returned to your collection.");
        return true;
    }

    /**
     * Retrieves a binder by name.
     *
     * @param name the name of the binder
     * @return the matching {@code Binder} object, or null if not found
     */
    public Binder getBinderByName(String name) {
        for (Binder b : binders) {
            if (b.getName().equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Checks if a binder with the given name exists.
     *
     * @param name the name of the binder
     * @return true if found; false otherwise
     */
    public boolean hasBinderWithName(String name) {
        for (Binder b : binders) {
            if (b.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new deck to the collector with the specified name.
     *
     * @param name the name of the deck
     */
    public void addDeck(String name) {
        decks.add(new Deck(name));
    }

    public void addDeck(Deck deck) { decks.add(deck); }

    /**
     * Deletes a deck by name and returns its cards back to the collection.
     *
     * @param name the name of the deck
     * @return true if deleted successfully; false if not found
     */
    public boolean deleteDeck(String name) {
        Deck deckToRemove = getDeckByName(name);

        if (deckToRemove == null) {
            System.out.println("Deck with name \"" + name + "\" not found.");
            return false;
        }

        for (Card card : deckToRemove.getCards()) {
            this.addCard(card);
        }

        decks.remove(deckToRemove);

        System.out.println("Deck '" + name + "' has been removed and cards returned to your collection.");
        return true;
    }

    /**
     * Retrieves a deck by name.
     *
     * @param name the name of the deck
     * @return the matching {@code Deck} object, or null if not found
     */
    public Deck getDeckByName(String name) {
        for (Deck d : decks) {
            if(d.getName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        return null;
    }

    /**
     * Checks if a deck with the given name exists.
     *
     * @param name the name of the deck
     * @return true if found; false otherwise
     */
    public boolean hasDeckWithName(String name) {
        for (Deck d : decks) {
            if (d.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
