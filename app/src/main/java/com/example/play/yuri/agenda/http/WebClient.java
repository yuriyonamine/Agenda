package com.example.play.yuri.agenda.http;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebClient {
    public String post(String json) {
        String response = "";
        try {
            URL url = new URL("https://www.caelum.com.br/mobile");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            httpURLConnection.setDoOutput(true);

            PrintStream output = new PrintStream(httpURLConnection.getOutputStream());
            output.println(json);

            httpURLConnection.connect();

            response = new Scanner(httpURLConnection.getInputStream()).next();

            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
