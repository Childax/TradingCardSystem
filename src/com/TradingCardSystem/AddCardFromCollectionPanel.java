package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * A panel that allows the user to add a card from their collection.
 *
 * Displays a grid of cards with options to view details or add the card,
 * and provides a back button to return to the previous view.
 */
public class AddCardFromCollectionPanel extends JPanel {

    /**
     * Constructs the panel for selecting and adding cards from a collector's collection.
     *
     * @param mainWindow the main window used for panel switching
     * @param collector the current collector whose card collection is shown
     * @param title the title displayed at the top of the panel
     * @param onCardAdded callback for when a card is added
     * @param onBack callback to execute when the back button is pressed
     */
    public AddCardFromCollectionPanel(MainProgramWindow mainWindow, Collector collector, String title, Consumer<Card> onCardAdded, Runnable onBack) {
        setLayout(new BorderLayout());

        JLabel heading = new JLabel(title, SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        add(heading, BorderLayout.NORTH);

        CardGridPanel cardGrid = new CardGridPanel(
                collector.getCards(),
                "Add",
                card -> {
                    onCardAdded.accept(card);
                    onBack.run();
                },
                card -> JOptionPane.showMessageDialog(this, card.getDetailedInfo(), "Card Details", JOptionPane.INFORMATION_MESSAGE),
                Card::incrementCount,
                Card::decrementCount,
                null
        );

        add(new JScrollPane(cardGrid), BorderLayout.CENTER);

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> onBack.run());

        JPanel bottom = new JPanel();
        bottom.add(backBtn);
        add(bottom, BorderLayout.SOUTH);
    }
}
