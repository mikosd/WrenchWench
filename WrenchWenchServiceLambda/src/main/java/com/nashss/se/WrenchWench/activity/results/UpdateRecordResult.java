package com.nashss.se.WrenchWench.activity.results;

import com.nashss.se.WrenchWench.models.RecordModel;

public class UpdateRecordResult {
    private final RecordModel recordModel;

    private UpdateRecordResult(RecordModel recordModel){
        this.recordModel = recordModel;
    }

    public RecordModel getRecordModel(){
        return recordModel;
    }

    @Override
    public String toString(){
        return "UpdateRecordResult{" +
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

        public UpdateRecordResult build(){
            return new UpdateRecordResult(recordModel);
        }
    }
}
