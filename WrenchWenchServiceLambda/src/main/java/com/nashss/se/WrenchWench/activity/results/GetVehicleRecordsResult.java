package com.nashss.se.WrenchWench.activity.results;

import com.nashss.se.WrenchWench.models.RecordModel;

import java.util.ArrayList;
import java.util.List;


public class GetVehicleRecordsResult {
    private final List<RecordModel> recordsList;

    private GetVehicleRecordsResult(List<RecordModel> recordsList){
        this.recordsList = recordsList;
    }

    public List<RecordModel> getRecordsList(){
        return new ArrayList<>(recordsList);
    }

    @Override
    public String toString(){
        return "GetVehicleRecordsResult{" +
                "recordsList=" + recordsList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private List<RecordModel> recordsList;

        public Builder withRecordsList(List<RecordModel> recordsList){
            this.recordsList = new ArrayList<>(recordsList);
            return this;
        }

        public GetVehicleRecordsResult build(){
            return new GetVehicleRecordsResult(recordsList);
        }
    }
}
