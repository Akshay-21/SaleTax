package com.saletax.saletaxapp.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {

    public List<String> readInputs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose input source:\n1. Console\n2. File");
        int selection = scanner.nextInt();
        scanner.nextLine();

        switch (selection) {
            case 1:
                return readFromConsole(scanner);
            case 2:
                System.out.println("Enter file path:");
                String filePath = scanner.nextLine();
                return readFromFile(filePath);
            default:
                System.out.println("Invalid choice. Exiting.");
                return new ArrayList<>();
        }
    }

    private List<String> readFromConsole(Scanner scanner) {
        System.out.println("Enter items (type 'done' to finish):");
        List<String> inputs = new ArrayList<>();
        String line;
        while (!(line = scanner.nextLine()).equalsIgnoreCase("done")) {
            inputs.add(line);
        }
        return inputs;
    }

    private List<String> readFromFile(String filePath) {
        List<String> inputs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                inputs.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return inputs;
    }
}
