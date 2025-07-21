package com.TradingCardSystem;

/**
 * The BinderController class manages interactions between the Binder model,
 * BinderView for user interface display, and the Collector and CardController logic.
 * It handles adding, removing, trading, and displaying cards within a binder.
 */
public class BinderController {

    private Collector collector;
    private Binder binder;
    private BinderView binderView;
    private CardController cardController;

    /**
     * Constructs a BinderController with references to the Collector, Binder, BinderView,
     * and CardController instances.
     *
     * @param collector      the collector who owns the binders and cards
     * @param binder         the specific binder to be managed
     * @param binderView     the view used to interact with the user
     * @param cardController the controller used to manage card-related operations
     */
    public BinderController(Collector collector, Binder binder, BinderView binderView, CardController cardController) {
        this.collector = collector;
        this.binder = binder;
        this.binderView = binderView;
        this.cardController = cardController;
    }

    /**
     * Starts and continuously runs the binder management menu,
     * allowing the user to choose actions until they decide to exit or delete the binder.
     */
    public void manageBinder() {
        while (true) {
            binderView.displayBinderMenu(binder);
            int choice = binderView.promptBinderMenuChoice();

            switch (choice) {
                case 1 -> handleAddCardToBinder();
                case 2 -> handleRemoveCardFromBinder();
                case 3 -> handleTradeCard();
                case 4 -> binderView.displayCardsFromBinder(binder);
                case 5 -> {
                    if (handleDeleteBinder()) {
                        return;
                    }
                }
                case 0 -> {
                    binderView.displayReturnMessage();
                    return;
                }
                default -> {
                    // Invalid choice can be handled in the view layer
                }
            }
        }
    }

    /**
     * Handles the process of adding a card from the collector's inventory to the binder.
     *
     * @return true if the card was successfully added, false otherwise
     */
    public boolean handleAddCardToBinder() {
        collector.displayCards();
        String name = binderView.promptAddCard();
        if (name.equals("0")) {
            System.out.println("Returning to binder menu...");
            return false;
        }
        Card card = collector.getCardWithName(name);

        if (card != null && card.getCount() > 0) {
            binder.addCard(new Card(card));
            card.decrementCount();
            binderView.displayAddCardConfirmation(name, binder.getName());
            return true;
        } else if (binder.isFull()) {
            binderView.displayBinderFull();
            return false;
        } else {
            binderView.displayCardNotFound();
            return false;
        }
    }

    /**
     * Handles the removal of a card from the binder back to the collector.
     *
     * @return true if the card was successfully removed, false if card not found
     */
    public boolean handleRemoveCardFromBinder() {
        binderView.displayCardsFromBinder(binder);
        String name = binderView.promptRemoveCard();
        if (name.equals("0")) {
            System.out.println("Returning to binder menu...");
            return false;
        }

        Card card = binder.getCardWithName(name);
        if (card == null) {
            binderView.displayCardNotFound();
            return false;
        }

        binder.removeCard(card);
        collector.addCard(card);
        binderView.displayRemoveCardConfirmation(name);
        return true;
    }

    /**
     * Handles the deletion of the current binder, if the user confirms.
     *
     * @return true if the binder was deleted, false otherwise
     */
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

    /**
     * Handles trading a card in the binder for a new one, after user confirmation.
     *
     * @return true if the trade was completed or cancelled explicitly, false if trade was aborted due to error
     */
    public boolean handleTradeCard() {
        binderView.displayCardsFromBinder(binder);
        if (binder.getCards().isEmpty()) {
            return false;
        }

        String name = binderView.promptTradeCardName();
        if (name.equals("0")) {
            System.out.println("Returning to binder menu...");
            return false;
        }

        if (binder.getCardWithName(name) == null) {
            binderView.displayCardNotFound();
            return false;
        }

        Card cardToBeRemoved = binder.getCardWithName(name);
        Card cardToBeAdded = binderView.promptTradeCardDetails(cardController);
        if (cardToBeRemoved == null || cardToBeAdded == null) {
            return false;
        }

        switch (binderView.promptTradeConfirmation(cardToBeRemoved.getValue(), cardToBeAdded.getValue())) {
            case 'y' -> {
                binder.tradeCard(cardToBeAdded, cardToBeRemoved);
                System.out.println("Trade successfully completed!");
                binderView.displayTradeInformation(cardToBeRemoved, cardToBeAdded);
                return true;
            }
            case 'n' -> {
                binderView.displayTradeCancellation();
                return true;
            }
            default -> {
                return false;
            }
        }
    }
}
