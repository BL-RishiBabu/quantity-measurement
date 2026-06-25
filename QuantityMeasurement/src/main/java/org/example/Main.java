package org.example;
import java.util.Scanner;

public class Main {

    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1 != null && quantity1.equals(quantity2);
    }

    public static <U extends IMeasurable> double demonstrateDivision(Quantity<U> quantity1, Quantity<U> quantity2) {
        if (quantity1 == null) throw new IllegalArgumentException("First quantity cannot be null");
        return quantity1.divide(quantity2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Advanced Quantity Measurement System Dashboard ===");

        while (true) {
            try {
                System.out.print("\nSelect Category (1: LENGTH, 2: WEIGHT, 3: VOLUME, -1: EXIT): ");
                int categoryChoice = scanner.nextInt();
                if (categoryChoice == -1) break;

                System.out.print("Select Operation (1: EQUALITY, 2: ADD, 3: SUBTRACT, 4: DIVIDE): ");
                int opChoice = scanner.nextInt();

                // Define prompt string helpers dynamically based on category
                String unitOptions = "";
                if (categoryChoice == 1) unitOptions = "(FEET, INCHES, YARDS, CENTIMETERS)";
                else if (categoryChoice == 2) unitOptions = "(GRAM, KILOGRAM, MILLIGRAM, POUND, TONNE)";
                else if (categoryChoice == 3) unitOptions = "(LITRE, MILLILITRE, GALLON)";
                else {
                    System.out.println("❌ Invalid category selection. Choose 1, 2, or 3.");
                    continue;
                }

                System.out.print("Enter first value: ");
                double val1 = scanner.nextDouble();
                System.out.print("Enter first unit string " + unitOptions + ": ");
                String unitStr1 = scanner.next().toUpperCase();

                System.out.print("Enter second value: ");
                double val2 = scanner.nextDouble();
                System.out.print("Enter second unit string " + unitOptions + ": ");
                String unitStr2 = scanner.next().toUpperCase();

                String targetUnitStr = "DEFAULT";
                if (opChoice == 2 || opChoice == 3) {
                    System.out.print("Enter target unit for the result " + unitOptions + " or type 'DEFAULT': ");
                    targetUnitStr = scanner.next().toUpperCase();
                }

                if (categoryChoice == 1) {
                    LengthUnit u1 = LengthUnit.valueOf(unitStr1);
                    LengthUnit u2 = LengthUnit.valueOf(unitStr2);
                    Quantity<LengthUnit> q1 = new Quantity<>(val1, u1);
                    Quantity<LengthUnit> q2 = new Quantity<>(val2, u2);

                    if (opChoice == 2) {
                        Quantity<LengthUnit> res = targetUnitStr.equals("DEFAULT") ? q1.add(q2) : q1.add(q2, LengthUnit.valueOf(targetUnitStr));
                        System.out.println("\n>>> [Addition Output]: " + res);
                    } else if (opChoice == 3) {
                        Quantity<LengthUnit> res = targetUnitStr.equals("DEFAULT") ? q1.subtract(q2) : q1.subtract(q2, LengthUnit.valueOf(targetUnitStr));
                        System.out.println("\n>>> [Subtraction Output]: " + res);
                    }
                    else if (opChoice == 1) System.out.println("\n>>> [Equality Check Output]: " + demonstrateEquality(q1, q2));
                    else if (opChoice == 4) System.out.println("\n>>> [Division Scalar Ratio Output]: " + demonstrateDivision(q1, q2));
                } else if (categoryChoice == 2) {
                    WeightUnit u1 = WeightUnit.valueOf(unitStr1);
                    WeightUnit u2 = WeightUnit.valueOf(unitStr2);
                    Quantity<WeightUnit> q1 = new Quantity<>(val1, u1);
                    Quantity<WeightUnit> q2 = new Quantity<>(val2, u2);

                    if (opChoice == 2) {
                        Quantity<WeightUnit> res = targetUnitStr.equals("DEFAULT") ? q1.add(q2) : q1.add(q2, WeightUnit.valueOf(targetUnitStr));
                        System.out.println("\n>>> [Addition Output]: " + res);
                    } else if (opChoice == 3) {
                        Quantity<WeightUnit> res = targetUnitStr.equals("DEFAULT") ? q1.subtract(q2) : q1.subtract(q2, WeightUnit.valueOf(targetUnitStr));
                        System.out.println("\n>>> [Subtraction Output]: " + res);
                    }
                    else if (opChoice == 1) System.out.println("\n>>> [Equality Check Output]: " + demonstrateEquality(q1, q2));
                    else if (opChoice == 4) System.out.println("\n>>> [Division Scalar Ratio Output]: " + demonstrateDivision(q1, q2));

                } else if (categoryChoice == 3) {
                    VolumeUnit u1 = VolumeUnit.valueOf(unitStr1);
                    VolumeUnit u2 = VolumeUnit.valueOf(unitStr2);
                    Quantity<VolumeUnit> q1 = new Quantity<>(val1, u1);
                    Quantity<VolumeUnit> q2 = new Quantity<>(val2, u2);

                    if (opChoice == 2) {
                        Quantity<VolumeUnit> res = targetUnitStr.equals("DEFAULT") ? q1.add(q2) : q1.add(q2, VolumeUnit.valueOf(targetUnitStr));
                        System.out.println("\n>>> [Addition Output]: " + res);
                    } else if (opChoice == 3) {
                        Quantity<VolumeUnit> res = targetUnitStr.equals("DEFAULT") ? q1.subtract(q2) : q1.subtract(q2, VolumeUnit.valueOf(targetUnitStr));
                        System.out.println("\n>>> [Subtraction Output]: " + res);
                    } else if (opChoice == 1) System.out.println("\n>>> [Equality Check Output]: " + demonstrateEquality(q1, q2));
                    else if (opChoice == 4) System.out.println("\n>>> [Division Scalar Ratio Output]: " + demonstrateDivision(q1, q2));
                }

            } catch (IllegalArgumentException e) {
                System.out.println("❌ Enum string spelling verification mapping failure. Match spelling parameters exactly.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("❌ General input parsing configuration failure.");
                scanner.nextLine();
            }
        }
        System.out.println("Exiting Application. Goodbye!");
        scanner.close();
    }
}