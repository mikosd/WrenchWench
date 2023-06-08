package com.nashss.se.WrenchWench.activity.results;

import com.nashss.se.WrenchWench.dynamodb.models.Vehicle;
import com.nashss.se.WrenchWench.models.VehicleModel;

public class CreateVehicleResult {
    private final VehicleModel vehicle;

    private CreateVehicleResult(VehicleModel vehicle){
        this.vehicle = vehicle;
    }

    public VehicleModel getVehicle(){
        return vehicle;
    }

    @Override
    public String toString(){
        return "CreateVehicleResult{" +
                "vehicle=" + vehicle +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private VehicleModel vehicle;

        public Builder withVehicle(VehicleModel vehicle){
            this.vehicle = vehicle;
            return this;
        }

        public CreateVehicleResult build(){
            return new CreateVehicleResult(vehicle);
        }
    }
}
