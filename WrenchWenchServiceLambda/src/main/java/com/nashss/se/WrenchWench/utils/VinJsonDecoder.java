package com.nashss.se.WrenchWench.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.nashss.se.WrenchWench.exceptions.InvalidVinException;
import org.json.JSONArray;
import org.json.JSONObject;

public class VinJsonDecoder {
    public static void main(String[] args) {

        String vin = "JM1NA3518p1419935";
        String apiUrl = "https://vpic.nhtsa.dot.gov/api/vehicles/DecodeVin/"+vin+"?format=json";

        VinValidatorUtil validatorUtil = new VinValidatorUtil();

        if(!validatorUtil.validateVin(vin)){
            throw new InvalidVinException("Check Sum was incorrect, double-check your VIN and try again.");
        }

        try {
            // Create URL object
            URL url = new URL(apiUrl);

            // Create HttpURLConnection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set request method
            conn.setRequestMethod("GET");

            // Get the response code
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String responseString = response.toString();

            // Print the response
            System.out.println("Response Body: " + responseString);

            JSONObject jsonResponse = new JSONObject(response.toString());

            // Get the "Results" array from the JSONObject
            JSONArray resultsArray = jsonResponse.getJSONArray("Results");

            String make = "";
            String model = "";
            // Extract specific values from the "Results" array
            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject result = resultsArray.getJSONObject(i);
                int variableId = result.getInt("VariableId");
                String variable = result.getString("Variable");
                String value = result.optString("Value");


                if (value != null && !value.isEmpty() && !value.equals("Not Applicable")) {
                    System.out.println("VariableId: " + variableId);
                    System.out.println("Variable: " + variable);
                    System.out.println("Value: " + value + '\n');
                }
            }

            // Close the connection
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
