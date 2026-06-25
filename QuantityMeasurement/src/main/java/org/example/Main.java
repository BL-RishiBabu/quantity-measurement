package org.example;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Multi-Category Quantity Measurement CLI ===");

        while (true) {
            try {
                System.out.print("\nSelect Category (1: LENGTH, 2: WEIGHT, 3: VOLUME, -1: EXIT): ");
                int category = scanner.nextInt();
                if (category == -1) break;
                if (category == 1) handleLengthOperations(scanner);
                else if (category == 2) handleWeightOperations(scanner);
                else if (category == 3) handleVolumeOperations(scanner);
                else System.out.println("Invalid selection. Please choose 1, 2, 3, or -1.");
            } catch (Exception e) {
                System.out.println("Error processing operation. Checking inputs...");
                scanner.nextLine();
            }
        }
        System.out.println("Exiting Application. Goodbye!");
        scanner.close();
    }

    public enum LengthUnit implements IMeasurable {
        INCHES(1.0), FEET(12.0), YARDS(36.0), CENTIMETERS(0.393701);
        private final double factor;
        LengthUnit(double f) { this.factor = f; }
        public double getConversionFactor() { return factor; }
        public double convertToBaseUnit(double v) { return v * factor; }
        public double convertFromBaseUnit(double b) { return b / factor; }
        public String getUnitName() { return name(); }
    }

    public enum WeightUnit implements IMeasurable {
        GRAM(1.0), KILOGRAM(1000.0), MILLIGRAM(0.001), POUND(453.592), TONNE(1000000.0);
        private final double factor;
        WeightUnit(double f) { this.factor = f; }
        public double getConversionFactor() { return factor; }
        public double convertToBaseUnit(double v) { return v * factor; }
        public double convertFromBaseUnit(double b) { return b / factor; }
        public String getUnitName() { return name(); }
    }

    private static void handleLengthOperations(Scanner scanner) {
        System.out.print("Enter first length value: ");
        double val1 = scanner.nextDouble();
        System.out.print("Enter first unit (FEET, INCHES, YARDS, CENTIMETERS): ");
        LengthUnit unit1 = LengthUnit.valueOf(scanner.next().toUpperCase());
        Quantity<LengthUnit> length1 = new Quantity<>(val1, unit1);
        System.out.print("Enter second length value: ");
        double val2 = scanner.nextDouble();
        System.out.print("Enter second unit: ");
        LengthUnit unit2 = LengthUnit.valueOf(scanner.next().toUpperCase());
        Quantity<LengthUnit> length2 = new Quantity<>(val2, unit2);
        System.out.print("Enter explicit target unit (or type 'DEFAULT'): ");
        String targetInput = scanner.next().toUpperCase();
        Quantity<LengthUnit> result = targetInput.equals("DEFAULT") ? length1.add(length2) : length1.add(length2, LengthUnit.valueOf(targetInput));
        System.out.println("\n[Length Sum Result]: " + result);
    }

    private static void handleWeightOperations(Scanner scanner) {
        System.out.print("Enter first weight value: ");
        double val1 = scanner.nextDouble();
        System.out.print("Enter first unit (MILLIGRAM, GRAM, KILOGRAM, POUND, TONNE): ");
        WeightUnit unit1 = WeightUnit.valueOf(scanner.next().toUpperCase());
        Quantity<WeightUnit> weight1 = new Quantity<>(val1, unit1);
        System.out.print("Enter second weight value: ");
        double val2 = scanner.nextDouble();
        System.out.print("Enter second unit: ");
        WeightUnit unit2 = WeightUnit.valueOf(scanner.next().toUpperCase());
        Quantity<WeightUnit> weight2 = new Quantity<>(val2, unit2);
        System.out.print("Enter explicit target unit (or type 'DEFAULT'): ");
        String targetInput = scanner.next().toUpperCase();
        Quantity<WeightUnit> result = targetInput.equals("DEFAULT") ? weight1.add(weight2) : weight1.add(weight2, WeightUnit.valueOf(targetInput));
        System.out.println("\n[Weight Sum Result]: " + result);
    }

    private static void handleVolumeOperations(Scanner scanner) {
        System.out.print("Enter first volume value: ");
        double val1 = scanner.nextDouble();
        System.out.print("Enter first unit (LITRE, MILLILITRE, GALLON): ");
        VolumeUnit unit1 = VolumeUnit.valueOf(scanner.next().toUpperCase());
        Quantity<VolumeUnit> volume1 = new Quantity<>(val1, unit1);

        System.out.print("Enter second volume value: ");
        double val2 = scanner.nextDouble();
        System.out.print("Enter second unit: ");
        VolumeUnit unit2 = VolumeUnit.valueOf(scanner.next().toUpperCase());
        Quantity<VolumeUnit> volume2 = new Quantity<>(val2, unit2);

        System.out.print("Enter explicit target unit (or type 'DEFAULT'): ");
        String targetInput = scanner.next().toUpperCase();

        Quantity<VolumeUnit> result = targetInput.equals("DEFAULT") ? volume1.add(volume2) : volume1.add(volume2, VolumeUnit.valueOf(targetInput));
        System.out.println("\n[Volume Sum Result]: " + result);
    }
}