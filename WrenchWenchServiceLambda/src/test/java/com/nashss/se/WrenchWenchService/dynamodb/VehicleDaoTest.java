package com.nashss.se.WrenchWenchService.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.WrenchWench.dynamodb.VehicleDao;
import com.nashss.se.WrenchWench.dynamodb.models.Vehicle;
import com.nashss.se.WrenchWench.metrics.MetricsPublisher;
import com.nashss.se.WrenchWenchService.test.helper.VehicleTestHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class VehicleDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private VehicleDao vehicleDao;

    private MetricsPublisher metricsPublisher;

    public VehicleDaoTest() { }

    @BeforeEach
    public void setup() {
        openMocks(this);
        vehicleDao = new VehicleDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void getVehicle_withVIN_callsMapperWithKey() {
        //GIVEN
        String vin = "JM1NB353X40403333";
        when(dynamoDBMapper.load(Vehicle.class, vin)).thenReturn(new Vehicle());

        //WHEN
        Vehicle vehicle = vehicleDao.getVehicle(vin);

        //THEN
        assertNotNull(vehicle);
        verify(dynamoDBMapper).load(Vehicle.class, vin);
    }

    @Test
    public void getVehicle_withVIN_callsMapperWithVehicle() {
        //GIVEN
        Vehicle vehicle = new Vehicle();

        //WHEN
        Vehicle result = vehicleDao.saveVehicle(vehicle);

        //THEN
        verify(dynamoDBMapper).save(vehicle);
        assertEquals(vehicle, result);
    }

    @Test
    public void getAllVehicles_callsMapper() {
        //GIVEN
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(VehicleTestHelper.generateVehicle());

        vehicleDao = mock(VehicleDao.class);

        //WHEN
        when(vehicleDao.getAllVehicles()).thenReturn(vehicleList);
        List<Vehicle> result = vehicleDao.getAllVehicles();

        //THEN
        verify(vehicleDao).getAllVehicles();
        assertEquals(vehicleList, result);
    }
}
