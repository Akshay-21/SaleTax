package com.saletax.saletaxapp;

import com.saletax.saletaxapp.constant.ProductType;
import com.saletax.saletaxapp.model.Item;

public class ItemTestCase {
    public static void main(String[] args) {
        Item book = new Item("book", 12.49, false, ProductType.BOOK);
        assert book.calculateSalesTax() == 0.0 : "Tax calculation failed for book";

        Item musicCD = new Item("music CD", 14.99, false, ProductType.OTHER);
        assert musicCD.calculateSalesTax() == 2.25 : "Tax calculation failed for music CD";

        System.out.println("All tests passed!");
    }
}
