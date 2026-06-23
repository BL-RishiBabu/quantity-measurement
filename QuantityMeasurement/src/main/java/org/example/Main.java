package org.example;
import java.util.Scanner;

public class Main {

    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static boolean checkFeetEquality(double first, double second) {
        Feet feet1 = new Feet(first);
        Feet feet2 = new Feet(second);
        return feet1.equals(feet2);
    }

    public static boolean checkInchesEquality(double first, double second) {
        Inches inch1 = new Inches(first);
        Inches inch2 = new Inches(second);
        return inch1.equals(inch2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first feet value : ");
        double feet1 = sc.nextDouble();
        System.out.print("Enter second feet value: ");
        double feet2 = sc.nextDouble();
        System.out.println("Input: " + feet1 + " ft and " + feet2 + " ft");
        System.out.println("Output: Equal (" + checkFeetEquality(feet1, feet2) + ")\n");

        System.out.print("Enter first inches value : ");
        double inch1 = sc.nextDouble();
        System.out.print("Enter second inches value: ");
        double inch2 = sc.nextDouble();
        System.out.println("Input: " + inch1 + " inch and " + inch2 + " inch");
        System.out.println("Output: Equal (" + checkInchesEquality(inch1, inch2) + ")");
    }
}