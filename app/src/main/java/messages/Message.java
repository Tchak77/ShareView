package messages;

public class Message {
    private String pseudo;
    private String message;

    public Message(String pseudo, String message){
        this.pseudo = pseudo;
        this.message = message;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMessage() {
        return message;
    }
}
