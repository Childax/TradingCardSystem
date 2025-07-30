package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

/**
 * A panel for user input to create a new {@link Card} instance.
 * Allows setting the card's name, rarity, variant (conditionally), and value.
 * UI is styled with a dark theme.
 */
public class CardInputPanel extends JPanel {
    private final JTextField nameField;
    private final JComboBox<String> rarityDropdown;
    private final JComboBox<String> variantDropdown;
    private final JTextField valueField;

    private final Rarity[] rarityValues;
    private final Variant[] variantValues;

    /**
     * Constructs a CardInputPanel with initialized fields and dropdowns.
     * Configures layout and event handling for dynamic UI behavior.
     */
    public CardInputPanel() {
        this.nameField = createTextField();
        this.valueField = createTextField();
        this.rarityValues = Rarity.values();
        this.variantValues = Variant.values();

        this.rarityDropdown = createComboBox(getFormattedEnumStrings(rarityValues));
        this.variantDropdown = createComboBox(getFormattedEnumStrings(variantValues));
        variantDropdown.setEnabled(false);

        rarityDropdown.addActionListener(e -> {
            Rarity selectedRarity = rarityValues[rarityDropdown.getSelectedIndex()];
            variantDropdown.setEnabled(selectedRarity == Rarity.RARE || selectedRarity == Rarity.LEGENDARY);
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        add(createLabeledField("Card Name:", nameField));
        add(Box.createVerticalStrut(3));
        add(createLabeledField("Rarity:", rarityDropdown));
        add(Box.createVerticalStrut(3));
        add(createLabeledField("Variant:", variantDropdown));
        add(Box.createVerticalStrut(3));
        add(createLabeledField("Value:", valueField));
    }

    /**
     * Creates a labeled panel for a given input field.
     *
     * @param labelText   The text for the label.
     * @param inputField  The associated input component (text field or dropdown).
     * @return A JPanel containing the label and input field.
     */
    private JPanel createLabeledField(String labelText, JComponent inputField) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        Dimension labelSize = new Dimension(60, 18);
        label.setPreferredSize(labelSize);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(new Color(30, 30, 30));
        panel.add(label);
        panel.add(Box.createHorizontalStrut(5));
        panel.add(inputField);
        return panel;
    }

    /**
     * Creates a styled text field component.
     *
     * @return A customized JTextField instance.
     */
    private JTextField createTextField() {
        JTextField field = new JTextField();
        Dimension size = new Dimension(100, 30);
        field.setPreferredSize(size);
        field.setMaximumSize(size);
        field.setMinimumSize(size);
        field.setBackground(new Color(50, 50, 50));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY),
                BorderFactory.createEmptyBorder(5, 5, 2, 2)
        ));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        return field;
    }

    /**
     * Creates a styled combo box populated with the provided items.
     *
     * @param items The string items to populate the combo box.
     * @return A customized JComboBox.
     */
    private JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        Dimension size = new Dimension(150, 30);
        comboBox.setPreferredSize(size);
        comboBox.setMaximumSize(size);
        comboBox.setMinimumSize(size);
        comboBox.setBackground(new Color(50, 50, 50));
        comboBox.setForeground(Color.LIGHT_GRAY);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY),
                BorderFactory.createEmptyBorder(2, 2, 2, 2)
        ));
        comboBox.setCursor(Cursor.getDefaultCursor());
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (index == -1) {
                    label.setBackground(new Color(50, 50, 50));
                } else {
                    label.setBackground(isSelected ? new Color(70, 70, 70) : new Color(40, 40, 40));
                }

                label.setForeground(Color.WHITE);
                label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                label.setOpaque(true);
                return label;
            }
        });

        return comboBox;
    }

    /**
     * Formats an array of enum values to display strings for UI components.
     *
     * @param values Array of Enum values.
     * @return Array of formatted strings.
     */
    private String[] getFormattedEnumStrings(Enum<?>[] values) {
        String[] result = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = formatEnumName(values[i]);
        }
        return result;
    }

    /**
     * Converts an enum constant to a more user-friendly string.
     * Example: LEGENDARY -> Legendary
     *
     * @param e The enum constant.
     * @return Formatted string.
     */
    private String formatEnumName(Enum<?> e) {
        String name = e.name().toLowerCase().replace("_", "-");
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    /**
     * Gathers the input field values and constructs a {@link Card} object.
     *
     * @return A new {@link Card} instance based on form inputs.
     * @throws IllegalArgumentException If any required fields are empty.
     * @throws NumberFormatException    If value is not a valid number.
     */
    public Card getCard() throws IllegalArgumentException, NumberFormatException {
        String name = nameField.getText().trim();
        String rarityStr = ((String) rarityDropdown.getSelectedItem()).toUpperCase().replace("-", "_");
        String variantStr = ((String) variantDropdown.getSelectedItem()).toUpperCase().replace("-", "_");
        String valueText = valueField.getText().trim();

        if (name.isEmpty() || valueText.isEmpty()) {
            throw new IllegalArgumentException("All fields must be filled in.");
        }

        Rarity rarity = Rarity.valueOf(rarityStr);
        Variant variant = (variantDropdown.isEnabled()) ? Variant.valueOf(variantStr) : Variant.NONE;
        double value = Double.parseDouble(valueText);

        return new Card(name, rarity, variant, value);
    }

    /**
     * Clears all input fields and resets dropdowns to default values.
     */
    public void clearFields() {
        nameField.setText("");
        valueField.setText("");
        rarityDropdown.setSelectedIndex(0);
        variantDropdown.setSelectedIndex(0);
        variantDropdown.setEnabled(false);
    }
}
