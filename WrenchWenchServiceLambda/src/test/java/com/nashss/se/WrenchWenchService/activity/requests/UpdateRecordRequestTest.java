package com.nashss.se.WrenchWenchService.activity.requests;

import com.nashss.se.WrenchWench.activity.requests.UpdateRecordRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateRecordRequestTest {

    @Test
    public void buildRequest_returnsCorrectValues() {
        // GIVEN
        String vin = "JM1NA3518P1419935";
        String recordId = "123456789";
        String description = "Updated description";
        String status = "In Progress";
        String priorityLevel = "High";

        // WHEN
        UpdateRecordRequest request = UpdateRecordRequest.builder()
                .withVin(vin)
                .withRecordId(recordId)
                .withDescription(description)
                .withStatus(status)
                .withPriorityLevel(priorityLevel)
                .build();

        // THEN
        assertEquals(vin, request.getVin());
        assertEquals(recordId, request.getRecordId());
        assertEquals(description, request.getDescription());
        assertEquals(status, request.getStatus());
        assertEquals(priorityLevel, request.getPriorityLevel());
    }
}
