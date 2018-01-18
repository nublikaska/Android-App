package com.example.denis.holodos.services;

import com.example.denis.holodos.modules.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class AuthorizationService {
    private HttpURLConnection urlConnection = null;
    private BufferedReader reader = null;
    private String resultJson = "";

    public boolean addUser(String login, String password) {
        try {
            String ACCEPT = "application/json";
            String CONTENT_TYPE = "application/json";
            String url = "http://192.168.42.62:8080/service/api/users";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", ACCEPT);
            con.setRequestProperty("Content-Type", CONTENT_TYPE);

            con.setDoOutput(true);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", login);
            jsonObject.put("password", password);

            OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
            wr.write(jsonObject.toString());

            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String authenticationUser(String login) {
        try {
            URL url = new URL("http://192.168.42.62:8080/service/api/users/" + login);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return resultJson;
    }

    public boolean checkUser(User user, String login, String password) {
        return login.equals(user.getLogin()) && password.equals(user.getPassword());
    }
}
