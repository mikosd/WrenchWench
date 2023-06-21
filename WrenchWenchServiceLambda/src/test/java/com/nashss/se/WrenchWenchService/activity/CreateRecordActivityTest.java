package com.nashss.se.WrenchWenchService.activity;

import com.nashss.se.WrenchWench.activity.CreateRecordActivity;
import com.nashss.se.WrenchWench.activity.requests.CreateRecordRequest;
import com.nashss.se.WrenchWench.activity.results.CreateRecordResult;
import com.nashss.se.WrenchWench.dynamodb.RecordDao;
import com.nashss.se.WrenchWench.dynamodb.models.Records;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateRecordActivityTest {

    @Mock
    private RecordDao recordDao;

    private CreateRecordActivity createRecordActivity;

    String testVin = "JM1NA3518P1419935";
    String expectedDescription = "expectedDescription";
    String expectedPriority = "expectedPriority";

    @BeforeEach
    void setup() {
        openMocks(this);
        createRecordActivity = new CreateRecordActivity(recordDao);
    }

    @Test
    public void handleRequest_createRecordWithRequest_createsAndSavesRecord() {
        //GIVEN
        CreateRecordRequest request = CreateRecordRequest.builder()
                .withVin(testVin)
                .withDescription(expectedDescription)
                .withPriorityLevel(expectedPriority)
                .build();

        //WHEN
        CreateRecordResult result = createRecordActivity.handleRequest(request);

        //THEN

        verify(recordDao).saveRecord(any(Records.class));

        assertNotNull(result.getRecordModel().getRecordId());
        assertEquals(testVin, result.getRecordModel().getVin());
        assertEquals(expectedDescription, result.getRecordModel().getDescription());
        assertEquals(expectedPriority, result.getRecordModel().getPriorityLevel());
    }
}
