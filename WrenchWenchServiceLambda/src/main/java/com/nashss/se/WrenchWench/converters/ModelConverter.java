package com.nashss.se.WrenchWench.converters;

import com.nashss.se.WrenchWench.dynamodb.models.Records;
import com.nashss.se.WrenchWench.dynamodb.models.Vehicle;
import com.nashss.se.WrenchWench.exceptions.InvalidVinException;
import com.nashss.se.WrenchWench.models.RecordModel;
import com.nashss.se.WrenchWench.models.VehicleModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


public class ModelConverter {

    @Inject
    public ModelConverter(){}

    public VehicleModel toVehicleModel(Vehicle vehicle) throws InvalidVinException {
        String vin = vehicle.getVin();
        if(vin == null)
            throw new InvalidVinException();

        return VehicleModel.builder()
                .withVin(vehicle.getVin())
                .withMake(vehicle.getMake())
                .withModel(vehicle.getModel())
                .withYear(vehicle.getYear())
                .withBodyClass(vehicle.getBodyClass())
                .withVehicleType(vehicle.getVehicleType())
                .withNumOfDoors(vehicle.getNumOfDoors())
                .withManufacturerName(vehicle.getManufacturerName())
                .withPlantCountry(vehicle.getPlantCountry())
                .withPlantState(vehicle.getPlantState())
                .withPlantCity(vehicle.getPlantCity())
                .withEngineCylinders(vehicle.getEngineCylinders())
                .withEngineHP(vehicle.getEngineHP())
                .withFuelType(vehicle.getFuelType())
                .build();
    }

    public List<VehicleModel> toVehicleModelList(List<Vehicle> allVehicles) {
        List<VehicleModel> vehicleModelsList = new ArrayList<>();

        for (Vehicle vehicle : allVehicles) {
            vehicleModelsList.add(toVehicleModel(vehicle));
        }
        return vehicleModelsList;
    }

    public List<RecordModel> toRecordModelList(List<Records> recordsList) {
        List<RecordModel> recordModels = new ArrayList<>();

        for (Records record : recordsList) {
            recordModels.add(toRecordModel(record));
        }
        return recordModels;
    }

    public RecordModel toRecordModel(Records newRecord) {
        String vin = newRecord.getVin();
        if(vin == null) {
            throw new InvalidVinException("InvalidVinException from ModelConverter. VIN cannot be null");
        }

        return RecordModel.builder()
                .withVin(newRecord.getVin())
                .withRecordId(newRecord.getRecordId())
                .withTimestamp(newRecord.getTimestamp())
                .withDescription(newRecord.getDescription())
                .withPriorityLevel(newRecord.getPriorityLevel())
                .withStatus(newRecord.getStatus())
                .build();
    }
}