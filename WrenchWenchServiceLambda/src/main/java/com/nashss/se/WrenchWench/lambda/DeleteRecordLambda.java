package com.nashss.se.WrenchWench.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.WrenchWench.activity.requests.DeleteRecordRequest;
import com.nashss.se.WrenchWench.activity.results.DeleteRecordResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteRecordLambda
        extends LambdaActivityRunner<DeleteRecordRequest, DeleteRecordResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteRecordRequest>, LambdaResponse> {
    Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteRecordRequest> input, Context context){
        log.warn("Reached handleRequest");
        return super.runActivity(
                () ->  input.fromPath(path ->
            DeleteRecordRequest.builder()
            .withVin(path.get("vin"))
            .withRecordId(path.get("recordId"))
            .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteRecordActivity().handleRequest(request)
        );

    }
}
