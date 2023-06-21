package java.com.nashss.se.WrenchWenchService.activity;

import com.nashss.se.WrenchWench.activity.GetAllVehiclesActivity;
import com.nashss.se.WrenchWench.activity.requests.GetAllVehiclesRequest;
import com.nashss.se.WrenchWench.dynamodb.VehicleDao;
import com.nashss.se.WrenchWench.dynamodb.models.Vehicle;
import com.nashss.se.WrenchWench.models.VehicleModel;
import com.nashss.se.WrenchWenchService.test.helper.VehicleTestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


public class GetAllVehiclesTest {

    @Mock
    private VehicleDao vehicleDao;

    private GetAllVehiclesActivity getAllVehiclesActivity;

    @BeforeEach
    void setup(){
        openMocks(this);
        getAllVehiclesActivity = new GetAllVehiclesActivity(vehicleDao);
    }

    @Test
    void handleRequest_vehiclesExistInDB_returnsVehicles(){
        //GIVEN
        Vehicle vehicle1 = VehicleTestHelper.generateVehicle();
        Vehicle vehicle2 = VehicleTestHelper.generateVehicle();

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);
        GetAllVehiclesRequest.builder().build();

        //WHEN
        when(vehicleDao.getAllVehicles()).thenReturn(vehicleList);
        List<VehicleModel> returnedVehicles = getAllVehiclesActivity.handleRequest().getVehicleList();

        //THEN
        Assertions.assertTrue(VehicleTestHelper.AssertVehicleModelEquals(vehicleList, returnedVehicles));
    }
}
