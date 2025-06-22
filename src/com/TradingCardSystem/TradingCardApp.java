package com.TradingCardSystem;

import java.util.Scanner;

public class TradingCardApp {
    public void run() {
        Collector collector = new Collector();
        CardView cardView = new CardView();
        CardController cardController = new CardController(cardView);
        CollectorView collectorView = new CollectorView(cardController);
        TradingCardView view = new TradingCardView();
        TradingCardController controller = new TradingCardController(collector, view, collectorView);
        controller.start();
    }
}
