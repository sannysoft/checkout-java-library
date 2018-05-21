package com.checkout.helpers;

import com.checkout.api.services.shared.Response;
import com.checkout.api.services.shared.ResponseError;
import com.checkout.utilities.HttpMethods;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ApiHttpClient {
    private static Logger logger = null;
    private Gson gson;

    public ApiHttpClient(Gson gsonInstance) {
        gson = gsonInstance;
    }

    public static void SetupLogger() throws IOException {
        if (logger == null) {
            logger = Logger.getLogger("Log");
            SimpleFormatter formatter = new SimpleFormatter();
            FileHandler fh = new FileHandler("Log.log", true);
            fh.setFormatter(formatter);
            logger.addHandler(fh);
        }
    }

    private <T> Response<T> sendRequest(String uri, String apiKey, String method, String payload, Class<T> returnType) throws IOException, JsonSyntaxException {
        Response<T> response;
        String lines;
        T jsonObject;
        BufferedReader reader = null;
        OutputStreamWriter outputStreamWriter = null;
        HttpURLConnection connection = null;

        URL url = new URL(uri);

        if (AppSettings.debugMode) {
            logger.info("**Request**  	" + method + ":	" + uri);
            logger.info("**Payload**	" + payload);
        }

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(AppSettings.connectTimeout * 1000);
            connection.setReadTimeout(AppSettings.readTimeout * 1000);
            connection.setRequestMethod(method);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", apiKey);
            connection.setDoOutput(true);

            connection.connect();

            if (HttpMethods.Post.equals(method) || HttpMethods.Put.equals(method)) {
                outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());

                outputStreamWriter.write(payload);
                outputStreamWriter.flush();
            }

            int httpStatus = connection.getResponseCode();

            if (httpStatus == 200) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                lines = reader.readLine();

                if (AppSettings.debugMode) {
                    logger.info("** HttpResponse**  Status 200 OK :" + lines);
                }

                jsonObject = gson.fromJson(lines, returnType);

                response = new Response<>(jsonObject);
                response.httpStatus = httpStatus;
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));

                lines = reader.readLine();

                ResponseError error = gson.fromJson(lines, ResponseError.class);

                response = new Response<>(null);

                response.error = error;
                response.hasError = true;
                response.httpStatus = httpStatus;

                if (AppSettings.debugMode) {
                    logger.info("** HttpResponse**  StatusError: " + response.httpStatus + lines);
                }
            }

            reader.close();

            return response;

        } catch (IOException e) {
            if (AppSettings.debugMode) {
                logger.info("** Exception ** " + e.toString());
            }
            throw e;
        } finally {

            if (reader != null) {
                reader.close();
            }

            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }

            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    public <T> Response<T> postRequest(String url, String key, String payload, Class<T> returnType) throws JsonSyntaxException, IOException {
        return sendRequest(url, key, HttpMethods.Post, payload, returnType);
    }

    public <T> Response<T> putRequest(String url, String key, String payload, Class<T> returnType) throws JsonSyntaxException, IOException {
        return sendRequest(url, key, HttpMethods.Put, payload, returnType);
    }

    public <T> Response<T> getRequest(String url, String key, Class<T> returnType) throws JsonSyntaxException, IOException {
        return sendRequest(url, key, HttpMethods.Get, null, returnType);
    }

    public <T> Response<T> deleteRequest(String url, String key, Class<T> returnType) throws JsonSyntaxException, IOException {
        return sendRequest(url, key, HttpMethods.Delete, null, returnType);
    }

}