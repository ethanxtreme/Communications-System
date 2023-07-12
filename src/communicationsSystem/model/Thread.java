package communicationsSystem.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Thread {

    // array of messages
    ArrayList<ChatMessage> chatMessages = new ArrayList<>();
    ArrayList<User> participants = new ArrayList<>();
    private int numMessages;

    // constructors
    public Thread() {
        numMessages = 0;
    }

    public Thread(ChatMessage[] messages, User[] participants) {
        this.chatMessages.addAll(Arrays.asList(messages));
        this.participants.addAll(Arrays.asList(participants));

        // this will save the current number of messages
        numMessages = chatMessages.size();
    }


    // other methods

    // Add new message to my TV
    public void addMessage(ChatMessage message) {
        // Add the new message to the end of the array
        chatMessages.add(message);

        // Increment the number of messages
        numMessages++;
    }

    public void addParticipant(User participant) {
        participants.add(participant);
    }

    // returns number of messages in thread
    public int getNumMessages() {
        return numMessages;
    }

    // returns the entire thread
    public ArrayList<ChatMessage> getThread() {
        return chatMessages;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

}
