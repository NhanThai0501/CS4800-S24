import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class Message {
   private String sender;
   private List<String> recipients;
   private String timestamp;
   private String content;

   public Message(String sender, List<String> recipients, String timestamp, String content) {
       this.sender = sender;
       this.recipients = recipients;
       this.timestamp = timestamp;
       this.content = content;
   }

   public String getSender() {
       return sender;
   }

   public List<String> getRecipients() {
       return recipients;
   }

   public String getTimestamp() {
       return timestamp;
   }

   public String getContent() {
       return content;
   }

   public boolean involvesUser(String username) {
    return sender.equals(username) || recipients.contains(username);
}
}

class User implements IterableByUser {
   private String username;
   private ChatServer mediator;
   private ChatHistory chatHistory;

   public User(String username, ChatServer mediator) {
       this.username = username;
       this.mediator = mediator;
       this.chatHistory = new ChatHistory();
   }

public void sendMessage(String content, List<String> recipients) {
    Message message = new Message(username, recipients, getCurrentTimestamp(), content);
    mediator.sendMessage(message);
    chatHistory.addMessage(message);

    for (String recipient : recipients) {
        System.out.println(username + " sent a message to " + recipient + " (" + message.getTimestamp() + "): " + content);
    }
}

   public void undoLastMessage() {
       Message lastMessage = chatHistory.getLastMessage();
       if (lastMessage != null) {
           mediator.undoMessage(lastMessage);
           chatHistory.removeLastMessage();
       }
   }

   public void viewChatHistory() {
       System.out.println("Chat history for " + username + ":");
       for (Message message : chatHistory.getMessages()) {
           System.out.println(message.getSender() + " (" + message.getTimestamp() + "): " + message.getContent());
       }
   }

   public String getUsername() {
       return username;
   }

   private String getCurrentTimestamp() {
       return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
   }

   public ChatHistory getChatHistory() {
        return chatHistory;
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return chatHistory.iterator(userToSearchWith);
    }
}

class ChatServer {
   private Map<String, User> users;
   private Map<String, List<String>> blockedUsers;

   public ChatServer() {
       users = new HashMap<>();
       blockedUsers = new HashMap<>();
   }

   public void registerUser(User user) {
       users.put(user.getUsername(), user);
   }

    public void sendMessage(Message message) {
        for (String recipient : message.getRecipients()) {
            if (!isBlocked(message.getSender(), recipient)) {
                System.out.println(message.getSender() + " sent a message to " + recipient +
                        " (" + message.getTimestamp() + "): " + message.getContent());
            }
        }
    }

   public void blockUser(String blocker, String blocked) {
       List<String> blockedList = blockedUsers.getOrDefault(blocker, new ArrayList<>());
       blockedList.add(blocked);
       blockedUsers.put(blocker, blockedList);
   }


    public void undoMessage(Message message) {
        String undoMessage = message.getSender() + " undid a message: " + message.getContent();
        for (String recipient : message.getRecipients()) {
            if (!isBlocked(message.getSender(), recipient)) {
                System.out.println(message.getSender() + " sent a message to " + recipient +
                        " (" + message.getTimestamp() + "): " + undoMessage);
            }
        }
    }


   private boolean isBlocked(String sender, String recipient) {
       List<String> blockedList = blockedUsers.getOrDefault(recipient, new ArrayList<>());
       return blockedList.contains(sender);
   }
}

class ChatHistory implements IterableByUser {

   private List<Message> messages;

   public ChatHistory() {
       messages = new ArrayList<>();
   }

   public List<Message> getMessages() {
       return messages;
   }

   public void addMessage(Message message) {
       messages.add(message);
   }

   public Message getLastMessage() {
       if (!messages.isEmpty()) {
           return messages.get(messages.size() - 1);
       }
       return null;
   }

   public void removeLastMessage() {
       if (!messages.isEmpty()) {
           messages.remove(messages.size() - 1);
       }
   }


    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return new searchMessagesByUser(messages, userToSearchWith);
    }
}

class MessageMemento {
   private String content;
   private String timestamp;

   public MessageMemento(String content, String timestamp) {
       this.content = content;
       this.timestamp = timestamp;
   }

   public String getContent() {
       return content;
   }

   public String getTimestamp() {
       return timestamp;
   }
}

class searchMessagesByUser implements Iterator<Message> {
    private List<Message> messages;
    private User userToSearchWith;
    private int currentIndex;

    public searchMessagesByUser(List<Message> messages, User userToSearchWith) {
        this.messages = messages;
        this.userToSearchWith = userToSearchWith;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < messages.size()) {
            Message message = messages.get(currentIndex);
            if (message.getSender().equals(userToSearchWith.getUsername()) ||
                    message.getRecipients().contains(userToSearchWith.getUsername())) {
                return true;
            }
            currentIndex++;
        }
        return false;
    }

    @Override
    public Message next() {
        if (hasNext()) {
            return messages.get(currentIndex++);
        }
        throw new java.util.NoSuchElementException();
    }
}



public class ChatApplication2 {
   public static void main(String[] args) {
       ChatServer chatServer = new ChatServer();

       User alice = new User("Alice", chatServer);
       User bob = new User("Bob", chatServer);
       User charlie = new User("Charlie", chatServer);

       chatServer.registerUser(alice);
       chatServer.registerUser(bob);
       chatServer.registerUser(charlie);

        alice.sendMessage("Hey everyone! How's it going?", List.of("Bob", "Charlie"));
        bob.sendMessage("Hi Alice! I'm doing great. How about you?", List.of("Alice"));
        charlie.sendMessage("Hey Alice and Bob! I'm good too. What are you guys up to?", List.of("Alice", "Bob"));
        alice.sendMessage("Not much, just enjoying the day. Any plans for the weekend?", List.of("Bob", "Charlie"));
        bob.sendMessage("I'm thinking about going on a hike. Want to join me?", List.of("Alice", "Charlie"));
        charlie.sendMessage("That sounds fun! Count me in.", List.of("Bob"));
        alice.sendMessage("I'd love to, but I have some work to catch up on. Maybe next time!", List.of("Bob"));
        bob.sendMessage("No worries, Alice. We'll plan something else soon.", List.of("Alice"));
        charlie.sendMessage("Yeah, let's definitely hang out sometime next week.", List.of("Alice", "Bob"));
        alice.sendMessage("Sounds good! I'm looking forward to it.", List.of("Bob", "Charlie"));
        bob.sendMessage("By the way, did you guys hear about the new movie that's coming out?", List.of("Alice", "Charlie"));
        charlie.sendMessage("No, I haven't. What's it about?", List.of("Bob"));
        alice.sendMessage("Oh, I heard about that! It's supposed to be a great action movie.", List.of("Bob", "Charlie"));
        bob.sendMessage("Yeah, I'm excited to see it. We should all go together when it comes out.", List.of("Alice", "Charlie"));
        charlie.sendMessage("Definitely! Let's make a plan.", List.of("Alice", "Bob"));
        alice.sendMessage("Count me in! It'll be fun.", List.of("Bob", "Charlie"));
        charlie.sendMessage("Great! I'll keep an eye out for the release date.", List.of("Alice", "Bob"));

       System.out.println("\n--- Undo Last Message ---");
       bob.undoLastMessage();
       System.out.println("Bob's last message undone.");

       System.out.println("\n--- Block User ---");
       chatServer.blockUser(alice.getUsername(), charlie.getUsername());
       System.out.println("Alice blocked Charlie.");
       charlie.sendMessage("Hey Alice, did you see my message?", List.of("Alice"));

       System.out.println("\n--- Alice's Chat History ---");
       alice.viewChatHistory();

       System.out.println("\n--- Bob's Chat History ---");
       bob.viewChatHistory();

       System.out.println("\n--- Charlie's Chat History ---");
       charlie.viewChatHistory();

       System.out.println("\n--- Alice's messages with Bob ---");
       Iterator<Message> aliceMessagesWithBob = alice.iterator(new User("Bob", chatServer));
       while (aliceMessagesWithBob.hasNext()) {
           Message message = aliceMessagesWithBob.next();
           System.out.println("At " + message.getTimestamp() + ": " + message.getContent());
       }

       System.out.println("\n--- Alice's messages with Charlie ---");
         Iterator<Message> aliceMessagesWithCharlie = alice.iterator(new User("Charlie", chatServer));
            while (aliceMessagesWithCharlie.hasNext()) {
                Message message = aliceMessagesWithCharlie.next();
                System.out.println("At " + message.getTimestamp() + ": " + message.getContent());
            }

        System.out.println("\n--- Bob's messages with Charlie ---");
        Iterator<Message> bobMessagesWithCharlie = bob.iterator(new User("Charlie",
                chatServer));   // Bob's messages involving Charlie
        while (bobMessagesWithCharlie.hasNext()) {
            Message message = bobMessagesWithCharlie.next();
            System.out.println("At " + message.getTimestamp() + ": " + message.getContent());
        }
   }
}
