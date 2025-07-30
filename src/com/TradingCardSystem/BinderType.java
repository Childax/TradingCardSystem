package com.TradingCardSystem;

/**
 * Represents the different types of binders that can be created in the trading card system.
 * Each binder type has specific rules for what kinds of cards it can contain,
 * and whether it is sellable or not.
 */
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
     * Non-sellable binder that only contain rare and legendary cards + special variants.
     */
    COLLECTOR
}
