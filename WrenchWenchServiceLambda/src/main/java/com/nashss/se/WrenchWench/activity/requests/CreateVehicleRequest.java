package com.nashss.se.WrenchWench.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateVehicleRequest.Builder.class)
public class CreateVehicleRequest {
    private final String vin;

    public CreateVehicleRequest(String vin) {

        this.vin = vin;
    }

    public String getVin() {
        return vin;
    }

    @Override
    public String toString() {
        return "CreateVehicleRequest{" + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder(){
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder{
        private String vin;

        public Builder withVin(String vin){
            this.vin = vin;
            return this;
        }

        public CreateVehicleRequest build(){
            return new CreateVehicleRequest(vin);
        }
    }

}
