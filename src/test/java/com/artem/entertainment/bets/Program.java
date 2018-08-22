package com.artem.entertainment.bets;

import java.util.Scanner;

import microsoft.aspnet.signalr.client.Action;
import microsoft.aspnet.signalr.client.ErrorCallback;
import microsoft.aspnet.signalr.client.LogLevel;
import microsoft.aspnet.signalr.client.Logger;
import microsoft.aspnet.signalr.client.MessageReceivedHandler;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;

import com.google.gson.JsonElement;

public class Program {

    public static void main(String[] args) {

        // Create a new console logger
        Logger logger = new Logger() {

            @Override
            public void log(String message, LogLevel level) {
                System.out.println(message);
            }
        };

        // Connect to the server
        HubConnection conn = new HubConnection("https://1xstavka.ru:8080/LiveR/", "tid=4", true, logger);

        // Create the hub proxy
        HubProxy proxy = conn.createHubProxy("livehub");

        proxy.subscribe(new Object() {
            @SuppressWarnings("unused")
            public void messageReceived(String name, String message) {
                System.out.println(name + ": " + message);
            }
        });

        // Subscribe to the error event
        conn.error(new ErrorCallback() {

            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });

        // Subscribe to the connected event
        conn.connected(new Runnable() {

            @Override
            public void run() {
                System.out.println("CONNECTED");
                proxy.invoke("subscribe","{\"H\":{\"M\":\"RG\"},\"RQ\":{\"L\":\"ru\",\"CV\":0,\"P\":51}}");
            }
        });

        // Subscribe to the closed event
        conn.closed(new Runnable() {

            @Override
            public void run() {
                System.out.println("DISCONNECTED");
            }
        });

        // Start the connection
        conn.start()
                .done(new Action<Void>() {

                    @Override
                    public void run(Void obj) throws Exception {
                        System.out.println("Done Connecting!");
                        proxy.invoke("subscribe", "{\"H\":{\"M\":\"W1x2\",\"UI\":1},\"RQ\":{\"CN\":1,\"CT\":10,\"MO\":4,\"CF\":3,\"VF\":258}}");
                    }
                });
        boolean isDone = false;
        // Subscribe to the received event
        conn.received(new MessageReceivedHandler() {

            @Override
            public void onMessageReceived(JsonElement json) {
                System.out.println("RAW received message: " + json.toString());
            }
        });

        // Read lines and send them as messages.
        Scanner inputReader = new Scanner(System.in);

        String line = inputReader.nextLine();
        while (!"exit".equals(line)) {
            proxy.invoke("send", "Console", line).done(new Action<Void>() {

                @Override
                public void run(Void obj) throws Exception {
                    System.out.println("SENT!");
                }
            });

            line = inputReader.next();
        }

        inputReader.close();

        conn.stop();
    }
}