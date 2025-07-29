package com.TradingCardSystem;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.function.Consumer;

public class DeckCardGridPanel extends JPanel {
    public DeckCardGridPanel(
            List<Card> deckCards,
            Consumer<Card> onRemoveClicked,
            Consumer<Card> onDetailsClicked
    ) {
        setLayout(new GridLayout(2, 5, 10, 10)); // 2 rows, 5 columns = 10 slots
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(18, 18, 18)); // match dark background

        for (int i = 0; i < 10; i++) {
            JPanel cardPanel = new JPanel();
            cardPanel.setPreferredSize(new Dimension(120, 120));
            cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
            cardPanel.setBackground(new Color(30, 30, 30)); // slot background
            cardPanel.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(50, 50, 50), 2, true),
                    new EmptyBorder(10, 10, 10, 10)
            ));

            if (i < deckCards.size()) {
                Card card = deckCards.get(i);

                JLabel nameLabel = new JLabel(card.getName(), SwingConstants.CENTER);
                nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
                nameLabel.setForeground(new Color(238, 238, 238));
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JButton removeBtn = new JButton("Remove");
                JButton detailsBtn = new JButton("Details");

                styleButton(removeBtn, new Color(239, 83, 80), Color.WHITE); // red remove
                styleButton(detailsBtn, new Color(129, 199, 132), Color.BLACK); // green details

                removeBtn.addActionListener(e -> onRemoveClicked.accept(card));
                detailsBtn.addActionListener(e -> onDetailsClicked.accept(card));

                JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
                buttonRow.setOpaque(false);
                buttonRow.add(removeBtn);
                buttonRow.setAlignmentX(Component.CENTER_ALIGNMENT);
                buttonRow.add(Box.createRigidArea(new Dimension(0, 5)));
                buttonRow.add(detailsBtn);
                buttonRow.setAlignmentX(Component.CENTER_ALIGNMENT);

                cardPanel.add(Box.createVerticalGlue());
                cardPanel.add(nameLabel);
                cardPanel.add(Box.createVerticalStrut(8));
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

            // Hover effect
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
        button.setFont(new Font("Segoe UI", Font.BOLD, 11));
        button.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
    }
}
