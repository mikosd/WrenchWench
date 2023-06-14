package com.nashss.se.WrenchWench.activity.results;

import com.nashss.se.WrenchWench.dynamodb.models.Records;
import com.nashss.se.WrenchWench.models.RecordModel;

public class DeleteRecordResult {
    private final RecordModel record;

    private DeleteRecordResult(RecordModel record){
        this.record = record;
    }

    @Override
    public String toString(){
        return "DeleteRecordResult{" + "record=" + record + '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private RecordModel record;

        public Builder withRecord(RecordModel record){
            this.record = record;
            return this;
        }

        public DeleteRecordResult build(){
            return new DeleteRecordResult(record);
        }
    }
}
