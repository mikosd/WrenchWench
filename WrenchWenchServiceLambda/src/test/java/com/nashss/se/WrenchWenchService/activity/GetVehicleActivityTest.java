package com.nashss.se.WrenchWenchService.activity;

import com.nashss.se.WrenchWench.activity.GetVehicleActivity;
import com.nashss.se.WrenchWench.activity.requests.GetVehicleRequest;
import com.nashss.se.WrenchWench.activity.results.GetVehicleResult;
import com.nashss.se.WrenchWench.dynamodb.VehicleDao;
import com.nashss.se.WrenchWench.dynamodb.models.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetVehicleActivityTest {
    @Mock
    private VehicleDao vehicleDao;

    private GetVehicleActivity getVehicleActivity;

    @BeforeEach
    public void setup(){
        openMocks(this);
        getVehicleActivity = new GetVehicleActivity(vehicleDao);
    }

    @Test
    public void handleRequest_savedVehicleFound_returnsVehicleModelInResult() {
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

        Vehicle vehicle = new Vehicle();
        vehicle.setVin(testVin);
        vehicle.setMake(expectedMake);
        vehicle.setModel(expectedModel);
        vehicle.setYear(expectedYear);
        vehicle.setBodyClass(expectedBodyClass);
        vehicle.setVehicleType(expectedVehicleType);
        vehicle.setNumOfDoors(expectedNumDoors);
        vehicle.setManufacturerName(expectedManufacturer);
        vehicle.setPlantCountry(expectedCountry);
        vehicle.setPlantState(expectedState);
        vehicle.setPlantCity(expectedCity);
        vehicle.setEngineCylinders(expectedCylinders);
        vehicle.setEngineHP(expectedHorsepower);
        vehicle.setFuelType(expectedFuelType);

        when(vehicleDao.getVehicle(testVin)).thenReturn(vehicle);

        GetVehicleRequest request = GetVehicleRequest.builder()
                                        .withVin(testVin).build();
        //WHEN
        GetVehicleResult result = getVehicleActivity.handleRequest(request);


        //THEN
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
        assertEquals(expectedHorsepower, result.getVehicle().getEngineHP());
        assertEquals(expectedFuelType, result.getVehicle().getFuelType());
    }
}
