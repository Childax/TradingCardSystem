package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * A panel that displays a grid of cards, allowing interactions such as viewing details,
 * incrementing/decrementing count, performing a custom action (e.g., add/remove), and optionally selling.
 */
public class CardGridPanel extends JPanel {

    /**
     * Constructs a new CardGridPanel with the given card list and action handlers.
     *
     * @param cards               the list of cards to display
     * @param buttonLabel         the label for the action button on each card
     * @param onCardClicked       the action to perform when the main button is clicked
     * @param onViewDetailsClicked the action to perform when the details button is clicked
     * @param onIncrementClicked  the action to perform when the increment button is clicked
     * @param onDecrementClicked  the action to perform when the decrement button is clicked
     * @param onSellClicked       the action to perform when the sell button is clicked (can be null if unused)
     */
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

    /**
     * Creates a single card box component with labels and buttons for actions.
     *
     * @param card                 the card to display
     * @param buttonLabel          the label for the action button
     * @param onCardClicked        action on primary button click
     * @param onViewDetailsClicked action on details button click
     * @param onIncrementClicked   action on increment button click
     * @param onDecrementClicked   action on decrement button click
     * @param onSellClicked        action on sell button click (nullable)
     * @return the constructed JPanel representing the card
     */
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(45, 45, 45));

        JButton actionBtn = createStyledButton(buttonLabel);
        actionBtn.addActionListener(e -> onCardClicked.accept(card));

        JButton detailsBtn = createStyledButton("Details");
        detailsBtn.addActionListener(e -> onViewDetailsClicked.accept(card));

        JButton sellBtn = null;
        if (onSellClicked != null) {
            sellBtn = createStyledButton("Sell");
            sellBtn.addActionListener(e -> onSellClicked.accept(card));
        }

        buttonPanel.add(detailsBtn);
        detailsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createVerticalStrut(5));
        buttonPanel.add(actionBtn);
        actionBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createVerticalStrut(5));
        if (sellBtn != null) {
            buttonPanel.add(sellBtn);
            sellBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonPanel.add(Box.createVerticalStrut(5));
        }

        cardPanel.add(buttonPanel, BorderLayout.SOUTH);

        return cardPanel;
    }

    /**
     * Creates a styled button used for card actions.
     *
     * @param text the button label
     * @return the styled JButton
     */
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

    /**
     * Creates a small-sized button used for increment/decrement controls.
     *
     * @param text the button label
     * @return the small JButton
     */
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
