package com.nashss.se.WrenchWench.activity;

import com.nashss.se.WrenchWench.activity.results.GetAllVehiclesResult;
import com.nashss.se.WrenchWench.converters.ModelConverter;
import com.nashss.se.WrenchWench.dynamodb.VehicleDao;
import com.nashss.se.WrenchWench.models.VehicleModel;

import javax.inject.Inject;
import java.util.List;

public class GetAllVehiclesActivity {
    public VehicleDao vehicleDao;

    @Inject
    public GetAllVehiclesActivity(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public GetAllVehiclesResult handleRequest() {
        ModelConverter modelConverter = new ModelConverter();

        List<VehicleModel> vehicleModels = modelConverter.toVehicleModelList(vehicleDao.getAllVehicles());

        return GetAllVehiclesResult.builder()
            .withVehicleList(vehicleModels)
            .build();
    }
}