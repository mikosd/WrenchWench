package com.nashss.se.WrenchWenchService.activity;

import com.nashss.se.WrenchWench.activity.UpdateRecordActivity;
import com.nashss.se.WrenchWench.activity.requests.UpdateRecordRequest;
import com.nashss.se.WrenchWench.activity.results.UpdateRecordResult;
import com.nashss.se.WrenchWench.dynamodb.RecordDao;
import com.nashss.se.WrenchWench.dynamodb.models.Records;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateRecordActivityTest {

    @Mock
    private RecordDao recordDao;

    private UpdateRecordActivity updateRecordActivity;

    @BeforeEach
    public void setup() {
        openMocks(this);
        updateRecordActivity = new UpdateRecordActivity(recordDao);
    }

    @Test
    public void handleRequest_validRequest_updatesRecord() {
        //GIVEN
        String vin = "JM1NA3518P1419935";
        String recordId = "P1419935201805220935";
        String description = "description";
        String status = "status";
        String priorityLevel = "priorityLevel";

        UpdateRecordRequest request = UpdateRecordRequest.builder()
                .withVin(vin)
                .withRecordId(recordId)
                .withDescription(description)
                .withStatus(status)
                .withPriorityLevel(priorityLevel)
                .build();


        Records initialRecord = new Records();
        initialRecord.setVin(vin);
        initialRecord.setRecordId(recordId);
        initialRecord.setDescription("old description");
        initialRecord.setStatus("old status");
        initialRecord.setPriorityLevel("old priorityLevel");

        when(recordDao.getRecord(vin, recordId)).thenReturn(initialRecord);
        when(recordDao.saveRecord(initialRecord)).thenReturn(initialRecord);

        //WHEN
        UpdateRecordResult result = updateRecordActivity.handleRequest(request);

        //THEN
        assertEquals(vin, result.getRecordModel().getVin());
        assertEquals(recordId, result.getRecordModel().getRecordId());
        assertEquals(description , result.getRecordModel().getDescription());
        assertEquals(status , result.getRecordModel().getStatus());
        assertEquals(priorityLevel , result.getRecordModel().getPriorityLevel());

    }

}
