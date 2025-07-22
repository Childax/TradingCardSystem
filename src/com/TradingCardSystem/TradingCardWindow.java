package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class TradingCardWindow {

    private Collector collector;
    private CollectorController collectorController;
    private CollectorView collectorView;
    private CardController cardController;

    public TradingCardWindow(Collector collector, CollectorController controller, CollectorView view, CardController cardController) {
        this.collector = collector;
        this.collectorController = controller;
        this.collectorView = view;
        this.cardController = cardController;
        createGUI();
    }


    public void createGUI() {
        JFrame frame = new JFrame("Trading Card Inventory System");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10, 1, 10, 10)); // 10 rows, 1 column, spacing
        frame.setLocationRelativeTo(null);

        JButton btnAddCard = new JButton("Add Card to Collection");
        JButton btnCreateBinder = new JButton("Create Binder");
        JButton btnCreateDeck = new JButton("Create Deck");
        JButton btnDisplayCollection = new JButton("Display Collection");
        JButton btnCardDetails = new JButton("Show Card Details");
        JButton btnRemoveCard = new JButton("Remove Card from Collection");
        JButton btnManageBinder = new JButton("Manage Binder");
        JButton btnManageDeck = new JButton("Manage Deck");

        // Add actions to buttons
        btnAddCard.addActionListener(e -> collectorController.addCardToCollection(collector));
        btnCreateBinder.addActionListener(e -> collectorController.createBinder(collector));
        btnCreateDeck.addActionListener(e -> collectorController.createDeck(collector));
        btnDisplayCollection.addActionListener(e -> collectorView.displayCollection(collector));
        btnCardDetails.addActionListener(e -> collectorView.showCardDetails(collector));
        btnRemoveCard.addActionListener(e -> collectorController.removeCardFromCollection(collector));
        btnManageBinder.addActionListener(e -> {
            Binder binder = collectorController.returnBinderChoice();
            if (binder != null) {
                BinderView binderView = new BinderView(new Scanner(System.in)); // placeholder
                BinderController binderController = new BinderController(collector, binder, binderView, cardController);
                binderController.manageBinder();
            }
        });
        btnManageDeck.addActionListener(e -> {
            Deck deck = collectorController.returnDeckChoice();
            if (deck != null) {
                DeckView deckView = new DeckView(new Scanner(System.in)); // placeholder
                DeckController deckController = new DeckController(collector, deckView, deck);
                deckController.manageDeck();
            }
        });

        // Add buttons to frame
        frame.add(btnAddCard);
        frame.add(btnCreateBinder);
        frame.add(btnCreateDeck);
        frame.add(btnDisplayCollection);
        frame.add(btnCardDetails);
        frame.add(btnRemoveCard);
        frame.add(btnManageBinder);
        frame.add(btnManageDeck);

        frame.setVisible(true);
    }
}
