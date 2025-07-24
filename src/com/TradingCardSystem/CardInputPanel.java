package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

public class CardInputPanel extends JPanel {
    private final JTextField nameField;
    private final JComboBox<String> rarityDropdown;
    private final JComboBox<String> variantDropdown;
    private final JTextField valueField;

    private final Rarity[] rarityValues;
    private final Variant[] variantValues;

    public CardInputPanel() {
        this.nameField = new JTextField();

        this.valueField = new JTextField();
        this.rarityValues = Rarity.values();
        this.variantValues = Variant.values();


        setLayout(new GridLayout(4, 2, 5, 5));

        rarityDropdown = new JComboBox<>(getFormattedEnumStrings(rarityValues));
        variantDropdown = new JComboBox<>(getFormattedEnumStrings(variantValues));
        variantDropdown.setEnabled(false); // Start disabled

        rarityDropdown.addActionListener(e -> {
            Rarity selectedRarity = rarityValues[rarityDropdown.getSelectedIndex()];
            variantDropdown.setEnabled(selectedRarity == Rarity.RARE || selectedRarity == Rarity.LEGENDARY);
        });

        add(new JLabel("Card Name:"));
        add(nameField);
        add(new JLabel("Rarity:"));
        add(rarityDropdown);
        add(new JLabel("Variant:"));
        add(variantDropdown);
        add(new JLabel("Value:"));
        add(valueField);
    }

    private String[] getFormattedEnumStrings(Enum<?>[] values) {
        String[] result = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = formatEnumName(values[i]);
        }
        return result;
    }

    private String formatEnumName(Enum<?> e) {
        String name = e.name().toLowerCase().replace("_", "-");
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

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

    public void clearFields() {
        nameField.setText("");
        valueField.setText("");
        rarityDropdown.setSelectedIndex(0);
        variantDropdown.setSelectedIndex(0);
        variantDropdown.setEnabled(false);
    }
}
