package org.example;
import java.util.Scanner;

public class Main {

    static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Multi-Category Quantity Measurement CLI ===");

        while (true) {
            try {
                System.out.print("\nSelect Category (1: LENGTH, 2: WEIGHT, -1: EXIT): ");
                int category = scanner.nextInt();
                if (category == -1) break;
                if (category == 1) handleLengthOperations(scanner);
                else if (category == 2) handleWeightOperations(scanner);
                else System.out.println("Invalid selection. Please choose 1, 2, or -1.");
            } catch (Exception e) {
                System.out.println("Error processing operation. Checking inputs...");
                scanner.nextLine();
            }
        }
        System.out.println("Exiting Application. Goodbye!");
        scanner.close();
    }

    private static void handleLengthOperations(Scanner scanner) {
        System.out.print("Enter first length value: ");
        double val1 = scanner.nextDouble();
        System.out.print("Enter first unit (FEET, INCHES, YARDS, CENTIMETERS): ");
        LengthUnit unit1 = LengthUnit.valueOf(scanner.next().toUpperCase());
        Length length1 = new Length(val1, unit1);

        System.out.print("Enter second length value: ");
        double val2 = scanner.nextDouble();
        System.out.print("Enter second unit: ");
        LengthUnit unit2 = LengthUnit.valueOf(scanner.next().toUpperCase());
        Length length2 = new Length(val2, unit2);

        System.out.print("Enter explicit target unit (or type 'DEFAULT'): ");
        String targetInput = scanner.next().toUpperCase();

        Length result = targetInput.equals("DEFAULT") ? length1.add(length2) : length1.add(length2, LengthUnit.valueOf(targetInput));
        System.out.println("\n[Length Sum Result]: " + result);
    }

    private static void handleWeightOperations(Scanner scanner) {
        System.out.print("Enter first weight value: ");
        double val1 = scanner.nextDouble();
        System.out.print("Enter first unit (MILLIGRAM, GRAM, KILOGRAM, POUND, TONNE): ");
        WeightUnit unit1 = WeightUnit.valueOf(scanner.next().toUpperCase());
        Weight weight1 = new Weight(val1, unit1);

        System.out.print("Enter second weight value: ");
        double val2 = scanner.nextDouble();
        System.out.print("Enter second unit: ");
        WeightUnit unit2 = WeightUnit.valueOf(scanner.next().toUpperCase());
        Weight weight2 = new Weight(val2, unit2);

        System.out.print("Enter explicit target unit (or type 'DEFAULT'): ");
        String targetInput = scanner.next().toUpperCase();

        Weight result = targetInput.equals("DEFAULT") ? weight1.add(weight2) : weight1.add(weight2, WeightUnit.valueOf(targetInput));
        System.out.println("\n[Weight Sum Result]: " + result);
    }
}