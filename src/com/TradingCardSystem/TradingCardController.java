package com.TradingCardSystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TradingCardController {
    private Collector collector;
    private TradingCardView view;
    private CollectorView collectorView;
    private CollectorController collectorController;
    private CardView cardView;
    private CardController cardController;
    private Scanner sc;

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
                if (binder != null){
                    BinderView binderView = new BinderView(sc);
                    BinderController binderController = new BinderController(collector, binder, binderView, cardController);

                    binderController.manageBinder();
                } else {
                    System.out.println("Binder not found.");
                }
                break;
            case 8:
                Deck deck = collectorController.returnDeckChoice();
                if (deck != null) {
                    DeckView deckView = new DeckView(sc);
                    DeckController deckController = new DeckController(collector, deckView, deck);

                    deckController.manageDeck();
                } else {
                    System.out.println("Deck not found.");
                }
                break;
            case 0:
                view.showExitMessage();
                break;
            default:
                view.showInvalidChoice(choice);
        }
    }
}
