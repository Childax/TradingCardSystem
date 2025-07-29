package com.TradingCardSystem;

import java.util.Scanner;

/**
 * The {@code TradingCardApp} class serves as the entry point for the Trading Card System.
 * It initializes the main program window where components of the application will be displayed.
 */
public class TradingCardApp {

    /**
     * Initializes all necessary components of the system and starts the main controller.
     */
    public void run() {
        Collector collector = new Collector();
        new MainProgramWindow(collector);
    }
}
