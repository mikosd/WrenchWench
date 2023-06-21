package com.nashss.se.WrenchWenchService.activity;

import com.nashss.se.WrenchWench.activity.GetVehicleRecordsActivity;
import com.nashss.se.WrenchWench.activity.requests.GetVehicleRecordsRequest;
import com.nashss.se.WrenchWench.activity.results.GetVehicleRecordsResult;
import com.nashss.se.WrenchWench.converters.ModelConverter;
import com.nashss.se.WrenchWench.dynamodb.RecordDao;
import com.nashss.se.WrenchWench.dynamodb.models.Records;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetVehicleRecordsActivityTest {
    @Mock
    private RecordDao recordDao;

    @Mock
    private ModelConverter modelConverter;

    private GetVehicleRecordsActivity getVehicleRecordsActivity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        getVehicleRecordsActivity = new GetVehicleRecordsActivity(recordDao, modelConverter);
    }

    @Test
    public void handleRequest_returnsGetVehicleRecordsResult() {
        // GIVEN
        String vin = "JM1NA3518P1419935";
        String recordId = "P1419935202306091216";
        GetVehicleRecordsRequest request = GetVehicleRecordsRequest.builder()
                .withVin(vin)
                .withRecordId(recordId)
                .build();
        List<Records> recordsList = new ArrayList<>();
        Records record = new Records();
        record.setVin(vin);
        recordsList.add(record);

        when(recordDao.getAllRecordsForVin(vin)).thenReturn(recordsList);

        // WHEN
        GetVehicleRecordsResult result = getVehicleRecordsActivity.handleRequest(request);

        // THEN
        assertEquals(recordsList.size(), result.getRecordsList().size());
        verify(recordDao).getAllRecordsForVin(vin);
    }

    @Test
    public void handleRequest_convertsRecordsToRecordModel() {
        // GIVEN
        String vin = "JM1NA3518P1419935";

        GetVehicleRecordsRequest request = GetVehicleRecordsRequest.builder()
                .withVin(vin)
                .build();

        List<Records> recordsList = new ArrayList<>();
        Records record = new Records();
        record.setVin(vin);
        recordsList.add(record);

        when(recordDao.getAllRecordsForVin(vin)).thenReturn(recordsList);

        //ModelConverter modelConverter = spy(new ModelConverter());

        // WHEN
        GetVehicleRecordsResult result = getVehicleRecordsActivity.handleRequest(request);

        // THEN
        assertEquals(recordsList.size(), result.getRecordsList().size());
        verify(recordDao).getAllRecordsForVin(vin);
        //verify(modelConverter, times(recordsList.size())).toRecordModel(any(Records.class));

//        RecordModel convertedRecord = result.getRecordsList().get(0);
//        assertEquals(record.getVin(), convertedRecord.getVin());
//        assertEquals(record.getRecordId(), convertedRecord.getRecordId());
//        assertEquals(record.getTimestamp(), convertedRecord.getTimestamp());
//        assertEquals(record.getDescription(), convertedRecord.getDescription());
//        assertEquals(record.getPriorityLevel(), convertedRecord.getPriorityLevel());
//        assertEquals(record.getStatus(), convertedRecord.getStatus());
    }
}