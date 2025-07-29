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
            Consumer<Card> onViewDetailsClicked,
            Consumer<Card> onIncrementClicked,
            Consumer<Card> onDecrementClicked,
            Consumer<Card> onSellClicked
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
                gridPanel.add(createCardBox(card, buttonLabel, onCardClicked, onViewDetailsClicked, onIncrementClicked, onDecrementClicked, onSellClicked));
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
            Consumer<Card> onViewDetailsClicked,
            Consumer<Card> onIncrementClicked,
            Consumer<Card> onDecrementClicked,
            Consumer<Card> onSellClicked
    ) {
        JPanel cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(150, 200));
        cardPanel.setLayout(new BorderLayout(5, 5));
        cardPanel.setBackground(new Color(45, 45, 45));
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70)));

        JLabel nameLabel = new JLabel(card.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);

        JLabel countLabel = new JLabel("x" + card.getCount(), SwingConstants.CENTER);
        countLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        countLabel.setForeground(Color.LIGHT_GRAY);

        // Increment/Decrement buttons
        JButton plusBtn = createSmallButton("+");
        JButton minusBtn = createSmallButton("−");

        plusBtn.addActionListener(e -> {
            onIncrementClicked.accept(card);
            countLabel.setText("x" + card.getCount());
        });

        minusBtn.addActionListener(e -> {
            onDecrementClicked.accept(card);
            countLabel.setText("x" + card.getCount());
        });

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setBackground(new Color(45, 45, 45));

        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel incDecPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        incDecPanel.setBackground(new Color(45, 45, 45));
        incDecPanel.add(minusBtn);
        incDecPanel.add(plusBtn);

        labelPanel.add(Box.createVerticalStrut(5));
        labelPanel.add(nameLabel);
        labelPanel.add(Box.createVerticalStrut(4));
        labelPanel.add(countLabel);
        labelPanel.add(Box.createVerticalStrut(3));
        labelPanel.add(Box.createVerticalStrut(5));
        labelPanel.add(incDecPanel);
        cardPanel.add(labelPanel, BorderLayout.CENTER);

        // Main buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(45, 45, 45));

        JButton actionBtn = createStyledButton(buttonLabel);
        actionBtn.addActionListener(e -> onCardClicked.accept(card));

        JButton detailsBtn = createStyledButton("Details");
        detailsBtn.addActionListener(e -> onViewDetailsClicked.accept(card));

        JButton sellBtn = null;
//        if (onSellClicked != null) {
            sellBtn = createStyledButton("Sell");
            sellBtn.addActionListener(e -> onSellClicked.accept(card));
        //}

        buttonPanel.add(detailsBtn);
        detailsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createVerticalStrut(5));
        buttonPanel.add(actionBtn);
        actionBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createVerticalStrut(5));
//        if (sellBtn != null) {
            buttonPanel.add(sellBtn);
            sellBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonPanel.add(Box.createVerticalStrut(5));
        //}

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

    private JButton createSmallButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(36, 22));
        button.setBackground(new Color(70, 70, 70));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));
        button.setCursor(Cursor.getDefaultCursor());

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(90, 90, 90));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 70, 70));
            }
        });

        return button;
    }
}
