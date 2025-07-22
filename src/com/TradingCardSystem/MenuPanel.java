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
        JButton btnDisplayCollection = new JButton("Display Collection");
        JButton btnCardDetails = new JButton("Show Card Details");
        JButton btnRemoveCard = new JButton("Remove Card from Collection");
        JButton btnManageBinder = new JButton("Manage Binder");
        JButton btnManageDeck = new JButton("Manage Deck");

        // Button actions (still use controller)
        btnAddCard.addActionListener(e -> {
            // Show add card screen, or reuse existing logic
            mainWindow.showPanel("addCard");
        });

        btnCreateBinder.addActionListener(e -> collectorController.createBinder(collector));
        btnCreateDeck.addActionListener(e -> collectorController.createDeck(collector));
        btnDisplayCollection.addActionListener(e -> collectorView.displayCollection(collector));
        btnCardDetails.addActionListener(e -> collectorView.showCardDetails(collector));
        btnRemoveCard.addActionListener(e -> collectorController.removeCardFromCollection(collector));

        btnManageBinder.addActionListener(e -> {
            Binder binder = collectorController.returnBinderChoice();
            if (binder != null) {
                BinderView binderView = new BinderView(new Scanner(System.in)); // temporary
                BinderController binderController = new BinderController(collector, binder, binderView, cardController);
                binderController.manageBinder();
            }
        });

        btnManageDeck.addActionListener(e -> {
            Deck deck = collectorController.returnDeckChoice();
            if (deck != null) {
                DeckView deckView = new DeckView(new Scanner(System.in)); // temporary
                DeckController deckController = new DeckController(collector, deckView, deck);
                deckController.manageDeck();
            }
        });

        add(btnAddCard);
        add(btnCreateBinder);
        add(btnCreateDeck);
        add(btnDisplayCollection);
        add(btnCardDetails);
        add(btnRemoveCard);
        add(btnManageBinder);
        add(btnManageDeck);
    }
}
