package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class BinderCardGridPanel extends JPanel {
    public BinderCardGridPanel(
            List<Card> cards,
            String buttonLabel,
            Consumer<Card> onCardClicked
    ) {
        setLayout(new GridLayout(0, 2, 10, 10)); // 2 cards per row

        if (cards == null || cards.isEmpty()) {
            JLabel emptyLabel = new JLabel("No cards in this binder.", SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            add(emptyLabel, BorderLayout.CENTER);
            return;
        }

        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        for (Card card : cards) {
            JPanel cardPanel = new JPanel();
            cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
            cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cardPanel.setBackground(Color.WHITE);
            cardPanel.setPreferredSize(new Dimension(200, 150));

            JLabel nameLabel = new JLabel(card.getName());
            JLabel rarityLabel = new JLabel("Rarity: " + card.getRarity());
            JLabel variantLabel = new JLabel("Variant: " + card.getVariant());
            JLabel valueLabel = new JLabel(String.format("Value: $%.2f", card.getValue()));

            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            rarityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            variantLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton actionBtn = new JButton(buttonLabel);
            actionBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            actionBtn.addActionListener(e -> onCardClicked.accept(card));

            cardPanel.add(Box.createVerticalStrut(5));
            cardPanel.add(nameLabel);
            cardPanel.add(rarityLabel);
            cardPanel.add(variantLabel);
            cardPanel.add(valueLabel);
            cardPanel.add(Box.createVerticalStrut(10));
            cardPanel.add(actionBtn);

            gridPanel.add(cardPanel);
        }

        add(new JScrollPane(gridPanel), BorderLayout.CENTER);
    }
}
