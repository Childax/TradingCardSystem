package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainProgramWindow extends JFrame {

    public MainProgramWindow(Collector collector) {
        UIManager.put("OptionPane.background", new Color(30, 30, 30));
        UIManager.put("Panel.background", new Color(30, 30, 30));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Button.background", new Color(60, 60, 60));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));

        this.setTitle("Trading Card Inventory System");
        this.setSize(700, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/icon.jpg")));
        setIconImage(icon.getImage());

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
