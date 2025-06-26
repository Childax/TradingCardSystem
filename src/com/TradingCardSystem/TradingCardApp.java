package com.TradingCardSystem;

import java.util.Scanner;

public class TradingCardApp {
    public void run() {
        Scanner sc = new Scanner(System.in);

        CardView cardView = new CardView(sc);
        CardController cardController = new CardController(cardView);

        Collector collector = new Collector();
        CollectorView collectorView = new CollectorView(cardController, cardView, sc);
        CollectorController collectorController = new CollectorController(collector, collectorView);

        TradingCardView view = new TradingCardView(sc);
        TradingCardController controller = new TradingCardController(collector,
                        view,
                        collectorView,
                        collectorController,
                cardController,
                cardView,
                        sc);

        controller.start();
    }
}
