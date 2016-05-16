package messages;

import android.util.Log;

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

    public void informConnection(){
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        sendQueueMessage.execute(addressIp, port, title, pseudo, "{\\\"admin\\\" : \\\"join\\\"}");
    }

    public void informDeconnection(){
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        sendQueueMessage.execute(addressIp, port, title, pseudo, "{\\\"admin\\\" : \\\"leave\\\"}");
        users = new ArrayList<String>();
        messages = new ArrayList<Message>();
    }

    public void reintializeManager(){
        this.users = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<String> getUsers() {
        return users;
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
