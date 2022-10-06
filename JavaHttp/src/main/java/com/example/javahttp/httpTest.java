package com.example.javahttp;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;


public class httpTest {

    static String url_temp = "http://localhost:8081/JavaHttp_war_exploded/hello-servlet";
    private static HttpURLConnection testHttpUrlConnection() throws MalformedURLException, IOException {
        long startTime = System.currentTimeMillis();
        URL url = new URL(url_temp);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        long endTime = System.currentTimeMillis();

        int statusCode = httpConn.getResponseCode();
        String pf = (statusCode == 200) ? "success" : "fail";

        System.out.println("1. connection " + pf + " in " + (endTime - startTime) + " millisecond");
        System.out.println("2. Response code: " + statusCode);

        return httpConn;
    }

    private static int getResponseStatus() throws MalformedURLException, IOException {

        URL url = new URL(url_temp);
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



    public static String POST(){

        /*
        InputStream is = null;
        String result = "";

        try {
            URL urlCon = new URL(url_temp);
            HttpURLConnection httpCon = (HttpURLConnection)urlCon.openConnection();
            String json = "";

            // build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name1", "1");
            jsonObject.put("name2", "2");
            jsonObject.put("name3", "3");

            // convert JSONObject to JSON to String

            json = jsonObject.toString();

            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("Accept", "application/json");
            httpCon.setRequestProperty("Content-type", "application/json");

            // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
            httpCon.setDoOutput(true);

            // InputStream으로 서버로 부터 응답을 받겠다는 옵션.
            httpCon.setDoInput(true);

            OutputStream os = httpCon.getOutputStream();
            os.write(json.getBytes("euc-kr"));
            os.flush();

            // receive response as inputStream
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;

         */
        InputStream is = null;
        String result = "";

        try {
            URL urlCon = new URL(url_temp);
            HttpURLConnection httpCon = (HttpURLConnection)urlCon.openConnection();
            String json = "";


        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "a";
    }

    public static String RESPONSE() throws IOException {
        URL urlCon = new URL(url_temp);
        HttpURLConnection httpCon = (HttpURLConnection)urlCon.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        String returnMsg = in.readLine();
        System.out.println("Aa");
        System.out.println(returnMsg);

        int responseCode = httpCon.getResponseCode();
        if(responseCode == 400){
            System.out.println("400 : 명령실행오류");
        } else if (responseCode == 500) {
            System.out.println("500 : 서버에러");
        } else {
            System.out.println(responseCode);
        }


        return "a";
    }



        public static void main(String[] args) throws MalformedURLException, IOException {

        HttpURLConnection httpConn = testHttpUrlConnection();
        int statusCode = getResponseStatus();
        POST();
        RESPONSE();

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
