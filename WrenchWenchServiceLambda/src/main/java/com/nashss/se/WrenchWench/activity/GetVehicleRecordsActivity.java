package com.nashss.se.WrenchWench.activity;


import com.nashss.se.WrenchWench.activity.requests.GetVehicleRecordsRequest;
import com.nashss.se.WrenchWench.activity.results.GetVehicleRecordsResult;
import com.nashss.se.WrenchWench.converters.ModelConverter;
import com.nashss.se.WrenchWench.dynamodb.VehicleDao;
import com.nashss.se.WrenchWench.dynamodb.models.Records;
import com.nashss.se.WrenchWench.dynamodb.models.Vehicle;
import com.nashss.se.WrenchWench.models.RecordModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class GetVehicleRecordsActivity {

    private final Logger log = LogManager.getLogger();
    private final VehicleDao vehicleDao;

    @Inject
    public GetVehicleRecordsActivity(VehicleDao vehicleDao){
        this.vehicleDao = vehicleDao;
    }

    public GetVehicleRecordsResult handleRequest(final GetVehicleRecordsRequest getVehicleRecordsRequest){
        log.info("Received GetVehicleRecordsRequest {}", getVehicleRecordsRequest);

        Vehicle vehicle = vehicleDao.getVehicle(getVehicleRecordsRequest.getVin());
        List<RecordModel> recordModels = new ModelConverter().toRecordModelList(vehicle.getRecordList());

        return GetVehicleRecordsResult.builder()
                .withRecordsList(recordModels)
                .build();
    }

    //private String sortOrderFunction
}
