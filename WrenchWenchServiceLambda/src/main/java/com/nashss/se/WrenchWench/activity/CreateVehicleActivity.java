package com.nashss.se.WrenchWench.activity;

import com.nashss.se.WrenchWench.activity.requests.CreateVehicleRequest;
import com.nashss.se.WrenchWench.activity.results.CreateVehicleResult;
import com.nashss.se.WrenchWench.converters.ModelConverter;
import com.nashss.se.WrenchWench.dynamodb.VehicleDao;
import com.nashss.se.WrenchWench.dynamodb.models.Vehicle;
import com.nashss.se.WrenchWench.exceptions.InvalidVinException;
import com.nashss.se.WrenchWench.models.VehicleModel;
import com.nashss.se.WrenchWench.utils.VinUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateVehicleActivity {

    private final Logger log = LogManager.getLogger();
    private final VehicleDao vehicleDao;

    /**
     * @param vehicleDao DAO object to access the Vehicle class
     */
    @Inject
    public CreateVehicleActivity(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    /**
     *
     * @param createVehicleRequest
     * @return
     */
    public CreateVehicleResult handleRequest(final CreateVehicleRequest createVehicleRequest) {
        log.warn("Received CreateVehicleRequest {}", createVehicleRequest);

        if (!VinUtils.validateVin(createVehicleRequest.getVin())) {
            throw new InvalidVinException("Invalid VIN - Check the number you have dialed and try again");
        }

        Vehicle newVehicle = new Vehicle();
        newVehicle = VinUtils.buildVehicleFromVin(createVehicleRequest.getVin());

        vehicleDao.saveVehicle(newVehicle);

        //Vehicle newVehicle = VinUtils.buildVehicleFromVin(createVehicleRequest.getVin());
        VehicleModel vehicleModel = new ModelConverter().toVehicleModel(newVehicle);
        return CreateVehicleResult.builder()
                .withVehicle(vehicleModel)
                .build();
    }
}

