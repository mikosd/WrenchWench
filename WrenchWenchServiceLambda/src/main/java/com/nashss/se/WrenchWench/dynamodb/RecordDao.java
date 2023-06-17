package com.nashss.se.WrenchWench.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.WrenchWench.dynamodb.models.Records;
import com.nashss.se.WrenchWench.exceptions.InvalidVinException;
import com.nashss.se.WrenchWench.models.RecordModel;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public RecordDao(DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Records getRecord(String vin, String recordId){
        if(vin == null){
            throw new InvalidVinException("VIN cannot be null");
        }

        Records record = this.dynamoDBMapper.load(Records.class, vin, recordId);

        if(record == null){
            throw new RuntimeException();
        }

        return record;
    }

    public Records saveRecord(Records record){
        this.dynamoDBMapper.save(record);
        return record;
    }

    public List<Records> getAllRecordsForVin(String vin){
        if(vin == null){
            throw new InvalidVinException("VIN cannot be null");
        }

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":vin", new AttributeValue().withS(vin));

        DynamoDBQueryExpression<Records> queryExpression = new DynamoDBQueryExpression<Records>()
                .withKeyConditionExpression("vin = :vin")
                .withExpressionAttributeValues(valueMap);

        PaginatedQueryList<Records> recordsList = dynamoDBMapper.query(Records.class, queryExpression);

        if(recordsList == null){
            throw new RuntimeException();
        }

        return recordsList;
    }

    public void deleteRecord(Records record){
        this.dynamoDBMapper.delete(record);
    }
}
