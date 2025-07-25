package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

public class DeckMenuPanel extends JPanel {
    private MainProgramWindow mainWindow;
    private Deck activeDeck;
    private Collector collector;

    public DeckMenuPanel(MainProgramWindow mainWindow, Deck activeDeck, Collector collector) {
        this.mainWindow = mainWindow;
        this.activeDeck = activeDeck;
        this.collector = collector;

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Deck: " + activeDeck.getName(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        JButton viewBtn = new JButton("View Cards in Deck");
        JButton addBtn = new JButton("Add Card to Deck");
        JButton deleteBtn = new JButton("Delete Deck");
        JButton backBtn = new JButton("Back to Main Menu");

        // TODO: Add button listeners for deck functionality
        // View Deck Cards
        viewBtn.addActionListener(e -> showDeckCards());

        // Add Card to Deck
        addBtn.addActionListener(e -> {
            AddCardFromCollectionPanel addPanel = new AddCardFromCollectionPanel(
                    mainWindow,
                    collector,
                    "Add a Card to Deck: " + activeDeck.getName(),
                    card -> {
                        if (activeDeck.getCards().size() >= 10) {
                            JOptionPane.showMessageDialog(this, "Deck is already full.");
                            return;
                        } else if (activeDeck.getCardWithName(card.getName()) != null) {
                            JOptionPane.showMessageDialog(this, "Deck already contains this card.");
                            return;
                        }
                        activeDeck.addCard(card);
                        card.decrementCount();
                        JOptionPane.showMessageDialog(this, "Card added!");
                    },
                    () -> mainWindow.showCustomPanel(this)
            );

            mainWindow.showCustomPanel(addPanel);
        });

        // Delete Deck
        deleteBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete the deck \"" + activeDeck.getName() + "\"?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                collector.deleteDeck(activeDeck.getName());
                JOptionPane.showMessageDialog(this, "Deck deleted successfully.");
                mainWindow.showManageDecksPanel(collector);
            }
        });

        backBtn.addActionListener(e -> mainWindow.showCustomPanel(new MenuPanel(mainWindow, collector)));

        for (JButton btn : new JButton[]{viewBtn, addBtn, deleteBtn, backBtn}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(200, 40));
            btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
            buttonPanel.add(btn);
            buttonPanel.add(Box.createVerticalStrut(15));
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void showDeckCards() {
        JPanel viewPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Cards in Deck: " + activeDeck.getName(), SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        viewPanel.add(title, BorderLayout.NORTH);

        DeckCardGridPanel gridPanel = new DeckCardGridPanel(
                activeDeck.getCards(),
                cardToRemove -> {
                    activeDeck.removeCard(cardToRemove);
                    cardToRemove.incrementCount();
                    JOptionPane.showMessageDialog(this, "Card removed from deck.");
                    showDeckCards(); // Refresh view
                },
                cardToInspect -> {
                    JOptionPane.showMessageDialog(this, cardToInspect.getDetailedInfoWithoutCount(),
                            "Card Details", JOptionPane.INFORMATION_MESSAGE);
                }
        );

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        viewPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> mainWindow.showCustomPanel(this));
        JPanel backPanel = new JPanel();
        backPanel.add(backBtn);
        viewPanel.add(backPanel, BorderLayout.SOUTH);

        mainWindow.showCustomPanel(viewPanel);
    }
}
