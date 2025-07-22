package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

public class BinderMenuPanel extends JPanel {
    private MainProgramWindow mainWindow;
    private Binder activeBinder;

    public BinderMenuPanel(MainProgramWindow mainWindow, Binder activeBinder) {
        this.mainWindow = mainWindow;
        this.activeBinder = activeBinder;

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Binder: " + activeBinder.getName(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        JButton viewBtn = new JButton("View Cards in Binder");
        JButton addBtn = new JButton("Add Card to Binder");
        JButton removeBtn = new JButton("Remove Card from Binder");
        JButton tradeBtn = new JButton("Trade Cards");
        JButton renameBtn = new JButton("Rename Binder");
        JButton backBtn = new JButton("Back to Main Menu");

        // Add button listeners here (e.g., mainWindow.showPanel("binderView"), etc.)

        for (JButton btn : new JButton[]{viewBtn, addBtn, removeBtn, tradeBtn, renameBtn, backBtn}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(200, 40));
            btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
            buttonPanel.add(btn);
            buttonPanel.add(Box.createVerticalStrut(15));
        }

        add(buttonPanel, BorderLayout.CENTER);
    }
}
