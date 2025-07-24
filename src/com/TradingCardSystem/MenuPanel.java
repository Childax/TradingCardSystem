package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class MenuPanel extends JPanel {

    private Collector collector;
    private JButton btnViewCollection;
    private JButton btnManageBinder;
    private JButton btnManageDeck;

    public MenuPanel(MainProgramWindow mainWindow, Collector collector) {

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
        btnAddCard.addActionListener(e -> mainWindow.showCustomPanel(new AddCardPanel(mainWindow, collector)));
        // Create Binder
        btnCreateBinder.addActionListener(e -> mainWindow.showCustomPanel(new CreateBinderPanel(mainWindow, collector)));
        // Create Deck
        btnCreateDeck.addActionListener(e -> mainWindow.showCustomPanel(new CreateDeckPanel(mainWindow, collector)));
        // Display Collection
        btnViewCollection.addActionListener(e -> mainWindow.showCustomPanel(new ViewCollectionPanel(mainWindow, collector)));

        btnManageBinder.addActionListener(e -> mainWindow.showManageBindersPanel(collector));

        btnManageDeck.addActionListener((e -> mainWindow.showManageDecksPanel(collector)));

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
