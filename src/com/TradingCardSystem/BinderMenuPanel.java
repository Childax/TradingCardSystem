package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

public class BinderMenuPanel extends JPanel {
    private MainProgramWindow mainWindow;
    private Binder activeBinder;
    private Collector collector;

    public BinderMenuPanel(MainProgramWindow mainWindow, Binder activeBinder, Collector collector) {
        this.mainWindow = mainWindow;
        this.activeBinder = activeBinder;
        this.collector = collector;

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
        JButton deleteBtn = new JButton("Delete Binder");
        JButton backBtn = new JButton("Back to Main Menu");

        // View Cards in Binder
        viewBtn.addActionListener(e -> showBinderCards());

        // Add Card to Binder
        addBtn.addActionListener(e -> {
            mainWindow.showCustomPanel(new AddCardFromCollectionPanel(
                    mainWindow,
                    collector,
                    "Add Card to Binder: " + activeBinder.getName(),
                    card -> {
                        activeBinder.addCard(card);
                        card.decrementCount();
                    },
                    () -> mainWindow.showBinderMenu(collector, activeBinder)
            ));
        });


        // Delete Binder
        deleteBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete the binder \"" + activeBinder.getName() + "\"?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                collector.deleteBinder(activeBinder.getName());
                JOptionPane.showMessageDialog(this, "Binder deleted successfully.");
                mainWindow.showManageBindersPanel(collector);
            }
        });

        // Back to main menu
        backBtn.addActionListener(e -> mainWindow.showCustomPanel(new MenuPanel(mainWindow, collector)));

        for (JButton btn : new JButton[]{viewBtn, addBtn, removeBtn, tradeBtn, deleteBtn, backBtn}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(200, 40));
            btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
            buttonPanel.add(btn);
            buttonPanel.add(Box.createVerticalStrut(15));
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void showBinderCards() {
        BinderCardGridPanel grid = new BinderCardGridPanel(
                activeBinder.getCards(),
                "Remove",
                card -> {
                    activeBinder.removeCard(card);
                    card.incrementCount();
                    showBinderCards(); // Refresh the view
                }
        );

        JScrollPane scrollPane = new JScrollPane(grid);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(scrollPane, BorderLayout.CENTER);

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> mainWindow.showBinderMenu(collector, activeBinder));

        JPanel bottom = new JPanel();
        bottom.add(backBtn);
        wrapper.add(bottom, BorderLayout.SOUTH);

        mainWindow.showCustomPanel(wrapper);
    }
}
