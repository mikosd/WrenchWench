package com.nashss.se.WrenchWench.activity;


import com.nashss.se.WrenchWench.activity.requests.GetVehicleRecordsRequest;
import com.nashss.se.WrenchWench.activity.results.GetVehicleRecordsResult;
import com.nashss.se.WrenchWench.converters.ModelConverter;
import com.nashss.se.WrenchWench.dynamodb.RecordDao;
import com.nashss.se.WrenchWench.dynamodb.models.Records;
import com.nashss.se.WrenchWench.models.RecordModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetVehicleRecordsActivity {

    private final Logger log = LogManager.getLogger();
    private final RecordDao recordDao;

    @Inject
    public GetVehicleRecordsActivity(RecordDao recordDao){
        this.recordDao = recordDao;
    }

    public GetVehicleRecordsResult handleRequest(final GetVehicleRecordsRequest getVehicleRecordsRequest){
        log.info("Received GetVehicleRecordsRequest {}", getVehicleRecordsRequest);



        List<Records> recordsForVinList = recordDao.getAllRecordsForVin(getVehicleRecordsRequest.getVin());
        List<RecordModel> recordModelsList = new ArrayList<>();

        for (Records record: recordsForVinList) {
            recordModelsList.add(new ModelConverter().toRecordModel(record));
        }

        return GetVehicleRecordsResult.builder()
                .withRecordsList(recordModelsList)
                .build();
    }
}
