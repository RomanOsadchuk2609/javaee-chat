package br.com.example.javaee.chat;

import br.com.example.javaee.chat.model.ChatMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatState {

    private String chatId;
    private List<ChatMessage> messages = new ArrayList<>();

    public ChatState(String chatId) {
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    public void addMessage(ChatMessage message) {
        this.messages.add(message);
    }

    public List<ChatMessage> getMessages() {
        return Collections.unmodifiableList(messages);
    }
}
