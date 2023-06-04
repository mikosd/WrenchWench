package com.nashss.se.WrenchWench.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "vehicles")
public class Vehicle {
    private String vin;
    private String make;
    private String model;
    private String year;

    @DynamoDBHashKey(attributeName = "vin")
    public String getVin(){
        return vin;
    }

    public void setVin(String vin){
        this.vin = vin;
    }

    @DynamoDBAttribute(attributeName = "make")
    public String getMake(){
        return make;
    }

    public void setMake(String make){
        this.make = make;
    }

    @DynamoDBAttribute(attributeName = "model")
    public String getModel(){
        return model;
    }

    public void setModel(String model){
        this.model = model;
    }

    @DynamoDBAttribute(attributeName = "year")
    public String getYear(){
        return year;
    }

    public void setYear(String year){
        this.year = year;
    }
}