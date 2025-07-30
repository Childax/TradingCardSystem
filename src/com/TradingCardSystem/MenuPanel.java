package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

/**
 * The MenuPanel class represents the main menu UI for the Trading Card Inventory System (TCIS).
 * It allows users to navigate to various features like adding cards, creating binders or decks,
 * viewing collections, and managing binders and decks.
 */
public class MenuPanel extends JPanel {

    private Collector collector;
    private JButton btnViewCollection;
    private JButton btnManageBinder;
    private JButton btnManageDeck;
    private JLabel moneyLabel;

    /**
     * Constructs the MenuPanel with interactive buttons to navigate the TCIS system.
     *
     * @param mainWindow the main window frame to which this panel belongs
     * @param collector the collector whose data is being managed
     */
    public MenuPanel(MainProgramWindow mainWindow, Collector collector) {
        this.collector = collector;

        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30)); // dark background

        // Title label
        JLabel title = new JLabel("TCIS Menu", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80)); // padding

        JButton btnAddCard = createMenuButton("Add New Card");
        JButton btnCreateBinder = createMenuButton("Create Binder");
        JButton btnCreateDeck = createMenuButton("Create Deck");
        btnViewCollection = createMenuButton("View Collection");
        btnManageBinder = createMenuButton("Manage Binders");
        btnManageDeck = createMenuButton("Manage Decks");

        // Enable/disable logic
        btnViewCollection.setEnabled(!collector.getCards().isEmpty());
        btnManageBinder.setEnabled(!collector.getBinders().isEmpty());
        btnManageDeck.setEnabled(!collector.getDecks().isEmpty());

        // Button actions
        btnAddCard.addActionListener(e -> mainWindow.showCustomPanel(new AddCardPanel(mainWindow, collector)));
        btnCreateBinder.addActionListener(e -> mainWindow.showCustomPanel(new CreateBinderPanel(mainWindow, collector)));
        btnCreateDeck.addActionListener(e -> mainWindow.showCustomPanel(new CreateDeckPanel(mainWindow, collector)));
        btnViewCollection.addActionListener(e -> mainWindow.showCustomPanel(new ViewCollectionPanel(mainWindow, collector)));
        btnManageBinder.addActionListener(e -> mainWindow.showManageBindersPanel(collector));
        btnManageDeck.addActionListener(e -> mainWindow.showManageDecksPanel(collector));

        // Add all buttons to button panel with spacing
        for (JButton btn : new JButton[]{btnAddCard, btnCreateBinder, btnCreateDeck, btnViewCollection, btnManageBinder, btnManageDeck}) {
            buttonPanel.add(btn);
            buttonPanel.add(Box.createVerticalStrut(15));
        }

        add(buttonPanel, BorderLayout.CENTER);

        moneyLabel = new JLabel("Money: $" + String.format("%.2f", collector.getMoney()), SwingConstants.CENTER);
        moneyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        moneyLabel.setForeground(Color.WHITE);
        moneyLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(moneyLabel, BorderLayout.SOUTH);
    }

    /**
     * Refreshes the panel's components such as enabling/disabling buttons
     * and updating the money label based on the current collector state.
     */
    public void refresh() {
        btnViewCollection.setEnabled(!collector.getCards().isEmpty());
        btnManageBinder.setEnabled(!collector.getBinders().isEmpty());
        btnManageDeck.setEnabled(!collector.getDecks().isEmpty());
        moneyLabel.setText("Money: $" + String.format("%.2f", collector.getMoney()));
    }

    /**
     * Creates a styled menu button with hover effects.
     *
     * @param text the text to display on the button
     * @return a configured JButton with styles
     */
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 40));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(new Color(50, 50, 50));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        button.setCursor(Cursor.getDefaultCursor());

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            Color defaultColor = new Color(50, 50, 50);
            Color hoverColor = new Color(70, 70, 70);

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(defaultColor);
            }
        });

        return button;
    }
}
