package com.TradingCardSystem;

public class BinderController {

    private Collector collector;
    private Binder binder;
    private BinderView binderView;
    private CardView cardView;

    public BinderController(Collector collector, Binder binder, BinderView binderView, CardView cardView) {
        this.collector = collector;
        this.binder = binder;
        this.binderView = binderView;
        this.cardView = cardView;
    }

    public void manageBinder() {
        while (true) {
            binderView.displayBinderMenu();
            int choice = binderView.promptBinderMenuChoice();

            switch (choice) {
                case 1:
                    //handleAddCardToBinder();
                    break;
                case 2:
                    //handleRemoveCardFromBinder();
                    break;
                case 3:
                    //handleTradeCard();
                    break;
                case 4:
                    binderView.displayCardsFromBinder();
                    break;
                case 5:
                    //handleDeleteBinder();
                    return;
                case 0:
                    binderView.displayReturnMessage();
                    return;
                default:

            }
        }
    }
}
