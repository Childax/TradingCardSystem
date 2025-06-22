package com.TradingCardSystem;

import java.util.Scanner;

public class TradingCardApp {
    public void run() {
        Collector collector = new Collector();
        TradingCardView view = new TradingCardView();
        TradingCardController controller = new TradingCardController(collector, view);
        controller.start();
    }
}
