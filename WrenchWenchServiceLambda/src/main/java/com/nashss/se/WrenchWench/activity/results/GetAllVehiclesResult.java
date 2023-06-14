package com.nashss.se.WrenchWench.activity.results;

import com.nashss.se.WrenchWench.models.VehicleModel;

import java.util.ArrayList;
import java.util.List;

public class GetAllVehiclesResult {
    private final List<VehicleModel> vehicleModelList;

    private GetAllVehiclesResult(List<VehicleModel> vehicleModelList){
        this.vehicleModelList = vehicleModelList;
    }

    public List<VehicleModel> getVehicleList(){
        return new ArrayList<>(vehicleModelList);
    }

    @Override
    public String toString(){
        return "GetAllVehiclesResult{" +
                "vehicleModelList=" + vehicleModelList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private List<VehicleModel> vehicleModelList;

        public Builder withVehicleList(List<VehicleModel> vehicleModelList){
            this.vehicleModelList = vehicleModelList;
            return this;
        }

        public GetAllVehiclesResult build(){
            return new GetAllVehiclesResult(vehicleModelList);
        }
    }
}
