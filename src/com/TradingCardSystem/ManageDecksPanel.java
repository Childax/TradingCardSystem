package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel that allows users to view and manage their decks.
 * Displays each deck in a card format with a manage button.
 */
public class ManageDecksPanel extends JPanel {

    /**
     * Constructs the ManageDecksPanel.
     *
     * @param mainWindow the main window of the program
     * @param collector the collector whose decks will be managed
     */
    public ManageDecksPanel(MainProgramWindow mainWindow, Collector collector) {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Select a Deck to Manage", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // Deck list container
        JPanel deckListPanel = new JPanel();
        deckListPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        deckListPanel.setBackground(new Color(30, 30, 30));

        JScrollPane scrollPane = new JScrollPane(deckListPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));
        add(scrollPane, BorderLayout.CENTER);

        ArrayList<Deck> decks = collector.getDecks();

        if (decks.isEmpty()) {
            JLabel emptyLabel = new JLabel("No decks available.", SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            emptyLabel.setForeground(Color.GRAY);
            deckListPanel.add(emptyLabel);
        } else {
            for (Deck deck : decks) {
                JPanel deckPanel = new JPanel();
                deckPanel.setPreferredSize(new Dimension(150, 100));
                deckPanel.setLayout(new BorderLayout(5, 5));
                deckPanel.setBackground(new Color(45, 45, 45));
                deckPanel.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70)));

                JLabel deckLabel = new JLabel(deck.getName(), SwingConstants.CENTER);
                deckLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
                deckLabel.setForeground(Color.WHITE);

                JPanel labelPanel = new JPanel();
                labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
                labelPanel.setBackground(new Color(45, 45, 45));
                deckLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                labelPanel.add(Box.createVerticalStrut(10));
                labelPanel.add(deckLabel);
                labelPanel.add(Box.createVerticalStrut(5));

                deckPanel.add(labelPanel, BorderLayout.CENTER);

                JButton manageBtn = createStyledButton("Manage");
                manageBtn.addActionListener(e -> mainWindow.showDeckMenu(collector, deck));

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
                buttonPanel.setBackground(new Color(45, 45, 45));
                buttonPanel.add(manageBtn);

                deckPanel.add(buttonPanel, BorderLayout.SOUTH);
                deckListPanel.add(deckPanel);
            }
        }

        // Back button
        JButton backBtn = createStyledButton("Back to Main Menu");
        backBtn.addActionListener(e -> mainWindow.showCustomPanel(new MenuPanel(mainWindow, collector)));
        JPanel backBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backBtnPanel.setBackground(new Color(30, 30, 30));
        backBtnPanel.add(backBtn);
        add(backBtnPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates a styled JButton with hover effects.
     *
     * @param text the button label
     * @return a styled JButton
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
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
}
