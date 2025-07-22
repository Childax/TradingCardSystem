package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

public class CreateBinderPanel extends JPanel {
    private MainProgramWindow mainWindow;
    private Collector collector;

    public CreateBinderPanel(MainProgramWindow mainWindow, Collector collector) {
        this.mainWindow = mainWindow;
        this.collector = collector;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Create New Binder", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel nameLabel = new JLabel("Enter binder name:");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JButton createBtn = new JButton("Create Binder");
        createBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        createBtn.addActionListener(e -> {
            String binderName = nameField.getText().trim();
            if (binderName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Binder name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (collector.hasBinderWithName(binderName)) {
                JOptionPane.showMessageDialog(this, "Binder with this name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            collector.addBinder(new Binder(binderName));
            JOptionPane.showMessageDialog(this, "Binder '" + binderName + "' created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            nameField.setText("");
            mainWindow.showBinderMenu(collector.getBinderByName(binderName));
        });

        centerPanel.add(nameLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(nameField);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(createBtn);

        add(centerPanel, BorderLayout.CENTER);

        JButton backBtn = new JButton("Back to Menu");
        backBtn.addActionListener(e -> mainWindow.showPanel("menu"));
        JPanel bottom = new JPanel();
        bottom.add(backBtn);
        add(bottom, BorderLayout.SOUTH);
    }
}
