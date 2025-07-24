package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

public class AddCardPanel extends JPanel {
    public AddCardPanel(MainProgramWindow mainWindow, Collector collector) {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Add Card to Collection", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        JLabel nameLabel = new JLabel("Card Name:");
        JTextField nameField = new JTextField();

        JLabel rarityLabel = new JLabel("Rarity:");
        JTextField rarityField = new JTextField();

        JLabel variantLabel = new JLabel("Variant:");
        JTextField variantField = new JTextField();

        JLabel valueLabel = new JLabel("Value:");
        JTextField valueField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(rarityLabel);
        formPanel.add(rarityField);
        formPanel.add(variantLabel);
        formPanel.add(variantField);
        formPanel.add(valueLabel);
        formPanel.add(valueField);

        JButton submitBtn = new JButton("Add Card");
        JButton backBtn = new JButton("Back to Menu");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitBtn);
        buttonPanel.add(backBtn);

        add(title, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action: Add card
        submitBtn.addActionListener(e -> {
            String nameInput = nameField.getText().trim();
            String rarityInput = rarityField.getText().trim();
            String variantInput = variantField.getText().trim();
            String valueInput = valueField.getText().trim();

            if (nameInput.isEmpty() || rarityInput.isEmpty() || variantInput.isEmpty() || valueInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            try {
                Rarity rarity = Rarity.valueOf(rarityInput.toUpperCase());
                Variant variant = Variant.valueOf(variantInput.toUpperCase());
                double value = Double.parseDouble(valueInput);
                collector.addCard(new Card(nameInput, rarity, variant, value));
                JOptionPane.showMessageDialog(this, "Card added successfully!");

                nameField.setText("");
                rarityField.setText("");
                variantField.setText("");
                valueField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Value must be a number.");
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(this, "Invalid Rarity or Variant selected.");
            }

        });

        // Action: Back to menu
        backBtn.addActionListener(e -> mainWindow.showCustomPanel(new MenuPanel(mainWindow, collector)));
    }
}

