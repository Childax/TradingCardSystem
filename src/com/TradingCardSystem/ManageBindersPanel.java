package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManageBindersPanel extends JPanel {

    public ManageBindersPanel(MainProgramWindow mainWindow, Collector collector) {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Label
        JLabel titleLabel = new JLabel("Select a Binder to Manage", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // Panel to hold all binder entries
        JPanel binderListPanel = new JPanel();
        binderListPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        binderListPanel.setBackground(new Color(30, 30, 30));

        // Scroll pane for binder list
        JScrollPane scrollPane = new JScrollPane(binderListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch Binders
        ArrayList<Binder> binders = collector.getBinders();

        if (binders.isEmpty()) {
            JLabel emptyLabel = new JLabel("No binders available.", SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            emptyLabel.setForeground(Color.GRAY);
            add(emptyLabel, BorderLayout.CENTER);
        } else {
            for (Binder binder : binders) {
                binderListPanel.add(createBinderCard(binder, mainWindow, collector));
            }
        }

        // Back button
        JButton backBtn = createStyledButton("Back to Main Menu");
        backBtn.addActionListener(e -> mainWindow.showCustomPanel(new MenuPanel(mainWindow, collector)));

        JPanel backBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backBtnPanel.setBackground(new Color(30, 30, 30));
        backBtnPanel.add(backBtn);
        add(backBtnPanel, BorderLayout.SOUTH);
    }

    private JPanel createBinderCard(Binder binder, MainProgramWindow mainWindow, Collector collector) {
        JPanel cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(160, 100));
        cardPanel.setLayout(new BorderLayout(5, 5));
        cardPanel.setBackground(new Color(45, 45, 45));
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70)));

        JLabel nameLabel = new JLabel(binder.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setBackground(new Color(45, 45, 45));

        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelPanel.add(Box.createVerticalStrut(12));
        labelPanel.add(nameLabel);
        labelPanel.add(Box.createVerticalStrut(12));

        cardPanel.add(labelPanel, BorderLayout.CENTER);

        JButton manageBtn = createStyledButton("Manage");
        manageBtn.addActionListener(e -> mainWindow.showBinderMenu(collector, binder));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        buttonPanel.setBackground(new Color(45, 45, 45));
        buttonPanel.add(manageBtn);

        cardPanel.add(buttonPanel, BorderLayout.SOUTH);

        return cardPanel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        button.setCursor(Cursor.getDefaultCursor());

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(80, 80, 80));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(60, 60, 60));
            }
        });

        return button;
    }
}
