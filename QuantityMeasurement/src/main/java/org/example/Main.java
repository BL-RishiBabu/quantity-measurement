package org.example;
import java.util.Scanner;

public class Main {
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1 != null && length1.equals(length2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Unified Quantity Measurement System ===");

        while (true) {
            try {
                System.out.print("\nEnter first value (or type -1 to exit): ");
                double val1 = scanner.nextDouble();
                if (val1 == -1) {
                    System.out.println("Exiting application. Goodbye!");
                    break;
                }

                System.out.print("Enter first unit (FEET / INCHES): ");
                String unitInput1 = scanner.next().toUpperCase();
                Length.LengthUnit unit1 = Length.LengthUnit.valueOf(unitInput1);

                System.out.print("Enter second value: ");
                double val2 = scanner.nextDouble();

                System.out.print("Enter second unit (FEET / INCHES): ");
                String unitInput2 = scanner.next().toUpperCase();
                Length.LengthUnit unit2 = Length.LengthUnit.valueOf(unitInput2);

                Length length1 = new Length(val1, unit1);
                Length length2 = new Length(val2, unit2);

                boolean result = demonstrateLengthEquality(length1, length2);
                System.out.println("------------------------------------------------");
                System.out.println("Result: Quantity(" + val1 + ", " + unit1 + ") and Quantity(" + val2 + ", " + unit2 + ")");
                System.out.println("Are lengths equal? -> " + result);
                System.out.println("------------------------------------------------");

            } catch (IllegalArgumentException e) {
                System.out.println("❌ Error: Invalid Unit type entered. Please use exactly 'FEET' or 'INCHES'.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("❌ Error: Please enter numerical values for measurements.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}