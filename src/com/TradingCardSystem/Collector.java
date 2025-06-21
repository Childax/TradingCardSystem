package com.TradingCardSystem;

import java.util.ArrayList;

public class Collector {

    private String username;
    private Collection collection;

    public Collector() {
        this.username = "fn";
        this.collection = new Collection();
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public Collection getCollection() {
        return collection;
    }
}
