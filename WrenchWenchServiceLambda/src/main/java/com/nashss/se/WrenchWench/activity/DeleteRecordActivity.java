package com.nashss.se.WrenchWench.activity;

import com.nashss.se.WrenchWench.activity.requests.DeleteRecordRequest;
import com.nashss.se.WrenchWench.activity.results.DeleteRecordResult;
import com.nashss.se.WrenchWench.converters.ModelConverter;
import com.nashss.se.WrenchWench.dynamodb.RecordDao;
import com.nashss.se.WrenchWench.dynamodb.models.Records;
import com.nashss.se.WrenchWench.models.RecordModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteRecordActivity {

    private final Logger log = LogManager.getLogger();
    private final RecordDao recordDao;

    @Inject
    public DeleteRecordActivity(RecordDao recordDao){
        this.recordDao = recordDao;
    }

    public DeleteRecordResult handleRequest(DeleteRecordRequest request){
        log.info("Received request to delete record with request {}", request);

        Records record = new Records();
        record.setRecordId(request.getRecordId());

        recordDao.deleteRecord(record);
        RecordModel recordModel = new ModelConverter().toRecordModel(record);

        return DeleteRecordResult.builder().withRecord(recordModel).build();
    }
}
