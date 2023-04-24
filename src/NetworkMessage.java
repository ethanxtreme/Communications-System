import java.io.Serializable;

public class NetworkMessage implements Serializable {

	protected MessageStatus status;
	protected MessageType type;
	
	protected String messageText;
	
	protected ChatMessage chatMessage;
	/*
	 * I dont think passing a string here is best
	 * I think that if we pass an additional object of type ChatMessage 
	 * e.g protected ChatMessage chatMessage;
	 * Then we would just de-construct that message on the server side so we
	 * know who to send it to, and can also just add that to the ChatLog
	 */
	
	public NetworkMessage(){
        this.type = MessageType.UNDEFINED;
        this.status = MessageStatus.UNDEFINED;
        this.messageText = "Undefined";
    }

    public NetworkMessage(MessageType type, MessageStatus status, String text){
        this.type = type;
        this.status = status;
        this.messageText = text;
    }

    public void setType(MessageType type){ //made public, was private
    	this.type = type;
    }

    public void setStatus(MessageStatus status){
    	this.status = status;
    }

    public void setText(String text){
    	this.messageText = text;
    }

    public MessageType getType(){
    	return this.type;
    }

    public MessageStatus getStatus(){
    	return this.status;
    }

    public String getText(){
    	return this.messageText;
    }
    
    public ChatMessage getChatMessage() {
    	return this.chatMessage;
    }
}
