package com.artem.entertainment.bets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;

import com.artem.entertainment.Utils.Network;
import com.artem.entertainment.ixstavka.models.ConnectionToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

/** This example demonstrates how to create a websocket connection to a server. Only the most important callbacks are overloaded. */
public class ExampleClient extends WebSocketClient {
    final String WSS_URL_reconnect = "wss://1xstavka.ru:8080/LiveR/signalr/reconnect?";
    static ConnectionToken token;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExampleClient(URI serverUri , Draft draft ) {
        super( serverUri, draft );
    }

    public ExampleClient( URI serverURI ) {
        super( serverURI );
    }

    public ExampleClient( URI serverUri, Map<String, String> httpHeaders ) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen( ServerHandshake handshakedata ) {
        send("Hello, it is me. Mario :)");
        System.out.println( "opened connection" );
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage( String message ) {
        System.out.println("received: " + message);
        this.message = message;
//        if (message.length() > 5 && message.length() < 100) {
//            try {
//                JsonElement jelement = new JsonParser().parse(message);
//                JsonObject jsonObject = jelement.getAsJsonObject();
//                String id = String.valueOf(jsonObject.get("C"));
//                String messageId = id.substring(1, id.length() - 1);
//                String url = generateReconnectUrl(messageId, WSS_URL_reconnect, token.getConnectionToken());
//                ExampleClient c = new ExampleClient(new URI(url)); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
//                c.connect();
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println( "Connection closed by " + ( remote ? "remote peer" : "us" ) + " Code: " + code + " Reason: " + reason );
    }

    @Override
    public void onError( Exception ex ) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    public static void main( String[] args ) throws URISyntaxException, IOException {
        final String TOKEN_URL = "https://1xstavka.ru:8080/LiveR/signalr/negotiate?clientProtocol=1.5&grp=ru40&connectionData=%5B%7B%22name%22%3A%22livehub%22%7D%5D&_=1534796598309";
        final String WSS_URL = "wss://1xstavka.ru:8080/LiveR/signalr/connect?transport=webSockets&clientProtocol=1.5&grp=ru40&connectionData=%5B%7B%22name%22%3A%22livehub%22%7D%5D&tid=0";
        final String WSS_URL_mini = "wss://1xstavka.ru:8080/LiveR/signalr/connect?";
        final String WSS_URL_reconnect = "wss://1xstavka.ru:8080/LiveR/signalr/reconnect?";
        String recconnect = "wss://1xstavka.ru:8080/LiveR/signalr/reconnect?transport=webSockets&messageId=d-143C1562-B%2C0%7CET2%2CB%7CET3%2C1&clientProtocol=1.5&grp=ru40&connectionToken=l44bGm3BYXE2VNX1uXZKGtm%2BcUicV4d8ifUaOJak%2BFfX4F7r6XmAdACv8hz7M13218uViTi5dnx9Vd6u%2Frisy%2FRcLvbY5jXctXExe%2BXNPxWa3F3H4Xl4JNltdjjx8AWL&connectionData=%5B%7B%22name%22%3A%22livehub%22%7D%5D&tid=4";

//        Gson gson = new Gson();
//        token = gson.fromJson(Network.readUrl(TOKEN_URL), ConnectionToken.class);
//        String stringToken = token.getConnectionToken();
        String stringToken = "LzQLbHw0x5BDMvRxHOkdx/Qxbne0UQoLEb9QrmzmzbwR9b/mfXa7FcpqcTMsIT4RMMfPKTcQJh0zo7hO2iV5LYM3uiQyFMXTxXetZV0wmpv1V8/M4pJ6RM4hcGmivj3X";
        String url = generateUrl(WSS_URL_reconnect, stringToken);
        ExampleClient c = new ExampleClient(new URI(url)); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
        c.connect();
    }

    private static String generateUrl(String mainUrl, String token) throws UnsupportedEncodingException {
        StringBuilder wssUrl = new StringBuilder(mainUrl);
        wssUrl.append("transport=");
        wssUrl.append(URLEncoder.encode("webSockets","UTF-8"));
        wssUrl.append("&");
        wssUrl.append("clientProtocol=");
        wssUrl.append(URLEncoder.encode("1.5","UTF-8"));
        wssUrl.append("&");
        wssUrl.append("grp=");
        wssUrl.append(URLEncoder.encode("ru40","UTF-8"));
        wssUrl.append("&");
        wssUrl.append("connectionToken=");
        wssUrl.append(URLEncoder.encode(token,"UTF-8"));
        wssUrl.append("&");
        wssUrl.append("connectionData=");
        wssUrl.append(URLEncoder.encode("[{\"name\":\"livehub\"}]","UTF-8"));
        wssUrl.append("&");
        wssUrl.append("tid=");
        wssUrl.append(URLEncoder.encode("0","UTF-8"));
        return wssUrl.toString();
    }

    private static String generateReconnectUrl(String messageId, String url, String token) throws UnsupportedEncodingException {
        StringBuilder wssUrl = new StringBuilder(url);

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
        wssUrl.append("connectionToken=");
        wssUrl.append(URLEncoder.encode(token,"UTF-8"));
        wssUrl.append("&");
        wssUrl.append("connectionData=");
        wssUrl.append(URLEncoder.encode("[{\"name\":\"livehub\"}]","UTF-8"));
        wssUrl.append("&");
        wssUrl.append("tid=");
        wssUrl.append(URLEncoder.encode(String.valueOf(new Random().nextInt(12)+1),"UTF-8"));
        return wssUrl.toString();
    }
}

