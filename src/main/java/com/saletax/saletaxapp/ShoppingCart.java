package com.saletax.saletaxapp;

import com.saletax.saletaxapp.constant.ProductType;
import com.saletax.saletaxapp.model.Item;
import com.saletax.saletaxapp.service.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "com.saletax.saletaxapp")
public class ShoppingCart implements CommandLineRunner {

    @Autowired
    private Receipt receipt;

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCart.class, args);

//        Receipt receipt = new Receipt();
//        BasedOnInput();
//        receipt.printReceipt();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello from the Spring Boot CLI application!");
        // Add any other business logic here

        getCLI();
    }

    public void BasedOnInput() {
        //        Input 1:
        System.out.println("Receipt 1");
        Receipt receipt1 = new Receipt();
        receipt1.addItem(new Item("book", 12.49, false, ProductType.BOOK));
        receipt1.addItem(new Item("music CD", 14.99, false, ProductType.OTHER));
        receipt1.addItem(new Item("chocolate bar", 0.85, false, ProductType.FOOD));

        receipt1.printReceipt();
        System.out.println();

        //        Input 2:
        System.out.println("Receipt 2");
        Receipt receipt2 = new Receipt();
        Item item4 = new Item("chocolates", 10.00, true, ProductType.FOOD);
        Item item5 = new Item("perfume", 47.50, true, ProductType.OTHER);
        receipt2.addItem(item4);
        receipt2.addItem(item5);
        receipt2.printReceipt();
        System.out.println();

        //        Input 3:
        System.out.println("Receipt 3");
        Receipt receipt3 = new Receipt();
        receipt3.addItem(new Item("imported bottle of perfume", 27.99, true, ProductType.OTHER));
        receipt3.addItem(new Item("bottle of perfume", 18.99, false, ProductType.OTHER));
        receipt3.addItem(new Item("packet of headache pills", 9.75, false, ProductType.MEDICAL));
        receipt3.addItem(new Item("imported box of chocolates", 11.25, true, ProductType.FOOD));

        receipt3.printReceipt();
    }

    public void getCLI() {

        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your item Name: ");
            String itemName = scn.nextLine();

            System.out.println("Enter your item price: ");
            double price = scn.nextDouble();

            System.out.println("Is item imported Y/N");
            String isImportedString = scn.next();
            boolean isImported;
            if (isImportedString.equalsIgnoreCase("Y")) {
                isImported = true;
            } else {
                isImported = false;
            }

            System.out.println("What is the item type: press number 0, 1, 2, 3 accordingly");
            System.out.println("BOOK -> 0");
            System.out.println("FOOD -> 1");
            System.out.println("MEDICAL -> 2");
            System.out.println("OTHERS -> 3");
            int productTypeCl = scn.nextInt();

            ProductType productType = switch (productTypeCl) {
                case 0 -> ProductType.BOOK;
                case 1 -> ProductType.FOOD;
                case 2 -> ProductType.MEDICAL;
                case 3 -> ProductType.OTHER;
                default -> null;
            };

            scn.nextLine();

            Item item = new Item(itemName, price, isImported, productType);
            receipt.addItem(item);

            System.out.print("press q to quit application or c to continue");
            System.out.println();
            var exit = scn.next();
            scn.nextLine();
            if (Objects.equals(exit, "q")) {
                System.out.println("Exiting the program.");
                receipt.printReceipt();
                break;
            } else if (Objects.equals(exit, "c")) {
                continue;
            }
        }
    }
}
