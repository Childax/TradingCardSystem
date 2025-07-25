package com.TradingCardSystem;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.function.Consumer;

public class BinderCardGridPanel extends JPanel {
    public BinderCardGridPanel(List<Card> cards, String buttonLabel, Consumer<Card> onCardClicked, Consumer<Card> onDetailsClicked) {
        setLayout(new GridLayout(4, 5, 20, 20));
        setBackground(new Color(18, 18, 18)); // dark background
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        int totalSlots = 20;

        for (int i = 0; i < totalSlots; i++) {
            JPanel cardPanel = new JPanel();
            cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
            cardPanel.setPreferredSize(new Dimension(200, 200));
            cardPanel.setBackground(new Color(30, 30, 30)); // card background
            cardPanel.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(50, 50, 50), 2, true),
                    new EmptyBorder(10, 10, 10, 10)
            ));

            if (i < cards.size()) {
                Card card = cards.get(i);

                JLabel nameLabel = new JLabel(card.getName(), SwingConstants.CENTER);
                nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
                nameLabel.setForeground(new Color(238, 238, 238));
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JButton removeBtn = new JButton(buttonLabel);
                JButton detailsBtn = new JButton("Details");

                styleButton(removeBtn, new Color(239, 83, 80), new Color(255, 255, 255)); // red button
                styleButton(detailsBtn, new Color(129, 199, 132), new Color(0, 0, 0)); // green button

                JPanel buttonRow = new JPanel();
                buttonRow.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
                buttonRow.setBackground(new Color(30, 30, 30));
                buttonRow.setOpaque(false);
                buttonRow.add(removeBtn);
                buttonRow.add(detailsBtn);

                removeBtn.addActionListener(e -> onCardClicked.accept(card));
                detailsBtn.addActionListener(e -> onDetailsClicked.accept(card));

                cardPanel.add(Box.createVerticalGlue());
                cardPanel.add(nameLabel);
                cardPanel.add(Box.createVerticalStrut(10));
                cardPanel.add(buttonRow);
                cardPanel.add(Box.createVerticalGlue());
            } else {
                JLabel emptyLabel = new JLabel("Empty Slot", SwingConstants.CENTER);
                emptyLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
                emptyLabel.setForeground(new Color(150, 150, 150));
                emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                cardPanel.add(Box.createVerticalGlue());
                cardPanel.add(emptyLabel);
                cardPanel.add(Box.createVerticalGlue());
            }

            // Hover effect (optional)
            cardPanel.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    cardPanel.setBackground(new Color(40, 40, 40));
                }

                public void mouseExited(MouseEvent e) {
                    cardPanel.setBackground(new Color(30, 30, 30));
                }
            });

            add(cardPanel);
        }
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
    }
}
