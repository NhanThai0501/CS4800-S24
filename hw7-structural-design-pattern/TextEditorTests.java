import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class TextEditorTests {

    @Test
    void testCharacterPropertyCreation() {
        // Testing unique property creation
        CharacterProperty prop1 = CharacterFactory.getProperty("Arial", 12, "Blue");
        CharacterProperty prop2 = CharacterFactory.getProperty("Arial", 12, "Blue");

        // Should be the same instance due to Flyweight Pattern
        assertSame(prop1, prop2, "CharacterProperties with same attributes should be the same instance.");
    }

    @Test
    void testCharacterCreationAndDisplay() {
        CharacterProperty prop = CharacterFactory.getProperty("Calibri", 14, "Red");
        Character character = new Character('H', prop);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        character.display();

        String expectedOutput = "'e' [Font: Calibri, Size: 14, Color: Red]\n";
        assertTrue(outContent.toString().contains(expectedOutput), "Output should contain the character with its properties.");
    }

    @Test
    void testCharacterSaveFormat() {
        CharacterProperty prop = CharacterFactory.getProperty("Verdana", 16, "Black");
        Character character = new Character('A', prop);

        String expectedFormat = "A,Verdana,16,Black";
        assertEquals(expectedFormat, character.saveFormat(), "Save format should match expected output.");
    }

    @Test
    void testMultipleCharacterProperties() {
        // Test creating multiple properties to ensure they are handled correctly
        CharacterProperty prop1 = CharacterFactory.getProperty("Arial", 12, "Blue");
        CharacterProperty prop2 = CharacterFactory.getProperty("Arial", 14, "Black");
        CharacterProperty prop3 = CharacterFactory.getProperty("Verdana", 16, "Black");

        assertNotSame(prop1, prop2, "Different properties should not be the same instance.");
        assertNotSame(prop1, prop3, "Different properties should not be the same instance.");
        assertNotSame(prop2, prop3, "Different properties should not be the same instance.");

        assertEquals("Arial", prop1.getFont());
        assertEquals(12, prop1.getSize());
        assertEquals("Blue", prop1.getColor());

        assertEquals("Arial", prop2.getFont());
        assertEquals(14, prop2.getSize());
        assertEquals("Black", prop2.getColor());

        assertEquals("Verdana", prop3.getFont());
        assertEquals(16, prop3.getSize());
        assertEquals("Black", prop3.getColor());
    }
}
