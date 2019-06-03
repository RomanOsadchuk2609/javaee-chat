package br.com.example.javaee.chat.encoders;

import br.com.example.javaee.chat.model.ChatMessage;
import com.google.gson.Gson;

import java.io.StringReader;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {
    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public ChatMessage decode(final String textMessage) throws DecodeException {
        ChatMessage chatMessage = new ChatMessage();
        Gson gson = new Gson();
        chatMessage = gson.fromJson(textMessage,ChatMessage.class);
        return chatMessage;
    }

    @Override
    public boolean willDecode(final String s) {
        return true;
    }
}
