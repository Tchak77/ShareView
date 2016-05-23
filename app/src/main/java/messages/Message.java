package messages;

public class Message {
    private String pseudo;
    private String message;

    /**
     * Create a Message
     * @param pseudo
     * @param message
     */
    public Message(String pseudo, String message){
        this.pseudo = pseudo;
        this.message = message;
    }

    /**
     * Display a message
     * @return
     */
    @Override
    public String toString() {
        return "FROM : " + pseudo + "\n" + message;
    }
}
