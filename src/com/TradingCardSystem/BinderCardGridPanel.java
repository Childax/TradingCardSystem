package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class BinderCardGridPanel extends JPanel {
    public BinderCardGridPanel(List<Card> cards, String buttonLabel, Consumer<Card> onCardClicked, Consumer<Card> onDetailsClicked) {
        setLayout(new GridLayout(4, 5, 10, 10)); // 4 rows, 5 columns = 20 slots
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        int totalSlots = 20;

        for (int i = 0; i < totalSlots; i++) {
            JPanel cardPanel = new JPanel();
            cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
            cardPanel.setPreferredSize(new Dimension(200, 200));
            cardPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            cardPanel.setBackground(Color.WHITE);

            if (i < cards.size()) {
                Card card = cards.get(i);

                JLabel nameLabel = new JLabel(card.getName(), SwingConstants.CENTER);
                nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JButton removeBtn = new JButton(buttonLabel);
                JButton detailsBtn = new JButton("Details");

                removeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                detailsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

                JPanel buttonRow = new JPanel();
                buttonRow.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
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
                emptyLabel.setFont(new Font("Arial", Font.ITALIC, 14));
                emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                cardPanel.add(Box.createVerticalGlue());
                cardPanel.add(emptyLabel);
                cardPanel.add(Box.createVerticalGlue());
            }

            add(cardPanel);
        }
    }
}
