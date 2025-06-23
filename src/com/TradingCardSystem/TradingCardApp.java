package com.TradingCardSystem;

import java.util.Scanner;

public class TradingCardApp {
    public void run() {
        CardView cardView = new CardView();
        CardController cardController = new CardController(cardView);

        Collector collector = new Collector();
        CollectorView collectorView = new CollectorView(cardController, cardView);
        CollectorController collectorController = new CollectorController(collector, collectorView);

        TradingCardView view = new TradingCardView();
        TradingCardController controller = new TradingCardController(collector, view, collectorView, collectorController);

        controller.start();
    }
}
