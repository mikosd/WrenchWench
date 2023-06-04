package com.nashss.se.WrenchWench.activity.results;

import com.nashss.se.WrenchWench.models.VehicleModel;

public class GetVehicleResult {
    private final VehicleModel vehicle;

    private GetVehicleResult(VehicleModel vehicle){
        this.vehicle = vehicle;
    }

    public VehicleModel getVehicle(){
        return vehicle;
    }

    @Override
    public String toString(){
        return "GetVehicleResult{" +
                "vehicle=" + vehicle +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private VehicleModel vehicle;

        public Builder withVehicle(VehicleModel vehicle){
            this.vehicle = vehicle;
            return this;
        }

        public GetVehicleResult build(){
            return new GetVehicleResult(vehicle);
        }
    }
}