package com.nashss.se.WrenchWench.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.WrenchWench.activity.requests.CreateVehicleRequest;
import com.nashss.se.WrenchWench.activity.results.CreateVehicleResult;

public class CreateVehicleLambda extends LambdaActivityRunner<CreateVehicleRequest, CreateVehicleResult>
            implements RequestHandler<AuthenticatedLambdaRequest<CreateVehicleRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateVehicleRequest> input, Context context){
        return super.runActivity(
                () -> {
                    CreateVehicleRequest unauthenticatedRequest = input.fromBody(CreateVehicleRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateVehicleRequest.builder()
                                    .withVin(unauthenticatedRequest.getVin())
                                    .withMake(unauthenticatedRequest.getMake())
                                    .withModel(unauthenticatedRequest.getModel())
                                    .withYear(unauthenticatedRequest.getYear())
                                    .withBodyClass(unauthenticatedRequest.getBodyClass())
                                    .withVehicleType(unauthenticatedRequest.getVehicleType())
                                    .withNumOfDoors(unauthenticatedRequest.getNumOfDoors())
                                    .withManufacturerName(unauthenticatedRequest.getManufacturerName())
                                    .withPlantCountry(unauthenticatedRequest.getPlantCountry())
                                    .withPlantState(unauthenticatedRequest.getPlantState())
                                    .withPlantCity(unauthenticatedRequest.getPlantCity())
                                    .withEngineCylinders(unauthenticatedRequest.getEngineCylinders())
                                    .withEngineSize(unauthenticatedRequest.getEngineSize())
                                    .withEngineHP(unauthenticatedRequest.getEngineHP())
                                    .withFuelType(unauthenticatedRequest.getFuelType())
                                    .build());
                },
                (request, serviceComponent) ->
                serviceComponent.provideCreateVehicleActivity().handleRequest(request)
//                        serviceComponent.provideCreateVehicleActivity().handleRequest(request)
        );
    }
}
