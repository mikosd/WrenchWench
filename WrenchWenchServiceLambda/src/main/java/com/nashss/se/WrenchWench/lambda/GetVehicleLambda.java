package com.nashss.se.WrenchWench.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.WrenchWench.activity.requests.GetVehicleRequest;
import com.nashss.se.WrenchWench.activity.results.GetVehicleResult;
import com.nashss.se.WrenchWench.exceptions.InvalidVinException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetVehicleLambda extends LambdaActivityRunner<GetVehicleRequest, GetVehicleResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetVehicleRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetVehicleRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromPath(path -> GetVehicleRequest.builder().withVin(path.get("vin")).build()),
                        (request, serviceComponent) -> {
                            try {
                                return serviceComponent.provideGetVehicleActivity().handleRequest(request);
                            } catch (InvalidVinException e) {
                                throw new RuntimeException(e);
                            }
                        }
        );
    }
}
