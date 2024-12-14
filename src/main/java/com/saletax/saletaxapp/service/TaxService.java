package com.saletax.saletaxapp.service;

import com.saletax.saletaxapp.constant.ProductType;
import com.saletax.saletaxapp.model.Item;
import org.springframework.stereotype.Service;

@Service
public class TaxService {
    public double calculateSalesTax(Item item) {
        double basicTax = 0.0;

        if (!isExempt(item)) {
            basicTax = item.getPrice() * 0.10;
        }

        if (item.isImport()) {
            basicTax += item.getPrice() * 0.05 ;
        }

        return roundUp(basicTax);
    }

    public boolean isExempt(Item item) {
        return item.getType().equals(ProductType.BOOK) || item.getType().equals(ProductType.FOOD) || item.getType().equals(ProductType.MEDICAL);
    }

    private double roundUp(double taxValue) {
        return Math.ceil(taxValue * 20.0) / 20.0;
    }

    public double getTotalPrice(Item item) {
        return item.getPrice() + calculateSalesTax(item);
    }
}
