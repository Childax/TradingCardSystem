package com.TradingCardSystem;

import javax.swing.*;
import java.util.Map;

/**
 * Panel for creating new binders within the application.
 * Allows users to specify the binder name and type, and creates a new binder accordingly.
 */
public class CreateBinderPanel extends AbstractCreatePanel {
    private Collector collector;
    private MainProgramWindow mainWindow;

    /**
     * Constructs the CreateBinderPanel.
     *
     * @param mainWindow the main program window
     * @param collector the collector who owns the binders
     */
    public CreateBinderPanel(MainProgramWindow mainWindow, Collector collector) {
        super("Create New Binder", new String[] {
                "Non-Curated", "Collector", "Pauper", "Rares", "Luxury"
        }, mainWindow, collector);
        this.collector = collector;
        this.mainWindow = mainWindow;
        createButton.setText("Create Binder");
    }

    /**
     * Handles the logic for creating a new binder.
     * Displays appropriate error messages if validation fails.
     * Adds the new binder to the collector and transitions to the binder menu.
     */
    @Override
    protected void handleCreate() {
        String binderName = nameField.getText().trim();
        String type = getSelectedType();
        Map<String, BinderType> displayNameToType = Map.of(
                "Non-Curated", BinderType.NON_CURATED,
                "Collector", BinderType.COLLECTOR,
                "Pauper", BinderType.PAUPER,
                "Rares", BinderType.RARES,
                "Luxury", BinderType.LUXURY
        );

        if (binderName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Binder name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (collector.hasBinderWithName(binderName)) {
            JOptionPane.showMessageDialog(this, "Binder with this name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Binder binder = new Binder(binderName);
        binder.setType(displayNameToType.get(type));
        collector.addBinder(binder);
        JOptionPane.showMessageDialog(this, "Binder '" + binderName + "' created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        nameField.setText("");
        mainWindow.showBinderMenu(collector, binder);
    }
}
