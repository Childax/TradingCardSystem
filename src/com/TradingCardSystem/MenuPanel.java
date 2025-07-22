package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class MenuPanel extends JPanel {

    private Collector collector;
    private JButton btnViewCollection;
    private JButton btnManageBinder;
    private JButton btnManageDeck;

    public MenuPanel(MainProgramWindow mainWindow, Collector collector,
                     CollectorController collectorController,
                     CollectorView collectorView, CardController cardController) {

        this.collector = collector;
        setLayout(new GridLayout(10, 1, 10, 10));

        JButton btnAddCard = new JButton("Add Card to Collection");
        JButton btnCreateBinder = new JButton("Create Binder");
        JButton btnCreateDeck = new JButton("Create Deck");
        btnViewCollection = new JButton("View Collection");
        btnManageBinder = new JButton("Manage Binders");
        btnManageDeck = new JButton("Manage Decks");

        // Check if collection, binders, or decks are empty
        if (collector.getCards().isEmpty()) {
            btnViewCollection.setEnabled(false);
        } else {
            btnViewCollection.setEnabled(true);
        }

        if (collector.getBinders().isEmpty()) {
            btnManageBinder.setEnabled(false);
        } else {
            btnManageBinder.setEnabled(true);
        }

        if (collector.getDecks().isEmpty()) {
            btnManageDeck.setEnabled(false);
        } else {
            btnManageDeck.setEnabled(true);
        }

        // Button actions

        // Add Card
        btnAddCard.addActionListener(e -> mainWindow.showPanel("addCard"));

        // Create Binder
        btnCreateBinder.addActionListener(e -> mainWindow.showPanel("createBinder"));
        // Create Deck
        btnCreateDeck.addActionListener(e -> mainWindow.showPanel("createDeck"));
        // Display Collection
        btnViewCollection.addActionListener(e -> mainWindow.showPanel("viewCollection"));

        btnManageBinder.addActionListener(e -> mainWindow.showManageBindersPanel(collector));

//        btnManageDeck.addActionListener(e -> {
//            Deck deck = collectorController.returnDeckChoice();
//            if (deck != null) {
//                DeckView deckView = new DeckView(new Scanner(System.in)); // temporary
//                DeckController deckController = new DeckController(collector, deckView, deck);
//                deckController.manageDeck();
//            }
//        });

        add(btnAddCard);
        add(btnCreateBinder);
        add(btnCreateDeck);
        add(btnViewCollection);
        add(btnManageBinder);
        add(btnManageDeck);
    }

    public void refresh() {
        btnViewCollection.setEnabled(!collector.getCards().isEmpty());
        btnManageBinder.setEnabled(!collector.getBinders().isEmpty());
        btnManageDeck.setEnabled(!collector.getDecks().isEmpty());
    }
}
