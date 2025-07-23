package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManageDecksPanel extends JPanel {

    public ManageDecksPanel(MainProgramWindow mainWindow, Collector collector) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Title
        JLabel titleLabel = new JLabel("Select a Deck to Manage", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // List panel inside a scroll pane
        JPanel deckListPanel = new JPanel();
        deckListPanel.setLayout(new BoxLayout(deckListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(deckListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch decks
        ArrayList<Deck> decks = collector.getDecks();

        if (decks.isEmpty()) {
            JLabel emptyLabel = new JLabel("No decks available.", SwingConstants.CENTER);
            emptyLabel.setFont(new Font("SansSerif", Font.ITALIC, 16));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            deckListPanel.add(Box.createVerticalStrut(20));
            deckListPanel.add(emptyLabel);
        } else {
            for (Deck deck : decks) {
                JPanel deckPanel = new JPanel(new BorderLayout(10, 10));
                deckPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));

                JLabel deckLabel = new JLabel(deck.getName());
                deckLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
                deckPanel.add(deckLabel, BorderLayout.CENTER);

                JButton manageBtn = new JButton("Manage");
                manageBtn.addActionListener(e -> mainWindow.showDeckMenu(collector, deck));
                deckPanel.add(manageBtn, BorderLayout.EAST);

                deckListPanel.add(Box.createVerticalStrut(10));
                deckListPanel.add(deckPanel);
            }
        }

        // Back button
        JButton backBtn = new JButton("Back to Main Menu");
        backBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        backBtn.addActionListener(e -> mainWindow.showPanel("menu"));
        JPanel backBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backBtnPanel.add(backBtn);
        add(backBtnPanel, BorderLayout.SOUTH);
    }
}
