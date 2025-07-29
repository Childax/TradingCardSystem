package com.TradingCardSystem;

import javax.swing.*;

public class CreateDeckPanel extends AbstractCreatePanel {
    private Collector collector;
    private MainProgramWindow mainWindow;

    public CreateDeckPanel(MainProgramWindow mainWindow, Collector collector) {
        super("Create New Deck", mainWindow, collector);
        this.collector = collector;
        this.mainWindow = mainWindow;
        createButton.setText("Create Deck");
    }

    @Override
    protected void handleCreate() {
        String deckName = nameField.getText().trim();
        if (deckName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Deck name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (collector.hasDeckWithName(deckName)) {
            JOptionPane.showMessageDialog(this, "Deck with this name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Deck deck = new Deck(deckName);
        collector.addDeck(deck);
        JOptionPane.showMessageDialog(this, "Deck '" + deckName + "' created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        nameField.setText("");
        mainWindow.showDeckMenu(collector, deck);
    }
}
