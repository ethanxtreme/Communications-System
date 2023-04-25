import java.util.Date;
import java.util.List;

public class ChatMessage {
	// Class fields
    private String messageId;
    private String senderId;
    private String[] recipientIds;
    private String messageText;
    private Date timeStamp;

    // Class constructor
    public ChatMessage(String messageId, String senderId, String[] recipientIds, String messageText, Date timeStamp) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.recipientIds = recipientIds;
        this.messageText = messageText;
        this.timeStamp = timeStamp;
    }

    // Getter methods
    public String getMessageId() {
        return messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String[] getRecipientIds() {
        return recipientIds;
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

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setRecipientIds(String[] recipientIds) {
        this.recipientIds = recipientIds;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

}
