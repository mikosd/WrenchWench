package com.nashss.se.WrenchWench.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecordIdGenerator {

    /**
     *
     * @param vin Vehicle Identification Number
     * @return recordID
     */
    public static String generateRecordId(String vin) {
        //The last 8 characters of the VIN are known as the Vehicle Identifier Section
        String vis = vin.substring(9);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String timestamp = now.format(formatter);

        //A combination of the VIS and a timestamp make up the recordId
        String recordId = vis + timestamp;

        return recordId;
    }

}
