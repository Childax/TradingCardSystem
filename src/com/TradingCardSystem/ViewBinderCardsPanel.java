package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

public class ViewBinderCardsPanel extends JPanel {
    public ViewBinderCardsPanel(MainProgramWindow mainWindow, Collector collector, Binder binder) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Cards in Binder: " + binder.getName(), SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        CardGridPanel cardGrid = new CardGridPanel(
                binder.getCards(),
                card -> {
                    int confirm = JOptionPane.showConfirmDialog(this, "Remove this card from binder?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        binder.removeCard(card);
                        collector.addCard(card);
                        // Refresh panel manually
                        mainWindow.showBinderMenu(collector, binder);
                    }
                },
                card -> JOptionPane.showMessageDialog(this, card.getDetailedInfo(), "Card Details", JOptionPane.INFORMATION_MESSAGE)
        );

        add(new JScrollPane(cardGrid), BorderLayout.CENTER);

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> mainWindow.showBinderMenu(collector, binder));
        JPanel bottom = new JPanel();
        bottom.add(backBtn);
        add(bottom, BorderLayout.SOUTH);
    }
}
