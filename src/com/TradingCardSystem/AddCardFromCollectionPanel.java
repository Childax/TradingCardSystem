package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class AddCardFromCollectionPanel extends JPanel {
    public AddCardFromCollectionPanel(MainProgramWindow mainWindow, Collector collector, String title, Consumer<Card> onCardAdded, Runnable onBack) {
        setLayout(new BorderLayout());

        JLabel heading = new JLabel(title, SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        add(heading, BorderLayout.NORTH);

        CardGridPanel cardGrid = new CardGridPanel(
                collector.getCards(),
                "Add",
                card -> {
                    onCardAdded.accept(card);
                    onBack.run();
                },
                card -> JOptionPane.showMessageDialog(this, card.getDetailedInfo(), "Card Details", JOptionPane.INFORMATION_MESSAGE),
                Card::incrementCount,
                Card::decrementCount,
                collector::sellCard
        );


        add(new JScrollPane(cardGrid), BorderLayout.CENTER);

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> onBack.run());

        JPanel bottom = new JPanel();
        bottom.add(backBtn);
        add(bottom, BorderLayout.SOUTH);
    }
}
