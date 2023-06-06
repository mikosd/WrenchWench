package com.nashss.se.WrenchWench.activity.requests;

public class GetAllVehiclesRequest {

    private GetAllVehiclesRequest() {}

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        public GetAllVehiclesRequest build() {
            return new GetAllVehiclesRequest();
        }
    }
}
