package com.nashss.se.WrenchWench.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = DeleteRecordRequest.Builder.class)
public class DeleteRecordRequest {

    private final String vin;
    private final String recordId;

    private DeleteRecordRequest(String vin, String recordId){
        this.vin = vin;
        this.recordId = recordId;
    }

    public String getVin(){
        return vin;
    }

    public String getRecordId(){
        return recordId;
    }

    @Override
    public String toString(){
        return "DeleteRecordRequest{" + '\'' +
                ", vin=" + vin + '\'' +
                ", recordId=" + recordId + '\'' +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder{

        private String vin;
        private String recordId;

        public Builder withVin(String vin){
            this.vin = vin;
            return this;
        }

        public Builder withRecordId(String recordId){
            this.recordId = recordId;
            return this;
        }

        public DeleteRecordRequest build(){
            return new DeleteRecordRequest(vin, recordId);
        }
    }
}
