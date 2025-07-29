package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TradeCardPanel extends JPanel {
    public TradeCardPanel(MainProgramWindow mainWindow, Collector collector, Binder binder) {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        JLabel title = new JLabel("Select a Card to Trade", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        List<Card> cards = binder.getCards();
        boolean hasVisibleCards = false;

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        gridPanel.setBackground(new Color(30, 30, 30));

        for (Card card : cards) {
            if (!binder.getCards().isEmpty()) {
                hasVisibleCards = true;
                gridPanel.add(createCardBox(card, mainWindow, collector, binder));
            }
        }

        if (hasVisibleCards) {
            JScrollPane scrollPane = new JScrollPane(gridPanel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            scrollPane.setBorder(null);
            scrollPane.getViewport().setBackground(new Color(30, 30, 30));
            add(scrollPane, BorderLayout.CENTER);
        } else {
            JLabel emptyLabel = new JLabel("No cards available in the binder.", SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            emptyLabel.setForeground(Color.GRAY);
            add(emptyLabel, BorderLayout.CENTER);
        }

        JButton backBtn = new JButton("Back");
        styleButton(backBtn);
        backBtn.addActionListener(e -> mainWindow.showCustomPanel(new BinderMenuPanel(mainWindow, binder, collector)));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(30, 30, 30));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        bottomPanel.add(backBtn);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createCardBox(Card card, MainProgramWindow mainWindow, Collector collector, Binder binder) {
        JPanel cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(150, 120));
        cardPanel.setLayout(new BorderLayout(5, 5));
        cardPanel.setBackground(new Color(45, 45, 45));
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70)));

        JLabel nameLabel = new JLabel(card.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setBackground(new Color(45, 45, 45));

        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelPanel.add(Box.createVerticalStrut(5));
        labelPanel.add(nameLabel);
        labelPanel.add(Box.createVerticalStrut(4));

        cardPanel.add(labelPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        buttonPanel.setBackground(new Color(45, 45, 45));

        JButton tradeBtn = createStyledButton("Trade");
        tradeBtn.addActionListener(e -> openTradeDialog(mainWindow, collector, binder, card));

        JButton detailsBtn = createStyledButton("Details");
        detailsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, card.getDetailedInfo(), "Card Details", JOptionPane.INFORMATION_MESSAGE));

        buttonPanel.add(tradeBtn);
        buttonPanel.add(detailsBtn);
        cardPanel.add(buttonPanel, BorderLayout.SOUTH);

        return cardPanel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        button.setCursor(Cursor.getDefaultCursor());

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

    private void styleButton(JButton button) {
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    private void openTradeDialog(MainProgramWindow mainWindow, Collector collector, Binder binder, Card cardToBeRemoved) {
        CardInputPanel inputPanel = new CardInputPanel();
        int result = JOptionPane.showConfirmDialog(
                this,
                inputPanel,
                "Enter Details of Card to Trade For",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return;

        try {
            Card cardToBeAdded = inputPanel.getCard();
            double removedVal = cardToBeRemoved.getValue();
            double addedVal = cardToBeAdded.getValue();

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    String.format("Trade %s ($%.2f) for %s ($%.2f)?",
                            cardToBeRemoved.getName(), removedVal,
                            cardToBeAdded.getName(), addedVal),
                    "Confirm Trade",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                if (!collector.hasCardWithName(cardToBeAdded.getName())) {
                    collector.addCard(cardToBeAdded);
                    cardToBeAdded.decrementCount();
                }
                binder.tradeCard(cardToBeAdded, cardToBeRemoved);
                collector.removeCardObject(cardToBeRemoved.getName());
                JOptionPane.showMessageDialog(this, "Trade completed successfully!");
                mainWindow.showCustomPanel(new TradeCardPanel(mainWindow, collector, binder));
            } else {
                JOptionPane.showMessageDialog(this, "Trade cancelled.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Trade aborted.");
        }
    }
}