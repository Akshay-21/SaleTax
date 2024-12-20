package com.saletax.saletaxapp;

import com.saletax.saletaxapp.model.Item;
import com.saletax.saletaxapp.model.Receipt;
import com.saletax.saletaxapp.utility.ParseInputUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SaletaxappApplicationTests {

    @Test
    void testParseInputUtil() {
        String input = "1 imported bottle of perfume at 47.50";
        Item item = ParseInputUtil.parseTextValue(input);

        assertEquals("imported bottle of perfume", item.getName());
        assertEquals(47.50, item.getPrice(), 0.01);
        assertTrue(item.isImported());
        assertFalse(item.isExempted());
        assertEquals(1, item.getQuantity());
    }

    @Test
    void testReceiptGenerationWithInput1() {
        List<String> input1 = Arrays.asList("1 book at 12.49", "1 music CD at 14.99", "1 chocolate bar at 0.85");

        Receipt receipt = new Receipt();
        for (String input : input1) {
            Item item = ParseInputUtil.parseTextValue(input);
            receipt.addItem(item);
        }

        String expectedOutput = "1 book: 12.49\n" + "1 music CD: 16.49\n" + "1 chocolate bar: 0.85\n" + "Sales Taxes: 1.50\n" + "Total: 29.83\n";

        assertEquals(expectedOutput, receipt.printReceipt());
    }

    @Test
    void testReceiptGenerationWithInput2() {
        List<String> input2 = Arrays.asList("1 imported box of chocolates at 10.00", "1 imported bottle of perfume at 47.50");

        Receipt receipt = new Receipt();
        for (String input : input2) {
            Item item = ParseInputUtil.parseTextValue(input);
            receipt.addItem(item);
        }

        String expectedOutput = "1 imported box of chocolates: 10.50\n" + "1 imported bottle of perfume: 54.65\n" + "Sales Taxes: 7.65\n" + "Total: 65.15\n";

        assertEquals(expectedOutput, receipt.printReceipt());
    }

    @Test
    void testReceiptGenerationWithInput3() {
        List<String> input3 = Arrays.asList("1 imported bottle of perfume at 27.99", "1 bottle of perfume at 18.99", "1 packet of headache pills at 9.75", "1 box of imported chocolates at 11.25");

        Receipt receipt = new Receipt();
        for (String input : input3) {
            Item item = ParseInputUtil.parseTextValue(input);
            receipt.addItem(item);
        }

        String expectedOutput = "1 imported bottle of perfume: 32.19\n" + "1 bottle of perfume: 20.89\n" + "1 packet of headache pills: 9.75\n" + "1 box of imported chocolates: 11.85\n" + "Sales Taxes: 6.70\n" + "Total: 74.68\n";

        assertEquals(expectedOutput, receipt.printReceipt());
    }

    @Test
    void testIntegrationForAllInputs() {
        List<List<String>> inputs = Arrays.asList(Arrays.asList("1 book at 12.49", "1 music CD at 14.99", "1 chocolate bar at 0.85"), Arrays.asList("1 imported box of chocolates at 10.00", "1 imported bottle of perfume at 47.50"), Arrays.asList("1 imported bottle of perfume at 27.99", "1 bottle of perfume at 18.99", "1 packet of headache pills at 9.75", "1 box of imported chocolates at 11.25"));

        List<String> expectedOutputs = Arrays.asList("1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\nSales Taxes: 1.50\nTotal: 29.83\n", "1 imported box of chocolates: 10.50\n1 imported bottle of perfume: 54.65\nSales Taxes: 7.65\nTotal: 65.15\n", "1 imported bottle of perfume: 32.19\n1 bottle of perfume: 20.89\n1 packet of headache pills: 9.75\n1 box of imported chocolates: 11.85\nSales Taxes: 6.70\nTotal: 74.68\n");

        for (int i = 0; i < inputs.size(); i++) {
            Receipt receipt = new Receipt();
            for (String input : inputs.get(i)) {
                Item item = ParseInputUtil.parseTextValue(input);
                receipt.addItem(item);
            }
            assertEquals(expectedOutputs.get(i), receipt.printReceipt());
        }
    }
}
