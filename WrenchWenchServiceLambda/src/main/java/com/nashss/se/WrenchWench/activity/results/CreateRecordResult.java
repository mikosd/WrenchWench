package com.nashss.se.WrenchWench.activity.results;

import com.nashss.se.WrenchWench.models.RecordModel;

public class CreateRecordResult {

    private final RecordModel recordModel;

    private CreateRecordResult(RecordModel recordModel){
        this.recordModel = recordModel;
    }

    public RecordModel getRecordModel(){
        return recordModel;
    }

    @Override
    public String toString(){
        return "CreateRecordResult{" +
                "recordModel=" + recordModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private RecordModel recordModel;

        public Builder withRecordModel(RecordModel recordModel){
            this.recordModel = recordModel;
            return this;
        }

        public CreateRecordResult build(){
            return new CreateRecordResult(recordModel);
        }
    }
}
