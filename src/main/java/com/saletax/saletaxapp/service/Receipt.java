package com.saletax.saletaxapp.service;

import com.saletax.saletaxapp.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Receipt {

    @Autowired
    private ItemService itemService;
    @Autowired
    private TaxService taxService;

    public void addItem(Item item) {
        itemService.addItem(item);
    }

    public void printReceipt() {
        double totalTax = 0;
        double totalPrice = 0;

        List<Item> allItems = itemService.getAllItems();

        for (Item item : allItems) {
            double tax = taxService.calculateSalesTax(item);
            totalTax += tax;
            totalPrice += taxService.getTotalPrice(item);

            System.out.println(1 + " " + item.getName() + ": " + String.format("%.2f", taxService.getTotalPrice(item)));
        }

        System.out.println("Sales Taxes: " + String.format("%.2f", totalTax));
        System.out.println("Total: " + String.format("%.2f", totalPrice));
    }

}
