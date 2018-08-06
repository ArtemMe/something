//package com.artem.entertainment.bets.controllers;
//
//import com.artem.entertainment.bets.models.fonbet.FonBetModel;
//import com.google.gson.Gson;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//@RestController("/fonbet")
//public class FonBetController {
//
//    private final String REQUEST_URL = "https://line16.bkfon-resource.ru/line/mobile/showEvents/top2?lang=rus&lineType=live";
//
//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    public FonBetModel getData() throws Exception {
//        Gson gson = new Gson();
//        FonBetModel model = gson.fromJson(readUrl(REQUEST_URL),FonBetModel.class);
//        return model;
//    }
//
//    private String readUrl(String url) throws IOException {
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet request = new HttpGet(url);
//
//        // add request header
//        request.addHeader("content-type", "application/json; charset=utf-8");
//        HttpResponse response = client.execute(request);
//
//        System.out.println("Response Code : "
//                + response.getStatusLine().getStatusCode());
//
//        BufferedReader rd = new BufferedReader(
//                new InputStreamReader(response.getEntity().getContent()));
//
//        StringBuffer result = new StringBuffer();
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            result.append(line);
//        }
//        return result.toString();
//    }
//}
