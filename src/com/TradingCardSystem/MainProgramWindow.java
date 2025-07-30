package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Main program window for the Trading Card Inventory System.
 * Responsible for initializing the application UI and switching panels.
 */
public class MainProgramWindow extends JFrame {

    /**
     * Constructs the main window and initializes the user interface with a given collector.
     *
     * @param collector the Collector to be used in the session
     */
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

    /**
     * Displays the binder menu panel for the given binder and collector.
     *
     * @param collector the current collector
     * @param binder the binder to be shown
     */
    public void showBinderMenu(Collector collector, Binder binder) {
        showCustomPanel(new BinderMenuPanel(this, binder, collector));
    }

    /**
     * Displays the deck menu panel for the given deck and collector.
     *
     * @param collector the current collector
     * @param deck the deck to be shown
     */
    public void showDeckMenu(Collector collector, Deck deck) {
        showCustomPanel(new DeckMenuPanel(this, deck, collector));
    }

    /**
     * Displays the manage binders panel for the given collector.
     *
     * @param collector the current collector
     */
    public void showManageBindersPanel(Collector collector) {
        showCustomPanel(new ManageBindersPanel(this, collector));
    }

    /**
     * Displays the manage decks panel for the given collector.
     *
     * @param collector the current collector
     */
    public void showManageDecksPanel(Collector collector) {
        showCustomPanel(new ManageDecksPanel(this, collector));
    }

    /**
     * Replaces the current panel with the specified panel.
     *
     * @param panel the JPanel to be displayed
     */
    public void showCustomPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        revalidate();
        repaint();
    }
}
