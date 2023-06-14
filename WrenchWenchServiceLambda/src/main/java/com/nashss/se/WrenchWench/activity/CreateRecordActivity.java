package com.nashss.se.WrenchWench.activity;

import com.nashss.se.WrenchWench.activity.requests.CreateRecordRequest;
import com.nashss.se.WrenchWench.activity.results.CreateRecordResult;
import com.nashss.se.WrenchWench.converters.ModelConverter;
import com.nashss.se.WrenchWench.dynamodb.RecordDao;
import com.nashss.se.WrenchWench.dynamodb.models.Records;
import com.nashss.se.WrenchWench.models.RecordModel;
import com.nashss.se.WrenchWench.utils.RecordIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateRecordActivity {

    private final Logger log = LogManager.getLogger();
    private final RecordDao recordDao;

    @Inject
    public CreateRecordActivity(RecordDao recordDao){
        this.recordDao = recordDao;
    }

    public CreateRecordResult handleRequest(final CreateRecordRequest createRecordRequest){
        log.info("Received CreateRecordRequest {}", createRecordRequest);

        Records newRecord = new Records();
        String vin = createRecordRequest.getVin();
        String recordId = RecordIdGenerator.generateRecordId(vin);
        String timestamp = recordId.substring(8);

        newRecord.setVin(vin);
        newRecord.setRecordId(recordId);
        newRecord.setTimestamp(timestamp);
        newRecord.setDescription(createRecordRequest.getDescription());
        newRecord.setPriorityLevel(createRecordRequest.getPriorityLevel());
        newRecord.setStatus("Incomplete");

        recordDao.saveRecord(newRecord);

        RecordModel recordModel = new ModelConverter().toRecordModel(newRecord);
        return CreateRecordResult.builder()
                .withRecordModel(recordModel)
                .build();
    }

}
