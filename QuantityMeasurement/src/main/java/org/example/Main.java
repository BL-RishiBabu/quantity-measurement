package org.example;
import java.util.Scanner;

public class Main {
    static void main() {
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController controller = new QuantityMeasurementController(service);

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== UC15 Architecture Multi-Tier System Dashboard ===");

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

                System.out.print("Enter first value: ");
                double val1 = scanner.nextDouble();
                System.out.print("Enter first unit string " + unitOptions + ": ");
                String unitStr1 = scanner.next();

                QuantityDTO d1 = new QuantityDTO(val1, unitStr1);

                if (opChoice == 5) {
                    System.out.print("Enter targeted destination unit: ");
                    String targetUnit = scanner.next();
                    QuantityDTO res = controller.performConversion(d1, targetUnit);
                    System.out.println("\n>>> [Conversion Output Result]: " + res);
                    continue;
                }

                System.out.print("Enter second value: ");
                double val2 = scanner.nextDouble();
                System.out.print("Enter second unit string " + unitOptions + ": ");
                String unitStr2 = scanner.next();

                QuantityDTO d2 = new QuantityDTO(val2, unitStr2);

                switch (opChoice) {
                    case 1 -> System.out.println("\n>>> [Equality Check Output]: " + controller.performComparison(d1, d2));
                    case 2, 3 -> {
                        System.out.print("Enter explicit target unit or type 'DEFAULT' (uses first unit format): ");
                        String tInput = scanner.next();
                        String actualTarget = tInput.equalsIgnoreCase("DEFAULT") ? d1.getUnit() : tInput;

                        if (opChoice == 2) System.out.println("\n>>> [Addition Output]: " + controller.performAddition(d1, d2, actualTarget));
                        else System.out.println("\n>>> [Subtraction Output]: " + controller.performSubtraction(d1, d2, actualTarget));
                    }
                    case 4 -> System.out.println("\n>>> [Division Scalar Ratio Output]: " + controller.performDivision(d1, d2));
                    default -> System.out.println("❌ Operational choice not recognized.");
                }

            } catch (QuantityMeasurementException | UnsupportedOperationException e) {
                System.out.println("\n>>> Caught Expected Boundary Violation Message: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Layer processing error: " + e.getMessage());
                scanner.nextLine();
            }
        }
        System.out.println("Application execution loops completed cleanly.");
        scanner.close();
    }
}