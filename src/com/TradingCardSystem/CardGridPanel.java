package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class CardGridPanel extends JPanel {

    public CardGridPanel(List<Card> cards, Consumer<Card> onRemove, Consumer<Card> onDetails) {
        setLayout(new GridLayout(0, 4, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (cards.isEmpty()) {
            add(new JLabel("You have no cards to display."));
        } else {
            for (Card card : cards) {
                add(createCardPanel(card, onRemove, onDetails));
            }
        }
    }

    private JPanel createCardPanel(Card card, Consumer<Card> onRemove, Consumer<Card> onDetails) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(150, 120));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel nameLabel = new JLabel(card.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel countLabel = new JLabel("Count: " + card.getCount());
        countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        countLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

        JButton removeBtn = new JButton("Remove");
        removeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeBtn.addActionListener(e -> onRemove.accept(card));

        JButton detailsBtn = new JButton("Details");
        detailsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsBtn.addActionListener(e -> onDetails.accept(card));

        panel.add(Box.createVerticalStrut(10));
        panel.add(nameLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(countLabel);
        panel.add(Box.createVerticalGlue());
        panel.add(removeBtn);
        panel.add(Box.createVerticalStrut(5));
        panel.add(detailsBtn);
        panel.add(Box.createVerticalStrut(10));

        return panel;
    }
}
