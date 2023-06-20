package com.nashss.se.WrenchWench.activity.requests;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateRecordRequest.Builder.class)
public class UpdateRecordRequest {
    private final String vin;
    private final String recordId;
    private final String description;
    private final String status;
    private final String priorityLevel;

    public UpdateRecordRequest(String vin, String recordId, String description, String status, String priorityLevel){
        this.vin = vin;
        this.recordId = recordId;
        this.description = description;
        this.status = status;
        this.priorityLevel = priorityLevel;
    }

    public String getVin() {
        return vin;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    @Override
    public String toString() {
        return "UpdateRecordRequest{" + '\''+
                "vin='" + vin  + '\'' +
                ", recordId='" + recordId + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", priorityLevel='" + priorityLevel + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder(){
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder{
        private String vin;
        private String recordId;
        private String description;
        private String status;
        private String priorityLevel;

        public Builder withVin(String vin){
            this.vin = vin;
            return this;
        }

        public Builder withRecordId(String recordId){
            this.recordId = recordId;
            return this;
        }

        public Builder withDescription(String description){
            this.description = description;
            return this;
        }

        public Builder withStatus(String status){
            this.status = status;
            return this;
        }

        public Builder withPriorityLevel(String priorityLevel){
            this.priorityLevel = priorityLevel;
            return this;
        }

        public UpdateRecordRequest build(){
            return new UpdateRecordRequest(vin, recordId, description, status, priorityLevel);
        }
    }
}
