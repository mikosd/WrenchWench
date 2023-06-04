package com.nashss.se.WrenchWench.models;

public class VehicleModel {

    private final String vin;
    private final String make;
    private final String model;
    private final String year;

    private VehicleModel(String vin, String make, String model, String year){
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
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

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String vin;
        private String make;
        private String model;
        private String year;

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

        public VehicleModel build(){
            return new VehicleModel(vin, make, model, year);
        }
    }
}