package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TradeCardPanel extends JPanel {
    public TradeCardPanel(MainProgramWindow mainWindow, Collector collector, Binder binder) {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Select a Card to Trade", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel cardGrid = new JPanel(new GridLayout(0, 2, 10, 10));
        List<Card> cards = binder.getCards();

        if (cards.isEmpty()) {
            add(title, BorderLayout.NORTH);
            add(new JLabel("No cards available in the binder.", SwingConstants.CENTER), BorderLayout.CENTER);
            JButton backBtn = new JButton("Back");
            backBtn.addActionListener(e -> mainWindow.showCustomPanel(new BinderMenuPanel(mainWindow, binder, collector)));
            add(backBtn, BorderLayout.SOUTH);
            return;
        }

        for (Card card : cards) {
            JPanel cardPanel = new JPanel(new BorderLayout());
            JTextArea cardInfo = new JTextArea(card.toString());
            cardInfo.setEditable(false);
            cardInfo.setFont(new Font("Monospaced", Font.PLAIN, 12));

            JButton tradeBtn = new JButton("Trade This Card");
            tradeBtn.addActionListener(e -> openTradeDialog(mainWindow, collector, binder, card));

            cardPanel.add(cardInfo, BorderLayout.CENTER);
            cardPanel.add(tradeBtn, BorderLayout.SOUTH);
            cardGrid.add(cardPanel);
        }

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> mainWindow.showCustomPanel(new BinderMenuPanel(mainWindow, binder, collector)));

        add(title, BorderLayout.NORTH);
        add(new JScrollPane(cardGrid), BorderLayout.CENTER);
        add(backBtn, BorderLayout.SOUTH);
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
                    String.format("Trade %s (₱%.2f) for %s (₱%.2f)?",
                            cardToBeRemoved.getName(), removedVal,
                            cardToBeAdded.getName(), addedVal),
                    "Confirm Trade",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                binder.tradeCard(cardToBeAdded, cardToBeRemoved);
                cardToBeRemoved.incrementCount(); // Return original to pool, optional
                JOptionPane.showMessageDialog(this, "Trade completed successfully!");
                mainWindow.showCustomPanel(new BinderMenuPanel(mainWindow, binder, collector)); // Refresh
            } else {
                JOptionPane.showMessageDialog(this, "Trade cancelled.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Trade aborted.");
        }
    }
}
