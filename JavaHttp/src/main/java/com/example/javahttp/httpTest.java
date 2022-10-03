package com.example.javahttp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;


public class httpTest {

    private static HttpURLConnection testHttpUrlConnection() throws MalformedURLException, IOException {
        long startTime = System.currentTimeMillis();
        URL url = new URL("http://localhost:8081/JavaHttp_war_exploded/hello-servlet");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        long endTime = System.currentTimeMillis();

        int statusCode = httpConn.getResponseCode();
        String pf = (statusCode == 200) ? "success" : "fail";

        System.out.println("1. connection " + pf + " in " + (endTime - startTime) + " millisecond");
        System.out.println("2. Response code: " + statusCode);

        return httpConn;
    }

    private static int getResponseStatus() throws MalformedURLException, IOException {

        URL url = new URL("http://localhost:8081/JavaHttp_war_exploded/hello-servlet");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        return responseCode;
    }

    private static String getJsonObjectStringFromFile() throws MalformedURLException, IOException {
        System.out.println("\nRead from json file instead!!");
        String currentPath = Paths.get(".").normalize().toString();
        FileReader fr = new FileReader("C:\\Users\\HWH\\IdeaProjects\\cnu-sw-academy-team-a\\JavaHttp\\src\\main\\resources\\test.json");
        System.out.println(fr);
        BufferedReader br = new BufferedReader(fr);

        String outputString = getStringFromJson(br);

        return outputString;
    }

    // Print Json using StringBuilder
    private static String getStringFromJson(BufferedReader br) throws IOException {

        StringBuilder sb = new StringBuilder();
        String read;
        while ((read = br.readLine()) != null) {
            sb.append(read);
        }

        return sb.toString();
    }

    public static void main(String[] args) throws MalformedURLException, IOException {

        HttpURLConnection httpConn = testHttpUrlConnection();
        int statusCode = getResponseStatus();

        String res = null;
        if (statusCode == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            res = getStringFromJson(br);
           // res = getJsonObjectStringFromFile();
        } else {
            res = getJsonObjectStringFromFile();
        }

        System.out.println("result string: " + res);

    }

}