package com.nashss.se.WrenchWench.converters;

import java.util.HashMap;
import java.util.Map;

public class VinDecoder {
    private String vin;

    //World Manufacturer Identifier Digits 1-3 of the VIN
    private String wmi;

    //Vehicle Descriptor Section - Digits 4-9 of the VIN
    private String vds;

    //Vehicle Identifier Section - Digits 10-17 of the VIN
    private String vis;

    private char checkDigit;
    private char modelYear;
    private char plantCode;

    public static Map<Character, Integer> YEAR_INDEX = new HashMap<>();
    public static char[] YEAR_CHARS = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'R', 'S',
            'T', 'V', 'W', 'X', 'Y', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    private VinDecoder(String vin){
        validateVin(vin);
        this.vin = vin;
        this.wmi = vin.substring(0,2);
        this.vds = vin.substring(3,8);
        this.vis = vin.substring(9,16);
        this.checkDigit = vin.charAt(8);
    }

    public void validateVin(String vin){
        int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 0, 1,
                2, 3, 4, 5, 0, 7, 0, 9, 2, 3,
                4, 5, 6, 7, 8, 9 };
        int[] weights = { 8, 7, 6, 5, 4, 3, 2, 10, 0, 9,
                8, 7, 6, 5, 4, 3, 2 };

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
            int weight = weights[i];
            if(c >= 'A' && c <='Z'){
                val = values[c - 'A'];
                if(val == 0)
                    throw new RuntimeException("Illegal character: " + c);
            } else if (c >= '0' && c <= '9') {
                val = c - '0';
            }else throw new RuntimeException("Illegal character: " + c);

            checkSum = checkSum + weight * val;
        }

        checkSum = checkSum % 11;
        if(checkDigit != 'X' && (checkDigit< '0' || checkDigit > '9')){
            throw new RuntimeException("Illegal check digit: " + checkDigit);
        }
        //if(checkSum == 10 && checkDigit == 'X') {}
    }

    public String getYear(){


        if(Character.isDigit(vin.charAt(6))){

        }
        return "";
    }

}