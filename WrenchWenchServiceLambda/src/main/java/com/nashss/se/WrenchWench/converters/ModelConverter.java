package com.nashss.se.WrenchWench.converters;

import com.nashss.se.WrenchWench.dynamodb.models.Vehicle;
import com.nashss.se.WrenchWench.exceptions.InvalidVinException;
import com.nashss.se.WrenchWench.models.VehicleModel;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
    public VehicleModel toVehicleModel(Vehicle vehicle) throws InvalidVinException {
        String vin = vehicle.getVin();
        if(vin == null)
            throw new InvalidVinException();

        return VehicleModel.builder()
                .withVin(vehicle.getVin())
                .withMake(vehicle.getMake())
                .withModel(vehicle.getModel())
                .withYear(vehicle.getYear())
                .build();
    }

    public List<VehicleModel> toVehicleModelList(List<Vehicle> allVehicles) {
        List<VehicleModel> vehicleModelsList = new ArrayList<>();

        for (Vehicle vehicle : allVehicles){
            vehicleModelsList.add(toVehicleModel(vehicle));
        }
        return vehicleModelsList;
    }
}