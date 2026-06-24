package org.example;
import java.util.Scanner;

public class Main {
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        if (length1 == null || length2 == null) return false;
        boolean result = length1.equals(length2);
        System.out.println("The two length measurements are " + (result ? "equal." : "not equal."));
        return result;
    }

    public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1, double value2, Length.LengthUnit unit2) {
        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);
        return demonstrateLengthEquality(length1, length2);
    }

    public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, Length.LengthUnit toUnit) {
        Length sourceLength = new Length(value, fromUnit);
        return sourceLength.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {
        if (length == null) throw new IllegalArgumentException("Source length instance cannot be null");
        return length.convertTo(toUnit);
    }

    static void main() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("\nEnter measurement value (or -1 to terminate): ");
                double val = scanner.nextDouble();
                if (val == -1) break;

                System.out.print("Enter source unit (FEET/INCHES/YARDS/CENTIMETERS): ");
                Length.LengthUnit fromUnit = Length.LengthUnit.valueOf(scanner.next().toUpperCase());

                System.out.print("Enter targeted destination conversion unit: ");
                Length.LengthUnit toUnit = Length.LengthUnit.valueOf(scanner.next().toUpperCase());

                Length convertedResult = demonstrateLengthConversion(val, fromUnit, toUnit);
                System.out.println("Transformation mapping details -> Source: [" + val + " " + fromUnit + "] converted into Target: [" + convertedResult + "]");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid processing entry. Match spelling parameters explicitly.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("❌ Input validation parsing failure.");
                scanner.nextLine();
            }
        }
        System.out.println("Session complete.");
        scanner.close();
    }
}