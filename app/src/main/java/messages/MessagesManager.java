package messages;

import android.os.AsyncTask;

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
    

    public void setPseudo(String pseudo){
        this.pseudo = pseudo;
    }

    public void setChatFragment(ChatFragment chatFragment){
        this.chatFragment = chatFragment;
    }
    public void setUsersFragment(UsersFragment usersFragment){
        this.usersFragment = usersFragment;
    }

    public void sendMessage(String messageStr) {
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        sendQueueMessage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,addressIp, port, title, pseudo, messageStr);
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
        sendQueueMessage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,addressIp, port, title, pseudo, "{\\\"admin\\\" : \\\"join\\\"}");
    }

    public void informDeconnection() {
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        sendQueueMessage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,addressIp, port, title, pseudo, "{\\\"admin\\\" : \\\"leave\\\"}");
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
