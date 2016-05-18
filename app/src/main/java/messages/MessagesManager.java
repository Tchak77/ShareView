package messages;

import java.util.ArrayList;
import java.util.List;

import asyncTasks.SendQueueMessage;
import fragments.ChatFragment;
import fragments.UsersFragment;

public class MessagesManager {
    private static MessagesManager manager;
    private List<Message> messages;
    private List<String> users;
    private String pseudo;
    private String addressIp;
    private String port;
    private String title;
    private ChatFragment chatFragment;
    private UsersFragment usersFragment;

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

    public static MessagesManager getSingleton(ChatFragment chatFragment) {
        getSingleton();
        manager.chatFragment = chatFragment;
        return manager;
    }

    public static MessagesManager getSingleton(UsersFragment usersFragment) {
        getSingleton();
        manager.usersFragment = usersFragment;
        return manager;
    }

    public void sendMessage(String messageStr) {
        Message message = new Message(pseudo, messageStr);
        messages.add(message);
        chatFragment.update();
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        sendQueueMessage.execute(addressIp, port, title, pseudo, messageStr);
    }

    public void JSONparser(String pseudo, String jsonstr) {
        if (jsonstr.contains("admin")) {
            if (jsonstr.contains("join")) {
                if (!users.contains(pseudo)) {
                    users.add(pseudo);
                    if(usersFragment!=null){
                        usersFragment.update();
                    }
                }
            } else {
                users.remove(pseudo);
                if(usersFragment!=null){
                    usersFragment.update();
                }
            }
            return;
        } else {
            messages.add(new Message(pseudo, jsonstr));
            if(chatFragment!=null){
                chatFragment.update();
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
