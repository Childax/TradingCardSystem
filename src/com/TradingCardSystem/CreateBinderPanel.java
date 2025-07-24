package com.TradingCardSystem;

import javax.swing.*;

public class CreateBinderPanel extends AbstractCreatePanel {
    private Collector collector;
    private MainProgramWindow mainWindow;

    public CreateBinderPanel(MainProgramWindow mainWindow, Collector collector) {
        super("Create New Binder", mainWindow, collector);
        this.collector = collector;
        this.mainWindow = mainWindow;
        createButton.setText("Create Binder");
    }

    @Override
    protected void handleCreate() {
        String binderName = nameField.getText().trim();
        if (binderName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Binder name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (collector.hasBinderWithName(binderName)) {
            JOptionPane.showMessageDialog(this, "Binder with this name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Binder binder = new Binder(binderName);
        collector.addBinder(binder);
        JOptionPane.showMessageDialog(this, "Binder '" + binderName + "' created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        nameField.setText("");
        mainWindow.showBinderMenu(collector, binder);
    }
}
