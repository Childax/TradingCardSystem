package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractCreatePanel extends JPanel {
    protected JTextField nameField;
    protected JButton createButton;
    protected JButton cancelButton;
    protected JLabel titleLabel;

    public AbstractCreatePanel(String titleText, MainProgramWindow mainWindow, Collector collector) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        titleLabel = new JLabel(titleText);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Enter name:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameLabel.setForeground(Color.LIGHT_GRAY);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameField.setBackground(new Color(50, 50, 50));
        nameField.setForeground(Color.WHITE);
        nameField.setCaretColor(Color.WHITE);
        nameField.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
        nameField.setHorizontalAlignment(JTextField.CENTER);

        createButton = createStyledButton("Create");
        cancelButton = createStyledButton("Back to Main Menu");

        createButton.addActionListener(e -> handleCreate());
        cancelButton.addActionListener(e -> {
            nameField.setText("");
            mainWindow.showCustomPanel(new MenuPanel(mainWindow, collector));
        });

        add(titleLabel);
        add(Box.createVerticalStrut(20));
        add(nameLabel);
        add(Box.createVerticalStrut(10));
        add(nameField);
        add(Box.createVerticalStrut(30));
        add(createButton);
        add(Box.createVerticalStrut(10));
        add(cancelButton);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFocusable(false);
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        button.setCursor(Cursor.getDefaultCursor());
        button.setMaximumSize(new Dimension(180, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Flash effect on hover
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

    protected abstract void handleCreate();
}
