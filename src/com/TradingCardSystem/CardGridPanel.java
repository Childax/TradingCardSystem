package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class CardGridPanel extends JPanel {
    public CardGridPanel(
            List<Card> cards,
            String buttonLabel,
            Consumer<Card> onCardClicked,
            Consumer<Card> onViewDetailsClicked
    ) {
        setLayout(new GridLayout(0, 2, 10, 10));

        for (Card card : cards) {
            JPanel cardPanel = new JPanel(new BorderLayout());
            cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JPanel topPanel = new JPanel();
            topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

            JLabel nameLabel = new JLabel(card.getName(), SwingConstants.CENTER);
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel countLabel = new JLabel("x" + card.getCount(), SwingConstants.CENTER);
            countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            topPanel.add(nameLabel);
            topPanel.add(countLabel);

            cardPanel.add(topPanel, BorderLayout.NORTH);

            // Buttons
            JButton actionBtn = new JButton(buttonLabel);
            actionBtn.addActionListener(e -> onCardClicked.accept(card));

            JButton detailsBtn = new JButton("Details");
            detailsBtn.addActionListener(e -> onViewDetailsClicked.accept(card));

            JPanel btnPanel = new JPanel();
            btnPanel.add(actionBtn);
            btnPanel.add(detailsBtn);

            cardPanel.add(btnPanel, BorderLayout.SOUTH);

            add(cardPanel);
        }
    }
}

