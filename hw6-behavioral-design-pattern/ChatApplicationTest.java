
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class ChatApplicationTests {

    private ChatServer server;
    private User alice;
    private User bob;
    private User charlie;

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
        assertFalse(messages.isEmpty(), "Messages list should not be empty.");
        Message lastMessage = messages.get(0);

        assertEquals("Alice", lastMessage.getSender(), "Sender should be Alice");
        assertTrue(lastMessage.getRecipients().contains("Bob"), "Recipients should contain Bob");
        assertEquals("Hello, Bob!", lastMessage.getContent(), "Message content should match.");
    }

    @Test
    void testUndoLastMessage() {
        alice.sendMessage("Hello, Bob!", Arrays.asList("Bob"));
        assertFalse(alice.getChatHistory().getMessages().isEmpty(), "Chat history should not be empty before undo.");

        alice.undoLastMessage();
        assertTrue(alice.getChatHistory().getMessages().isEmpty(), "Chat history should be empty after undo.");
    }

    @Test
    void testBlockUser() {
        server.blockUser("Alice", "Bob");

        bob.sendMessage("Hi Alice!", Arrays.asList("Alice"));
        assertTrue(alice.getChatHistory().getMessages().isEmpty(), "Alice should not receive messages from Bob as he is blocked.");
    }

//    @Test
//    void testSendAndReceiveMessage() {
//        charlie.sendMessage("Hi Alice and Bob!", Arrays.asList("Alice", "Bob"));
//        assertFalse(alice.getChatHistory().getMessages().isEmpty(), "Alice should have received the message.");
//        assertFalse(bob.getChatHistory().getMessages().isEmpty(), "Bob should have received the message.");
//
//        Message alicesMessage = alice.getChatHistory().getMessages().get(0);
//        Message bobsMessage = bob.getChatHistory().getMessages().get(0);
//
//        assertEquals("Charlie", alicesMessage.getSender(), "Charlie should be the sender in Alice's message.");
//        assertEquals("Charlie", bobsMessage.getSender(), "Charlie should be the sender in Bob's message.");
//        assertEquals("Hi Alice and Bob!", alicesMessage.getContent(), "The message content should match.");
//        assertEquals("Hi Alice and Bob!", bobsMessage.getContent(), "The message content should match.");
//    }

    @Test
    void testMessageHistoryAfterBlocking() {
        server.blockUser("Bob", "Charlie");

        charlie.sendMessage("Hey Bob, can you read this?", Arrays.asList("Bob"));
        assertTrue(bob.getChatHistory().getMessages().isEmpty(), "Bob should not receive messages from Charlie as Charlie is blocked.");
    }
}
