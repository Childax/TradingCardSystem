package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManageBindersPanel extends JPanel {

    public ManageBindersPanel(MainProgramWindow mainWindow, Collector collector) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Title Label
        JLabel titleLabel = new JLabel("Select a Binder to Manage", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Panel to hold all binder entries
        JPanel binderListPanel = new JPanel();
        binderListPanel.setLayout(new BoxLayout(binderListPanel, BoxLayout.Y_AXIS));

        // Scroll pane for binder list
        JScrollPane scrollPane = new JScrollPane(binderListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch Binders
        ArrayList<Binder> binders = collector.getBinders();

        if (binders.isEmpty()) {
            // Show message if no binders exist
            JLabel emptyLabel = new JLabel("No binders available.", SwingConstants.CENTER);
            emptyLabel.setFont(new Font("SansSerif", Font.ITALIC, 16));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            binderListPanel.add(Box.createVerticalStrut(20));
            binderListPanel.add(emptyLabel);
        } else {
            // Populate binders
            for (Binder binder : binders) {
                JPanel binderPanel = new JPanel(new BorderLayout(10, 10));
                binderPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));

                JLabel binderLabel = new JLabel(binder.getName());
                binderLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
                binderPanel.add(binderLabel, BorderLayout.CENTER);

                JButton manageBtn = new JButton("Manage");
                manageBtn.addActionListener(e -> mainWindow.showBinderMenu(collector, binder));
                binderPanel.add(manageBtn, BorderLayout.EAST);

                binderListPanel.add(Box.createVerticalStrut(10));
                binderListPanel.add(binderPanel);
            }
        }

        // Back button
        JButton backBtn = new JButton("Back to Main Menu");
        backBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        backBtn.addActionListener(e -> mainWindow.showCustomPanel(new MenuPanel(mainWindow, collector)));
        JPanel backBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backBtnPanel.add(backBtn);
        add(backBtnPanel, BorderLayout.SOUTH);
    }
}
