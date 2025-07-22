package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DisplayCollectionPanel extends JPanel {
    private MainProgramWindow mainWindow;
    private Collector collector;
    private JPanel cardGridPanel;

    public DisplayCollectionPanel(MainProgramWindow mainWindow, Collector collector) {
        this.collector = collector;
        this.mainWindow = mainWindow;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Your Card Collection", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        cardGridPanel = new JPanel();
        cardGridPanel.setLayout(new GridLayout(0, 4, 10, 10)); // 0 rows = dynamic, 4 columns

        List<Card> collection = collector.getCards();
        if (collection.isEmpty()) {
            cardGridPanel.add(new JLabel("You have no cards in your collection."));
        } else {
            for (Card card : collection) {
                cardGridPanel.add(createCardPanel(card));
            }
        }

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
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panel.setPreferredSize(new Dimension(150, 100));

        JLabel nameLabel = new JLabel("<html><b>" + card.getName() + "</b></html>", SwingConstants.CENTER);
        JTextArea shortInfo = new JTextArea(card.getName());
        shortInfo.setLineWrap(true);
        shortInfo.setWrapStyleWord(true);
        shortInfo.setEditable(false);
        shortInfo.setBackground(null);
        shortInfo.setFont(new Font("SansSerif", Font.PLAIN, 11));

        JButton detailsBtn = new JButton("Details");
        detailsBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    card.getDetailedInfo(), "Card Details", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(shortInfo, BorderLayout.CENTER);
        panel.add(detailsBtn, BorderLayout.SOUTH);

        return panel;
    }

    public void refresh() {
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
}
