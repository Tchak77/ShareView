package messages;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import asyncTasks.SendQueueMessage;

public class MessagesManager {
    private static MessagesManager manager;
    private List<Message> messages;
    private List<String> users;
    private String pseudo;
    private String addressIp;
    private String port;
    private String title;
    private ArrayAdapter<Message> messageArrayAdapter;
    private ArrayAdapter<String> userArrayAdapter;

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

    public static MessagesManager getSingleton(String pseudo) {
        getSingleton();
        manager.pseudo = pseudo;
        return manager;
    }

    public void sendMessage(String messageStr) {
        Message message = new Message(pseudo, messageStr);
        messages.add(message);
        if (messageArrayAdapter != null) {
            messageArrayAdapter.notifyDataSetChanged();
        }
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        sendQueueMessage.execute(addressIp, port, title, pseudo, messageStr);
    }

    public void JSONparser(String pseudo, String jsonstr) {
        if (jsonstr.contains("admin")) {
            if (jsonstr.contains("join")) {
                if (!users.contains(pseudo)) {
                    users.add(pseudo);
                    if (userArrayAdapter != null) {
                        userArrayAdapter.notifyDataSetChanged();
                    }
                }
            } else {
                users.remove(pseudo);
                if (userArrayAdapter != null) {
                    userArrayAdapter.notifyDataSetChanged();
                }
            }
            return;
        } else {
            messages.add(new Message(pseudo, jsonstr));
            if (messageArrayAdapter != null) {
                messageArrayAdapter.notifyDataSetChanged();
            }
        }
    }

    public void informConnection() {
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        sendQueueMessage.execute(addressIp, port, title, pseudo, "{\\\"admin\\\" : \\\"join\\\"}");
    }

    public void informDeconnection() {
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        sendQueueMessage.execute(addressIp, port, title, pseudo, "{\\\"admin\\\" : \\\"leave\\\"}");
        users = new ArrayList<String>();
        messages = new ArrayList<Message>();
    }

    public void reintializeManager() {
        this.users = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUserAdapter(ArrayAdapter<String> userArrayAdapter){
        this.userArrayAdapter = userArrayAdapter;
    }

    public void setMessageAdapter(ArrayAdapter<Message> messageArrayAdapter){
        this.messageArrayAdapter = messageArrayAdapter;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setAddressIp(String addressIp) {
        this.addressIp = addressIp;
    }
}
