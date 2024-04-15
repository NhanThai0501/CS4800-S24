import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class ChatApplication2Tests {

    private ChatServer server;
    private User alice, bob, charlie;

    @BeforeEach
    void setUp() {
        server = new ChatServer();
        alice = new User("Alice", server);
        bob = new User("Bob", server);
        charlie = new User("Charlie", server);
        server.registerUser(alice);
        server.registerUser(bob);
        server.registerUser(charlie);
    }

    @Test
    void testSendMessage() {
        alice.sendMessage("Hello, Bob!", Arrays.asList("Bob"));
        List<Message> messages = alice.getChatHistory().getMessages();
        assertFalse(messages.isEmpty(), "Messages list should not be empty after sending a message.");
        assertEquals(1, messages.size(), "Chat history should contain exactly one message.");
        Message sentMessage = messages.get(0);
        assertEquals("Alice", sentMessage.getSender(), "Sender should be Alice.");
        assertTrue(sentMessage.getRecipients().contains("Bob"), "Bob should be the recipient.");
        assertEquals("Hello, Bob!", sentMessage.getContent(), "Message content should match the sent message.");
    }

    @Test
    void testUndoLastMessage() {
        alice.sendMessage("Hello, Bob!", Arrays.asList("Bob"));
        assertFalse(alice.getChatHistory().getMessages().isEmpty(), "Chat history should not be empty after sending a message.");
        alice.undoLastMessage();
        assertTrue(alice.getChatHistory().getMessages().isEmpty(), "Chat history should be empty after undoing the message.");
    }

    @Test
    void testBlockUser() {
        server.blockUser("Alice", "Bob");
        bob.sendMessage("Hi Alice!", Arrays.asList("Alice"));
        assertTrue(alice.getChatHistory().getMessages().isEmpty(), "Alice should not receive messages from Bob as he is blocked.");
    }

    @Test
    void testViewChatHistory() {
        alice.sendMessage("Hey everyone!", Arrays.asList("Bob", "Charlie"));
        alice.sendMessage("How are you guys doing?", Arrays.asList("Bob", "Charlie"));
        List<Message> messages = alice.getChatHistory().getMessages();
        assertEquals(2, messages.size(), "There should be two messages in the chat history.");
        assertEquals("Hey everyone!", messages.get(0).getContent(), "First message content should match.");
        assertEquals("How are you guys doing?", messages.get(1).getContent(), "Second message content should match.");
    }

    @Test
    void testChatHistoryOrder() {
        alice.sendMessage("First message", Arrays.asList("Bob"));
        alice.sendMessage("Second message", Arrays.asList("Bob"));
        List<Message> messages = alice.getChatHistory().getMessages();
        assertEquals("First message", messages.get(0).getContent(), "The first sent message should be first in the chat history.");
        assertEquals("Second message", messages.get(1).getContent(), "The second sent message should be second in the chat history.");
    }
}
