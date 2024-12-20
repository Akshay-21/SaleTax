package com.saletax.saletaxapp;

import com.saletax.saletaxapp.io.InputReader;
import com.saletax.saletaxapp.model.Item;
import com.saletax.saletaxapp.model.Receipt;
import com.saletax.saletaxapp.utility.ParseInputUtil;

import java.util.Arrays;
import java.util.List;

public class ShoppingCart {

    public static void main(String[] args) {
        consoleInput();

//        For quick testing only
//        inputValues();
    }


    public static void consoleInput() {
        InputReader reader = new InputReader();
        List<String> inputs = reader.readInputs();

        if (inputs == null || inputs.isEmpty()) {
            System.out.println("No inputs provided. Please try again.");
            return;
        }

        Receipt receipt = new Receipt();
        for (String input : inputs) {
            Item item = ParseInputUtil.parseTextValue(input);
            receipt.addItem(item);
        }

        System.out.print(receipt.printReceipt());
    }

    public static void inputValues() {

//        Input 1
        List<String> input1 = Arrays.asList(
                "1 book at 12.49",
                "1 music CD at 14.99",
                "1 chocolate bar at 0.85"
        );

//        Input 2
        List<String> input2 = Arrays.asList(
                "1 imported box of chocolates at 10.00",
                "1 imported bottle of perfume at 47.50"
        );

//        Input 3
        List<String> input3 = Arrays.asList(
                "1 imported bottle of perfume at 27.99",
                "1 bottle of perfume at 18.99",
                "1 packet of headache pills at 9.75",
                "1 box of imported chocolates at 11.25"
        );


        List<List<String>> inputs = Arrays.asList(input1, input2, input3);

        for (List<String> inputValue : inputs) {
            Receipt receipt = new Receipt();

            for (String inputData : inputValue) {
                Item item = ParseInputUtil.parseTextValue(inputData);
                receipt.addItem(item);
            }

            System.out.print(receipt.printReceipt());
            System.out.println();
        }
    }
}