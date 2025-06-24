package com.TradingCardSystem;

public class BinderController {

    private Collector collector;
    private CollectorController collectorController;
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
                    handleAddCardToBinder();
                    break;
                case 2:
                    handleRemoveCardFromBinder();
                    break;
                case 3:
                    //handleTradeCard();
                    break;
                case 4:
                    binderView.displayCardsFromBinder();
                    break;
                case 5:
                    if (handleDeleteBinder()) {
                        return;
                    } else {
                        break;
                    }
                case 0:
                    binderView.displayReturnMessage();
                    return;
                default:

            }
        }
    }

    public boolean handleAddCardToBinder() {
        collector.displayCards();
        String name = binderView.promptAddCard();
        Card card = collector.getCardWithName(name);

        if (card != null && card.getCount() > 0) {
            binder.addCard(new Card(card));
            card.decrementCount();
            binderView.displayAddCardConfirmation(name, binder.getName());
            return true;
        } else {
            binderView.displayCardNotFound();
            return false;
        }
    }

    public boolean handleRemoveCardFromBinder() {
        binderView.displayCardsFromBinder();
        String name = binderView.promptRemoveCard();

        Card card = binder.getCardWithName(name);
        if(card == null){
            binderView.displayCardNotFound();
            return false;
        }

        binder.removeCard(card);
        collector.addCard(card);
        binderView.displayRemoveCardConfirmation(name);
        return true;
    }

    public boolean handleDeleteBinder() {
        String name = binder.getName();
        if (binderView.promptBinderDeletionConfirmation(name).equalsIgnoreCase("Y")) {
            collector.deleteBinder(name);
            return true;
        } else {
            binderView.displayBinderNotDeleted();
            return false;
        }
    }
}
