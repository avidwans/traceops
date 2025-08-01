package ai;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LLMClient {
    private static final String API_URL = "http://localhost:1234/v1/chat/completions";

    private static final String MODEL_NAME = "google/gemma-3-4b";

    public static String sendPromptToModel(String prompt) throws IOException {
        // Construct JSON payload
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);

        JSONArray messages = new JSONArray();
        messages.put(message);

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL_NAME); // or just leave blank; LM Studio may ignore this
        requestBody.put("messages", messages);
        requestBody.put("temperature", 0.7);

        // Setup connection
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        // Send request
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes("utf-8");
            os.write(input);
        }

        // Read response
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
        }

        // Parse response
        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray choices = jsonResponse.getJSONArray("choices");
        JSONObject firstChoice = choices.getJSONObject(0);
        JSONObject messageObject = firstChoice.getJSONObject("message");

        return messageObject.getString("content");
    }

    public static String queryChatBot(String logContent, String userQuery) throws IOException {
        JSONArray messages = new JSONArray();

        // Optional system message to guide the LLM
        messages.put(new JSONObject()
                .put("role", "system")
                .put("content", "You are a Java log analysis assistant. You analyze exception stack traces from a log file and answer questions about them."));

        // Provide the full log content as context
        messages.put(new JSONObject()
                .put("role", "user")
                .put("content", "Here is the log file:\n\n" + logContent));

        // Append user query
        messages.put(new JSONObject()
                .put("role", "user")
                .put("content", userQuery));

        JSONObject payload = new JSONObject();
        payload.put("model", MODEL_NAME);
        payload.put("messages", messages);
        payload.put("temperature", 0.2);

        System.out.println(payload);

        // Setup connection
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        // Send request
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = payload.toString().getBytes("utf-8");
            os.write(input);
        }

        // Read response
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
        }

        // Parse response
        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray choices = jsonResponse.getJSONArray("choices");
        JSONObject firstChoice = choices.getJSONObject(0);
        JSONObject messageObject = firstChoice.getJSONObject("message");

        return messageObject.getString("content");

    }
}