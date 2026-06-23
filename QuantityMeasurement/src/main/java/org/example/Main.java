package org.example;

public class Main {

    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || this.getClass() != obj.getClass()) {
                return false;
            }

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }
    static void main() {
        Feet firstValue = new Feet(1.0);
        Feet secondValue = new Feet(1.0);

        boolean isEqual = firstValue.equals(secondValue);

        System.out.println("Input: " + firstValue.value + " ft and " + secondValue.value + " ft");
        System.out.println("Output: Equal (" + isEqual + ")");
    }
}
