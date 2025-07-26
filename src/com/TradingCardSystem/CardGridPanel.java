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
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        gridPanel.setBackground(new Color(30, 30, 30));

        boolean hasVisibleCards = false;

        for (Card card : cards) {
            if (card.getCount() != 0) {
                hasVisibleCards = true;
                gridPanel.add(createCardBox(card, buttonLabel, onCardClicked, onViewDetailsClicked));
            }
        }

        if (hasVisibleCards) {
            JScrollPane scrollPane = new JScrollPane(gridPanel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            scrollPane.setBorder(null);
            scrollPane.getViewport().setBackground(new Color(30, 30, 30));
            add(scrollPane, BorderLayout.CENTER);
        } else {
            JLabel emptyLabel = new JLabel("No cards available.", SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            emptyLabel.setForeground(Color.GRAY);
            add(emptyLabel, BorderLayout.CENTER);
        }
    }

    private JPanel createCardBox(
            Card card,
            String buttonLabel,
            Consumer<Card> onCardClicked,
            Consumer<Card> onViewDetailsClicked
    ) {
        JPanel cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(150, 120));
        cardPanel.setLayout(new BorderLayout(5, 5));
        cardPanel.setBackground(new Color(45, 45, 45));
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70)));

        JLabel nameLabel = new JLabel(card.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);

        JLabel countLabel = new JLabel("x" + card.getCount(), SwingConstants.CENTER);
        countLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        countLabel.setForeground(Color.LIGHT_GRAY);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setBackground(new Color(45, 45, 45));

        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelPanel.add(Box.createVerticalStrut(5));
        labelPanel.add(nameLabel);
        labelPanel.add(Box.createVerticalStrut(4));
        labelPanel.add(countLabel);
        labelPanel.add(Box.createVerticalStrut(5));

        cardPanel.add(labelPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        buttonPanel.setBackground(new Color(45, 45, 45));

        JButton actionBtn = createStyledButton(buttonLabel);
        actionBtn.addActionListener(e -> onCardClicked.accept(card));

        JButton detailsBtn = createStyledButton("Details");
        detailsBtn.addActionListener(e -> onViewDetailsClicked.accept(card));

        buttonPanel.add(actionBtn);
        buttonPanel.add(detailsBtn);

        cardPanel.add(buttonPanel, BorderLayout.SOUTH);

        return cardPanel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        button.setCursor(Cursor.getDefaultCursor());

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(80, 80, 80));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(60, 60, 60));
            }
        });

        return button;
    }
}
