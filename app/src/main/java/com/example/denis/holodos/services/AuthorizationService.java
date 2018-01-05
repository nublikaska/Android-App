package com.example.denis.holodos.services;

import com.example.denis.holodos.modules.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class AuthorizationService {

    public boolean addUser(String login, String password) {
        try {
            String ACCEPT = "application/json";
            String CONTENT_TYPE = "application/json";
            String url = "http://localhost:8080/service/api/users";

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

    public User authenticationUser(String login) {
        try {
            String url = "http://localhost:8080/service/api/users/" + login;

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new ParameterNamesModule())
                    .registerModule(new Jdk8Module())
                    .registerModule(new JavaTimeModule());
            objectMapper.findAndRegisterModules();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.readValue(response.toString(), User.class);
        } catch (ProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkUser(User user, String login, String password) {
        return login.equals(user.getLogin()) && password.equals(user.getPassword());
    }
}
