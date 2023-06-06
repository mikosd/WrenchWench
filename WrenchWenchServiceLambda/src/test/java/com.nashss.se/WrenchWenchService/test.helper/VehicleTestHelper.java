package com.nashss.se.WrenchWenchService.test.helper;

import com.nashss.se.WrenchWench.dynamodb.models.Vehicle;
import com.nashss.se.WrenchWench.models.VehicleModel;

import java.util.List;

public class VehicleTestHelper {

    public static Vehicle generateVehicle(){
        Vehicle vehicle = new Vehicle();
        vehicle.setVin("JM1NB353X40403333");
        vehicle.setMake("Mazda");
        vehicle.setModel("MX-5 Miata");
        vehicle.setYear("2004");

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
        }
        return true;
    }
}
