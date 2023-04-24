import java.util.Date;
import java.util.List;

public class ChatMessage {
    private String messageId;
    private String senderId;
    private String[] recipientIds;
    private String messageText;
    private Date timeStamp;

    public ChatMessage(String messageId, String senderId, String[] recipientIds, String messageText, Date timeStamp) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.recipientIds = recipientIds;
        this.messageText = messageText;
        this.timeStamp = timeStamp;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String[] getRecipientIds() {
        return recipientIds;
    }

    public void setRecipientIds(String[] recipientIds) {
        this.recipientIds = recipientIds;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return messageText;
    }

    public void setMessage(String messageText) {
        this.messageText = messageText;
    }
}
