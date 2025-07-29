package com.TradingCardSystem;

public enum BinderType {
    /**
     * Basic binder, does not have restrictions and CANNOT be sold
     */
    NON_CURATED,

    /**
     * Sellable binder that only contain common and uncommon cards.
     */
    PAUPER,

    /**
     * Sellable binder that only contain rare and legendary cards.
     */
    RARES,

    /**
     * Sellable binder that only contain cards with special variants.
     */
    LUXURY,

    /**
     * Sellable binder that only contain rare and legendary cards + special variants.
     */
    COLLECTOR
}
