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
    private String bodyClass;
    private String vehicleType;
    private String numOfDoors;
    private String manufacturerName;
    private String plantCountry;
    private String plantState;
    private String plantCity;
    private String engineCylinders;
    private String engineSize;
    private String engineHP;
    private String fuelType;


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

    @DynamoDBAttribute(attributeName = "bodyClass")
    public String getBodyClass() {
        return bodyClass;
    }
    public void setBodyClass(String bodyClass) {
        this.bodyClass = bodyClass;
    }

    @DynamoDBAttribute(attributeName = "vehicleType")
    public String getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @DynamoDBAttribute(attributeName = "numOfDoors")
    public String getNumOfDoors() {
        return numOfDoors;
    }
    public void setNumOfDoors(String numOfDoors) {
        this.numOfDoors = numOfDoors;
    }

    @DynamoDBAttribute(attributeName = "manufacturerName")
    public String getManufacturerName() {
        return manufacturerName;
    }
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    @DynamoDBAttribute(attributeName = "plantCountry")
    public String getPlantCountry() {
        return plantCountry;
    }
    public void setPlantCountry(String plantCountry) {
        this.plantCountry = plantCountry;
    }

    @DynamoDBAttribute(attributeName = "plantState")
    public String getPlantState() {
        return plantState;
    }
    public void setPlantState(String plantState) {
        this.plantState = plantState;
    }

    @DynamoDBAttribute(attributeName = "plantCity")
    public String getPlantCity() {
        return plantCity;
    }
    public void setPlantCity(String plantCity) {
        this.plantCity = plantCity;
    }

    @DynamoDBAttribute(attributeName = "engineCylinders")
    public String getEngineCylinders() {
        return engineCylinders;
    }
    public void setEngineCylinders(String engineCylinders) {
        this.engineCylinders = engineCylinders;
    }

    @DynamoDBAttribute(attributeName = "engineSize")
    public String getEngineSize() {
        return engineSize;
    }
    public void setEngineSize(String engineSize) {
        this.engineSize = engineSize;
    }

    @DynamoDBAttribute(attributeName = "engineHP")
    public String getEngineHP() {
        return engineHP;
    }
    public void setEngineHP(String engineHP) {
        this.engineHP = engineHP;
    }

    @DynamoDBAttribute(attributeName = "FuelType")
    public String getFuelType() {
        return fuelType;
    }
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}