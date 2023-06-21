package com.nashss.se.WrenchWenchService.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.WrenchWench.dynamodb.RecordDao;
import com.nashss.se.WrenchWench.dynamodb.models.Records;
import com.nashss.se.WrenchWenchService.test.helper.RecordsTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class RecordDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private RecordDao recordDao;

    @BeforeEach
    public void setup() {
        openMocks(this);
        recordDao = new RecordDao(dynamoDBMapper);
    }

    @Test
    void getRecordList_withVin_callsMapper() {
        //GIVEN
        String vin = "JM1NB353X40403333";

        List<Records> recordsList = new ArrayList<>();
        recordsList.add(RecordsTestHelper.generateRecord());

        recordDao = mock(RecordDao.class);

        when(recordDao.getAllRecordsForVin(vin)).thenReturn(recordsList);

        //WHEN
        List<Records> result = recordDao.getAllRecordsForVin(vin);

        //THEN
        verify(recordDao).getAllRecordsForVin(vin);
        assertEquals(recordsList, result);
    }
}
