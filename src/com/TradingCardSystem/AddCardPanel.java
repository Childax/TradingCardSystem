package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

public class AddCardPanel extends JPanel {
    public AddCardPanel(MainProgramWindow mainWindow, Collector collector) {
        setLayout(new BorderLayout(10, 10));
        JLabel title = new JLabel("Add Card to Collection", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        CardInputPanel inputPanel = new CardInputPanel();

        JButton submitBtn = new JButton("Add Card");
        JButton backBtn = new JButton("Back to Menu");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitBtn);
        buttonPanel.add(backBtn);

        add(title, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

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
}


