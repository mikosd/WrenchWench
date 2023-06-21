package com.nashss.se.WrenchWenchService.activity;

import com.nashss.se.WrenchWench.activity.DeleteRecordActivity;
import com.nashss.se.WrenchWench.activity.requests.DeleteRecordRequest;
import com.nashss.se.WrenchWench.activity.results.DeleteRecordResult;
import com.nashss.se.WrenchWench.dynamodb.RecordDao;
import com.nashss.se.WrenchWench.dynamodb.models.Records;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class DeleteRecordActivityTest {

    @Mock
    private RecordDao recordDao;

    private DeleteRecordActivity deleteRecordActivity;

    @BeforeEach
    public void setup(){
        openMocks(this);
        deleteRecordActivity = new DeleteRecordActivity(recordDao);
    }

    @Test
    public void handleRequest_withVinAndRecordId_deleteRecord() {
        //GIVEN
        DeleteRecordRequest request = DeleteRecordRequest.builder()
                .withVin("JM1NA3518P1419935")
                .withRecordId("P1419935202207041234")
                .build();

        Records record = new Records();
        record.setVin(request.getVin());
        record.setRecordId(request.getRecordId());

        ArgumentCaptor<Records> captor = ArgumentCaptor.forClass(Records.class);

        //WHEN
        DeleteRecordResult result = deleteRecordActivity.handleRequest(request);

        //THEN
        verify(recordDao).deleteRecord(captor.capture());
        Records capturedRecord = captor.getValue();

        assertEquals(request.getVin(), capturedRecord.getVin());
        assertEquals(request.getRecordId(), capturedRecord.getRecordId());
    }
}
