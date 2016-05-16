package messages;

public class Message {
    private String pseudo;
    private String message;

    public Message(String pseudo, String message){
        this.pseudo = pseudo;
        this.message = message;
    }

    @Override
    public String toString() {
        return "FROM : " + pseudo + "\n" + message;
    }
}
