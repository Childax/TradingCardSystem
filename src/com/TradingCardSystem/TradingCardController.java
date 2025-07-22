package com.TradingCardSystem;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * The {@code TradingCardController} class is the main controller for the Trading Card System.
 * It handles the user's main menu interactions, validates input choices, and routes actions
 * to the appropriate sub-controllers or views (e.g., card, binder, deck management).
 */
public class TradingCardController {
    private Collector collector;
    private TradingCardView view;
    private CollectorView collectorView;
    private CollectorController collectorController;
    private CardView cardView;
    private CardController cardController;
    private CardWindow cardWindow;
    private Scanner sc;

    /**
     * Constructs a new {@code TradingCardController} with all necessary components injected.
     *
     * @param collector           The main model holding the user's cards, binders, and decks.
     * @param view                View for main menu and system messages.
     * @param collectorView       View for card, binder, and deck operations.
     * @param collectorController Controller for collector-level logic.
     * @param cardController      Controller for card creation and validation.
     * @param cardView            View responsible for card input prompts.
     * @param sc                  Scanner object for user input.
     */
    public TradingCardController(Collector collector,
                                 TradingCardView view,
                                 CollectorView collectorView,
                                 CollectorController collectorController,
                                 CardController cardController,
                                 CardView cardView,
                                 Scanner sc) {
        this.collector = collector;
        this.view = view;
        this.collectorView = collectorView;
        this.collectorController = collectorController;
        this.cardView = cardView;
        this.cardController = cardController;
        this.sc = sc;
    }

    /**
     * Returns the set of valid menu options based on the collector's current state.
     *
     * @return A set of valid integer menu options.
     */
    public Set<Integer> getValidChoices() {
        Set<Integer> validChoices = new HashSet<>();
        validChoices.add(0);
        validChoices.add(1);
        validChoices.add(2);
        validChoices.add(3);

        if (!collector.getCards().isEmpty()) {
            validChoices.add(4);
            validChoices.add(5);
            validChoices.add(6);
        }

        if (!collector.getBinders().isEmpty()) validChoices.add(7);
        if (!collector.getDecks().isEmpty()) validChoices.add(8);

        return validChoices;
    }

    /**
     * Starts the main menu loop, prompting the user for input and routing the selection.
     */
    public void start() {
        int choice = -1;
        Set<Integer> validChoices;

        do {
            do {
                validChoices = this.getValidChoices();
                view.displayMenu(collector);
                choice = view.getMenuChoice();

                if (!validChoices.contains(choice)) {
                    view.showInvalidChoice(choice);
                }
            } while (!validChoices.contains(choice));

            handleChoice(choice);
        } while (choice != 0);
    }

    /**
     * Executes an action based on the selected menu option.
     *
     * @param choice The user-selected menu number.
     */
    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                collectorController.addCardToCollection(collector);
                break;

            case 2:
                collectorController.createBinder(collector);
                break;

            case 3:
                collectorController.createDeck(collector);
                break;

            case 4:
                collectorView.displayCollection(collector);
                break;

            case 5:
                collectorView.showCardDetails(collector);
                break;

            case 6:
                collectorController.removeCardFromCollection(collector);
                break;

            case 7:

                Binder binder = collectorController.returnBinderChoice();
                if (binder == null) {
                    return;
                }
                BinderView binderView = new BinderView(sc);
                BinderController binderController = new BinderController(collector, binder, binderView, cardController);
                binderController.manageBinder();
                break;

            case 8:
                Deck deck = collectorController.returnDeckChoice();
                if (deck == null) {
                    return;
                }
                DeckView deckView = new DeckView(sc);
                DeckController deckController = new DeckController(collector, deckView, deck);
                deckController.manageDeck();
                break;

            case 0:
                view.showExitMessage();
                break;

            default:
                view.showInvalidChoice(choice);
                break;
        }
    }
}
