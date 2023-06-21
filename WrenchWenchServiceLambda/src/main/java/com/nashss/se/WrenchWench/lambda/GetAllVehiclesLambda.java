package com.nashss.se.WrenchWench.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.WrenchWench.activity.requests.GetAllVehiclesRequest;
import com.nashss.se.WrenchWench.activity.results.GetAllVehiclesResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetAllVehiclesLambda extends LambdaActivityRunner<GetAllVehiclesRequest, GetAllVehiclesResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllVehiclesRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllVehiclesRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetAllVehiclesRequest.builder()
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetAllVehiclesActivity().handleRequest()
        );
    }
}