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
    private Scanner sc;

    public TradingCardController(Collector collector,
                                 TradingCardView view,
                                 CollectorView collectorView,
                                 CollectorController collectorController,
                                 CardView cardView,
                                 Scanner sc) {
        this.collector = collector;
        this.view = view;
        this.collectorView = collectorView;
        this.collectorController = collectorController;
        this.cardView = cardView;
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
                // Placeholder for deck creation logic
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
                Binder binder = new Binder();
                if ((binder = collectorController.returnBinderChoice()) != null){
                    BinderView binderView = new BinderView(binder, cardView, sc);
                    BinderController binderController = new BinderController(collector, binder, binderView, cardView);

                    binderController.manageBinder();
                } else {
                    System.out.println("Binder not found.");
                }
                break;
            case 8:
                // Placeholder for managing decks
                break;
            case 0:
                view.showExitMessage();
                break;
            default:
                view.showInvalidChoice(choice);
        }
    }
}
