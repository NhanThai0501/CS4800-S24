import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SnackTest {
    private Snack snack;

    @BeforeEach
    public void setUp() {
        snack = new Snack("Coke", 1.25, 10);
    }

    @Test
    public void testGetName() {
        assertEquals("Coke", snack.getName(), "Snack name should be Coke");
    }

    @Test
    public void testGetPrice() {
        assertEquals(1.25, snack.getPrice(), "Snack price should be 1.25");
    }

    @Test
    public void testGetQuantity() {
        assertEquals(10, snack.getQuantity(), "Initial quantity should be 10");
    }

    @Test
    public void testDecrementQuantity() {
        snack.decrementQuantity();
        assertEquals(9, snack.getQuantity(), "Quantity should decrease by 1");
    }

    @Test
    public void testDecrementQuantityBelowZero() {
        Snack zeroSnack = new Snack("KitKat", 1.00, 0);
        zeroSnack.decrementQuantity();
        assertEquals(0, zeroSnack.getQuantity(), "Quantity should remain 0");
    }
}
