package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewCollectionPanel extends JPanel {
    private MainProgramWindow mainWindow;
    private Collector collector;
    private JPanel cardGridPanel;

    public ViewCollectionPanel(MainProgramWindow mainWindow, Collector collector) {
        this.collector = collector;
        this.mainWindow = mainWindow;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Your Card Collection", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        cardGridPanel = new JPanel();
        cardGridPanel.setLayout(new GridLayout(0, 4, 10, 10));
        cardGridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        populateCardGrid();

        JScrollPane scrollPane = new JScrollPane(cardGridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JButton backBtn = new JButton("Back to Menu");
        backBtn.addActionListener(e -> mainWindow.showPanel("menu"));
        JPanel bottom = new JPanel();
        bottom.add(backBtn);
        add(bottom, BorderLayout.SOUTH);
    }

    private JPanel createCardPanel(Card card) {
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
        removeBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to remove this card?",
                    "Confirm Remove",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                collector.removeCardObject(card.getName());
                refresh(); // refresh collection view
            }
        });

        JButton detailsBtn = new JButton("Details");
        detailsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    card.getDetailedInfo(), "Card Details", JOptionPane.INFORMATION_MESSAGE);
        });


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

    private void populateCardGrid() {
        cardGridPanel.removeAll();

        List<Card> cards = collector.getCards();
        if (cards.isEmpty()) {
            cardGridPanel.add(new JLabel("You have no cards in your collection."));
        } else {
            for (Card card : cards) {
                cardGridPanel.add(createCardPanel(card));
            }
        }

        cardGridPanel.revalidate();
        cardGridPanel.repaint();
    }

    public void refresh() {
        populateCardGrid();
    }
}
