package org.example;
import java.util.Scanner;

public class Main {
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1 != null && length1.equals(length2);
    }

    public static void demonstrateLengthComparison(double value1, Length.LengthUnit unit1, double value2, Length.LengthUnit unit2) {
        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);
        boolean result = demonstrateLengthEquality(length1, length2);
        System.out.println("Comparing " + value1 + " " + unit1 + " with " + value2 + " " + unit2 + " -> Equal: " + result);
    }

    static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available Units: FEET, INCHES, YARDS, CENTIMETERS");
        System.out.println("-------------------------------------------------");
        while (true) {
            try {
                System.out.print("\nEnter first numerical value (or -1 to exit): ");
                double val1 = scanner.nextDouble();
                if (val1 == -1) break;

                System.out.print("Enter first unit (FEET/INCHES/YARDS/CENTIMETERS): ");
                Length.LengthUnit unit1 = Length.LengthUnit.valueOf(scanner.next().toUpperCase());

                System.out.print("Enter second numerical value: ");
                double val2 = scanner.nextDouble();

                System.out.print("Enter second unit (FEET/INCHES/YARDS/CENTIMETERS): ");
                Length.LengthUnit unit2 = Length.LengthUnit.valueOf(scanner.next().toUpperCase());

                System.out.println("\n--- Result ---");
                demonstrateLengthComparison(val1, unit1, val2, unit2);

            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid unit selection. Please check your spelling.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("❌ Input error. Please input numbers for values.");
                scanner.nextLine();
            }
        }
        System.out.println("Goodbye!");
        scanner.close();
    }
}