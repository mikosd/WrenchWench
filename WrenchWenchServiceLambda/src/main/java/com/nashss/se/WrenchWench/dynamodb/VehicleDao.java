package com.nashss.se.WrenchWench.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.nashss.se.WrenchWench.metrics.MetricsPublisher;
import com.nashss.se.WrenchWench.dynamodb.models.Vehicle;
import com.nashss.se.WrenchWench.metrics.MetricsConstants;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class VehicleDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;


    /**
     * Instantiates a VehicleDao object.
     *
     * @param dynamoDBMapper   the {@link DynamoDBMapper} used to interact with the vehicle table

     * @param metricsPublisher the {@link MetricsPublisher} used to record metrics.
     */
    @Inject
    public VehicleDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Vehicle getVehicle(String vin){
        Vehicle vehicle = this.dynamoDBMapper.load(Vehicle.class, vin);

        if(vehicle == null){
            metricsPublisher.addCount(MetricsConstants.GETVEHICLE_VEHICLENOTFOUND_COUNT, 1);
            //throw new VehicleNotFoundException("Could not find a vehicle with VIN " + vin);
        }
        metricsPublisher.addCount(MetricsConstants.GETVEHICLE_VEHICLENOTFOUND_COUNT, 0);
        return vehicle;
    }


    public Vehicle saveVehicle(Vehicle vehicle){
        this.dynamoDBMapper.save(vehicle);
        return vehicle;
    }

    public List<Vehicle> getAllVehicles(){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        return dynamoDBMapper.scan(Vehicle.class, scanExpression);
    }
}