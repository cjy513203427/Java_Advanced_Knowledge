package com.advance.P2P_Connect;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jack on 2018/4/1.
 */
public class Client {


    private List<WebSocket> sockets = new ArrayList<WebSocket>();

    public List<WebSocket> getSockets() {
        return sockets;
    }

    public void connectToPeer(String peer) {
        try {
            final WebSocketClient socketClient = new WebSocketClient(new URI(peer)) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    write(this, "客户端连接成功");
                    sockets.add(this);
                }

                @Override
                public void onMessage(String msg) {
                    System.out.println("收到服务端发送的消息:" + msg);
                }

                @Override
                public void onClose(int i, String msg, boolean b) {
                    System.out.println("connection failed");
                    sockets.remove(this);
                }

                @Override
                public void onError(Exception e) {
                    System.out.println("connection failed");
                    sockets.remove(this);
                }
            };
            socketClient.connect();
        } catch (URISyntaxException e) {
            System.out.println("p2p connect is error:" + e.getMessage());
        }
    }

    public void write(WebSocket ws, String message) {
        System.out.println("发送给" + ws.getRemoteSocketAddress().getPort() + "的p2p消息:" + message);
        ws.send(message);
    }

    public void broatcast(String message) {
        if (sockets.size() == 0) {
            return;
        }
        System.out.println("======广播消息开始：");
        for (WebSocket socket : sockets) {
            this.write(socket, message);
        }
        System.out.println("======广播消息结束");
    }
}