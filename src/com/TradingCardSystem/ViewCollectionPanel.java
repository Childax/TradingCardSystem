package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The ViewCollectionPanel displays the collector's card collection
 * and provides functionalities to remove, sell, or modify card counts.
 */
public class ViewCollectionPanel extends JPanel {
    private MainProgramWindow mainWindow;
    private Collector collector;
    private JPanel scrollWrapper;

    /**
     * Constructs the panel for viewing the collector's card collection.
     *
     * @param mainWindow the main program window to switch panels
     * @param collector  the collector whose card collection is displayed
     */
    public ViewCollectionPanel(MainProgramWindow mainWindow, Collector collector) {
        this.collector = collector;
        this.mainWindow = mainWindow;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Your Card Collection", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        scrollWrapper = new JPanel(new BorderLayout());
        add(new JScrollPane(scrollWrapper), BorderLayout.CENTER);

        refresh();

        JButton backBtn = new JButton("Back to Menu");
        backBtn.addActionListener(e -> mainWindow.showCustomPanel(new MenuPanel(mainWindow, collector)));
        JPanel bottom = new JPanel();
        bottom.add(backBtn);
        add(bottom, BorderLayout.SOUTH);
    }

    /**
     * Refreshes the card grid to reflect current state of the collector's collection.
     * Updates UI after card modifications like removal or selling.
     */
    public void refresh() {
        scrollWrapper.removeAll();

        CardGridPanel cardGrid = new CardGridPanel(
                collector.getCards(),
                "Remove",
                card -> {
                    int confirm = JOptionPane.showConfirmDialog(this, "Remove this card?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        collector.removeCardObject(card.getName());
                        refresh();
                    }
                },
                card -> JOptionPane.showMessageDialog(this, card.getDetailedInfo(), "Card Details", JOptionPane.INFORMATION_MESSAGE),
                Card::incrementCount,
                Card::decrementCount,
                card -> {
                    int confirm = JOptionPane.showConfirmDialog(this, "Sell this card?", "Confirm Sell", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        collector.sellCard(card);
                        JOptionPane.showMessageDialog(this, String.format("Card sold for $%.2f", card.getValue()));
                        refresh();
                    }
                }
        );

        scrollWrapper.add(cardGrid, BorderLayout.CENTER);
        scrollWrapper.revalidate();
        scrollWrapper.repaint();
    }
}
