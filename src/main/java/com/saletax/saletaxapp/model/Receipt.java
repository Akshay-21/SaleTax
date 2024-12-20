package com.saletax.saletaxapp.model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private final List<Item> items;

    public Receipt() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public String printReceipt() {
        StringBuilder stringAddReceiptValue = new StringBuilder();
        double totalTax = 0.0;
        double totalPrice = 0.0;

        for (Item item : items) {
            double itemTax = item.calculateSalesTax() * item.getQuantity();
            double itemTotal = item.calculateTotalPrice();

            totalTax += itemTax;
            totalPrice += itemTotal;

            stringAddReceiptValue.append(item).append("\n");
        }

        stringAddReceiptValue.append(String.format("Sales Taxes: %.2f\n", totalTax));
        stringAddReceiptValue.append(String.format("Total: %.2f\n", totalPrice));

        return stringAddReceiptValue.toString();
    }
}