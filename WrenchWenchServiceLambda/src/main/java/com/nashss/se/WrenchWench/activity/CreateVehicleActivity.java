package com.nashss.se.WrenchWench.activity;

import com.nashss.se.WrenchWench.activity.requests.CreateVehicleRequest;
import com.nashss.se.WrenchWench.activity.results.CreateVehicleResult;
import com.nashss.se.WrenchWench.converters.ModelConverter;
import com.nashss.se.WrenchWench.dynamodb.VehicleDao;
import com.nashss.se.WrenchWench.dynamodb.models.Vehicle;
import com.nashss.se.WrenchWench.models.VehicleModel;
import com.nashss.se.WrenchWench.utils.VinUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateVehicleActivity {

    private final Logger log = LogManager.getLogger();
    private final VehicleDao vehicleDao;


    @Inject
    public CreateVehicleActivity(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public CreateVehicleResult handleRequest(final CreateVehicleRequest createVehicleRequest) {
        log.info("Received CreateVehicleRequest {}", createVehicleRequest);

        if (VinUtils.validateVin(createVehicleRequest.getVin())) {
            System.out.println("Reached validator");

            Vehicle newVehicle = VinUtils.buildVehicleFromVin(createVehicleRequest.getVin());

//        newVehicle.setVin(createVehicleRequest.getVin());
//        newVehicle.setMake(createVehicleRequest.getMake());
//        newVehicle.setModel(createVehicleRequest.getModel());
//        newVehicle.setYear(createVehicleRequest.getYear());
//        newVehicle.setBodyClass(createVehicleRequest.getBodyClass());
//        newVehicle.setVehicleType(createVehicleRequest.getVehicleType());
//        newVehicle.setNumOfDoors(createVehicleRequest.getNumOfDoors());
//        newVehicle.setManufacturerName(createVehicleRequest.getManufacturerName());
//        newVehicle.setPlantCountry(createVehicleRequest.getPlantCountry());
//        newVehicle.setPlantState(createVehicleRequest.getPlantState());
//        newVehicle.setPlantCity(createVehicleRequest.getPlantCity());
//        newVehicle.setEngineCylinders(createVehicleRequest.getEngineCylinders());
//        newVehicle.setEngineSize(createVehicleRequest.getEngineSize());
//        newVehicle.setEngineHP(createVehicleRequest.getEngineHP());
//        newVehicle.setEngineHP(createVehicleRequest.getEngineHP());

            System.out.println(newVehicle);

            System.out.println(newVehicle.getVin());
            VehicleModel vehicleModel = new ModelConverter().toVehicleModel(vehicleDao.saveVehicle(newVehicle));
            return CreateVehicleResult.builder()
                    .withVehicle(vehicleModel)
                    .build();

        }
        return null;
    }
}

