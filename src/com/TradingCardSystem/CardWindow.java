package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

public class CardWindow {

    private JFrame frame;
    private JLabel nameLabel;

    public CardWindow(Card card) {
        frame = new JFrame("Card Viewer");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        nameLabel = new JLabel("Name: " + card.getName());

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(nameLabel);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void updateCard(Card card) {
        nameLabel.setText("Name: " + card.getName());
    }

    public void show() {
        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }
}
