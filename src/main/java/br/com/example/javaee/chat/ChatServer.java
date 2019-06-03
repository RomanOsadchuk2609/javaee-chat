package br.com.example.javaee.chat;

import br.com.example.javaee.chat.encoders.ChatMessageDecoder;
import br.com.example.javaee.chat.encoders.ChatMessageEncoder;
import br.com.example.javaee.chat.model.ChatMessage;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/websocket", encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class ChatServer {
    private static final Set<Session> peers =
            Collections.synchronizedSet(new HashSet<Session>());
    @OnOpen
    public void onOpen(Session peer) {
        peers.add(peer);
    }
    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }

    @OnMessage
    public void message(ChatMessage message, Session client)
            throws IOException, EncodeException {
        for (Session peer : peers) {
            peer.getBasicRemote().sendObject(message);
        }
    }

}
