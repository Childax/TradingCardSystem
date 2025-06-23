package com.TradingCardSystem;

public class TradingCardController {
    private Collector collector;
    private TradingCardView view;
    private CollectorView collectorView;

    public TradingCardController(Collector collector, TradingCardView view, CollectorView collectorView) {
        this.collector = collector;
        this.view = view;
        this.collectorView = collectorView;
    }

    public void start() {
        int choice = -1;

        do {
            do {
                view.displayMenu(collector);
                choice = view.getMenuChoice();

                if (choice < 0 || choice > 7) {
                    view.showInvalidChoice(choice);
                }
            } while (choice < 0 || choice > 7);

            handleChoice(choice);
        } while (choice != 0);
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                collectorView.promptAddCard(collector);
                break;
            case 2:
                // Placeholder for binder creation logic
                break;
            case 3:
                // Placeholder for deck creation logic
                break;
            case 4:
                collectorView.displayCollection(collector);
                break;
            case 6:
                collectorView.showCardDetails(collector);
                break;
            case 0:
                view.showExitMessage();
                break;
        }
    }
}
