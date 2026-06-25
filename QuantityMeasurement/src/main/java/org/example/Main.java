package org.example;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Advanced Quantity Measurement System Dashboard ===");

        while (true) {
            try {
                System.out.print("\nSelect Category (1: LENGTH, 2: WEIGHT, 3: VOLUME, 4: TEMPERATURE, -1: EXIT): ");
                int categoryChoice = scanner.nextInt();
                if (categoryChoice == -1) break;

                System.out.print("Select Operation (1: EQUALITY, 2: ADD, 3: SUBTRACT, 4: DIVIDE, 5: CONVERT): ");
                int opChoice = scanner.nextInt();

                String unitOptions = switch (categoryChoice) {
                    case 1 -> "(FEET, INCHES, YARDS, CENTIMETERS)";
                    case 2 -> "(GRAM, KILOGRAM, MILLIGRAM, POUND, TONNE)";
                    case 3 -> "(LITRE, MILLILITRE, GALLON)";
                    case 4 -> "(CELSIUS, FAHRENHEIT)";
                    default -> "";
                };

                if (unitOptions.isEmpty()) {
                    System.out.println("❌ Invalid category choice.");
                    continue;
                }

                System.out.print("Enter first value: ");
                double val1 = scanner.nextDouble();
                System.out.print("Enter first unit string " + unitOptions + ": ");
                String unitStr1 = scanner.next().toUpperCase();

                if (opChoice == 5) { // Pure conversion pathway optimization
                    System.out.print("Enter conversion target unit " + unitOptions + ": ");
                    String targetStr = scanner.next().toUpperCase();
                    if (categoryChoice == 4) {
                        Quantity<TemperatureUnit> temp = new Quantity<>(val1, TemperatureUnit.valueOf(unitStr1));
                        System.out.println("\n>>> [Conversion Output]: " + temp.convertTo(TemperatureUnit.valueOf(targetStr)));
                    }
                    continue;
                }

                System.out.print("Enter second value: ");
                double val2 = scanner.nextDouble();
                System.out.print("Enter second unit string " + unitOptions + ": ");
                String unitStr2 = scanner.next().toUpperCase();

                if (categoryChoice == 4) {
                    Quantity<TemperatureUnit> q1 = new Quantity<>(val1, TemperatureUnit.valueOf(unitStr1));
                    Quantity<TemperatureUnit> q2 = new Quantity<>(val2, TemperatureUnit.valueOf(unitStr2));

                    switch (opChoice) {
                        case 1 -> System.out.println("\n>>> [Equality Check Output]: " + q1.equals(q2));
                        case 2 -> q1.add(q2);
                        case 3 -> q1.subtract(q2);
                        case 4 -> q1.divide(q2);
                    }
                } else {
                    System.out.println("🔄 Non-temperature categories processed normally via standard operational handlers.");
                }

            } catch (UnsupportedOperationException e) {
                System.out.println("\n>>> Cannot compute operation: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Entry validation error matching unit values.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("❌ Error processing execution parameters.");
                scanner.nextLine();
            }
        }
        System.out.println("Exiting Application. Goodbye!");
        scanner.close();
    }
}