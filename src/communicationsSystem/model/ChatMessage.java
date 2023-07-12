package communicationsSystem.model;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;


public class ChatMessage implements Serializable {
	// Class fields
    private String messageId; // TODO: Figure out what this is for
    private User sender;
    private ArrayList<User> recipients;
    private String messageText;
    private Date timeStamp;

    // Class constructor
    public ChatMessage(String messageId, User sender, ArrayList<User> recipientIds, String messageText, Date timeStamp) {
        this.messageId = messageId;
        this.sender = sender;
        this.recipients = recipientIds;
        this.messageText = messageText;
        this.timeStamp = timeStamp;
    }

    public ChatMessage() {
        this.messageId = "";
        this.sender = null;
        this.recipients = null;
        this.messageText = "";
        this.timeStamp = null;
    }

    // Getter methods
    public String getMessageId() {
        return messageId;
    }

    public User getSender() {
        return sender;
    }

    public ArrayList<User> getRecipients() {
        return recipients;
    }

    public String getMessageText() {
        return messageText;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    // Setter methods
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setRecipients(ArrayList<User> recipientIds) {
        this.recipients = recipientIds;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

}
