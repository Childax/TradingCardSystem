package com.TradingCardSystem;

/**
 * Represents a trading card with attributes such as name, rarity, variant, value, and quantity.
 */
public class Card {
    private String name;
    private Rarity rarity;
    private Variant variant;
    private double value;
    private int count;

    /**
     * Constructs a card with the specified name, rarity, variant, and base value.
     * The count is initialized to 0.
     *
     * @param name    the name of the card
     * @param rarity  the rarity of the card
     * @param variant the variant of the card
     * @param value   the base value of the card
     */
    public Card(String name, Rarity rarity, Variant variant, double value) {
        this.name = name;
        this.rarity = rarity;
        this.variant = variant;
        this.value = value;
        this.adjustValueByVariant();
        this.count = 0;
    }

    /**
     * Constructs a card with default values (null or 0).
     */
    public Card() {
        this.name = null;
        this.rarity = null;
        this.variant = null;
        this.value = 0;
        this.count = 0;
    }

    /**
     * Copy constructor that duplicates the basic details of an existing card.
     * Count is set to 1 regardless of the original card's count.
     *
     * @param card the card to copy from
     */
    public Card(Card card) {
        this.name = card.getName();
        this.rarity = card.getRarity();
        this.variant = card.getVariant();
        this.value = card.getValue();
        this.count = 1;
    }

    /**
     * Gets the rarity of the card.
     *
     * @return the rarity
     */
    public Rarity getRarity() {
        return rarity;
    }

    /**
     * Sets the rarity of the card.
     *
     * @param rarity the rarity to set
     */
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    /**
     * Gets the name of the card.
     *
     * @return the card name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the card.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the variant of the card.
     *
     * @return the variant
     */
    public Variant getVariant() {
        return variant;
    }

    /**
     * Sets the variant of the card and adjusts its value accordingly.
     *
     * @param variant the variant to set
     */
    public void setVariant(Variant variant) {
        this.variant = variant;
        this.adjustValueByVariant();
    }

    /**
     * Gets the value of the card.
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the value of the card.
     *
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Gets the count of how many of this card exist.
     *
     * @return the card count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the count of the card.
     *
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Increments the card count by 1.
     */
    public void incrementCount() {
        this.count++;
    }

    /**
     * Decrements the card count by 1.
     */
    public void decrementCount() {
        this.count--;
    }

    /**
     * Displays card details in a concise hidden format.
     */
    public void displayCardHidden() {
        System.out.printf("- %s (x%d): $%.2f each\n", this.getName(), this.getCount(), this.getValue());
    }

    /**
     * Displays card details in a single-line format.
     */
    public void displayCardInline() {
        System.out.printf("Name: %s | Rarity: %s | Variant: %s | Value: %.2f | Count: %d\n",
                this.getName(), this.getRarity(), this.getVariant(), this.getValue(), this.getCount());
    }

    /**
     * Displays card details in a decorative boxed layout.
     */
    public void displayCardBox() {
        String name = "Name: " + this.getName();
        String rarity = "Rarity: " + this.getRarity();
        String variant = "Variant: " + (this.getVariant() != null ? this.getVariant() : "None");
        String value = String.format("Value: $%.2f", this.getValue());
        String count = "Count: " + this.getCount();

        int contentWidth = Math.max(
                Math.max(name.length(), rarity.length()),
                Math.max(Math.max(variant.length(), value.length()), count.length())
        );
        int width = contentWidth + 4;

        String border = "+" + "-".repeat(width - 2) + "+";

        String title = "CARD DETAILS";
        int padding = (width - 2 - title.length()) / 2;
        String titleLine = "|" + " ".repeat(padding) + title + " ".repeat(width - 2 - padding - title.length()) + "|";

        System.out.println(border);
        System.out.println(titleLine);
        System.out.println(border);
        System.out.printf("| %-" + contentWidth + "s |\n", name);
        System.out.printf("| %-" + contentWidth + "s |\n", rarity);
        System.out.printf("| %-" + contentWidth + "s |\n", variant);
        System.out.printf("| %-" + contentWidth + "s |\n", value);
        System.out.printf("| %-" + contentWidth + "s |\n", count);
        System.out.println(border);
    }

    /**
     * Compares this card to another object for equality based on name.
     *
     * @param obj the object to compare with
     * @return true if the names match, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Card card = (Card) obj;
        return this.name.equals(card.name);
    }

    /**
     * Adjusts the card's value based on its variant.
     * EXTENDED_ART increases value by 1.5x,
     * FULL_ART by 2x, and ALT_ART by 3x.
     */
    public void adjustValueByVariant() {
        switch (this.variant) {
            case EXTENDED_ART -> this.setValue(this.getValue() * 1.5);
            case FULL_ART     -> this.setValue(this.getValue() * 2);
            case ALT_ART      -> this.setValue(this.getValue() * 3);
            default           -> {
                // No adjustment
            }
        }
    }

    public String getDetailedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Rarity: ").append(rarity).append("\n");
        sb.append("Variant: ").append(variant).append("\n");
        sb.append("Value: ").append(value).append("\n");
        sb.append("Count: ").append(count).append("\n");
        return sb.toString();
    }
}
