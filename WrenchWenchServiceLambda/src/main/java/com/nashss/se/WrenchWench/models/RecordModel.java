package com.nashss.se.WrenchWench.models;

import java.util.Objects;

public class RecordModel {

    private final String vin;
    private final String recordId;
    private final String timestamp;
    private final String description;
    private final String priorityLevel;
    private final String status;

    private RecordModel(String vin, String recordId, String timestamp,
                        String description, String priorityLevel, String status){
        this.vin = vin;
        this.recordId = recordId;
        this.timestamp = timestamp;
        this.description = description;
        this.priorityLevel = priorityLevel;
        this.status = status;
    }

    public String getVin() {
        return vin;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordModel)) return false;
        RecordModel that = (RecordModel) o;
        return vin.equals(that.vin) && recordId.equals(that.recordId) && timestamp.equals(that.timestamp) && description.equals(that.description) && priorityLevel.equals(that.priorityLevel) && status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin, recordId, timestamp, description, priorityLevel, status);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String vin;
        private String recordId;
        private String timestamp;
        private String description;
        private String priorityLevel;
        private String status;

        public Builder withVin(String vin){
            this.vin = vin;
            return this;
        }

        public Builder withRecordId(String recordId){
            this.recordId = recordId;
            return this;
        }

        public Builder withTimestamp(String timestamp){
            this.timestamp = timestamp;
            return this;
        }

        public Builder withDescription(String description){
            this.description = description;
            return this;
        }

        public Builder withPriorityLevel(String priorityLevel){
            this.priorityLevel = priorityLevel;
            return this;
        }

        public Builder withStatus(String status){
            this.status = status;
            return this;
        }

        public RecordModel build(){
            return new RecordModel(vin, recordId, timestamp, description, priorityLevel, status);
        }
    }
}