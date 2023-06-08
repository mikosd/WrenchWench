package com.nashss.se.WrenchWench.utils;

public class VinValidatorUtil {
    public VinValidatorUtil(){}

    public boolean validateVin(String vin){
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
}