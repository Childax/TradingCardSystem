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
        setBackground(new Color(30, 30, 30));

        JLabel titleLabel = new JLabel("Deck: " + activeDeck.getName(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 30, 100));

        JButton viewBtn = createStyledButton("View Cards in Deck");
        JButton addBtn = createStyledButton("Add Card to Deck");
        JButton deleteBtn = createStyledButton("Delete Deck");
        JButton sellBtn = createStyledButton("Sell Deck");
        JButton backBtn = createStyledButton("Back to Main Menu");

        viewBtn.addActionListener(e -> showDeckCards());

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

        sellBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to sell this deck?",
                    "Confirm Sale", JOptionPane.YES_NO_OPTION);
            // TODO: Edit when deck sale is implemented
            if (confirm == JOptionPane.YES_OPTION) {
                collector.removeDeck(activeDeck);
                JOptionPane.showMessageDialog(this, "Deck sold for 10 million dollars.");
                mainWindow.showManageDecksPanel(collector);
            }
        });

        backBtn.addActionListener(e -> mainWindow.showCustomPanel(new MenuPanel(mainWindow, collector)));

        for (JButton btn : new JButton[]{viewBtn, addBtn, deleteBtn, sellBtn, backBtn}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonPanel.add(btn);
            buttonPanel.add(Box.createVerticalStrut(20));
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setMaximumSize(new Dimension(240, 45));
        button.setCursor(Cursor.getDefaultCursor());
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(80, 80, 80));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(60, 60, 60));
            }
        });

        return button;
    }

    private void showDeckCards() {
        DeckCardGridPanel gridPanel = new DeckCardGridPanel(
                activeDeck.getCards(),
                cardToRemove -> {
                    activeDeck.removeCard(cardToRemove);
                    cardToRemove.incrementCount();
                    JOptionPane.showMessageDialog(this, "Card removed from deck.");
                    showDeckCards();
                },
                cardToInspect -> {
                    JOptionPane.showMessageDialog(this, cardToInspect.getDetailedInfoWithoutCount(),
                            "Card Details", JOptionPane.INFORMATION_MESSAGE);
                }
        );

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(30, 30, 30));
        wrapper.add(scrollPane, BorderLayout.CENTER);

        JButton backBtn = createStyledButton("Back");
        backBtn.addActionListener(e -> mainWindow.showCustomPanel(this));

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(30, 30, 30));
        bottom.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        bottom.add(backBtn);
        wrapper.add(bottom, BorderLayout.SOUTH);

        mainWindow.showCustomPanel(wrapper);
    }
}
