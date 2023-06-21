package com.nashss.se.WrenchWench.activity.results;

import com.nashss.se.WrenchWench.models.RecordModel;

public class DeleteRecordResult {
    private final RecordModel record;

    private DeleteRecordResult(RecordModel record){
        this.record = record;
    }

    public RecordModel getRecord(){
        return record;
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
            //Gson gson = new Gson();
            //this.record = Gson.toJson(record, RecordModel.class);

            this.record = record;
            return this;
        }

        public DeleteRecordResult build(){
            return new DeleteRecordResult(record);
        }
    }
}
