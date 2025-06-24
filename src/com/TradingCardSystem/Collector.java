package com.TradingCardSystem;

import java.util.ArrayList;
import java.util.Comparator;

public class Collector {

    private String username;
    private ArrayList<Card> cards;
    private ArrayList<Binder> binders;
    private ArrayList<Deck> decks;

    public Collector() {
        this.username = "fn";
        this.cards = new ArrayList<>();
        this.binders = new ArrayList<>();
        this.decks = new ArrayList<>();
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Binder> getBinders() {
        return binders;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    // Add card to collection (merge if name matches)
    public void addCard(Card newCard) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(newCard.getName())) {
                card.incrementCount();
                return;
            }
        }
        cards.add(newCard);
    }

    // Remove card by name
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
        return false; // card not found
    }

    // Display all cards
    public void displayCards() {
        if (cards.isEmpty()) {
            System.out.println("Your collection is empty.");
            return;
        }

        cards.sort(Comparator.comparing(Card::getName, String.CASE_INSENSITIVE_ORDER));

        System.out.println("Your Collection:");
        for (Card card : cards) {
            card.displayCardHidden();
        }
    }

    // Check if card with given name exists
    public boolean hasCardWithName(String name) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Get card by name
    public Card getCardWithName(String name) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;
    }

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

    public void addBinder(String name) {
        binders.add(new Binder(name));
    }

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

    public Binder getBinderByName(String name) {
        for (Binder b : binders) {
            if (b.getName().equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
    }

    public boolean hasBinderWithName(String name) {
        for (Binder b : binders) {
            if (b.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void addDeck(String name) {
        decks.add(new Deck(name));
    }

    public boolean removeDeck(String name) {
        Deck deckToRemove = getDeckByName(name);

        if (deckToRemove == null) {
            System.out.println("Deck not found.");
            return false;
        }

        for (Card card : deckToRemove.getCards()) {
            this.addCard(card);
        }

        decks.remove(deckToRemove);

        System.out.println("Deck '" + name + "' has been removed and cards returned to your collection.");
        return true;
    }

    public Deck getDeckByName(String name) {
        for (Deck d : decks) {
            if(d.getName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        return null;
    }

    public boolean hasDeckWithName(String name) {
        for (Deck d : decks) {
            if (d.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
