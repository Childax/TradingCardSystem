package com.TradingCardSystem;

import javax.swing.*;
import java.text.Normalizer;

/**
 * Panel for creating new decks within the application.
 * Allows users to specify the deck name and whether it is sellable.
 */
public class CreateDeckPanel extends AbstractCreatePanel {
    private Collector collector;
    private MainProgramWindow mainWindow;

    /**
     * Constructs the CreateDeckPanel.
     *
     * @param mainWindow the main program window
     * @param collector the collector who owns the decks
     */
    public CreateDeckPanel(MainProgramWindow mainWindow, Collector collector) {
        super("Create New Deck", new String[]{
                "Normal",
                "Sellable"
        }, mainWindow, collector);
        this.collector = collector;
        this.mainWindow = mainWindow;
        createButton.setText("Create Deck");
    }

    /**
     * Handles the logic for creating a new deck.
     * Displays appropriate error messages if validation fails.
     * Adds the new deck to the collector and transitions to the deck menu.
     */
    @Override
    protected void handleCreate() {
        String deckName = nameField.getText().trim();
        boolean sellability = false;
        if (deckName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Deck name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (collector.hasDeckWithName(deckName)) {
            JOptionPane.showMessageDialog(this, "Deck with this name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (getSelectedType().equals("Sellable")) {
            sellability = true;
        }

        Deck deck = new Deck(deckName);
        deck.setSellablility(sellability);
        collector.addDeck(deck);

        JOptionPane.showMessageDialog(this, "Deck '" + deckName + "' created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        nameField.setText("");
        mainWindow.showDeckMenu(collector, deck);
    }
}
