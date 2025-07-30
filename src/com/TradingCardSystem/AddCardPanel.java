package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

/**
 * A panel that allows users to input and add a new card to their collection.
 *
 * Includes form inputs for card details, a submit button to add the card,
 * and a back button to return to the main menu.
 */
public class AddCardPanel extends JPanel {

    /**
     * Constructs the panel for adding a new card to the collection.
     *
     * @param mainWindow the main window for switching panels
     * @param collector the collector object to which the card will be added
     */
    public AddCardPanel(MainProgramWindow mainWindow, Collector collector) {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title styling
        JLabel title = new JLabel("Add Card to Collection", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // Input panel
        CardInputPanel inputPanel = new CardInputPanel();
        add(inputPanel, BorderLayout.CENTER);

        // Button panel
        JButton submitBtn = createStyledButton("Add Card");
        JButton backBtn = createStyledButton("Back to Menu");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.add(submitBtn);
        buttonPanel.add(backBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        submitBtn.addActionListener(e -> {
            try {
                Card newCard = inputPanel.getCard();
                collector.addCard(newCard);
                JOptionPane.showMessageDialog(this, "Card added successfully!");
                inputPanel.clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Value must be a valid number.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
            }
        });

        backBtn.addActionListener(e -> mainWindow.showCustomPanel(new MenuPanel(mainWindow, collector)));
    }

    /**
     * Creates a styled JButton with consistent appearance and hover effects.
     *
     * @param text the text to display on the button
     * @return the styled JButton instance
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setSize(100, 50);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY),
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // top, left, bottom, right padding
        ));
        button.setCursor(Cursor.getDefaultCursor());

        // Flash effect on hover
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
