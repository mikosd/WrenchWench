package com.nashss.se.WrenchWench.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "records")
public class Records {
    private String vin;
    private String recordId;
    private String timestamp;
    private String description;
    private String priorityLevel;
    private String status;

    @DynamoDBHashKey(attributeName = "vin")
    public String getVin() {
        return vin;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }

    @DynamoDBRangeKey(attributeName = "recordId")
    public String getRecordId() {
        return recordId;
    }
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
    @DynamoDBAttribute(attributeName = "timestamp")
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "priorityLevel")
    public String getPriorityLevel() {
        return priorityLevel;
    }
    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    @DynamoDBAttribute(attributeName = "status")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
