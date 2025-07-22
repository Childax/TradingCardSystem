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

        JButton addBtn = new JButton("Add Card to Deck");
        JButton removeBtn = new JButton("Remove Card from Deck");
        JButton viewBtn = new JButton("View Cards in Deck");
        JButton deleteBtn = new JButton("Delete Deck");
        JButton backBtn = new JButton("Back to Main Menu");

        // TODO: Add button listeners for deck functionality

        deleteBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete the deck \"" + activeDeck.getName() + "\"?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                collector.deleteDeck(activeDeck.getName());
                JOptionPane.showMessageDialog(this, "Deck deleted successfully.");
                mainWindow.showPanel("menu");
            }
        });

        backBtn.addActionListener(e -> mainWindow.showPanel("menu"));

        for (JButton btn : new JButton[]{addBtn, removeBtn, viewBtn, deleteBtn, backBtn}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(200, 40));
            btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
            buttonPanel.add(btn);
            buttonPanel.add(Box.createVerticalStrut(15));
        }

        add(buttonPanel, BorderLayout.CENTER);
    }
}
