import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CharacterProperty {
    private String font;
    private int size;
    private String color;

    public CharacterProperty(String font, int size, String color) {
        this.font = font;
        this.size = size;
        this.color = color;
    }

    public String getFont() {
        return font;
    }

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Font: " + font + ", Size: " + size + ", Color: " + color;
    }
}

class CharacterFactory {
    private static final Map<String, CharacterProperty> properties = new HashMap<>();

    public static CharacterProperty getProperty(String font, int size, String color) {
        String key = font + "-" + size + "-" + color;
        if (!properties.containsKey(key)) {
            properties.put(key, new CharacterProperty(font, size, color));
            System.out.println("Creating new CharacterProperty: " + key);
        }
        return properties.get(key);
    }
}

class Character {
    private char character;
    private CharacterProperty property;

    public Character(char character, CharacterProperty property) {
        this.character = character;
        this.property = property;
    }

    public void display() {
        System.out.println("'" + character + "' [" + property + "]");
    }

    public String saveFormat() {
        return character + "," + property.getFont() + "," + property.getSize() + "," + property.getColor();
    }
}

class Document {
    private List<Character> characters = new ArrayList<>();

    public void addCharacter(char c, CharacterProperty property) {
        characters.add(new Character(c, property));
    }

    public void display() {
        characters.forEach(Character::display);
    }

    public void saveToFile(String fileName) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(fileName))) {
            for (Character character : characters) {
                out.println(character.saveFormat());
            }
            System.out.println("Document saved to " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Error saving document: " + e.getMessage());
        }
    }

    public static Document loadFromFile(String fileName) {
        Document document = new Document();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    char c = parts[0].charAt(0);
                    String font = parts[1];
                    int size = Integer.parseInt(parts[2]);
                    String color = parts[3];
                    CharacterProperty property = CharacterFactory.getProperty(font, size, color);
                    document.addCharacter(c, property);
                }
            }
            System.out.println("Document loaded from " + fileName);
        } catch (IOException e) {
            System.out.println("Error loading document: " + e.getMessage());
        }
        return document;
    }
}

public class TextEditor {
    public static void main(String[] args) {
        Document document = new Document();

        CharacterProperty prop1 = CharacterFactory.getProperty("Arial", 12, "Blue");
        CharacterProperty prop2 = CharacterFactory.getProperty("Calibri", 14, "Red");
        CharacterProperty prop3 = CharacterFactory.getProperty("Verdana", 16, "Black");
        CharacterProperty prop4 = CharacterFactory.getProperty("Arial", 12, "Black");

        // Add characters with properties
        document.addCharacter('H', prop1);
        document.addCharacter('e', prop2);
        document.addCharacter('l', prop3);
        document.addCharacter('l', prop3);
        document.addCharacter('o', prop4);
        document.addCharacter('W', prop1);
        document.addCharacter('o', prop4);
        document.addCharacter('r', prop2);
        document.addCharacter('l', prop3);
        document.addCharacter('d', prop3);
        document.addCharacter('C', prop1);
        document.addCharacter('S', prop2);
        document.addCharacter('5', prop4);
        document.addCharacter('8', prop3);
        document.addCharacter('0', prop1);

        // Display the document
        document.display();

        // Save to file
        document.saveToFile("document.txt");

        // Load from file
        Document loadedDocument = Document.loadFromFile("document.txt");
        System.out.println("\nLoaded Document:");
        loadedDocument.display();
    }
}
