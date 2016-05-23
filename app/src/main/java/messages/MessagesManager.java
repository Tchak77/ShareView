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

    /**
     * Creates an unique MessagesManager
     * @return an unique MessagesManager
     */
    public static MessagesManager getSingleton() {
        if (manager == null) {
            manager = new MessagesManager();
        }
        return manager;
    }

    /**
     * Set pseudo of the user connected
     * @param pseudo
     */
    public void setPseudo(String pseudo){
        this.pseudo = pseudo;
    }

    /**
     * Set ChatFragment to update it next
     * @param chatFragment
     */
    public void setChatFragment(ChatFragment chatFragment){
        this.chatFragment = chatFragment;
    }

    /**
     * Set the user fragment to update it next
     * @param usersFragment
     */
    public void setUsersFragment(UsersFragment usersFragment){
        this.usersFragment = usersFragment;
    }

    /**
     * Send a message to the server
     * @param messageStr
     */
    public void sendMessage(String messageStr) {
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        sendQueueMessage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,addressIp, port, title, pseudo, messageStr);
    }

    /**
     *
     * @param pseudo Pseudo of the user sending the JSon
     * @param jsonstr Message encoded in JSon
     */
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

    /**
     * Send to server information about connection
     */
    public void informConnection() {
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        sendQueueMessage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,addressIp, port, title, pseudo, "{\\\"admin\\\" : \\\"join\\\"}");
    }

    /**
     * Send to server information about disconnection
     */
    public void informDeconnection() {
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        sendQueueMessage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,addressIp, port, title, pseudo, "{\\\"admin\\\" : \\\"leave\\\"}");
        users = new ArrayList<String>();
        messages = new ArrayList<Message>();
    }

    /**
     * Reset the users list and messages list
     */
    public void reintializeManager() {
        this.users = new ArrayList<>();
        this.messages = new ArrayList<>();
    }


    /**
     * Get the list of the message
     * @return List<Message> containing all the message in the chat
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Get the list of users on the queue
     * @return list of string of users
     */
    public List<String> getUsers() {
        return users;
    }

    /**
     * Set title of the board
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set port of the server
     * @param port
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * Set ip address of the server
     * @param addressIp
     */
    public void setAddressIp(String addressIp) {
        this.addressIp = addressIp;
    }
}
