package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

/**
 * An abstract JPanel used as a template for creating GUI forms to add new items with a name and type.
 *
 * Provides common components like name input, type dropdown, and create/cancel buttons.
 * Subclasses must implement the {@code handleCreate()} method for creation logic.
 */
public abstract class AbstractCreatePanel extends JPanel {
    protected JTextField nameField;
    protected JComboBox<String> typeComboBox;
    protected JButton createButton;
    protected JButton cancelButton;
    protected JLabel titleLabel;

    /**
     * Constructs the panel with UI components for input and action handling.
     *
     * @param titleText the title to display at the top of the panel
     * @param typeChoices the list of types to show in the combo box
     * @param mainWindow reference to the main window for panel switching
     * @param collector the current collector instance
     */
    public AbstractCreatePanel(String titleText, String[] typeChoices, MainProgramWindow mainWindow, Collector collector) {
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

        JLabel typeLabel = new JLabel("Select Type:");
        typeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        typeLabel.setForeground(Color.LIGHT_GRAY);
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        typeComboBox = new JComboBox<>(typeChoices);
        typeComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        typeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        typeComboBox.setBackground(new Color(50, 50, 50));
        typeComboBox.setForeground(Color.WHITE);
        typeComboBox.setFocusable(false);
        typeComboBox.setCursor(Cursor.getDefaultCursor());
        typeComboBox.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));

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
        add(Box.createVerticalStrut(20));
        add(typeLabel);
        add(Box.createVerticalStrut(10));
        add(typeComboBox);
        add(Box.createVerticalStrut(30));
        add(createButton);
        add(Box.createVerticalStrut(10));
        add(cancelButton);
    }

    /**
     * Creates a JButton with consistent styling and hover effects.
     *
     * @param text the label of the button
     * @return the styled JButton instance
     */
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

    /**
     * Abstract method to define the logic when the Create button is pressed.
     */
    protected abstract void handleCreate();

    /**
     * Gets the currently selected type from the combo box.
     *
     * @return the selected type as a String
     */
    public String getSelectedType() {
        return (String) typeComboBox.getSelectedItem();
    }
}
