package com.nashss.se.WrenchWenchService.test.helper;

import com.nashss.se.WrenchWench.dynamodb.models.Records;
import com.nashss.se.WrenchWench.models.RecordModel;

import java.util.List;

public class RecordsTestHelper {

    public RecordsTestHelper(){}

    public static Records generateRecord(){
        Records record = new Records();
        record.setVin("vin");
        record.setRecordId("recordId");
        record.setDescription("description");
        record.setStatus("status");
        record.setTimestamp("timestamp");

        return record;
    }

    public static boolean AssertRecordsModelEquals(List<Records> recordsList, List<RecordModel> recordModels){
        for(int i = 0; i<recordModels.size(); i++){
            if(!recordsList.get(i).getVin().equals(recordModels.get(i).getVin())){
                return false;
            }

            if(!recordsList.get(i).getRecordId().equals(recordModels.get(i).getRecordId())){
                return false;
            }

            if(!recordsList.get(i).getDescription().equals(recordModels.get(i).getDescription())){
                return false;
            }

            if(!recordsList.get(i).getStatus().equals(recordModels.get(i).getStatus())){
                return false;
            }

            if(!recordsList.get(i).getTimestamp().equals(recordModels.get(i).getTimestamp())){
                return false;
            }
        }
        return true;
    }
}
