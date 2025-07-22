package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class MenuPanel extends JPanel {
    public MenuPanel(MainProgramWindow mainWindow, Collector collector,
                     CollectorController collectorController,
                     CollectorView collectorView, CardController cardController) {

        setLayout(new GridLayout(10, 1, 10, 10));

        JButton btnAddCard = new JButton("Add Card to Collection");
        JButton btnCreateBinder = new JButton("Create Binder");
        JButton btnCreateDeck = new JButton("Create Deck");
        JButton btnViewCollection = new JButton("View Collection");
        JButton btnManageBinder = new JButton("Manage Binders");
        JButton btnManageDeck = new JButton("Manage Decks");

        // Button actions

        // Add Card
        btnAddCard.addActionListener(e -> {
            // Show add card screen, or reuse existing logic
            mainWindow.showPanel("addCard");
        });

        // Create Binder
        btnCreateBinder.addActionListener(e -> collectorController.createBinder(collector));
        // Create Deck
        btnCreateDeck.addActionListener(e -> collectorController.createDeck(collector));
        // Display Collection
        btnViewCollection.addActionListener(e -> {
            mainWindow.showPanel("viewCollection");
        });

//        btnManageBinder.addActionListener(e -> {
//            Binder binder = collectorController.returnBinderChoice();
//            if (binder != null) {
//                BinderView binderView = new BinderView(new Scanner(System.in)); // temporary
//                BinderController binderController = new BinderController(collector, binder, binderView, cardController);
//                binderController.manageBinder();
//            }
//        });
//
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
}
