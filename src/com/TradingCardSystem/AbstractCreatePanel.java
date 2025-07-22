package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractCreatePanel extends JPanel {
    protected JTextField nameField;
    protected JButton createButton;
    protected JButton cancelButton;
    protected JLabel titleLabel;

    public AbstractCreatePanel(String titleText, MainProgramWindow mainWindow) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        titleLabel = new JLabel(titleText);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Enter name:");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        createButton = new JButton("Create");
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.addActionListener(e -> handleCreate());

        cancelButton = new JButton("Back to Main Menu");
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.addActionListener(e -> {
            nameField.setText("");
            mainWindow.showPanel("menu");
        });

        add(titleLabel);
        add(Box.createVerticalStrut(20));
        add(nameLabel);
        add(Box.createVerticalStrut(10));
        add(nameField);
        add(Box.createVerticalStrut(20));
        add(createButton);
        add(Box.createVerticalStrut(10));
        add(cancelButton);
    }

    // Each subclass must define how to handle creation
    protected abstract void handleCreate();
}
