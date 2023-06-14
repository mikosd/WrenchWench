package com.nashss.se.WrenchWench.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = DeleteRecordRequest.Builder.class)
public class DeleteRecordRequest {
    private final String recordId;

    private DeleteRecordRequest(String recordId){
        this.recordId = recordId;
    }

    public String getRecordId(){
        return recordId;
    }

    @Override
    public String toString(){
        return "DeleteRecordRequest{" + "recordId='" + recordId + '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder{
        private String recordId;

        public Builder withRecordId(String recordId){
            this.recordId = recordId;
            return this;
        }

        public DeleteRecordRequest build(){
            return new DeleteRecordRequest(recordId);
        }
    }
}
