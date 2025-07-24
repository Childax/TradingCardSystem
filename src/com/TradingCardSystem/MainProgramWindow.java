package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

public class MainProgramWindow extends JFrame {

    public MainProgramWindow(Collector collector) {
        this.setTitle("Trading Card Inventory System");
        this.setSize(500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        showCustomPanel(new MenuPanel(this, collector));
    }

    public void showBinderMenu(Collector collector, Binder binder) {
        showCustomPanel(new BinderMenuPanel(this, binder, collector));
    }

    public void showDeckMenu(Collector collector, Deck deck) {
        showCustomPanel(new DeckMenuPanel(this, deck, collector));
    }

    public void showManageBindersPanel(Collector collector) {
        showCustomPanel(new ManageBindersPanel(this, collector));
    }

    public void showManageDecksPanel(Collector collector) {
        showCustomPanel(new ManageDecksPanel(this, collector));
    }

    public void showCustomPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        revalidate();
        repaint();
    }
}
