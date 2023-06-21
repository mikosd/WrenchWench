package com.nashss.se.WrenchWenchService.activity;

import com.nashss.se.WrenchWench.activity.CreateVehicleActivity;
import com.nashss.se.WrenchWench.activity.requests.CreateVehicleRequest;
import com.nashss.se.WrenchWench.activity.results.CreateVehicleResult;
import com.nashss.se.WrenchWench.dynamodb.VehicleDao;
import com.nashss.se.WrenchWench.dynamodb.models.Vehicle;
import com.nashss.se.WrenchWench.exceptions.InvalidVinException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateVehicleActivityTest {
    @Mock
    private VehicleDao vehicleDao;

    private CreateVehicleActivity createVehicleActivity;

    @BeforeEach
    void setup(){
        openMocks(this);
        createVehicleActivity = new CreateVehicleActivity(vehicleDao);
    }

    @Test
    public void handleRequest_withVin_createsAndSavesVehicle() {
        //GIVEN
        String testVin = "JM1NA3518P1419935";
        String expectedMake = "MAZDA";
        String expectedModel = "MX-5";
        String expectedYear = "1993";
        String expectedBodyClass = "Convertible/Cabriolet";
        String expectedVehicleType = "PASSENGER CAR";
        String expectedNumDoors = "2";
        String expectedManufacturer = "MAZDA MOTOR CORPORATION";
        String expectedCountry = "JAPAN";
        String expectedState = "YAMAGUCHI";
        String expectedCity = "HOFU";
        String expectedCylinders = "4";
        String expectedEngineSize = null;
        String expectedHorsepower = "116";
        String expectedFuelType = "Gasoline";

        CreateVehicleRequest request = CreateVehicleRequest.builder()
                .withVin(testVin).build();


        //WHEN
        CreateVehicleResult result = createVehicleActivity.handleRequest(request);

        //THEN
        verify(vehicleDao).saveVehicle(any(Vehicle.class));

        assertNotNull(result.getVehicle().getVin());
        assertEquals(testVin, result.getVehicle().getVin());

        assertEquals(expectedMake, result.getVehicle().getMake());
        assertEquals(expectedModel, result.getVehicle().getModel());
        assertEquals(expectedYear, result.getVehicle().getYear());
        assertEquals(expectedBodyClass ,result.getVehicle().getBodyClass());
        assertEquals(expectedVehicleType, result.getVehicle().getVehicleType());
        assertEquals(expectedNumDoors, result.getVehicle().getNumOfDoors());
        assertEquals(expectedManufacturer, result.getVehicle().getManufacturerName());
        assertEquals(expectedCountry, result.getVehicle().getPlantCountry());
        assertEquals(expectedState, result.getVehicle().getPlantState());
        assertEquals(expectedCity, result.getVehicle().getPlantCity());
        assertEquals(expectedCylinders, result.getVehicle().getEngineCylinders());
        assertNull(result.getVehicle().getEngineSize());
        assertEquals(expectedHorsepower, result.getVehicle().getEngineHP());
        assertEquals(expectedFuelType, result.getVehicle().getFuelType());
    }

    @Test
    public void handleRequest_invalidVin_throwsException() {
        //GIVEN
        CreateVehicleRequest request = CreateVehicleRequest.builder()
                .withVin("JM1NA3517P1419935")   //same as test VIN above, checksum was changed by one
                .build();

        //WHEN + THEN
        assertThrows(InvalidVinException.class, () -> createVehicleActivity.handleRequest(request));

    }
}