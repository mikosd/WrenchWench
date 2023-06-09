package com.nashss.se.WrenchWench.activity;

import com.nashss.se.WrenchWench.activity.requests.UpdateRecordRequest;
import com.nashss.se.WrenchWench.activity.results.UpdateRecordResult;
import com.nashss.se.WrenchWench.converters.ModelConverter;
import com.nashss.se.WrenchWench.dynamodb.RecordDao;
import com.nashss.se.WrenchWench.dynamodb.models.Records;
import com.nashss.se.WrenchWench.utils.RecordIdGenerator;
import com.nashss.se.WrenchWench.utils.VinUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateRecordActivity {
    private Logger log = LogManager.getLogger();
    private final RecordDao recordDao;

    @Inject
    public UpdateRecordActivity(RecordDao recordDao){
        this.recordDao = recordDao;
    }

    public UpdateRecordResult handleRequest(UpdateRecordRequest updateRecordRequest){
        log.info("Received UpdateRecordRequest {}", updateRecordRequest);

        Records records = recordDao.getRecord(updateRecordRequest.getVin(), updateRecordRequest.getRecordId());

        records.setDescription(updateRecordRequest.getDescription());
        records.setStatus(updateRecordRequest.getStatus());
        records.setPriorityLevel(updateRecordRequest.getPriorityLevel());

        recordDao.saveRecord(records);

        return UpdateRecordResult.builder().withRecordModel(new ModelConverter().toRecordModel(records)).build();
    }
}
