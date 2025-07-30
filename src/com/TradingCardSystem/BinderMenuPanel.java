package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

public class BinderMenuPanel extends JPanel {
    private MainProgramWindow mainWindow;
    private Binder activeBinder;
    private Collector collector;
    private JLabel valueLabel;

    public BinderMenuPanel(MainProgramWindow mainWindow, Binder activeBinder, Collector collector) {
        this.mainWindow = mainWindow;
        this.activeBinder = activeBinder;
        this.collector = collector;

        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(30, 30, 30));

        JLabel titleLabel = new JLabel("Binder: " + activeBinder.getName(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel typeLabel = new JLabel("Type: " + activeBinder.getTypeString(), SwingConstants.CENTER);
        typeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        typeLabel.setForeground(Color.LIGHT_GRAY);
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(typeLabel);

        add(titlePanel, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 30, 100));

        JButton viewBtn = createStyledButton("View Cards in Binder");
        JButton addBtn = createStyledButton("Add Card to Binder");
        JButton tradeBtn = createStyledButton("Trade Cards");
        JButton deleteBtn = createStyledButton("Delete Binder");
        JButton sellBtn = createStyledButton("Sell Binder");
        JButton backBtn = createStyledButton("Back to Main Menu");

        tradeBtn.setEnabled(!activeBinder.isSellable());
        sellBtn.setEnabled(activeBinder.isSellable());

        viewBtn.addActionListener(e -> showBinderCards());

        addBtn.addActionListener(e -> {
            AddCardFromCollectionPanel addPanel = new AddCardFromCollectionPanel(
                    mainWindow,
                    collector,
                    "Add Card to Binder: " + activeBinder.getName(),
                    card -> {
                        if (activeBinder.getCards().size() >= 20) {
                            JOptionPane.showMessageDialog(this, "Binder is already full.");
                            return;
                        }
                        if (activeBinder.addCard(card)) {
                            card.decrementCount();
                            JOptionPane.showMessageDialog(this, "Card added!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Card could not be added.");
                        }
                        refresh();
                    },
                    () -> mainWindow.showBinderMenu(collector, activeBinder)
            );
            mainWindow.showCustomPanel(addPanel);
        });

        tradeBtn.addActionListener(e -> {
            mainWindow.showCustomPanel(new TradeCardPanel(mainWindow, collector, activeBinder));
            refresh();
        });

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

        sellBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to sell this binder?",
                    "Confirm Sale", JOptionPane.YES_NO_OPTION);

            double price = 0;

            if (activeBinder.getType().equals(BinderType.LUXURY)) {
                double minPrice = activeBinder.getBaseValue();
                boolean isPriceValid = false;
                while (!isPriceValid) {
                    String input = JOptionPane.showInputDialog(this,
                            String.format("Enter selling price (must be at least $%.2f):", minPrice));

                    if (input == null) return; // user cancelled

                    try {
                        price = Double.parseDouble(input);
                        if (price >= minPrice) {
                            price *= 1.1;
                            isPriceValid = true;
                        } else {
                            JOptionPane.showMessageDialog(this,
                                    String.format("Price must be at least $%.2f", minPrice));
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid number.");
                    }
                }
            } else {
                // use default price for non-luxury binders
                price = activeBinder.getSellPrice();
            }

            if (confirm == JOptionPane.YES_OPTION) {
                if (collector.sellBinder(activeBinder, price)) {
                    JOptionPane.showMessageDialog(this, String.format("Binder sold for $%.2f", price));;
                } else {
                    JOptionPane.showMessageDialog(this, "Binder was not sold.");
                }
                mainWindow.showManageBindersPanel(collector);
            }
        });

        backBtn.addActionListener(e -> mainWindow.showCustomPanel(new MenuPanel(mainWindow, collector)));

        for (JButton btn : new JButton[]{viewBtn, addBtn, tradeBtn, deleteBtn, sellBtn, backBtn}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonPanel.add(btn);
            buttonPanel.add(Box.createVerticalStrut(20));
        }

        add(buttonPanel, BorderLayout.CENTER);

        valueLabel = new JLabel("Binder Value: $" + String.format("%.2f", activeBinder.getBaseValue()), SwingConstants.CENTER);
        valueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(valueLabel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setMaximumSize(new Dimension(240, 45));
        button.setCursor(Cursor.getDefaultCursor());
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

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

    public void refresh() {
        valueLabel.setText("Binder Value: $" + String.format("%.2f", activeBinder.getBaseValue()));
    }

    private void showBinderCards() {
        BinderCardGridPanel grid = new BinderCardGridPanel(
                activeBinder.getCards(),
                "Remove",
                card -> {
                    activeBinder.removeCard(card);
                    card.incrementCount();
                    JOptionPane.showMessageDialog(this, "Card removed!");
                    mainWindow.showBinderMenu(collector, activeBinder);
                },
                card -> JOptionPane.showMessageDialog(this, card.getDetailedInfoWithoutCount(), "Card Details", JOptionPane.INFORMATION_MESSAGE)
        );

        JScrollPane scrollPane = new JScrollPane(grid);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(30, 30, 30));
        wrapper.add(scrollPane, BorderLayout.CENTER);

        JButton backBtn = createStyledButton("Back");
        backBtn.addActionListener(e -> mainWindow.showBinderMenu(collector, activeBinder));

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(30, 30, 30));
        bottom.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        bottom.add(backBtn);
        wrapper.add(bottom, BorderLayout.SOUTH);

        mainWindow.showCustomPanel(wrapper);
    }
}
