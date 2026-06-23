import org.example.Main;
//import org.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void givenSameFeetValues_WhenCompared_ShouldReturnTrue() {
        // Given
        Main.Feet feet1 = new Main.Feet(1.0);
        Main.Feet feet2 = new Main.Feet(1.0);

        // When & Then
        assertTrue(feet1.equals(feet2), "Two Feet objects with the same value (1.0 ft) should be equal.");
    }

    @Test
    void givenDifferentFeetValues_WhenCompared_ShouldReturnFalse() {
        // Given
        Main.Feet feet1 = new Main.Feet(1.0);
        Main.Feet feet2 = new Main.Feet(2.0);

        // When & Then
        assertFalse(feet1.equals(feet2), "Feet objects with different values (1.0 ft and 2.0 ft) should not be equal.");
    }

    @Test
    void givenFeetValueAndNull_WhenCompared_ShouldReturnFalse() {
        // Given
        Main.Feet feet = new Main.Feet(1.0);

        // When & Then
        assertFalse(feet.equals(null), "Comparing a Feet object with null must return false.");
    }

    @Test
    void givenFeetValueAndDifferentType_WhenCompared_ShouldReturnFalse() {
        // Given
        Main.Feet feet = new Main.Feet(1.0);
        String nonNumericInput = "1.0 ft"; // Different type

        // When & Then
        assertFalse(feet.equals(nonNumericInput), "Comparing a Feet object with a different type should return false.");
    }

    @Test
    void givenSameFeetReference_WhenCompared_ShouldReturnTrue() {
        // Given (Reflexive property)
        Main.Feet feet = new Main.Feet(1.0);

        // When & Then
        assertTrue(feet.equals(feet), "A Feet object compared with itself must return true.");
    }
}