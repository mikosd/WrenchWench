package com.nashss.se.WrenchWench.utils;

import com.nashss.se.WrenchWench.activity.CreateVehicleActivity;
import com.nashss.se.WrenchWench.dynamodb.models.Vehicle;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VinUtils {
    private static final int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 0, 1,
            2, 3, 4, 5, 0, 7, 0, 9, 2, 3,
            4, 5, 6, 7, 8, 9 };
    private static final int[] weights = { 8, 7, 6, 5, 4, 3, 2, 10, 0, 9,
            8, 7, 6, 5, 4, 3, 2 };

    public VinUtils() { }

    public static boolean validateVin(String vin){

        char checkDigit = vin.charAt(8);
        int checkSum = 0;

        String vinCheck = vin;
        vinCheck = vinCheck.replaceAll("-", "");
        vinCheck = vinCheck.toUpperCase();

        if(vinCheck.length() != 17){
            throw new RuntimeException("VIN must be 17 characters long");
        }

        for (int i = 0; i<vinCheck.length(); i++) {
            char c = vinCheck.charAt(i);
            int val;
            int charWeight = weights[i];
            if(c >= 'A' && c <='Z'){
                val = values[c - 'A'];
                if(val == 0)
                    throw new RuntimeException("Illegal character: " + c);
            } else if (c >= '0' && c <= '9') {
                val = c - '0';
            }else throw new RuntimeException("Illegal character: " + c);

            checkSum += charWeight * val;
        }

        checkSum = checkSum % 11;
        if(checkDigit != 'X' && (checkDigit < '0' || checkDigit > '9')){
            throw new RuntimeException("Illegal check digit: " + checkDigit);
        }
        return (checkSum == 10 && checkDigit == 'X') || checkSum == checkDigit - '0';
    }

    public static Vehicle buildVehicleFromVin(String vin) {

        String apiUrl = "https://vpic.nhtsa.dot.gov/api/vehicles/DecodeVin/" + vin + "?format=json";

        Vehicle vehicle = new Vehicle();
        try {
            // Create URL object
            URL url = new URL(apiUrl);

            // Create HttpURLConnection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set request method
            conn.setRequestMethod("GET");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());

            // Get the "Results" array from the JSONObject
            JSONArray resultsArray = jsonResponse.getJSONArray("Results");

            // Extract specific values from the "Results" array
            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject result = resultsArray.getJSONObject(i);
                int variableId = result.getInt("VariableId");
                String value = result.optString("Value");

                vehicle.setVin(vin);

                switch (variableId) {
                    case 5:
                        vehicle.setBodyClass(value);
                        System.out.println("BodyClass - " + value);
                        break;
                    case 9:
                        vehicle.setEngineCylinders(value);
                        break;
                    case 14:
                        vehicle.setNumOfDoors(value);
                        break;
                    case 24:
                        vehicle.setFuelType(value);
                        break;
                    case 26:
                        vehicle.setMake(value);
                        break;
                    case 27:
                        vehicle.setManufacturerName(value);
                        break;
                    case 28:
                        vehicle.setModel(value);
                        System.out.println("Model - " + value);
                        break;
                    case 29:
                        vehicle.setYear(value);
                        break;
                    case 31:
                        vehicle.setPlantCity(value);
                        break;
                    case 39:
                        vehicle.setVehicleType(value);
                        break;
                    case 71:
                        vehicle.setEngineHP(value);
                        break;
                    case 75:
                        vehicle.setPlantCountry(value);
                        break;
                    case 77:
                        vehicle.setPlantState(value);
                        break;
                    default:
                }
            }

            // Close the connection
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vehicle;
    }
}
