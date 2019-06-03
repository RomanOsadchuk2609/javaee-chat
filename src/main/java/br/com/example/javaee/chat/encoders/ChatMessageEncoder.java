package br.com.example.javaee.chat.encoders;

import br.com.example.javaee.chat.model.ChatMessage;
import com.google.gson.Gson;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {
    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(final ChatMessage chatMessage) throws EncodeException {
        Gson gson = new Gson();
        return gson.toJson(chatMessage);
    }
}