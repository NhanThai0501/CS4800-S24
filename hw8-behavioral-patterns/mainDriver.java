import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

class Character {
    
    private char character;
    private CharacterProperties characterProperty;

    public Character(char character, CharacterProperties characterProperty) {
        this.character = character;
        this.characterProperty = characterProperty;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public void setCharacterProperty(CharacterProperties characterProperty) {
        this.characterProperty = characterProperty;
    }

    public char getCharacter() {
        return character;
    }

    public String getFont() {
        return characterProperty.getFont();
    }

    public String getColor() {
        return characterProperty.getColor();
    }

    public int getSize() {
        return characterProperty.getSize();
    }
}

class CharacterProperties {
    
    private final String font;
    private final String color;
    private final int size;

    public CharacterProperties(String font, String color, int size) {
        this.font = font;
        this.color = color;
        this.size = size;
    }

    public String getFont() {
        return font;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }
}


// Flyweight factory that creates and manages CharacterProperties objects
// Reuse CharacterProperties that have the same properties as other characters
class CharacterPropertiesFactory {

    private final Map<String, CharacterProperties> flyweights = new HashMap<>();

    // Add CharacterProperty to the Map if not already inside.
    // If it is inside, retrieve it without creating a duplicate.
    public CharacterProperties setAndRetrieveFlyweightCharacterProperties(String font, String color, int size) {
        String key = font + size + color;
        if (!flyweights.containsKey(key)) {
            CharacterProperties properties = new CharacterProperties(font, color, size);
            flyweights.put(key, properties);
        }
        return flyweights.get(key);
    }

    // Not required - to show the number of flyweight Objects
    public int sizeOfMap() {
        return flyweights.size();
    }

    // Deleting would require checking that no other character in the document uses
    // the character property that is set for removal.
}



// Document object that stores a list of characters and allows the user to create, edit, and save documents
class Document {
    private final List<Character> characters = new ArrayList<>();
    private final CharacterPropertiesFactory propertiesFactory = new CharacterPropertiesFactory();

    // Adds a character with specified properties to the document
    public void addCharacter(char c, String font, String color, int size) {
        CharacterProperties flyweightCharacterProperties = propertiesFactory.setAndRetrieveFlyweightCharacterProperties(font, color, size);
        Character character = new Character(c, flyweightCharacterProperties); // character property is really a flyweight object
        characters.add(character);
    }

    // Edits the properties of a character at a specific index
    public void editCharacterProperties(int index, String font, String color, int size) {
        Character character = characters.get(index);
        CharacterProperties flyweightCharacterProperties = propertiesFactory.setAndRetrieveFlyweightCharacterProperties(font, color, size);
        character.setCharacterProperty(flyweightCharacterProperties);
    }

    // Saves the document to a file
    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Character character : characters) {
            writer.write(character.getCharacter() + "," + character.getFont() + "," + character.getColor() + "," + character.getSize() + "\n");
        }
        writer.close();
        System.out.println("The number of flyweight objects is: " + propertiesFactory.sizeOfMap());
    }

    // Loads the document from a file
    public void loadFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            char c = parts[0].charAt(0);
            String font = parts[1];
            String color = parts[2];
            int size = Integer.parseInt(parts[3]);
            this.addCharacter(c, font, color, size);
        }
    }
}
public class mainDriver {
    public static void main(String[] args) throws IOException {
        System.out.println("Create a new Document");
        Document testDocument = new Document();
    
        // Adding characters to the document
        testDocument.addCharacter('H', "Arial", "Black", 16);
        testDocument.addCharacter('e', "Arial", "Black", 14);
        testDocument.addCharacter('l', "Arial", "Black", 14);
        testDocument.addCharacter('l', "Arial", "Black", 14);
        testDocument.addCharacter('o', "Arial", "Black", 14);
        testDocument.addCharacter('W', "Arial", "Black", 16);
        testDocument.addCharacter('o', "Arial", "Black", 14);
        testDocument.addCharacter('r', "Arial", "Black", 14);
        testDocument.addCharacter('l', "Arial", "Black", 14);
        testDocument.addCharacter('d', "Arial", "Black", 14);
        testDocument.addCharacter('C', "Verdana", "Blue", 18);
        testDocument.addCharacter('S', "Verdana", "Blue", 18);
        testDocument.addCharacter('5', "Verdana", "Red", 12);
        testDocument.addCharacter('8', "Verdana", "Red", 12);
        testDocument.addCharacter('0', "Verdana", "Red", 12);
        testDocument.addCharacter('0', "Verdana", "Red", 12);
    
        // Save document to a file
        try {
            testDocument.saveToFile("test.txt");
            System.out.println("Saved Document as test.txt");
        } catch (IOException e) {
            System.out.println("Failed on Save");
        }
    
        // Load document from a file
        Document readDocumentVerify = new Document();
        try {
            readDocumentVerify.loadFromFile("test.txt");
            System.out.println("Loaded Document from test.txt");
        } catch (IOException e) {
            System.out.println("Failed on Load");
        }
    
        // Edit properties of specific characters
        readDocumentVerify.editCharacterProperties(0, "Calibri", "Blue", 99); // Modify 'H'
        readDocumentVerify.editCharacterProperties(5, "Calibri", "Blue", 99); // Modify 'W'
    
        // Save modified document to a new file
        try {
            readDocumentVerify.saveToFile("verify.txt");
            System.out.println("Saved Modified Document as verify.txt");
        } catch (IOException e) {
            System.out.println("Failed on Verify Save");
        }
    }

}


