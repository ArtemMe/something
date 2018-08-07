package com.artem.entertainment.betcity.controllers;

import com.artem.entertainment.betcity.Deserializers.ChmpDeserializer;
import com.artem.entertainment.betcity.Deserializers.EventDeserializer;
import com.artem.entertainment.betcity.models.Championships;
import com.artem.entertainment.betcity.models.Events;
import com.artem.entertainment.betcity.models.MainModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.applet.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@RestController("/betcity")
public class BetcityController {

    private static final String REQUEST_URL = "https://ad.betcity.ru/d/on_air/events?rev=6&ver=696&csn=ooca9s";

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public MainModel getData() throws Exception {
//        Gson gson = new Gson();
//        MainModel model = gson.fromJson(readUrl(REQUEST_URL), MainModel.class);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Championships.class,  new ChmpDeserializer());
        builder.registerTypeAdapter(Events.class, new EventDeserializer());

        Gson gson = builder.create();
        MainModel model = gson.fromJson(readUrl(REQUEST_URL), MainModel.class);
        return model;
    }

    private String readUrl(String url) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("content-type", "application/json; charset=utf-8");
        HttpResponse response = client.execute(request);

        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}
