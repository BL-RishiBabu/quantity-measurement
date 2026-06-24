package org.example;
import java.util.Scanner;

public class Main {
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        if (length1 == null || length2 == null) return false;
        return length1.equals(length2);
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2) {
        if (length1 == null) {
            throw new IllegalArgumentException("First operand cannot be null");
        }
        return length1.add(length2);
    }

    static void main() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("\nEnter first value (or -1 to exit): ");
                double val1 = scanner.nextDouble();
                if (val1 == -1) break;
                System.out.print("Enter first unit (FEET, INCHES, YARDS, CENTIMETERS): ");
                Length.LengthUnit unit1 = Length.LengthUnit.valueOf(scanner.next().toUpperCase());
                Length length1 = new Length(val1, unit1);

                System.out.print("Enter second value: ");
                double val2 = scanner.nextDouble();
                System.out.print("Enter second unit: ");
                Length.LengthUnit unit2 = Length.LengthUnit.valueOf(scanner.next().toUpperCase());
                Length length2 = new Length(val2, unit2);

                Length result = demonstrateLengthAddition(length1, length2);
                System.out.println("\n[Result in unit of first operand]: " + result);

            } catch (IllegalArgumentException e) {
                System.out.println("Error: Invalid Unit or Value entered.");
            }
        }
    }
}