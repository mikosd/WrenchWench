package com.nashss.se.WrenchWench.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.WrenchWench.activity.requests.GetVehicleRecordsRequest;
import com.nashss.se.WrenchWench.activity.results.GetVehicleRecordsResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetVehicleRecordsLambda
        extends LambdaActivityRunner<GetVehicleRecordsRequest, GetVehicleRecordsResult>
        implements RequestHandler<LambdaRequest<GetVehicleRecordsRequest>, LambdaResponse> {

            private final Logger log = LogManager.getLogger();

            @Override
            public LambdaResponse handleRequest(LambdaRequest<GetVehicleRecordsRequest> input, Context context) {

                log.info("handleRequest");
                return super.runActivity(
                    () -> input.fromPath(path ->
                GetVehicleRecordsRequest.builder()
                .withVin(path.get("vin"))
                .build()),
                        (request, serviceComponent) ->
                            serviceComponent.provideGetVehicleRecordsActivity().handleRequest(request)
                );
            }
}
