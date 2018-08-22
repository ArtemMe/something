package com.artem.entertainment.bets;

import com.artem.entertainment.Utils.Network;
import com.artem.entertainment.betcity.Deserializers.ChmpDeserializer;
import com.artem.entertainment.betcity.Deserializers.EventDeserializer;
import com.artem.entertainment.betcity.models.Championships;
import com.artem.entertainment.betcity.models.Events;
import com.artem.entertainment.betcity.models.MainModel;
import com.artem.entertainment.ixstavka.WebsocketClientEndpoint;
import com.artem.entertainment.ixstavka.models.ConnectionToken;
import com.google.gson.*;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import org.junit.Test;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.Scanner;

import static sun.plugin.javascript.navig.JSType.URL;

public class TestWS {
    final String TOKEN_URL = "https://1xstavka.ru:8080/LiveR/signalr/negotiate?clientProtocol=1.5&grp=ru40&";
    final String WSS_URL = "wss://1xstavka.ru:8080/LiveR/signalr/connect?transport=webSockets&clientProtocol=1.5&grp=ru40&connectionData=%5B%7B%22name%22%3A%22livehub%22%7D%5D&tid=0";
    final String WSS_URL_mini = "wss://1xstavka.ru:8080/LiveR/signalr/connect?";
    final String WSS_URL_reconnect = "wss://1xstavka.ru:8080/LiveR/signalr/reconnect?";

    ConnectionToken token;
    @Test
    public void testWS() throws IOException, WebSocketException {

        Gson gson = new Gson();
        token = gson.fromJson(Network.readUrl(TOKEN_URL), ConnectionToken.class);
        StringBuilder wssUrl = new StringBuilder(WSS_URL_mini);

        wssUrl.append("transport=");
        wssUrl.append(URLEncoder.encode("webSockets","UTF-8"));
        wssUrl.append("&");
        wssUrl.append("clientProtocol=");
        wssUrl.append(URLEncoder.encode("1.5","UTF-8"));
        wssUrl.append("&");
        wssUrl.append("grp=");
        wssUrl.append(URLEncoder.encode("ru40","UTF-8"));
        wssUrl.append("&");
        wssUrl.append("connectionData=");
        wssUrl.append(URLEncoder.encode("[{\"name\":\"livehub\"}]","UTF-8"));
        wssUrl.append("&");
        wssUrl.append("tid=");
        wssUrl.append(URLEncoder.encode("0","UTF-8"));
        wssUrl.append("&");
        wssUrl.append("connectionToken=");
        wssUrl.append(URLEncoder.encode(token.getConnectionToken(),"UTF-8"));
        URI.create(wssUrl.toString());

while(true) {
    try {
        // open websocket
        final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(wssUrl.toString()));

        // add listener
        clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
            public void handleMessage(String message) throws UnsupportedEncodingException {
                System.out.println(message);
                if(message.length()>5){
                    JsonElement jelement = new JsonParser().parse(message);
                    JsonObject jsonObject = jelement.getAsJsonObject();
                    String messageId = String.valueOf(jsonObject.get("C"));
                    messageId.substring(1,messageId.length()-1);

                    StringBuilder wssUrl = new StringBuilder(WSS_URL_reconnect);

                    wssUrl.append("transport=");
                    wssUrl.append(URLEncoder.encode("webSockets","UTF-8"));
                    wssUrl.append("&");
                    wssUrl.append("messageId=");
                    wssUrl.append(URLEncoder.encode(messageId,"UTF-8"));
                    wssUrl.append("&");
                    wssUrl.append("clientProtocol=");
                    wssUrl.append(URLEncoder.encode("1.5","UTF-8"));
                    wssUrl.append("&");
                    wssUrl.append("grp=");
                    wssUrl.append(URLEncoder.encode("ru40","UTF-8"));
                    wssUrl.append("&");
                    wssUrl.append("connectionData=");
                    wssUrl.append(URLEncoder.encode("[{\"name\":\"livehub\"}]","UTF-8"));
                    wssUrl.append("&");
                    wssUrl.append("tid=");
                    wssUrl.append(URLEncoder.encode(String.valueOf(new Random().nextInt(12)+1),"UTF-8"));
                    wssUrl.append("&");
                    wssUrl.append("connectionToken=");
                    wssUrl.append(URLEncoder.encode(token.getConnectionToken(),"UTF-8"));

                    while(true) {
                        try {
                            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(wssUrl.toString()));
                            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                                public void handleMessage(String message) throws UnsupportedEncodingException {
                                    System.out.println(message);
                                }
                            });
                            //clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            System.err.println("InterruptedException exception: " + ex.getMessage());
                        } catch (URISyntaxException ex) {
                            System.err.println("URISyntaxException exception: " + ex.getMessage());
                        }
                    }
                }
            }
        });

        // send message to websocket
        //clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");

        // wait 5 seconds for messages from websocket
        Thread.sleep(5000);

    } catch (InterruptedException ex) {
        System.err.println("InterruptedException exception: " + ex.getMessage());
    } catch (URISyntaxException ex) {
        System.err.println("URISyntaxException exception: " + ex.getMessage());
    }


}
        // Connect to "wss://echo.websocket.org" and send "Hello." to it.
        // When a response from the WebSocket server is received, the
        // WebSocket connection is closed.
//        new WebSocketFactory()
//                .createSocket(wssUrl.toString())
////                .createSocket("wss://1xstavka.ru:8080/LiveR/signalr/connect?transport=webSockets&clientProtocol=1.5&grp=ru40&connectionToken=UJyx90yb1hv4I1FJlq4fxDMWHeL7Nvik1%2B9iAZsjQ3tImDf3Tlr29%2FeAbZOMVgS7LY0JLswyezb1iWjU984SoW4GEKdURiRflZ1NfvFlEXrtjINBAe%2FjnVT9B5bKp3VU&connectionData=%5B%7B%22name%22%3A%22livehub%22%7D%5D&tid=4")
//                .addListener(new WebSocketAdapter() {
//                    @Override
//                    public void onTextMessage(WebSocket ws, String message) {
//                        // Received a response. Print the received message.
//                        System.out.println(message);
//
//                        // Close the WebSocket connection.
//                        ws.disconnect();
//                    }
//                });

    }
}
