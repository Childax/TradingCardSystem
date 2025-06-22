package com.TradingCardSystem;

public class TradingCardController {
    private Collector collector;
    private TradingCardView view;

    public TradingCardController(Collector collector, TradingCardView view) {
        this.collector = collector;
        this.view = view;
    }

    public void start() {
        int choice = -1;

        do {
            do {
                view.displayMenu();
                choice = view.getMenuChoice();

                if (choice < 0 || choice > 3) {
                    view.showInvalidChoice(choice);
                }
            } while (choice < 0 || choice > 3);

            handleChoice(choice);
        } while (choice != 0);
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                CollectionController.promptAddCard(collector.getCollection());
                collector.getCollection().displayCards();
                break;
            case 2:
                // Placeholder for binder creation logic
                break;
            case 3:
                // Placeholder for deck creation logic
                break;
            case 0:
                view.showExitMessage();
                break;
        }
    }
}
