package com.TradingCardSystem;

import java.util.Scanner;

/**
 * The {@code TradingCardApp} class serves as the entry point for the Trading Card System.
 * It initializes the core components of the application, such as the views, controllers,
 * and models, and starts the application via the {@code TradingCardController}.
 * <p>
 * This class follows the Model-View-Controller (MVC) architecture pattern to
 * separate the logic, user interface, and data handling of the system.
 */
public class TradingCardApp {

    /**
     * Initializes all necessary components of the system and starts the main controller.
     * Sets up the {@link Scanner}, views, controllers, and the main {@link Collector} model.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        CardView cardView = new CardView(sc);
        CardController cardController = new CardController(cardView);

        Collector collector = new Collector();
        CollectorView collectorView = new CollectorView(cardController, cardView, sc);
        CollectorController collectorController = new CollectorController(collector, collectorView);

        TradingCardView view = new TradingCardView(sc);

        TradingCardController controller = new TradingCardController(
                collector,
                view,
                collectorView,
                collectorController,
                cardController,
                cardView,
                sc
        );

        new MainProgramWindow(collector);
        controller.start();
    }
}
