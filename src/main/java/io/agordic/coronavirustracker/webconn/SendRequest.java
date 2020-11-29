package io.agordic.coronavirustracker.webconn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class SendRequest {
    private static HttpURLConnection con;
    private static final  String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    private static final  String CONTENT_TYPE = "Content-Type";
    private static final String AUTHORIZATION = "Authorization";
    private static final String GET = "GET";

    @Value("${isbndb.rest.key}")
    private String ISBN_REST_KEY;

    public void send(String urlStr){
        try {
            URL url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
            con.setRequestProperty(AUTHORIZATION, ISBN_REST_KEY);
            con.setRequestMethod(GET);

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()
            ))) {
                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            System.out.println(content.toString());
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            con.disconnect();
        }

    }
}
