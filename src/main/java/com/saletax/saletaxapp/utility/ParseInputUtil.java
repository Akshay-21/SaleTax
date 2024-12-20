package com.saletax.saletaxapp.utility;

import com.saletax.saletaxapp.model.Item;

public class ParseInputUtil {
    private static final String regexValue = ".*(book|chocolate|pills).*";

    public static Item parseTextValue(String input) {
        String[] parts = input.split(" at ");
        String details = parts[0];
        double price = Double.parseDouble(parts[1]);

        boolean isImported = details.contains("imported");
        boolean isExempt = details.matches(regexValue);

        int quantity = Integer.parseInt(details.split(" ")[0]);
        String name = details.substring(details.indexOf(" ") + 1);

        return new Item(name, price, quantity, isImported, isExempt);
    }
}
