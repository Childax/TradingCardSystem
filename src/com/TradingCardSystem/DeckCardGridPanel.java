package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
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

        for (int i = 0; i < 10; i++) {
            JPanel slot = new JPanel();
            slot.setPreferredSize(new Dimension(120, 120));
            slot.setLayout(new BoxLayout(slot, BoxLayout.Y_AXIS));
            slot.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            slot.setBackground(Color.WHITE);

            if (i < deckCards.size()) {
                Card card = deckCards.get(i);

                JLabel nameLabel = new JLabel(card.getName(), SwingConstants.CENTER);
                nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JButton removeBtn = new JButton("Remove");
                removeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                removeBtn.addActionListener(e -> onRemoveClicked.accept(card));

                JButton detailsBtn = new JButton("Details");
                detailsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                detailsBtn.addActionListener(e -> onDetailsClicked.accept(card));

                JPanel buttonRow = new JPanel();
                buttonRow.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
                buttonRow.add(removeBtn);
                buttonRow.add(detailsBtn);

                slot.add(Box.createVerticalStrut(10));
                slot.add(nameLabel);
                slot.add(Box.createVerticalStrut(10));
                slot.add(buttonRow);
            } else {
                JLabel emptyLabel = new JLabel("Empty Slot", SwingConstants.CENTER);
                emptyLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
                emptyLabel.setForeground(Color.GRAY);
                emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                slot.add(Box.createVerticalGlue());
                slot.add(emptyLabel);
                slot.add(Box.createVerticalGlue());
            }

            add(slot);
        }
    }
}
