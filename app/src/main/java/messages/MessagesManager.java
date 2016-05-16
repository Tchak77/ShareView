package messages;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MessagesManager {
    private static MessagesManager manager;
    private List<Message> messages;
    private List<String> users;

    private MessagesManager() {
        messages = new ArrayList<>();
        users = new ArrayList<>();
    }

    public static MessagesManager getSingleton() {
        if (manager == null) {
            manager = new MessagesManager();
        }
        return manager;
    }

    public void JSONparser(String pseudo, String jsonstr) {
        if (jsonstr.contains("admin")) {
            if (jsonstr.contains("join")) {
                if(!users.contains(pseudo)) {
                    users.add(pseudo);
                }
            } else {
                users.remove(pseudo);
            }
            return;
        }
        Log.v("MESSSAGE", pseudo + " says " + jsonstr);

    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
