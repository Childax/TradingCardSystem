package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewCollectionPanel extends JPanel {
    private MainProgramWindow mainWindow;
    private Collector collector;
    private JPanel scrollWrapper;

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
                card -> JOptionPane.showMessageDialog(this, card.getDetailedInfo(), "Card Details", JOptionPane.INFORMATION_MESSAGE)
        );

        scrollWrapper.add(cardGrid, BorderLayout.CENTER);
        scrollWrapper.revalidate();
        scrollWrapper.repaint();
    }
}

