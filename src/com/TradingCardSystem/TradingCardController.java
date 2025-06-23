package com.TradingCardSystem;

public class TradingCardController {
    private Collector collector;
    private TradingCardView view;
    private CollectorView collectorView;
    private CollectorController collectorController;

    public TradingCardController(Collector collector,
                                 TradingCardView view,
                                 CollectorView collectorView,
                                 CollectorController collectorController) {
        this.collector = collector;
        this.view = view;
        this.collectorView = collectorView;
        this.collectorController = collectorController;
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
                collectorController.addCardToCollection(collector);
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
            case 5:
                // Placeholder for card removal from collection
                break;
            case 6:
                collectorView.showCardDetails(collector);
                break;
            case 7:
                // Placeholder for managing binders
                break;
            case 8:
                // Placeholder for managing decks
                break;
            case 0:
                view.showExitMessage();
                break;
        }
    }
}
