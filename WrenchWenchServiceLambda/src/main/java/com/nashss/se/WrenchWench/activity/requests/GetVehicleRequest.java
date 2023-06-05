package com.nashss.se.WrenchWench.activity.requests;

import com.nashss.se.WrenchWench.models.VehicleModel;

public class GetVehicleRequest {
    private final String vin;

    private GetVehicleRequest(String vin){
        this.vin = vin;
    }

    public String getVin(){
        return vin;
    }

    @Override
    public String toString(){
        return "GetVehicleRequest{" +
                "vin='" + vin + '\'' +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String vin;

        public Builder withVin(String vin) {
            this.vin = vin;
            return this;
        }



        public GetVehicleRequest build() {
            return new GetVehicleRequest(vin);
        }
    }
}