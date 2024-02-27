package com.example.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.model.SessionDetails;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LoginService {

    @Value("${login.api.url}") // Define the API endpoint URL in application.properties
    private String apiUrl;

    // Replace <YOUR-API-KEY> with your actual API key
    private static final String API_KEY = "<YOUR-API-KEY>";

    public SessionDetails login(String username, String password) throws UnsupportedEncodingException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(apiUrl);

        // Set the API key as a request header for authentication
        httpPost.setHeader("Authorization", "Bearer " + API_KEY);

        // Prepare the request body
        String requestBody = "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }";
        httpPost.setEntity(new StringEntity(requestBody, "application/json", "UTF-8"));

        try {
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // Read the response content
                    InputStream inputStream = entity.getContent();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder responseBody = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        responseBody.append(line);
                    }

                    // Parse the response JSON
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(responseBody.toString());

                    // Extract the session details from the response JSON
                    String sessionId = jsonNode.get("session_id").asText();
                    // You can extract any other session-related details here

                    // Create and return the SessionDetails object
                    SessionDetails sessionDetails = new SessionDetails();
                    sessionDetails.setSessionId(sessionId);

                    return sessionDetails;
                }
            } else {
                // Handle error scenarios
                System.out.println("API request failed with status code: " + statusCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}