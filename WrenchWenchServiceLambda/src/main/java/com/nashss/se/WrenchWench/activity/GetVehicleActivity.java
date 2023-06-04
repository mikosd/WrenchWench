package com.nashss.se.WrenchWench.activity;

import com.nashss.se.WrenchWench.activity.requests.GetVehicleRequest;
import com.nashss.se.WrenchWench.activity.results.GetVehicleResult;
import com.nashss.se.WrenchWench.converters.ModelConverter;
import com.nashss.se.WrenchWench.dynamodb.VehicleDao;
import com.nashss.se.WrenchWench.dynamodb.models.Vehicle;
import com.nashss.se.WrenchWench.exceptions.InvalidVinException;
import com.nashss.se.WrenchWench.models.VehicleModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;


/*
 * Implementation of the GetVehicleActivity for the WrenchWenchService's GetVehicle API.
 *
 * This API allows the user to get one of their vehicles
 */
public class GetVehicleActivity {
    Logger log = LogManager.getLogger();
    private final VehicleDao vehicleDao;

    @Inject
    public GetVehicleActivity(VehicleDao vehicleDao){
        this.vehicleDao = vehicleDao;
    }


    public GetVehicleResult handleRequest(final GetVehicleRequest getVehicleRequest) throws InvalidVinException {
        log.info("Received GetVehicleRequest {}", getVehicleRequest);
        String requestId = getVehicleRequest.getVin();
        Vehicle vehicle = vehicleDao.getVehicle(requestId);
        VehicleModel vehicleModel = new ModelConverter().toVehicleModel(vehicle);

        return GetVehicleResult.builder()
                .withVehicle(vehicleModel)
                .build();
    }
}