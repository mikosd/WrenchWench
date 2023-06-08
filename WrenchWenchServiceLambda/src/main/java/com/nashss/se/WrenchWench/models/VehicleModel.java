package com.nashss.se.WrenchWench.models;

public class VehicleModel {

    private final String vin;
    private final String make;
    private final String model;
    private final String year;
    private final String bodyClass;
    private final String vehicleType;
    private final String numOfDoors;
    private final String manufacturerName;
    private final String plantCountry;
    private final String plantState;
    private final String plantCity;
    private final String engineCylinders;
    private final String engineSize;
    private final String engineHP;
    private final String fuelType;


    private VehicleModel(String vin, String make, String model, String year,
                         String bodyClass, String vehicleType, String numOfDoors,
                         String manufacturerName, String plantCountry, String plantState,
                         String plantCity, String engineCylinders, String engineSize,
                         String engineHP, String fuelType){
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.bodyClass = bodyClass;
        this.vehicleType = vehicleType;
        this.numOfDoors = numOfDoors;
        this.manufacturerName = manufacturerName;
        this.plantCountry = plantCountry;
        this.plantState = plantState;
        this.plantCity = plantCity;
        this.engineCylinders = engineCylinders;
        this.engineSize = engineSize;
        this.engineHP = engineHP;
        this.fuelType = fuelType;
    }

    public String getVin(){
        return vin;
    }

    public String getMake(){
        return make;
    }

    public String getModel(){
        return model;
    }

    public String getYear(){
        return year;
    }

    public String getBodyClass() {
        return bodyClass;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getNumOfDoors() {
        return numOfDoors;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getPlantCountry() {
        return plantCountry;
    }

    public String getPlantState() {
        return plantState;
    }

    public String getPlantCity() {
        return plantCity;
    }

    public String getEngineCylinders() {
        return engineCylinders;
    }

    public String getEngineSize() {
        return engineSize;
    }

    public String getEngineHP() {
        return engineHP;
    }

    public String getFuelType(){
        return fuelType;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
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

        public Builder() {
        }


        public Builder withVin(String vin){
            this.vin = vin;
            return this;
        }

        public Builder withMake(String make){
            this.make = make;
            return this;
        }

        public Builder withModel(String model){
            this.model = model;
            return this;
        }

        public Builder withYear(String year){
            this.year = year;
            return this;
        }

        public Builder withBodyClass(String bodyClass){
            this.bodyClass = bodyClass;
            return this;
        }

        public Builder withVehicleType(String vehicleType){
            this.vehicleType = vehicleType;
            return this;
        }

        public Builder withNumOfDoors(String numOfDoors){
            this.numOfDoors = numOfDoors;
            return this;
        }

        public Builder withManufacturerName(String manufacturerName){
            this.manufacturerName = manufacturerName;
            return this;
        }

        public Builder withPlantCountry(String plantCountry){
            this.plantCountry = plantCountry;
            return this;
        }

        public Builder withPlantState(String plantState){
            this.plantState = plantState;
            return this;
        }

        public Builder withPlantCity(String plantCity){
            this.plantCity = plantCity;
            return this;
        }

        public Builder withEngineCylinders(String engineCylinders){
            this.engineCylinders = engineCylinders;
            return this;
        }

        public Builder withEngineSize(String engineSize){
            this.engineSize = engineSize;
            return this;
        }

        public Builder withEngineHP(String engineHP){
            this.engineHP = engineHP;
            return this;
        }

        public Builder withFuelType(String fuelType){
            this.fuelType = fuelType;
            return this;
        }

        public VehicleModel build(){
            return new VehicleModel(vin, make, model, year,
                    bodyClass, vehicleType, numOfDoors,
                    manufacturerName, plantCountry, plantState,
                    plantCity, engineCylinders, engineSize,
                    engineHP, fuelType);
        }
    }
}