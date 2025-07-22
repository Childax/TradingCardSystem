package com.TradingCardSystem;

import javax.swing.*;
import java.awt.*;

public class MainProgramWindow extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private ViewCollectionPanel viewCollectionPanel;

    public MainProgramWindow(Collector collector, CollectorController controller,
                             CollectorView view, CardController cardController, CollectorController collectorController) {
        this.setTitle("Trading Card Inventory System");
        this.setSize(500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add the menu screen
        MenuPanel menu = new MenuPanel(this, collector, controller, view, cardController);
        mainPanel.add(menu, "menu");

        // Other Panels
        mainPanel.add(new AddCardPanel(this, collector, collectorController), "addCard");
        mainPanel.add(new CreateBinderPanel(this, collector), "createBinder");

        viewCollectionPanel = new ViewCollectionPanel(this, collector);
        mainPanel.add(viewCollectionPanel, "viewCollection");




        this.add(mainPanel);
        this.setVisible(true);

        showPanel("menu");
    }

    public void showPanel(String panelName) {
        if (panelName.equals("viewCollection")) {
            viewCollectionPanel.refresh();
        }
        cardLayout.show(mainPanel, panelName);
    }
}
