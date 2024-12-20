package com.saletax.saletaxapp.model;

public class Item {
    private final String name;
    private final double price;
    private final boolean isImported;
    private final boolean isExempted;
    private final int quantity;

    public Item(String name, double price, int quantity, boolean isImported, boolean isExempt) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isImported = isImported;
        this.isExempted = isExempt;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isImported() {
        return isImported;
    }

    public boolean isExempted() {
        return isExempted;
    }

    public int getQuantity() {
        return quantity;
    }


    public double calculateSalesTax() {
        double tax = 0.0;
        if (!isExempted()) {
            tax += getPrice() * 0.10;
        }
        if (isImported()) {
            tax += getPrice() * 0.05;
        }
        return roundUp(tax);
    }

    public double calculateTotalPrice() {
        return (price + calculateSalesTax()) * quantity;
    }

    private double roundUp(double value) {
        return Math.ceil(value * 20.0) / 20.0;
    }


    public double getTotalPrice() {
        return getPrice() + calculateSalesTax();
    }

    @Override
    public String toString() {
        return String.format("%d %s: %.2f", quantity, name, calculateTotalPrice());
    }
}
