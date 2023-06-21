package com.nashss.se.WrenchWenchService.test.helper;

import com.nashss.se.WrenchWench.dynamodb.models.Vehicle;
import com.nashss.se.WrenchWench.models.VehicleModel;

import java.util.List;

public class VehicleTestHelper {

    public static Vehicle generateVehicle(){
        Vehicle vehicle = new Vehicle();
        vehicle.setVin("JM1NB353X40403333");
        vehicle.setMake("MAZDA");
        vehicle.setModel("MX-5 Miata");
        vehicle.setYear("2004");
        vehicle.setBodyClass("Convertible/Cabriolet");
        vehicle.setVehicleType("PASSENGER CAR");
        vehicle.setNumOfDoors("2");
        vehicle.setManufacturerName("MAZDA MOTOR CORPORATION");
        vehicle.setPlantCountry("JAPAN");
        vehicle.setPlantState(null);
        vehicle.setPlantCity("HIROSHIMA");
        vehicle.setEngineCylinders("4");
        vehicle.setEngineSize("1.6L");
        vehicle.setEngineHP("128");
        vehicle.setFuelType("Gasoline");

        return vehicle;
    }

    public static boolean AssertVehicleModelEquals(List<Vehicle> vehicleList, List<VehicleModel> modelList){
        for(int i = 0; i<modelList.size(); i++){
            if(!modelList.get(i).getVin().equals(vehicleList.get(i).getVin()))
                return false;
            if(!modelList.get(i).getMake().equals(vehicleList.get(i).getMake()))
                return false;
            if(!modelList.get(i).getModel().equals(vehicleList.get(i).getModel()))
                return false;
            if(!modelList.get(i).getYear().equals(vehicleList.get(i).getYear()))
                return false;
            if(!modelList.get(i).getBodyClass().equals(vehicleList.get(i).getBodyClass()))
                return false;
            if(!modelList.get(i).getVehicleType().equals(vehicleList.get(i).getVehicleType()))
                return false;
            if(!modelList.get(i).getNumOfDoors().equals(vehicleList.get(i).getNumOfDoors()))
                return false;
            if(!modelList.get(i).getManufacturerName().equals(vehicleList.get(i).getManufacturerName()))
                return false;
            if(!modelList.get(i).getPlantCountry().equals(vehicleList.get(i).getPlantCountry()))
                return false;
            if(!modelList.get(i).getPlantState().equals(vehicleList.get(i).getPlantState()))
                return false;
            if(!modelList.get(i).getPlantCity().equals(vehicleList.get(i).getPlantCity()))
                return false;
            if(!modelList.get(i).getEngineCylinders().equals(vehicleList.get(i).getEngineCylinders()))
                return false;
            if(!modelList.get(i).getEngineSize().equals(vehicleList.get(i).getEngineSize()))
                return false;
            if(!modelList.get(i).getEngineHP().equals(vehicleList.get(i).getEngineHP()))
                return false;
        }
        return true;
    }
}
